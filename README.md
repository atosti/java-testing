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
   * For reference, my examples use the prepended version.
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
      myService_AllParametersNull_AssertReturnsFalse(){}    //Example method name
      ```

## Writing tests
### Mocks
Mocking is a practice which allows you to avoid calling certain methods, instead faking their responses. It's primarily used for testing features that require calls to databases or third-party services.
  * It also helps in testing how your code handles failures in connecting to these services.

### Stubbing
1. Begin by creating a **_setter injector_** in the **_actual_** source code, so that you can inject your mocked items later.
      
      ```java
      /*In MyService.java*/
      private EmployeeRepository employeeRepository; //Some repository item
      //The setter, which can be injected into later from our test methods
      public void setEmployeeRepository(EmployeeRepository employeeRepository){
          this.employeeRepository = employeeRepository;
      }
      ```
      
2. Next, in your test method, create a mocked object.

      ```java
      /*In TestMyService.java*/
      EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
      ```

3. Then, use `when().thenReturn()` statements to hardcode results when calling this object.
    * The `when()` portion can take either particular parameters, or more general [Matchers](https://static.javadoc.io/org.mockito/mockito-core/1.9.5/org/mockito/Matchers.html), which simply match a parameter type.
    * Note that you cannot mix and match real parameters and matchers, you must use one or the other.
    * The `thenReturn()` function can return any object as long as it matches the return type of the call in `when()`.

      ```java
      /*In TestMyService.java*/
      when(employeeRepository.searchDatabase(“Searchterm”).thenReturn(true);
      when(employeeRepository.searchDatabase(anyString()).thenReturn(true);
      ```

4. Finally, the mocked object must be injected to the service from within the test method.

    ```java
    /*In TestMyService.java*/
    MyService myService = new MyService();                //The service object whose methods you're testing
    myService.setEmployeeRepository(employeeRepository);  //Calling the injection method
    ```

### Reflection
Reflection allows for the testing of private methods. This is a [heated issue](https://stackoverflow.com/questions/34571/how-do-i-test-a-private-function-or-a-class-that-has-private-methods-fields-or) among developers, and should generally be avoided by designing your application to only require testing of public methods. Regardless, it often proves useful in testing legacy code and refactoring old sectors of code.
  * Note that reflection **_requires_** that the source and test files share a package.

1. TBD - Add a guide here to match a reflection example in the codebase.

## Running tests
After all your tests are written, you can run them from your IDE or the command line.
  * In Eclipse: `Right click TestMyService.java > Run As > JUnit Test`

## Tips
* While designing a test, set an always failing assert statement (e.g. `AssertEquals(false, true)`) to ensure incomplete tests never appear as though they're working.
  * This will reduce the likelihood of pushing unfinished tests into your codebase.

