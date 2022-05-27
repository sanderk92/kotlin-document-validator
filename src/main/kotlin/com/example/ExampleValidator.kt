package com.example

import com.validator.ValidationResult
import com.validator.Validator.Companion.check

class Example1 {

    fun validate(document: Document): ValidationResult<String> =

        check(document) {

            checkProperty(Document::owner) {

                forConstraint("The owner field must not be empty or blank") {
                    it.isNotBlank()
                }

                forConstraint("The owner field must be at least three characters") {
                    it.length >= 3
                }
            }
        }
}

class Example2 {

    fun validate(document: Document): ValidationResult<Constraint> =

        check(document) {

            checkProperty(Document::owner) {

                forConstraint(Constraint("The owner field must not be empty or blank", ErrorCode.E001)) {
                    it.isNotBlank()
                }

                forConstraint(Constraint("The owner field must be at least three characters", ErrorCode.E002)) {
                    it.length >= 3
                }
            }
        }
}

class Example3 {

    fun validate(document: Document): ValidationResult<String> =

        check(document) {

            checkIterableProperty(Document::content) {

                forConstraint("Must contain only positive values") {
                    it >= 0
                }

                forConstraint("Must contain only values smaller than 100") {
                    it < 100
                }
            }
        }
}

