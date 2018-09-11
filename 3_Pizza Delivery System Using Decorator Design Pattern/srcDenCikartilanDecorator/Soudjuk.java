package PA3;

public class Soudjuk implements Toppings{
	double cost = 3.0;
	String comment = "Soudjuk ";
	Soudjuk(){
		aciklama();
	}
	Soudjuk(Toppings topping){
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
