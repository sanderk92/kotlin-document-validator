package com.example.validations

import com.example.Constraint
import com.example.Document
import com.example.ErrorCode
import com.validator.ValidationResult
import com.validator.Validator
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleWithCustomConstraintType {

    fun validate(document: Document): ValidationResult<List<Constraint>> =

        checkEagerly(document) {

            checkProperty(Document::owner) { owner ->

                Constraint("The owner field must not be empty or blank", ErrorCode.E001) enforcing {
                    owner.isNotBlank()
                }
            }
        }
}
