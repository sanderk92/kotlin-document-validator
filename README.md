# Kotlin Validator DSL
An easy to use DSL for validating the content of any document.

## Instructions

First we define an arbitrary `Document` class:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/ExampleDocument.kt#L3-L6

## Instantiate a validator

Then we must instantiate a `Validator`:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/Instantiation.kt#L10-L12

<sup>*Returns all errors that occurred<sup>

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/Instantiation.kt#L16-L18

<sup>*Returns the first error that occurred and skips evaluation of remaining predicates<sup>

## Validation

### Enforcing

Properties can be checked for whether they fulfill a predicate:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L14-L16

### Trying

Properties can be checked for whether an operation on them, i.e. a parse, will result in an exception:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L18-L20

## Property accessors

Property accessors allow us to structure our `Validator` and perform constraint checks on specific properties:

#### All properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleWithSubject.kt#L9-L19

#### Single property

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleWithSingleProperty.kt#L9-L19

#### Iterable properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleWithIterableProperty.kt#L9-L19

#### Nested properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleWithNestedProperties.kt#L9-L22

## Custom constraint types

Property checks can be given additional failure context:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationExampleWithCustomConstraintType.kt#L12-L22

## Validation result

A `ValidationResult` can be used in pattern matching and contains a few helper functions in case multiple results are returned:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/1d1e2440c32a1b8e9778db83a9f6dece2dc0ebed/src/main/kotlin/com/example/validations/ValidationResultUsage.kt#L8-L18