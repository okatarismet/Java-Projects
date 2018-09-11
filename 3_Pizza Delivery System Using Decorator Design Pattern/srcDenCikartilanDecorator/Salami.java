package PA3;

public class Salami implements Toppings{
	double cost = 3.0;
	String comment = "Salami ";
	Salami(){
		aciklama();
	}
	Salami(Toppings topping){
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
