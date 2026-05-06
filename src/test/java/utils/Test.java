package utils;

public class Test {
	public static void main(String[] args) {
		String value = "Rs. 1,999.00";
	
		
		System.out.println(value);
		System.out.println("===================================");

		String price = value
				.replace("Rs.", "")
				.replaceAll("[^0-9.]", "");
		System.out.println(price);

	}
}
