package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleWithExtractedMethods {

    fun validate(document: Document): ValidationResult<List<String>> =

        checkEagerly(document) {
            checkOwner()
        }

    private fun Validator<Document, String>.checkOwner() =

        checkProperty(Document::owner) { owner ->

            "The owner field must not be empty or blank" enforcing {
                owner.isNotBlank()
            }
        }
}


