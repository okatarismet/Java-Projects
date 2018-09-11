package PA3;

public class HotPepper implements Toppings{
	double cost = 1.0;
	String comment = "HotPepper ";
	HotPepper(){
		aciklama();
	}
	HotPepper(Toppings topping){
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
