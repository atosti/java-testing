# Java Unit Testing
This is a short repository which outlines basic guidelines for unit testing in Java. Additionally, it includes example files.

## Testing Guidelines
File structure
* Create a separate source folder for all your tests which mirrors your project’s structure.

`src/main/java/… - Contains your project`

`src/test/java/… - Contains the tests for your project`

* Although the source is kept separate, both the tests and the main code should reside in the same package.

`package com.site.my; - Project’s package line`

`package com.site.my; - Tests’ package line`

* It's also required to add the JUnit library to your project in order for the imports to be found by your IDE.

## Naming convention of files
* The typical naming convention for tests is pre-pending or post-pending `Test` to the name of the file being testing. This is up to you to decide, but the decision should be _**uniform**_, don't mix these two formats.

`MyService.java - The service you wish to test`

`TestMyService.java - The test with a pre-pended identifier`

`MyServiceTest.java - The test with a post-pended identifier`

## Naming convention of tests
* Inside your test files, the methods should follow a naming convention similar to:

`MethodName_StateUnderTest_ExpectedBehavior - Naming convention`

`myService_AllParametersNull_AssertReturnsFalse() - Example`

## Mocking and Stubbing
* Allows you to “call” methods without truly calling a method. Instead, a fake method is called which has a return value hard-coded by you.

* Helpful in mocking database or third-party service interactions

`MyRepository myRepo = mock(MyRepository.class);`

* Then use When().ThenReturn() statements to mock for consistent results.

`When(myRepo.searchDatabase(“Findme”, 5000L).thenReturn(true);`

* Additionally, matchers can be used in place of real values. These allow more flexible tests, but you must use either matchers or real values (can’t mix them).

`When(myRepo.searchDatabase(anyString(), anyLong()).thenReturn(true);`

* For this to work, the mock must be set within the test, which requires you to create a special method for setting the object to be mocked in your actual source code.

## Reflection
Allows for testing of private methods

Requires the source and the test files to share a package.

Having to do this is bad practice, but in legacy applications it can be necessary. Build new code that only requires testing public methods, use this to refactor and test old code sectors.

## Running tests

Guide to configuring running all tests in a folder

## Tips
* While designing a test, add an assert that will always fail (e.g. `AssertEquals(false, true)`) to ensure incomplete tests never look as though they're working correctly. This will prevent accidentally pushing broken tests to your repo.

