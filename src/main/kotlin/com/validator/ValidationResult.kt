package com.validator

sealed class ValidationResult<out Error, out Subject> {
    data class Passed<Subject>(val value: Subject) : ValidationResult<Nothing, Subject>()
    data class Failed<Error>(val errors: Error) : ValidationResult<Error, Nothing>()
}

val <Subject> ValidationResult.Failed<List<Subject>>.errorCount: Int
    get() = this.errors.size

val <Subject> ValidationResult.Failed<List<Subject>>.uniqueErrors: Set<Subject>
    get() = this.errors.toSet()

val <Subject> ValidationResult.Failed<List<Subject>>.uniqueErrorCount: Int
    get() = this.errors.toSet().size
