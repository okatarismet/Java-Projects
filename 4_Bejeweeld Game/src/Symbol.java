package PA4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Symbol {
	int lX = 0,lY = 0,point = 0;
	String symbol ="X",Symbol_name = "";
	public boolean Math = false;
	HashMap<String,Integer> ranked = new HashMap<String,Integer>(50);
	public String SYMBOL = "B";
	/**
	 * That method will be overridin at al subclasses. It is for assigning the attributes of the jewels
	 * @param string the first letter like "D"
	 * @param Math if it is math sybol or not
	 * @param point how much point will it give.
	 */
	public Symbol(String symbol,boolean Math,int point) {
		SYMBOL = symbol;
		this.point = point;
		this.Math = Math;
	}
	/**
	 *  thats for applying gravity at deleted positions.
	 * @param D vertical location
	 * @param Y horizontal location
	 * @param Main gameGrid
	 */
	public static void gravity(int D, int Y,Symbol[][] Main) {
		while(D-1 >= 0) {
		Main[D][Y] = Main[D-1][Y];
		Main[D-1][Y].lX += 1;
		D--;
			}
		Main[0][Y] = randomGenerator();
			
		
	}
	/**
	 *  Starts the whole program
	 * @param Main The Map
	 * @param directions Which directions it can look at.
	 * @param type if true its mean it is math symbol. If it is false its mean it is other jewels.(used for wildCard)
	 * @param lY y location
	 * @param lX x location 
	 * @param compareType if type its look at type . Otherwise you should enter an Symbol char like "D"
	 */
	public static int start(Symbol[][] Main,int[] directions,boolean type,int lY,int lX,String compareType) {
		for(int i : directions) {
			int curPoint = directionController(i, type, Main, lY, lX,compareType);
			if(curPoint > 0) {
				return curPoint;
			}
		}
		return 0;
	}
	/**
	 *  it generates random objects 
	 * @return a random object
	 */
	public static Symbol randomGenerator() {
		Symbol[] myArray = {symbolToObj("D"),symbolToObj("S"),symbolToObj("T"),symbolToObj("W"),symbolToObj("/"),symbolToObj("-"),
				symbolToObj("+"),symbolToObj("\\"),symbolToObj("|")};
		Random generator = new Random();
		int randomIndex = generator.nextInt(myArray.length);
		return myArray[randomIndex];
	}
	/**
	 *  That method is checking the directions with wanted attributes
	 * @param direction which direction it will be looking for
	 * @param type (if it is math symbol == true) (if it is not math symbol == false)
	 * @param Main the main map
	 * @param lD vertical location
	 * @param lY horizontal location
	 * @param compareType it can be  "type" or first letter of jewel like "D","S","M"
	 * @return s the point
	 */
	public static int directionController(int direction,boolean type,Symbol[][] Main,int lD,int lY,String compareType) {
		int Y1 = 0,Y2 = 0,D1 = 0,D2 = 0;
		int point = 0;
		if(direction == 1) {//tam
			Y1 = -2;
			D1 = -2;
			Y2 = -1;
			D2 = -1;
		}
		if(direction == 2) {
			Y1 = 0;
			D1 = -2;
			Y2 = 0;
			D2 = -1;
		}
		if(direction == 3) {
			Y1 = 2;
			D1 = -2;
			Y2 = 1;
			D2 = -1;
		}
		if(direction == 4) {
			Y1 = 2;
			D1 = 0;
			Y2 = 1;
			D2 = 0;
		}
		if(direction == 5) {
			Y1 = 2;
			D1 = 2;
			Y2 = 1;
			D2 = 1;
		}
		if(direction == 6) {
			Y1 = 0;
			D1 = 1;
			Y2 = 0;
			D2 = 2;
		}
		if(direction == 7) {
			Y1 = -1;
			D1 = +1;
			Y2 = -2;
			D2 = +2;
		}
		if(direction == 8) {
			Y1 = -2;
			D1 = 0;
			Y2 = -1;
			D2 = 0;
		}
		if((lD + D1) >= 0 && (lY + Y1) >= 0 && (lD + D2) >= 0 && (lY + Y2) >= 0 && Main[lD + D1][lY + Y1] != null&& Main[lD + D1][lY + Y1] != null ) {
		if(compareType.equals("type")&&
				Main[lD + D1][lY + Y1].Math == type&&
				Main[lD + D2][lY + Y2].Math == type) {
			gravity(lD+D1,lY+Y1, Main);
			gravity(lD+D2,lY+Y2,Main);
			gravity(lD,lY,Main);
			point += Main[lD + D1][lY + Y1].point;
			point += Main[lD + D2][lY + Y2].point;
			point += Main[lD][lY].point;
			return point;
		}
		else if( 
		   Main[lD + D1][lY + Y1].SYMBOL.equals(compareType)&&
		   Main[lD + D2][lY + Y2].SYMBOL.equals(compareType)) {
				gravity(lD+D1,lY+Y1, Main);
				gravity(lD+D2,lY+Y2,Main);
				gravity(lD,lY,Main);
				point += Main[lD + D1][lY + Y1].point;
				point += Main[lD + D2][lY + Y2].point;
				point += Main[lD][lY].point;
				return point;
		}
		}
		else{return point;}
		return point;
	}
	/**
	 *  it converts first letter String to object and return it
	 * @param symbol like "D","/"
	 * @return Symbol object
	 */
	public static Symbol symbolToObj(String symbol) {
		if(symbol.equals("D")) {return new Diamond();}
		if(symbol.equals("S")) {return new Square();}
		if(symbol.equals("T")) {return new Triangle();}
		if(symbol.equals("W")) {return new Wildcard();}
		if(symbol.equals("/")) {return new Div();}
		if(symbol.equals("-")) {return new Minus();}
		if(symbol.equals("+")) {return new Plus();}
		if(symbol.equals("\\")) {return new CDiv();}
		if(symbol.equals("|")) {return new Line();}
		else {return null;}
		}
	/**
	 * Starts the whole game.
	 * @param Main main map
	 * @param ranked HashMap which inculde players name and points
	 * @param rankedRev reverse of ranked
	 * @return point
	 * @throws IOException 
	 */
	public static int gameStart(Symbol[][] Main,HashMap<String,Integer> ranked,HashMap<Integer,String> rankedRev) {
		Scanner scan = new Scanner(System.in);
		BufferedReader read1 = null;
		int totalPoint = 0;
		int a = 1;
		FileReader r1;
		try {
			r1 = new FileReader("test_case.txt");
			read1 = new BufferedReader(r1);
		} catch (FileNotFoundException e1) {}
		while(true) {
		System.out.printf("Select coordinate or enter E to end the game:\n");
		String curLoc = null;		
		File f  = new File("test_case.txt");	
		String name = null;
		if(f.exists() && !f.isDirectory()) {
		 try {
			curLoc = read1.readLine();
			if(curLoc.equals("E")) {
				System.out.printf("Enter name:");

				name = read1.readLine();
			}
		} catch (IOException e) {e.printStackTrace();}
		}
		else {
			curLoc = scan.nextLine();	
			if (curLoc.equals("E")){
				System.out.printf("Enter name:");
				name = scan.nextLine();
			}
		}
		if(curLoc.equals("E")) {
			String[] text = new String[50];
			String cur;
			
			try {
				FileReader r = new FileReader("leaderboard.txt");
				BufferedReader read = new BufferedReader(r);
				int i = 0;
				while((cur = read.readLine()) != null) {
					text[i] = cur;
					i++;
				}
				text[i] = name+" "+totalPoint;
				read.close();
				PrintWriter w = new PrintWriter("leaderboard.txt", "UTF-8");
				int k = 0;
				while(text[k] != null) {
					w.println(text[k]);
					k++;
				}
				w.close();

			} catch ( IOException e) {e.printStackTrace();}
			MultiThread.blackSort(ranked, rankedRev, name,totalPoint);
			System.out.printf("Good bye!");
			return 0;
		}
		else {
			String[] curLocS = curLoc.split(" ");
			int lY = Integer.parseInt(curLocS[0]);
			int lX = Integer.parseInt(curLocS[1]);
			Symbol symbol = Main[lY][lX];
			int point = MultiThread.start(Main, lY, lX, symbol.SYMBOL);
			totalPoint += point;
			IO.printMap(Main);
			System.out.println("Score : "+totalPoint+" points.");
		}
		}
	}
}
	




















