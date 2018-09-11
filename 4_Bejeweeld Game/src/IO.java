package PA4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class IO {
	/**
	 	*  That method reads a file and creates a map about it.
	 * @param gameGrid the text file which include the map
	 * @return
	 */
		public static Symbol[][] MapCreate(String gameGrid){
			Symbol[][] Main = new Symbol[50][50] ;
			try {
				FileReader r = new FileReader(gameGrid);
				BufferedReader read = new BufferedReader(r);
				String curLine;
				int y = 0;
				while((curLine = read.readLine()) != null) {			
					String[] curLineS = curLine.split(" ");
					for (int x = 0; x < curLineS.length; x++) {	
						Main[y][x] = Symbol.symbolToObj(curLineS[x]);
					}
					y++;
				}	
			} catch (IOException e) {e.printStackTrace();}
			return Main;
	  }
		/**
		*  Prints the map
		* @param Main The array whihc includes the map
		*/
		public static void printMap(Symbol[][] Main) {
			int i = 0,j = 0;
			System.out.printf("Game Grid:\n\n");
			for ( i = 0;Main[i][0] != null ; i++) {
				for (j = 0; Main[i][j] != null ; j++) {
					System.out.printf("%s ",Main[i][j].SYMBOL);
				}
				
				System.out.println();
			}
			System.out.println();
		}
}