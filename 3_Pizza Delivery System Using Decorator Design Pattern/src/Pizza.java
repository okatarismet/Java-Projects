package PA3;

import java.util.Arrays;

public class Pizza {
	double cost;
	String comment;
	static final double pepper = 1.0;
	static final double onion = 2.0;
	static final double sucuk = 3.0;
	static final double salam = 3.0;
	static final double americanPan =5.0;
	static final double Napolitan = 10.0;
	static final double softDrink = 2.0;
	public static double cost(String curLine) {
		String[] curLineS = new String[12];
		curLineS = curLine.split(" ");
		double cost = 0;
		Arrays.sort(curLineS);
		if(Arrays.binarySearch(curLineS,"AmericanPan")>0) {cost += Pizza.americanPan;}
		if(Arrays.binarySearch(curLineS,"Neapolitan")>0) {cost += Pizza.Napolitan;}
		if(Arrays.binarySearch(curLineS,"Salami")>0) {cost += Pizza.salam;}
		if(Arrays.binarySearch(curLineS,"Soudjouk")>0) {cost += Pizza.sucuk;}
		if(Arrays.binarySearch(curLineS,"Onion")>0) {cost += Pizza.onion;}
		if(Arrays.binarySearch(curLineS,"HotPepper")>0) {cost += Pizza.pepper;}
		return cost;
	}
}
