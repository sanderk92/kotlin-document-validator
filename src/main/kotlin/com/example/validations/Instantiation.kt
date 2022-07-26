package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator
import com.validator.Validator.Companion.checkEagerly
import com.validator.Validator.Companion.checkLazily

fun validateEagerly(document: Document): ValidationResult<List<String>> =

    checkEagerly(document) {
        // ...
    }

fun validateLazily(document: Document): ValidationResult<String> =

    checkLazily(document) {
        // ...
    }