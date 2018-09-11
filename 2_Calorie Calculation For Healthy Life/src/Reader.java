package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Reader {
	/**
	 * that method redads lines from text and sets the objects
	 * @param text text which will readen
	 * @param object1 object which will be setted
	 */
	public static void foodRead(String text,Food[] object1) {
		try {
			FileReader r = new FileReader(text);
			BufferedReader read = new BufferedReader(r);
			String curLine ;
			int i = 0;
			while((curLine = read.readLine()) != null) {
					object1[i].id = Integer.parseInt(curLine.split("\t")[0]);
					object1[i].name = curLine.split("\t")[1];
					object1[i].cal = Integer.parseInt(curLine.split("\t")[2]);
					i++;
			}
			r.close();
			read.close();
		} catch ( IOException e) {
		e.printStackTrace();
		}
	}
	public static void sportRead(String text,Sport[] object1) {
		try {
			FileReader r = new FileReader(text);
			BufferedReader read = new BufferedReader(r);
			String curLine ;
			int i = 0;
			while((curLine = read.readLine()) != null) {
					object1[i].id = Integer.parseInt(curLine.split("\t")[0]);
					object1[i].name = curLine.split("\t")[1];
					object1[i].cal = Integer.parseInt(curLine.split("\t")[2]);
					i++;
			}
			r.close();
			read.close();
		} catch ( IOException e) {
		e.printStackTrace();
		}
	}
	public static void peopleRead(String text,People[] object1) {
		try {
			FileReader r = new FileReader(text);
			BufferedReader read = new BufferedReader(r);
			String curLine ;
			int i = 0;
			while((curLine = read.readLine()) != null) {
				if(curLine.charAt(0) < '0' || curLine.charAt(0) > '9' ) {
					curLine = curLine.substring(1);
				}
				String[] curLineSplit = curLine.split("\t");
					object1[i].id = Integer.parseInt(curLineSplit[0]);
					object1[i].name = curLineSplit[1];
					object1[i].gender = curLineSplit[2];
					object1[i].weight = Integer.parseInt(curLineSplit[3]);
					object1[i].height = Integer.parseInt(curLineSplit[4]);
					object1[i].age = (2018 - Integer.parseInt(curLineSplit[5]));
					object1[i].dailyCal = People.dailyCal(object1[i]);
					i++;
			}
			r.close();
			read.close();
		} catch ( IOException e) {
		e.printStackTrace();
		}		
	}
}
