package PA4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Symbol[][] Main = IO.MapCreate("gameGrid.txt");
		HashMap<String,Integer> ranked = new HashMap<String,Integer>(50);
		HashMap<Integer,String> rankedRev = new HashMap<Integer,String>(50);
		try {
			FileReader r = new FileReader("leaderBoard.txt");
			BufferedReader read = new BufferedReader(r);
			String cur ;
			while((cur = read.readLine()) != null) {
			String[] curs = cur.split(" ");
			ranked.put(curs[0],Integer.parseInt(curs[1]));
			rankedRev.put(Integer.parseInt(curs[1]),curs[0]);
			}
		} catch ( IOException e) {e.printStackTrace();}
		IO.printMap(Main);
		Symbol.gameStart(Main, ranked,rankedRev);

	}
	

}
