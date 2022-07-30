# Kotlin Validator DSL
An easy to use DSL for validating the content of any document.

## Instructions

First we define an arbitrary `Document` class:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/ExampleDocument.kt#L3-L6

## Instantiate a validator

Then we must instantiate a `Validator`:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/Instantiation.kt#L10-L12

<sup>*Returns all errors that occurred<sup>

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/Instantiation.kt#L16-L18

<sup>*Returns the first error that occurred and skips evaluation of remaining predicates<sup>

## Validation definition

### Enforcing

Properties can be enforced to fulfill a predicate:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L14-L16

### Trying

Properties can be checked for whether an operation on them will result in an exception:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L18-L20

### Ignoring

Properties can be checked but the result ignored:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L22-L24

### Peek

A validation definition as described above can immediately have its result peeked on:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleLogging.kt#L13-L19

<sup>*Adds an execution of the check for each peek<sup>

## Property accessors

Property accessors allow us to structure our `Validator` and perform constraint checks on specific properties:

#### All properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleWithSubject.kt#L9-L19

#### Single property

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleWithSingleProperty.kt#L9-L19

#### Iterable properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleWithIterableProperty.kt#L9-L19

#### Nested properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleWithNestedProperties.kt#L9-L22

## Custom constraint types

Property checks can be given additional failure context:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationExampleWithCustomConstraintType.kt#L12-L22

## Validation result

A `ValidationResult` can be used in pattern matching and contains a few helper functions in case multiple results are returned:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/97c4563bbf8d2dce077eefc465e260038508d9da/src/main/kotlin/com/example/validations/ValidationResultUsage.kt#L8-L18