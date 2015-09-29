package it.salestaxes;

public abstract class AbstractItem implements Item {

protected Item item;
protected double rate;

public AbstractItem(Item item){
this.item = item;
}

}