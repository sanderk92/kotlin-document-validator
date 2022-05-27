package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleWithIterableProperty {

    fun validate(document: Document): ValidationResult<List<String>> =

        checkEagerly(document) {

            checkIterableProperty(Document::content) { element ->

                "The content field must contain only positive values" enforcing {
                    element >= 0
                }
            }
        }
}
