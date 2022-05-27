package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidationExampleWithExtractedMethodsTest {

    @Test
    fun `Owners with empty names are not allowed`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = ValidationExampleWithExtractedMethods().validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactly(
            "The owner field must not be empty or blank"
        )
    }


    @Test
    fun `Owners with non-empty names are allowed`() {
        val document = Document(
            owner = "Frank",
            content = emptyList(),
        )

        val result = ValidationExampleWithExtractedMethods().validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }
}