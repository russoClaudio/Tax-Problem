package it.salestaxes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
	private static final String ITEM_REGEX = "(\\d+)\\s((\\w+\\s)+)at\\s(\\d+.\\d+)";
	
	public static TypeOfItem parser(String input) {
		Matcher fileParsed = parse(input);
		String name = fileParsed.group(2).trim();
		TypeOfItem item = new TypeOfItem(name, Double.valueOf(fileParsed.group(4)));
		if (name.contains("imported"))
			item.setImported(true);
		if (MathTax.isExempt(name))
			item.setExempt(true);
		return item;
	}
	
	public static Matcher parse(String description) {
		Pattern pattern = Pattern.compile(ITEM_REGEX);
		Matcher matcher = pattern.matcher(description);
		matcher.find();
		return matcher;
	}
	
	public static boolean matches(String description) {
		return Pattern.matches(ITEM_REGEX, description);
	}
	
	public static int counter (String order) {
		return Integer.valueOf(parse(order).group(1));
	}
}
