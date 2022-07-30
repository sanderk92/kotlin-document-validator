package com.validator

import kotlin.reflect.KProperty1

private typealias ConstraintPredicate = () -> Boolean

class Validator<Subject, Constraint : Any> private constructor(private val subject: Subject) {

    private val constraints = mutableListOf<Pair<Constraint, ConstraintPredicate>>()

    companion object {

        /**
         * Initializes an eager [Validator], which runs all checks and returns all failures that
         * occurred, including duplicates.
         */
        fun <Subject : Any, Constraint : Any> checkEagerly(
            subject: Subject,
            block: Validator<Subject, Constraint>.(Subject) -> Unit,
        ): ValidationResult<List<Constraint>> {
            val validator = Validator<Subject, Constraint>(subject)
            validator.block(subject)
            val failures = validator.evaluateEagerly()
            return result(failures)
        }

        private fun <Constraint : Any> result(failures: List<Constraint>): ValidationResult<List<Constraint>> =
            if (failures.isNotEmpty()) {
                Failed(failures)
            } else {
                Passed()
            }

        /**
         * Initializes a lazy [Validator], which runs checks from the top down and returns the
         * first failure to occur. This method can be useful for constraint checks that have a
         * high time complexity, as it skips evaluation of remaining predicates when an error
         * occurred.
         */
        fun <Subject : Any, Constraint : Any> checkLazily(
            subject: Subject,
            block: Validator<Subject, Constraint>.(Subject) -> Unit,
        ): ValidationResult<Constraint> {
            val validator = Validator<Subject, Constraint>(subject)
            validator.block(subject)
            val failure = validator.evaluateLazily()
            return result(failure)
        }

        private fun <Constraint : Any> result(failure: Constraint?): ValidationResult<Constraint> =
            if (failure != null) {
                Failed(failure)
            } else {
                Passed()
            }
    }

    /**
     * Performs the constraint checks in the given block on the complete subject, which can
     * optionally be destructured.
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
     * Defines a validation which adds a failure to the result when the given predicate returns false.
     */
    infix fun Constraint.enforcing(predicate: ConstraintPredicate): Pair<Constraint, ConstraintPredicate> =
        constraints.addPair(this, predicate)

    /**
     * Defines a validation which adds a failure to the result when the given block throws an exception.
     */
    infix fun Constraint.trying(block: () -> Unit): Pair<Constraint, ConstraintPredicate> =
        constraints.addPair(this) { runCatching { block() }.isSuccess }

    /**
     * Defines a validation which never adds a failure to the result.
     */
    infix fun Constraint.ignoring(predicate: ConstraintPredicate): Pair<Constraint, ConstraintPredicate> =
        constraints.addPair(this) { true }.let { this to predicate }

    private fun <A, B> MutableList<Pair<A, B>>.addPair(a: A, b: B): Pair<A, B> =
        (a to b).also(this::add)

    /**
     * Peeks a validation definition, immediately executing it and running the block if it passed.
     */
    infix fun Pair<Constraint, ConstraintPredicate>.onPass(block: () -> Unit): Pair<Constraint, ConstraintPredicate> =
        also { if (second()) block() }

    /**
     * Peeks a validation definition, immediately executing it and running the block if it failed.
     */
    infix fun Pair<Constraint, ConstraintPredicate>.onFail(block: () -> Unit): Pair<Constraint, ConstraintPredicate> =
        also { if (!second()) block() }

    private fun evaluateEagerly(): List<Constraint> =
        constraints
            .filter { (_, predicate) -> !predicate() }
            .map { (constraint, _) -> constraint }

    private fun evaluateLazily(): Constraint? =
        constraints
            .firstOrNull { (_, predicate) -> !predicate() }
            .let { it?.first }
}

