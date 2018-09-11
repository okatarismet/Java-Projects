package PA4;

import java.util.HashMap;

public class MultiThread {
	/**
	 * 
	 * @param Main gameGrid
	 * @param lY location Y
	 * @param lX location X
	 * @param symbol the symbol of the first letter like "D"
	 * @return point
	 */
public static int start(Symbol[][] Main,int lY,int lX,String symbol) {
	int[] directions = null;
	int point = 0;
		boolean type = false;
		String compareType = null;
		if(symbol.equals("D")) {directions = new int[]{1,5,7,9}; type = false;compareType = "D";}
		if(symbol.equals("S")) {directions = new int[]{8,4}; type = false;compareType = "S";}
		if(symbol.equals("T")) {directions = new int[]{2,6}; type = false;compareType = "T";}
		if(symbol.equals("W")) {directions = new int[]{2,6,8,4,1,5,3,7}; type = false;compareType = "type";}
		if(symbol.equals("/")) {directions = new int[]{3,5}; type = true;compareType = "type";}
		if(symbol.equals("-")) {directions = new int[]{8,4}; type = true;compareType = "type";}
		if(symbol.equals("+")) {directions = new int[]{8,4,2,6}; type = true;compareType = "type";}
		if(symbol.equals("\\")) {directions = new int[]{1,7}; type = true;compareType = "type";}
		if(symbol.equals("|")) {directions = new int[]{2,6}; type = true;compareType = "type";}
		 point = Symbol.start(Main, directions, type, lY, lX, compareType);
		 return point;
	}
/**
 *  That methos is writed for  avoiding to use compareTo interface. i tried to do same thing writing a method myself
 * @param ranked HashMap inculding player informations
 * @param rankedRev ranked reverse
 * @param key new players name
 * @param value new players point
 */
public static void blackSort(HashMap<String,Integer> ranked ,HashMap<Integer,String> rankedRev ,String key,int value ) {
		int rankCount = 0;
		int higherInt = 100000;
		String higherName = null;
		int lowerInt  = 100000;
		String lowerName = null;
		for(Integer i:ranked.values()) {
			if(value <= i) {
				rankCount++;
				if((i-value) < lowerInt) {
					lowerInt = (i-value);
					lowerName = rankedRev.get(i);
				}
			}
			if(value > i ) {
				if((value-i) <higherInt) {
					higherInt = (value-i);
					higherName = rankedRev.get(i);
				}
			}
		}
		if(rankCount == ranked.size()) {
			System.out.println("Your rank is "+rankCount+"/"+ranked.size()+", your score is "+lowerInt+" points lower than "+lowerName);	
		}
		else if(rankCount == 0){
			System.out.println("Your rank is "+rankCount+"/"+ranked.size()+", your score is "+higherInt+
					" points higher than "+higherName);
			
		}
		else {
		System.out.println("Your rank is "+rankCount+"/"+ranked.size()+", your score is "+higherInt+
				" points higher than "+higherName+" and "+lowerInt+" points lower than "+lowerName);
		
		}ranked.put(key, value);
		rankedRev.put(value, key);
	}
}
