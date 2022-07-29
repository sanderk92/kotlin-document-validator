package com.example.validations

import com.example.Constraint
import com.example.Document
import com.example.ErrorCode
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidationExampleWithCustomConstraintTypeTest {

    @Test
    fun `Valid documents pass`() {
        val document = Document(
            owner = "Frank",
            content = emptyList(),
        )

        val result = ValidationExampleWithCustomConstraintType().validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }


    @Test
    fun `Owners with empty name are not allowed`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = ValidationExampleWithCustomConstraintType().validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            Constraint("The owner field must not be empty or blank", ErrorCode.E001)
        )
    }
}
