package com.example

enum class ErrorCode {
    E001,
    E002,
}

data class Constraint(
    val message: String,
    val code: ErrorCode,
)