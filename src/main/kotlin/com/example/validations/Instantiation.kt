package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly
import com.validator.Validator.Companion.checkLazily

fun validateEagerly(subject: Document): ValidationResult<List<String>> =

    checkEagerly(subject) { document ->
        // ...
    }

fun validateLazily(subject: Document): ValidationResult<String> =

    checkLazily(subject) { document ->
        // ...
    }