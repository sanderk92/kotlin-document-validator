package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidationExampleWithSinglePropertyTest {

    @Test
    fun `Owners with too short names are not allowed`() {
        val document = Document(
            owner = "ab",
            content = emptyList(),
        )

        val result = ValidationExampleWithSingleProperty().validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactly(
            "The owner field must be at minimum three characters"
        )
    }

    @Test
    fun `Owners with valid name are allowed`() {
        val document = Document(
            owner = "Jason",
            content = emptyList(),
        )

        val result = ValidationExampleWithSingleProperty().validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }
}