package com.company.app.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.company.app.Employee;
import com.company.app.EmployeeRepository;

/**
 * Unit test for simple MyService.
 */
public class TestMyService{
    //Follows naming convention of `MethodName_StateUnderTest_ExpectedBehavior`
    @Test
    public void isJohnSmith_EmployeeIdMatchingJohnSmith_AssertReturnsTrue(){
        //Setup
        Long johnsId = 100L;
        Employee john = new Employee();
        //Create `john` whose name is `John Smith` and matches our searched ID
        john.setEmployeeId(johnsId);
        john.setFirstName("John");
        john.setLastName("Smith");
        //Create an object for search to return, which holds our `john` employee
        Optional<Employee> searchResult;
        //Assign a non-null value for our result to hold
        searchResult = Optional.of(john); 
        
        //Mocks
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findByEmployeeId(johnsId)).thenReturn(searchResult);
        
        //Results
        MyService myService = new MyService();
        myService.setEmployeeRepository(employeeRepository);
        boolean result = myService.isJohnSmith(johnsId);
        
        assertEquals(true, result);
    }
    
    @Test
    public void addSecretly_Add2And5_AssertEquals7() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        //Setup
        Long firstNum = 2L;
        Long secondNum = 5L;
        Long expected = firstNum + secondNum;
        
        //Mocks
        //Empty...
        
        //Results
        MyService myService = new MyService();
        //Reflection
        Method addSecretly = MyService.class.getDeclaredMethod("addSecretly", Long.class, Long.class);
        addSecretly.setAccessible(true); //Allows us to access it despite it being private
        Long result = (Long) addSecretly.invoke(myService, firstNum, secondNum);
        
        assertEquals(expected, result);
    }
}
