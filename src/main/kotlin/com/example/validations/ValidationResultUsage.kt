package com.example.validations

import com.validator.*
import com.validator.Validator.Companion.checkEagerly

fun useResult() =

    when (val result = getValidationResult()) {
        is Passed<*> -> {
            println("Validation passed!")
        }
        is Failed<List<String>> -> {
            println("Total errors: ${result.errors}" )
            println("Total error count: ${result.errorCount}")
            println("Unique errors: ${result.uniqueErrors}")
            println("Unique error count ${result.uniqueErrorCount}")
        }
    }

private fun getValidationResult(): ValidationResult<List<String>> =

    checkEagerly("Subject") { string ->

        "Test" enforcing {
            string.isEmpty()
        }

        "Test" enforcing {
            string.isEmpty()
        }
    }