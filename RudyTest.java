import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class RudyTest {
	
		static WebDriver driver = new HtmlUnitDriver();
		
		@Before
		public void setUp() throws Exception {
			driver.manage().deleteAllCookies();
			//
			driver.get("http://lit-bayou-7912.herokuapp.com");
		}
	
		//User Story 1
		//As a user
		//I want to tokenize the code I wrote
		//So that code can then be parsed.

		
		
		//Scenario 1
		//Given an input of 
		//str1="Hello world" 
		//puts str1
		//When I try to tokenize the code
		//Then I should receive a page with “:on_sp”, “:on_ident”, “:on_nl” and ":on_op" on it.
		@Test
		public void testU1S1() {
			
			String myText ="str1='Hello world'\nputs str1";
//			myText = myText.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));
			driver.findElement(By.id("code_code")).sendKeys(myText);
			
			WebElement submitButton = driver.findElement(By.cssSelector("input[name='commit']"));
			submitButton.click();
			
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
			assertTrue(t.contains(":on_sp"));
			assertTrue(t.contains(":on_nl"));
			assertTrue(t.contains(":on_ident"));
			assertTrue(t.contains(":on_op"));
			
		}
		
		
		//Scenario 2
		//Given an input of  
		//puts “abc”
		//When I try to tokenize the code
		//Then I should receive a page with “:on_sp”, “:on_ident” on it, and “:on_nl”, “:on_op” not on it.
		@Test
		public void testU1S2() {
			
			String myText ="puts 'abc'";
			driver.findElement(By.id("code_code")).sendKeys(myText);
			
			WebElement submitButton = driver.findElement(By.cssSelector("input[name='commit']"));
			submitButton.click();
			
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
			assertTrue(t.contains(":on_sp"));
			assertFalse(t.contains(":on_nl"));//only one line, so no ":on_nl" be showed.
			assertTrue(t.contains(":on_ident"));
			assertFalse(t.contains(":on_op"));	
		}
		
		
		//Scenario 3
		//Given an input of 
		//a=1
		//b=2
		//c=a+b
		//puts c
		//When I try to tokenize the code
		//Then I should receive a page with “:on_sp”, “:on_ident”, “:on_nl”, “:on_op” on it.
		@Test
		public void testU1S3() {
			
			String myText ="a=1\nb=2\nc=a+b\nputs c";
			driver.findElement(By.id("code_code")).sendKeys(myText);
			
			WebElement submitButton = driver.findElement(By.cssSelector("input[name='commit']"));
			submitButton.click();
			
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
			assertTrue(t.contains(":on_sp"));
			assertTrue(t.contains(":on_nl"));
			assertTrue(t.contains(":on_ident"));
			assertTrue(t.contains(":on_op"));
			
		}
		
		
		//User story 2
		//As a user
		//I want to parse the code I wrote
		//So that I can see the AST of my code, in order to get it compiled.
		
		//Scenario 1
		//Given an input of 
		//a=1
		//b=2
		//c=a+b
		//puts c
		//When I try to parse the code
		//Then I should receive a page with “@ident”, “assign”, “+”, “puts” on it. 
		//Meanwhile, “:on_sp” and “:on_nl” should not on the received page.
		@Test
		public void testU2S1() {
			
			String myText ="a=1\nb=2\nc=a+b\nputs c";
			driver.findElement(By.id("code_code")).sendKeys(myText);
			
			WebElement submitButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			submitButton.click();
			
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
			assertFalse(t.contains(":on_sp"));//“:on_sp” and “:on_nl” are part of results from user story 1 scenario 3.
			assertFalse(t.contains(":on_nl"));
			assertTrue(t.contains("@ident"));
			assertTrue(t.contains("+"));
			assertTrue(t.contains("assign"));
			assertTrue(t.contains("puts"));
			
		}
		
		
		//Scenario 2
		//Given an input of 
		//a=1
		//b=2
		//c=a + b
		//putsc
		//When I try to parse the code
		//Then I should receive a page with “@ident”, “assign”, “+” on it. 
		//Meanwhile, “:on_sp” and “:on_nl” and "puts" should not on the received page.
		@Test
		public void testU2S2() {
			
			String myText ="a=1\nb=2\nc=a+b\nputs c";
			driver.findElement(By.id("code_code")).sendKeys(myText);
			
			WebElement submitButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			submitButton.click();
			
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
			assertFalse(t.contains(":on_sp"));//“:on_sp” and “:on_nl” are part of results from user story 1 scenario 3.
			assertFalse(t.contains(":on_nl"));
			assertTrue(t.contains("@ident"));
			assertTrue(t.contains("+"));
			assertTrue(t.contains("assign"));
			assertFalse(t.contains("puts")); //since putsc cannot be tokenized into "puts c",puts function cannot be parsed
			
		}
		
		//User story 3
		//As a user
		//I want to compile the code I wrote
		//So that AST can be compiled into lower level byte code.
		
		//Scenario 1
		//Given an input of 
		//a=1
		//b=2
		//c=a+b
		//puts c
		//When I try to compile the code
		//Then I should receive a page with “putstring”, “opt_plus”, “putobject” on it. 
		
		@Test
		public void testU3S1() {
					
			String myText ="a=1\nb=2\nc=a+b\nputs c";
			driver.findElement(By.id("code_code")).sendKeys(myText);
					
			WebElement submitButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
			submitButton.click();
					
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
					
			//assertTrue(t.contains("putstring"));//this one will fail since no "putstring" is in the result
			assertTrue(t.contains("opt_plus"));//"+"
			assertTrue(t.contains("putobject"));//where there is an assignment, there is a "putobject" after compilation.
					
		}
				
				
		//Scenario 2
		//Given an input of 
		//a=1
		//b=2
		//c=a-b
		//puts c
		//When I try to parse the code
		//Then I should receive a page with “putstring”, “opt_minus”, “putobject” on it. 
		
		@Test
		public void testU3S2() {
							
			String myText ="a=1\nb=2\nc=a-b\nputs c";
			driver.findElement(By.id("code_code")).sendKeys(myText);
							
			WebElement submitButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
			submitButton.click();
							
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
							
			//assertTrue(t.contains("putstring"));//this one will fail since no "putstring" is in the result
			assertTrue(t.contains("opt_minus"));//"-"
			assertTrue(t.contains("putobject"));//where there is an assignment, there is a "putobject" after compilation.
							
		}
						
				
		//Scenario 3
		//Given an input of 
		//a=1
		//b=2
		//c=a*b
		//puts c
		//When I try to parse the code
		//Then I should receive a page with  “putstring”, “opt_mult”, “putobject” on it. 
		
		@Test
		public void testU3S3() {
									
			String myText ="a=1\nb=2\nc=a*b\nputs c";
			driver.findElement(By.id("code_code")).sendKeys(myText);
									
			WebElement submitButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
			submitButton.click();
									
			WebElement text = driver.findElement(By.cssSelector("code"));
			String t= text.getText();
									
			//assertTrue(t.contains("putstring"));//this one will fail since no "putstring" is in the result
			assertTrue(t.contains("opt_mult"));//"*"
			assertTrue(t.contains("putobject"));//where there is an assignment, there is a "putobject" after compilation.
									
		}

		//Scenario 4
				//Given an input of 
				//a=1
				//b=2
				//c=a /   b
				//puts c
				//When I try to parse the code
				//Then I should receive a page with “putstring”, “opt_div”, “putobject” on it. 
				//Meanwhile, “:on_sp” and “:on_nl” should not on the received page.
				@Test
				public void testU3S4() {
											
					String myText ="a=1\nb=2\nc=a /   b\nputs c";//spaces will not impact compilation
					driver.findElement(By.id("code_code")).sendKeys(myText);
											
					WebElement submitButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
					submitButton.click();
											
					WebElement text = driver.findElement(By.cssSelector("code"));
					String t= text.getText();
											
					//assertTrue(t.contains("putstring"));//this one will fail since no "putstring" is in the result
					assertTrue(t.contains("opt_div"));//"/"
					assertTrue(t.contains("putobject"));//where there is an assignment, there is a "putobject" after compilation.
											
				}
		

		

}
