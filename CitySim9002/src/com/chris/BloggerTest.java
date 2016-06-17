package com.chris;

import static org.junit.Assert.*;

import org.junit.Test;

public class BloggerTest {

	@Test
	public void Bloggertest() {
		
		Blogger b = new Blogger(1);
		assertEquals(b.type(), "Blogger");
	}

}
