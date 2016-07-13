import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class TestPropertyTest {

	//Property: Output array size should be exactly one bigger than input array size. 
	@Test
	public void testSizeOfNewArray() {

		int[] input= PropertyTest.RandomGenerator();
		int[] output= PropertyTest.billify(input);
		int inputsize=input.length;
		int outputsize= output.length;
		try{
			assertEquals(inputsize+1,outputsize);
			}catch(NoSuchElementException nseex){
				fail();
			}
	}
		
	//Property: Elements of input array should be respectively mapped the square to output array.
	//(Except for the last element in the output array)
	@Test
	public void testElementsAreSquared() {

		int[] input= PropertyTest.RandomGenerator();
		int[] output= PropertyTest.billify(input);
		int inputsize=input.length;
		
		for(int i=0; i<inputsize;i++){
			//calculate square of each element of input array
			int square=input[i]*input[i];
			//assert elements are indeed mapped square.
			try{assertEquals(output[i],square);
				}catch(NoSuchElementException nseex){
					fail();
				}
		
		}
	}
	
	//Property: The last element of output array is the sum of all previous elements.
	@Test
	public void testLastOneIsSum() {

		int[] input= PropertyTest.RandomGenerator();
		int[] output= PropertyTest.billify(input);
		int outputsize= output.length;
		int sum=0;
		for(int i=0; i<outputsize-1;i++){
			sum+=output[i];
		}
		try{
			assertEquals(sum,output[outputsize-1]);
			}catch(NoSuchElementException nseex){
				fail();
			}
	}
	
	//Property: Running it twice on same array should always has same output.
		@Test
		public void testPure() {

			int[] input= PropertyTest.RandomGenerator();
			int[] output= PropertyTest.billify(input);
			int[] output1= PropertyTest.billify(input);
			
			try{
				assertArrayEquals(output,output1);
				}catch(NoSuchElementException nseex){
					fail();
				}
		}
		
		//Property: last element of output array should always equals to or greater than size of input array.
		//Considering of the case when every element is 1
		@Test
		public void testLastElementGreaterThanSizeOfInput() {

			int[] input= PropertyTest.RandomGenerator();
			int[] output= PropertyTest.billify(input);
			int inputsize= input.length;
			int outputsize= output.length;
			boolean b = output[outputsize-1]>=inputsize; 
			
			try{
				assertTrue(b);
				}catch(NoSuchElementException nseex){
					fail();
				}
		}
	

}
