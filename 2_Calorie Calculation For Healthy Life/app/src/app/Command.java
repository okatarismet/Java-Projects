package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Command {
	/**
	 * A function which executes the commands whichs are readen from command.txt file
	 * @param comText the name of the text file
	 * @param People the people object list
	 * @param Food the food object list
	 * @param Sport the sport object list
	 */
	public static void command(String comText,People[] people,Food[] food,Sport[] sport) {
		try {
			PrintWriter w = new PrintWriter("monitoring.txt", "UTF-8");
			
			
			FileReader r = new FileReader(comText);
			BufferedReader read = new BufferedReader(r);
			String curLine ;
			int[] statPersNum = new int[50];
			Arrays.fill(statPersNum,55);
			int pNumcounter=  0;
			while((curLine = read.readLine()) != null) {
				String[] curLineS = curLine.split("\t");	
						//doldur
						if(curLineS[0].equals("printList")) {
//							Object j;
							for (int j:statPersNum) {
								if(j != 55) {
									w.println(people[j].name + "\t" + people[j].age + "\t" + people[j].dailyCal + "kcal\t" + people[j].calTaken + "kcal\t"+ people[j].calBurn + "kcal\t" + (people[j].calTaken - people[j].calBurn - people[j].dailyCal) + "kcal");								}
							}
						}//End of printlist
						else if(curLineS[0].substring(0,5).equals("print")) {
							for (int j = 0; j < people.length ; j++) {
								String curId = curLine.split("\\(")[1].split("\\)")[0];																
								if(Integer.parseInt(curId) == people[j].id) {
									w.println(people[j].name + "\t" + people[j].age + "\t" + people[j].dailyCal + "kcal\t" + people[j].calTaken + "kcal\t"+ people[j].calBurn + "kcal\t" + (people[j].calTaken - people[j].calBurn - people[j].dailyCal) + "kcal");	
								}
							}//end of for
						}// End of printID
						else if(curLineS[1].charAt(0) == '1') {//food block
							if(curLineS[0].charAt(0) < '0' || curLine.charAt(0) > '9' ) {
								curLineS[0] = curLineS[0].substring(1);
							}
							int curFoodNum = 0; 
							for (int j = 0; j < food.length; j++) {
								if(Integer.parseInt(curLineS[1]) == food[j].id) {
									curFoodNum = j;
									break;
								}							
							}//end of inner for
							for (int k = 0; k < people.length; k++) {
								if(Integer.parseInt(curLineS[0]) == people[k].id) {
									statPersNum[pNumcounter] = k;
									pNumcounter++;									
									people[k].calTaken += Integer.parseInt(curLineS[2]) * food[curFoodNum].cal;
									w.println(people[k].id + "\thastaken\t"+Integer.parseInt(curLineS[2]) * food[curFoodNum].cal+"kcal\tfrom\t"+food[curFoodNum].name);
								}
							}								
							}//End of food block
						else if(curLineS[1].charAt(0) == '2') {//sport block
							int curSportNum = 0;						
							for (int j = 0; j < sport.length; j++) {
								if(Integer.parseInt(curLineS[1]) == sport[j].id) {
									curSportNum = j;			
									break;
								}							
							}//end of inner for
							for (int k = 0; k < people.length; k++) {
								if(Integer.parseInt(curLineS[0]) == people[k].id) {	
									statPersNum[pNumcounter] = k;
									pNumcounter++;
									people[k].calBurn += (Integer.parseInt(curLineS[2]) * sport[curSportNum].cal )/ 60;
									w.println(people[k].id + "\thasburned\t"+(Integer.parseInt(curLineS[2]) * sport[curSportNum].cal )/ 60+"kcal\tthanks\tto\t"+sport[curSportNum].name);
								}
							}
							}//End of sport block						
						w.println("***************");
			}//while i okuyucu
			r.close();
			read.close();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
	}
}
