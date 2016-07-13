import java.util.Random;

public class PropertyTest {
	
	public static int[] billify(int[] arr){
		int length = arr.length;
		int[] newArr = new int[length+1];
		int sum = 0;
		
		for(int i=0;i<length;i++){
			newArr[i] = arr[i]*arr[i];
			sum = sum+ newArr[i];
		}
		newArr[length] = sum;
		return newArr;
	}
	
	public static int[] RandomGenerator(){
		Random rm = new Random();
		//size of the input array is between 1 to 100
		int size= rm.nextInt(100)+1;
		int[] arr= new int[size];
		
		for(int i=0; i<size; i++){
			arr[i]= rm.nextInt(100)+1;
		}
		return arr;
	}
}
