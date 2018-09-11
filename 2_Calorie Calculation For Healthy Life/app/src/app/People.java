package app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class People {

int id;
String name;
String gender;
int weight;
int height;
int age;
int dailyCal;
int calTaken;
int calBurn;
/**
 *  a function which returns the daily calorie need
 * @param h the people object
 * @return daily calorie need in int
 */
public static int dailyCal(People h) {
	int result = 0;
	if(h.gender.equals("male")) {
		result =  (int)Math.round(((66 + (13.75 * h.weight)) + (5 * h.height) - (6.8 * h.age)));
		}
	if(h.gender.equals("female")) {
		result = (int) Math.round((665 + (9.6 * h.weight)) + (1.7 * h.height) - (4.7 * h.age));
		}
	return result;
	}
}
