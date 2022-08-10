package com.validator

import com.validator.dto.Constraint
import com.validator.dto.Document
import com.validator.dto.ErrorCode
import com.validator.Failed
import com.validator.Passed
import com.validator.ValidationResult
import com.validator.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun validate(document: Document): ValidationResult<List<Constraint>> =

    Validator.checkEagerly(document) {

        checkProperty(Document::owner) { owner ->

            Constraint("The owner field must not be empty or blank", ErrorCode.E001) enforcing {
                owner.isNotBlank()
            }
        }
    }

class CustomConstraintTest {

    @Test
    fun `Valid documents pass`() {
        val document = Document(
            owner = "Frank",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }


    @Test
    fun `Owners with empty name are not allowed`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            Constraint("The owner field must not be empty or blank", ErrorCode.E001)
        )
    }
}
