package com.validator

sealed interface ValidationResult<Constraint>
class Passed<Result> : ValidationResult<Result>
data class Failed<Result: Any>(val errors: Result) : ValidationResult<Result>

val <Result> Failed<List<Result>>.errorCount: Int
    get() = this.errors.size

val <Result> Failed<List<Result>>.uniqueErrors: Set<Result>
    get() = this.errors.toSet()

val <Result> Failed<List<Result>>.uniqueErrorCount: Int
    get() = this.errors.toSet().size
