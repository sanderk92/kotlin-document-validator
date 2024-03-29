# Kotlin Document Validator DSL

An easy to use DSL for validating the content of any document.

## Instructions

First we define an arbitrary `Document` class:

```kotlin
data class Document(
    val owner: String,
    val content: List<Int>,
) 
```

## Instantiate a validator

Then we must instantiate a `Validator`:

```kotlin
fun validate(document: Document): ValidationResult<List<String>, Document> =

    document validate { subject ->
        // ...
    }

```

<sup>*Returns all errors that occur during validation of the document.<sup>

```kotlin
fun validateLazily(document: Document): ValidationResult<String, Document> =

    document validateLazily { subject ->
        // ...
    }
```

<sup>*Returns the first error that occurs and skips evaluation of remaining predicates.<sup>

## Validation definition

### Enforcing

Properties can be enforced to fulfill a predicate:

```kotlin
document validate { subject ->
    
    "The owner field may not be empty" enforcing {
        subject.owner.isNotEmpty()
    }
}
```

### Trying

Properties can be checked for whether an operation on them will result in an exception:

```kotlin
document validate { subject ->
    
    "The owner field must be a valid UUID" trying {
        UUID.fromString(subject.owner)
    }
}
```

### Ignoring

Properties can be checked but the result ignored:

```kotlin
document validate { subject ->
    
    "The content field may not be empty" ignoring {
        subject.content.isEmpty()
    }
}
```

## Peek

A validation definition as described above can immediately have its result peeked on:

```kotlin
document validate { subject ->
    
    "The content field may not be empty" enforcing {
        subject.content.isNotEmpty()
    } onPass {
        println("The content field was not empty!")
    } onFail {
        println("The content field was empty")
    }
}
```

<sup>*Adds an execution of the check for each peek<sup>

## Conditional

A validation definition as described above can conditionally be executed

```kotlin
document validate { (owner, content) ->

    content.isEmpty() then  {
        "Owner may not be empty if content is" enforcing {
            owner.isNotBlank()
        }
    }
}
```

## Property accessors

Property accessors allow us to structure our `Validator` and perform constraint checks on specific properties:

#### Single property

```kotlin
document validate {
    
    Document::owner check { owner ->

        "The owner field must be at minimum three characters" enforcing {
            owner.length >= 3
        }
    }
}
```

#### Iterable properties

```kotlin
document validate {

     Document::content onEach { element ->

        "The content field must contain only positive values" enforcing {
            element > 0
        }
    }
}
```

#### Nested properties

```kotlin
document validate {

    Document::owner check {

        String::length check { length ->

            "The owner field must not be longer than 10 characters" enforcing {
                length <= 10
            }
        }
    }
}
```

## Custom constraint types

Property checks can be given additional failure context:

```kotlin
document validate {

    Document::owner check { owner ->

        Constraint("The owner field must not be empty or blank", ErrorCode.E001) enforcing {
            owner.isNotBlank()
        }
    }
}
```

## Validation result

A `ValidationResult` contains a few helper functions to help you process the results, i.e.:

```kotlin
when (val result = validate(document)) {
    is Passed<Document> -> {
        println("Validation passed: ${result.value}!")
    }
    is Failed<List<String>> -> {
        println("Total errors: ${result.errors}" )
        println("Total error count: ${result.errorCount}")
        println("Unique errors: ${result.uniqueErrors}")
        println("Unique error count ${result.uniqueErrorCount}")
    }
}
```

Or when a lazy validation is performed:

```kotlin
when (val result = validate(document)) {
    is Passed<Document> -> println("Validation passed: ${result.value}!")
    is Failed<String> -> println("Validation failed: ${result.errors}!")
}
```
