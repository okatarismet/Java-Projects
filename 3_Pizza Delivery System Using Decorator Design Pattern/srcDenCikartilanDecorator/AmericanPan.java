package PA3;

public class AmericanPan extends Pizza{
	double cost = 5.0;
	String comment = "AmericanPan ";
	int american;
	AmericanPan(){
		aciklama();
	}
	AmericanPan(Toppings toppings){
		cost += toppings.cost();
		comment +=toppings.aciklama();
	}
	public String aciklama() {
		return comment;
	}
	public double tutar() {
		return cost;
	}

}
