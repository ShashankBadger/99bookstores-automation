package utils;

public class Test {
	public static void main(String[] args) {
		String value = "The Naturals 4 books Boxset by Jennifer Lynn\r\n"
				+ "Regular price\r\n"
				+ "Rs. 1,999.00\r\n"
				+ "Sale price\r\n"
				+ "Rs. 899.00\r\n"
				+ "Add to cart";
		
		System.out.println(value);
		System.out.println("===================================");
		value = value.replace("\r\n", " ");
		System.out.println(value);
	}
}
