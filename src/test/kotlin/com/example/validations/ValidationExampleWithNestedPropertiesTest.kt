package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidationExampleWithNestedPropertiesTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "Jason",
            content = emptyList(),
        )

        val result = ValidationExampleWithNestedProperties().validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owners with too long names are not allowed`() {
        val document = Document(
            owner = "abcdefghijklmnopqrstuvwxyz",
            content = emptyList(),
        )

        val result = ValidationExampleWithNestedProperties().validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field must not be longer than 10 characters"
        )
    }
}