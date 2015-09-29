package it.salestaxes;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* PROBLEM: SALES TAXES
*
* @author  Russo Claudio
* @version 1.0
* @since   2015-09-29 
*/
public class ShoppingCart {

	private final Map<Item, Integer> itemMap = new HashMap<Item, Integer>();
	//DecimalFormat df = new DecimalFormat("###.##");		 ///////
	DecimalFormat format = new DecimalFormat("###.00");

	public void put (Item item, int count){
		if (item.isImported()) item = new ImportTaxShc(item);
		if (!item.isExempt()) item = new SalesTaxShc(item);
		Integer i = this.itemMap.get(item); 
		if ( i!= null) count += i;
		this.itemMap.put(item, count);
	}
	
	public void remove (Item item) {
		this.itemMap.remove(item);
	}
	
	public void clear () {
		this.itemMap.clear();
	}
	
	public Set<Item> getItems() {
		return itemMap.keySet();
	}
	
	public int getQuantity(Item item){
		return itemMap.get(item);	
	}
	
	public double getTaxtotal() {
		double taxtotal = 0;
		for (Item item : itemMap.keySet()){		
			double subTotal = item.getPrice() * getQuantity(item);
			double subInitTotal = item.getInitPrice() * getQuantity(item);
			taxtotal += subTotal - subInitTotal;
		}
		return taxtotal;
	}

	public double getTotal() {
		double total = 0;
		for (Item item : itemMap.keySet()){		
			double subTotal = item.getPrice() * getQuantity(item);
			total += subTotal;
		}
		return MathTax.roundPrice(total);
	}

	public void printOrderInput() {
		System.out.println("//-----------------------------------------------------------//");
		System.out.println("Order input: ");
		for ( Item item : itemMap.keySet() ){
			System.out.println(itemMap.get(item) + " " + item.getName() + " at $ " + format.format(item.getInitPrice()));
		}	
	}
	
	public void printOrderResults() {	
		double taxtotal = 0;
		double total = 0;
		System.out.println("\nOrder results: ");
		Set<Item> taxedItems = itemMap.keySet();
		for (Item item : taxedItems){		
			double subTotal = item.getPrice() * getQuantity(item);
			double subInitTotal = item.getInitPrice() * getQuantity(item);
			taxtotal += subTotal - subInitTotal;
			total += subTotal;
			System.out.println(getQuantity(item) + " " + item.getName() + ": $ " + format.format(subTotal));
		}
		total = MathTax.roundPrice(total);
		System.out.println("Sales Taxes: $ "+format.format(taxtotal));
		System.out.println("Total: $ " + format.format(total) + "\n");
		System.out.println("//-----------------------------------------------------------//\n");
	}
	
	public static void main(String[] args)
	{
	    if(args.length == 0)
	    {
	        System.out.println("Please, insert the arguments in the input");
	        System.exit(0);
	    }
	    for (String input: args) {
	    	MathTax.parserShoppingCart(input);
	    	System.out.println("The file: " + input.toString() + " is calculated with success!\n");
	    }
	    }
	}


