# Java unit testing
This is a short repository which outlines basic guidelines for unit testing in Java. Additionally, it includes example files illustrating how to write basic tests. The aim of this document is to give you the flexibility to design tests in a manner best suited to you and your team.
   * Remember that consistency is most important when selecting your own standards.

## Environment setup
1. Add the JUnit library to your project in order for the JUnit imports to be found by your IDE.
    * In Eclipse: `Project > Properties > Java Build Path > Add Library > JUnit > Select JUnit 4 > Finish > Ok`
2. Install the Mockito dependencies, so its imports can be found.
    * Follow this [guide](http://www.vogella.com/tutorials/Mockito/article.html#mockito_installation) for more information.
3. File Structure of a project with tests
   * Keep tests separate from the real source code. However, create the internal structure for your tests should mirror your project.
   
      ```java
      src/main/java/…    //Contains your project
      src/test/java/…    //Contains the tests for your project
      ```
   
   * Although the tests are in a separate folder, put them in the same **_package_** as your source. This gives your tests the permissions required to perform [reflection](https://docs.oracle.com/javase/tutorial/reflect/), allowing for testing of private methods (should you choose to do so).

## Formatting guidelines
### Breaking down test files
It can often become challenging to decide if/when to break a test class into smaller files focused on examining a single method.
* One suggestion is to only do this if many tests exist who share common, lengthy setup parameters that could benefit from being in a separate file.

### File naming conventions
The two most common conventions are to either prepend or append `Test` to the name of the service being tested. Both have pros/cons which are discussed [here](https://stackoverflow.com/questions/3146821/naming-convention-junit-suffix-or-prefix-test).
   * Whichever you choose, remember to be consistent and ensure your team only uses one or the other (never mix them).

      ```java
      TestMyService.java    //File name with prepended identifier
      MyServiceTest.java    //File name with appended identifier
      ```

### Test naming conventions
Within your test files, the methods should all be named so that it's immediately evident what is being tested and why a test may have failed.
  * There are many [popular options](https://dzone.com/articles/7-popular-unit-test-naming) for naming, however I've outlined my preference (and the format used in my examples) below.

      ```java
      MethodName_StateUnderTest_ExpectedBehavior(){}        //Naming convention
      myService_AllParametersNull_AssertReturnsFalse(){}    //Example
      ```

## Writing tests
### Mocks
Mocking is a practice which allows you to avoid calling certain methods, instead faking their responses. It's primarily used for testing features that require calls to databases or third-party services.
  * It also helps in testing how your code handles failures in connecting to these services.

### Stubbing steps
1. Begin by creating a **_setter injector_** in the actual source code, so that you can inject your mocked item later.
      
      ```java
      private EmployeeRepository employeeRepository;
      public void setEmployeeRepository(EmployeeRepository employeeRepository){
          this.employeeRepository = employeeRepository;
      }
      ```
      
2. Pickup here...
      `MyRepository myRepo = mock(MyRepository.class);`

* Then use When().ThenReturn() statements to mock for consistent results.

`When(myRepo.searchDatabase(“Findme”, 5000L).thenReturn(true);`

* Additionally, matchers can be used in place of real values. These allow more flexible tests, but you must use either matchers or real values (can’t mix them).

`When(myRepo.searchDatabase(anyString(), anyLong()).thenReturn(true);`

* For this to work, the mock must be set within the test, which requires you to create a special method for setting the object to be mocked in your actual source code.

### Reflection
Allows for testing of private methods

Requires the source and the test files to share a package.

Having to do this is bad practice, but in legacy applications it can be necessary. Build new code that only requires testing public methods, use this to refactor and test old code sectors.

### Running tests

Guide to configuring running all tests in a folder

## Tips
* While designing a test, add an assert that will always fail (e.g. `AssertEquals(false, true)`) to ensure incomplete tests never look as though they're working correctly. This will prevent accidentally pushing broken tests to your repo.

