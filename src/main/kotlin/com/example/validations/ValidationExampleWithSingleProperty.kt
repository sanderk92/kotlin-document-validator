package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleWithSingleProperty {

    fun validate(document: Document): ValidationResult<List<String>> =

        checkEagerly(document) {

            checkProperty(Document::owner) { owner ->

                "The owner field must be at minimum three characters" enforcing {
                    owner.length >= 3
                }

                "The owner field must be at maximum ten characters" enforcing {
                    owner.length <= 10
                }
            }
        }
}
