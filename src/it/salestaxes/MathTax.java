package it.salestaxes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class MathTax {
	
	public static double roundPrice(double amount) {
		return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	static public double calculateFivePercent(double amount) {
		return new BigDecimal(Math.ceil(amount * 20)/20).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}
	
	public static void parserShoppingCart(String fileName) {
		ShoppingCart shc = new ShoppingCart();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null) {
				if (ItemParser.matches(str) && !str.isEmpty())
					shc.put(ItemParser.parser(str), ItemParser.counter(str)); 
				else
					if (!str.isEmpty()) System.out.println("Error input format: " + str + "\nThis row will be not considered");
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Input/Output error:" + e.getMessage());
			return;
		}
		// Show the results of the input file/s
		shc.printOrderInput();
		// Show the results of the orders
		shc.printOrderResults();
	}
	
	// Collection of exempt items
	private static Set<String> exemptItems = null;
	static	{
		exemptItems = new HashSet<String>();
		exemptItems.add("book");
		exemptItems.add("headache pills");
		exemptItems.add("packet of headache pills");
		exemptItems.add("box of imported chocolates");
		exemptItems.add("imported box of chocolates");
		exemptItems.add("box of chocolates");
		exemptItems.add("chocolate");
		exemptItems.add("chocolate bar");
		exemptItems.add("pills");
	}

	public static boolean isExempt(String name) {
		return exemptItems.contains(name);
	}
}