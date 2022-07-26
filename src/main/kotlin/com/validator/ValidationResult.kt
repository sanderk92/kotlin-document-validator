package com.validator

sealed class ValidationResult<Constraint>
class Passed<Result> : ValidationResult<Result>()
class Failed<Result>(val errors: Result) : ValidationResult<Result>()