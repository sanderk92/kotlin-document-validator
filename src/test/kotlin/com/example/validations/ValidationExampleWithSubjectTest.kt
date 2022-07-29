package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ValidationExampleWithSubjectTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "Jason",
            content = emptyList(),
        )

        val result = ValidationExampleWithSingleProperty().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owners with empty names are not allowed`() {
        val document = Document(
            owner = "",
            content = listOf(1),
        )

        val result = ValidationExampleWithSubject().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field must not be empty or blank"
        )
    }
}