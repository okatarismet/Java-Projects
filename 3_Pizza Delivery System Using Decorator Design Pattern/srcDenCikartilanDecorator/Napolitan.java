package PA3;

public class Napolitan extends Pizza{
	double cost = 10.0;
	String comment = "Napolitan";
	Napolitan(){
		aciklama();
	}
	Napolitan(Toppings toppings){
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
