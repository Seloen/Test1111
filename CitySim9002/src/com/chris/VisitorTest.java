/**
 * 
 */
package com.chris;

import static org.junit.Assert.*;

import org.junit.Test;


public class VisitorTest {
	
	// test that professor will like all the four places
	//when object Professor calls public method likeOrNot with locations as parameter
	//true should be returned for all the four locations 
	@Test
	public void testProfessorLikes(){
		Visitor v = new Professor(3);
		assertTrue(v.likeOrNot("The Cathedral of Learning"));
		assertTrue(v.likeOrNot("The Point"));
		assertTrue(v.likeOrNot("Squirrel Hill"));
		assertTrue(v.likeOrNot("Downtown"));
	}
	
	//test that type() method will return right type of a visitor.
	//if object Business Man calls type() method, the type of "BusinessMan" should be returned.
	@Test
	public void testVisitorType(){
		Visitor v = new BusinessMan(1);
		assertEquals(v.type(), "BusinessMan");
	}
}
