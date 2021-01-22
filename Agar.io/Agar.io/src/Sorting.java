import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		
		int[] nums = {3, -5, 20, 50, 21};
		boolean sorted = false;
		//System.out.println("Hello");
		long start = System.currentTimeMillis();
		while(!sorted) {
			int num1 = (int)(Math.random()*(nums.length));
			int num2 = (int)(Math.random()*(nums.length));
			
			int temp = nums[num1];
			nums[num1] = nums[num2];
			nums[num2] = temp;
			boolean finish = true;
			for(int i = 0; i <nums.length-1; i++) {
				if (nums[i] > nums[i+1]) {
					finish = false;
					break;
				}
			}
			if (finish) {
				break;
			}
			
		}
		//ystem.out.println(System.currentTimeMillis()-start);
		//System.out.println(Arrays.toString(nums));
		
		
		
		boolean swapped = true;
		for(int i = 0; i< nums.length; i++) {
			swapped = false;
			for(int j = 0; j < nums.length-i-1; j++) {
				if (nums[j] > nums[j+1]) {
					int temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
		System.out.println(Arrays.toString(nums));
		
	}
	
	
	
	
}
