package com.validator

import kotlin.reflect.KProperty1

sealed class ValidationResult<Constraint>
class Passed<Result> : ValidationResult<Result>()
class Failed<Result>(val errors: Result) : ValidationResult<Result>()

class Validator<Subject, Constraint : Any> private constructor(private val subject: Subject) {

    private val constraints = mutableListOf<Pair<Constraint, () -> Boolean>>()

    companion object {

        /**
         * Initialize an eager [Validator], which runs all checks and returns all failures that
         * occurred, including duplicates.
         */
        fun <Subject : Any, Constraint : Any> checkEagerly(
            subject: Subject,
            block: Validator<Subject, Constraint>.(Subject) -> Unit,
        ): ValidationResult<List<Constraint>> {
            val validator = Validator<Subject, Constraint>(subject)
            validator.block(subject)
            val result = validator.eagerConstraintFailures()
            return allResults(result)
        }

        private fun <Constraint> allResults(failures: List<Constraint>): ValidationResult<List<Constraint>> =
            if (failures.isNotEmpty()) {
                Failed(failures)
            } else {
                Passed()
            }

        /**
         * Initialize a lazy [Validator], which runs checks from the top down and returns the
         * first failure to occur. This method can be useful for constraint checks that have a
         * high time complexity, as the remaining checks are not performed after a failure has
         * occurred.
         */
        fun <Subject : Any, Constraint : Any> checkLazily(
            subject: Subject,
            block: Validator<Subject, Constraint>.(Subject) -> Unit,
        ): ValidationResult<Constraint> {
            val validator = Validator<Subject, Constraint>(subject)
            validator.block(subject)
            val failure = validator.lazyConstraintFailure()
            return result(failure)
        }

        private fun <Constraint> result(failure: Constraint?): ValidationResult<Constraint> =
            if (failure != null) {
                Failed(failure)
            } else {
                Passed()
            }
    }

    /**
     * Performs the constraint checks in the given block on the complete subject which can
     * be destructured.
     */
    fun checkSubject(
        block: Validator<Subject, Constraint>.(Subject) -> Unit,
    ) {
        val validator = Validator<Subject, Constraint>(subject)
        validator.block(subject)
        constraints += validator.constraints
    }

    /**
     * Performs the constraint checks in the given block on a single property of the current
     * subject.
     */
    fun <Property> checkProperty(
        property: KProperty1<Subject, Property>,
        block: Validator<Property, Constraint>.(Property) -> Unit,
    ) {
        property.get(subject).also {
            val validator = Validator<Property, Constraint>(it)
            validator.block(it)
            constraints += validator.constraints
        }
    }

    /**
     * Performs the constraint checks in the given block on a single [Iterable] property of
     * the current subject. This allows performing checks as if a single value is validated.
     */
    fun <Property> checkIterableProperty(
        property: KProperty1<Subject, Iterable<Property>>,
        block: Validator<Property, Constraint>.(Property) -> Unit,
    ) {
        property.get(subject).forEach {
            val validator = Validator<Property, Constraint>(it)
            validator.block(it)
            constraints += validator.constraints
        }
    }

    /**
     * Add a [Constraint] to this [Validator].
     */
    infix fun Constraint.enforcing(predicate: () -> Boolean) {
        constraints.add(Pair(this, predicate))
    }

    private fun eagerConstraintFailures(): List<Constraint> =
        constraints
            .filter { (_, predicate) -> !predicate() }
            .map { (constraint, _) -> constraint }

    private fun lazyConstraintFailure(): Constraint? =
        constraints
            .firstOrNull { (_, predicate) -> !predicate() }
            .let { it?.first }
}
