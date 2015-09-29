package it.salestaxes;

public interface Item {
	
String getName();
double getInitPrice();
double getPrice();
boolean isImported();
boolean isExempt();

}