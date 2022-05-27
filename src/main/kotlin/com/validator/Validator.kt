package com.validator

import kotlin.reflect.KProperty1

sealed class ValidationResult<Constraint>
class Passed<Constraint> : ValidationResult<Constraint>()
class Failed<Constraint>(val errors: Set<Constraint>) : ValidationResult<Constraint>()

class Validator<Subject, Constraint> private constructor(private val subject: Subject) {

    private val failures = mutableSetOf<Constraint>()

    /**
     * Initialize a [Validator] with the given subject on which all the constraint checks in the
     * given block are performed. Its [ValidationResult] are returned subsequently.
     */
    companion object {
        fun <Subject, Constraint> check(
            subject: Subject,
            block: Validator<Subject, Constraint>.(Subject) -> Unit,
        ): ValidationResult<Constraint> {
            val validator = Validator<Subject, Constraint>(subject)
            validator.block(subject)
            return validator.result()
        }
    }

    /**
     * Performs the constraint checks in the given block on a single property of the current subject,
     * which may be of any type. Calls to this method may be nested as to walk through all
     * underlying nested properties.
     */
    fun <Property> checkProperty(
        property: KProperty1<Subject, Property>,
        block: Validator<Property, Constraint>.(Property) -> Unit,
    ) {
        property.get(subject).also {
            val validator = Validator<Property, Constraint>(it)
            validator.block(it)
            failures += validator.failures
        }
    }

    /**
     * Performs the constraint checks in the given block on a single property of the current subject,
     * which must be of type [Iterable]. This allows performing checks on [Iterable] while writing
     * constraints as if a single value is validated. Calls to this method may be nested to walk
     * through the all underlying nested properties.
     */
    fun <Property> checkIterableProperty(
        property: KProperty1<Subject, Iterable<Property>>,
        block: Validator<Property, Constraint>.(Property) -> Unit,
    ) {
        property.get(subject).forEach {
            val validator = Validator<Property, Constraint>(it)
            validator.block(it)
            failures += validator.failures
        }
    }

    /**
     * Add a constraint to this [Validator], which is memorized as failed when the subject does not
     * meet the given predicate, otherwise it is ignored.
     */
    fun forConstraint(constraint: Constraint, predicate: (Subject) -> Boolean) {
        if (!predicate(subject)) {
            failures += constraint
        }
    }

    /**
     * Retrieve the [ValidationResult] of this [Validator].
     */
    fun result(): ValidationResult<Constraint> =
        if (failures.isNotEmpty()) {
            Failed(failures)
        } else {
            Passed()
        }
}
