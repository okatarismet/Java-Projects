package PA3;

public class Onion implements Toppings{
	double cost = 2.0;
	String comment = "Onion ";
	Onion(){
		aciklama();
	}
	Onion(Toppings topping){
		cost += topping.cost();
		comment += topping.aciklama();
	}
	public String aciklama() {
		return comment;
	}
	public double cost() {
		return cost;
	}

}
