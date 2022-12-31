package com.validator

import com.validator.Validator.Companion.validate
import com.validator.dto.Document
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private var value = 0

private fun validate(document: Document): ValidationResult<List<String>> =

    document validate { (owner, _) ->

        "Owner may not be empty" enforcing {
            owner.isNotBlank()
        } onPass {
            value = 1
        } onFail {
            value = -1
        }
    }

class PeekTest {
    @BeforeEach
    fun setUp() {
        value = 0
    }

    @Test
    fun `The onPass function is called`() {
        val document = Document(
            owner = "Joe",
            content = emptyList(),
        )

        validate(document)

        assertThat(value).isEqualTo(1)
    }

    @Test
    fun `The onFail function is called`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        validate(document)

        assertThat(value).isEqualTo(-1)
    }
}