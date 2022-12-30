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
fun validateEagerly(document: Document): ValidationResult<List<String>> =

    document validateEagerly { subject ->
        // ...
    }

```

<sup>*Returns all errors that occurred<sup>

```kotlin
fun validateLazily(document: Document): ValidationResult<String> =

    document validateLazily { subject ->
        // ...
    }
```

<sup>*Returns the first error that occurred and skips evaluation of remaining predicates<sup>

## Validation definition

### Enforcing

Properties can be enforced to fulfill a predicate:

```kotlin
document validateEagerly { subject ->
    
    "The owner field may not be empty" enforcing {
        subject.owner.isNotEmpty()
    }
}
```

### Trying

Properties can be checked for whether an operation on them will result in an exception:

```kotlin
document validateEagerly { subject ->
    
    "The owner field must be a valid UUID" trying {
        UUID.fromString(subject.owner)
    }
}
```

### Ignoring

Properties can be checked but the result ignored:

```kotlin
document validateEagerly { subject ->
    
    "The content field may not be empty" ignoring {
        subject.content.isEmpty()
    }
}
```

### Peek

A validation definition as described above can immediately have its result peeked on:

```kotlin
document validateEagerly { subject ->
    
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

## Property accessors

Property accessors allow us to structure our `Validator` and perform constraint checks on specific properties:

#### Single property

```kotlin
document validateEagerly {
    
    Document::owner check { owner ->

        "The owner field must be at minimum three characters" enforcing {
            owner.length >= 3
        }
    }
}
```

#### Iterable properties

```kotlin
document validateEagerly {

     Document::content checkEach { element ->

        "The content field must contain only positive values" enforcing {
            element > 0
        }
    }
}
```

#### Nested properties

```kotlin
document validateEagerly {

    Document::owner check { owner ->

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
document validateEagerly {

    Document::owner check { owner ->

        Constraint("The owner field must not be empty or blank", ErrorCode.E001) enforcing {
            owner.isNotBlank()
        }
    }
}
```

## Validation result

A `ValidationResult` can be used in pattern matching and contains a few helper functions in case multiple results are
returned:

```kotlin
when (val result = getEagerValidationResult()) {
    is Passed<*> -> {
        println("Validation passed!")
    }
    is Failed<List<String>> -> {
        println("Total errors: ${result.errors}" )
        println("Total error count: ${result.errorCount}")
        println("Unique errors: ${result.uniqueErrors}")
        println("Unique error count ${result.uniqueErrorCount}")
    }
}
```

Or when a single result is returned:

```kotlin
when (val result = getLazyValidationResult()) {
    is Passed<*> -> println("Validation passed!")
    is Failed<String> -> println("Total errors: ${result.errors}")
}
```
