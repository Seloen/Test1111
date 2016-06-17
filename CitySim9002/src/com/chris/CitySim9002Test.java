/**
 * 
 */
package com.chris;

import static org.junit.Assert.*;




import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;
import org.mockito.Mock;  
import org.mockito.MockitoAnnotations;  
import org.mockito.ArgumentCaptor;  
import static org.mockito.Mockito.*; 


public class CitySim9002Test {
	
	@Mock
	CitySim9002 cs = Mockito.mock(CitySim9002.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(cs);		
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	//Test input that is not integer will lead to false 
	@Test
	public void testcheckInputNotInteger(){
		Mockito.when(cs.checkInput(new String[]{"aaa"})).thenCallRealMethod();
		assertFalse(cs.checkInput(new String[]{"aaa"}));

	}
	
	//Test integer input that is 10 (edge case)
	@Test
	public void testcheckInputWrongInteger(){
		Mockito.when(cs.checkInput(new String[]{"10"})).thenCallRealMethod();
		assertFalse(cs.checkInput(new String[]{"10"}));

	}
//	@Test
//	public void testGenerate(){
//		Student a= new Student(1);
//		Mockito.when(cs.generateVisitor(0,1)).thenCallRealMethod();
//		assertSame(cs.generateVisitor(0,1), a);
//
//	}
	
	
	
	//Test that student will go to squirrel hill and like it.
	//Doubles and Stub are both used here. CallRealMethod() is used to mock some methods without mocking others
	@Test
	public void testRightLocationAndRightLike(){
		Visitor v = Mockito.mock(Student.class);
		String loc ="Squirrel Hill";
		Mockito.when(v.likeOrNot(loc)).thenReturn(true);
		Mockito.when(cs.visit(v, loc)).thenCallRealMethod();
		assertTrue(cs.visit(v, loc));
	}


}
