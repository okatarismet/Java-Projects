package app;
public class Main {
	public static void main(String[] args){		
		Food[] food = new Food[100];
		Sport[] sport = new Sport[100];
		People[] people = new People[50];
		for (int i = 0; i < 50; i++) {
			people[i] = new People();
		}
		for (int i = 0; i < 100; i++) {
			food[i] = new Food();
			sport[i] = new Sport();	
		}
		Reader.foodRead("food.txt",food);
		Reader.sportRead("sport.txt", sport); 
		Reader.peopleRead("people.txt", people);
		Command.command("command.txt", people, food, sport);
	}	// End of main block
}// En dof class block
