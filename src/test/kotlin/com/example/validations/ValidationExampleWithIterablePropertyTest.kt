package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidationExampleWithIterablePropertyTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "",
            content = listOf(1, 2, 3, 4),
        )

        val result = ValidationExampleWithIterableProperty().validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Content with negative values is not allowed`() {
        val document = Document(
            owner = "",
            content = listOf(-1, -2, -3, -4),
        )

        val result = ValidationExampleWithIterableProperty().validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The content field must contain only positive values",
            "The content field must contain only positive values",
            "The content field must contain only positive values",
            "The content field must contain only positive values",
        )
    }
}
