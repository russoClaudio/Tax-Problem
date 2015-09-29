package it.salestaxes;

public class ImportTaxShc extends AbstractItem {

	private Item itemToShc;
	final double percentage = 0.05;

	public ImportTaxShc(Item item) {
		super(item);
		this.itemToShc = item;
	}

	double getPercentage() {
		return this.percentage;
	}

	public String getName() {
		return itemToShc.getName();
	}

	public double getInitPrice() {
		return itemToShc.getInitPrice();
	}

	public double getPrice(){
		double salesTax = MathTax.calculateFivePercent(this.item.getInitPrice() * this.getPercentage());
		return MathTax.roundPrice(this.item.getPrice() + salesTax);
	}
	
	public boolean isImported() {
		return itemToShc.isImported();
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof Item) {
			return (((Item) obj).hashCode() == this.hashCode());
		} else
			return false;
	}

	@Override
	public boolean isExempt() {
		return itemToShc.isExempt();
	}
	
	
}