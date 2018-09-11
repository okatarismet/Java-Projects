package PA3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class Main {

	public static void main(String[] args){
		try {
			PrintWriter w = new PrintWriter("output.txt", "UTF-8");
			FileReader r = new FileReader(args[0]);
			BufferedReader read = new BufferedReader(r);
			String curLine;
			while((curLine = read.readLine()) != null) {
				String[] curLineS = curLine.split(" ");
			if(curLineS[0].equals("AddCustomer")) { //adress daha eklenmedi
				Customer customer = new Customer();
				customer.fullString = curLine;
				customer.id = Integer.parseInt(curLineS[1]);
				customer.name = curLineS[2];
				customer.surname = curLineS[3];
				customer.phone = curLineS[4];
				String com = " ";
				for (int i = 5; i < curLineS.length; i++) {
					com = com +" "+ curLineS[i];
				}
				customer.adress = com;
				DAO.cusAdd(customer);
				w.println("Customer "+customer.getId()+" "+customer.getName()+" added");
				System.out.println("Customer "+customer.getId()+" "+customer.getName()+" added");
			}
			if(curLineS[0].equals("RemoveCustomer")) {
				w.println("Customer "+Integer.parseInt(curLineS[1])+""+DAO.cusGetById(Integer.parseInt(curLineS[1])).name+" removed");
				System.out.println("Customer "+Integer.parseInt(curLineS[1])+""+DAO.cusGetById(Integer.parseInt(curLineS[1])).name+" removed");
				DAO.cusRemove(Integer.parseInt(curLineS[1])); 
				DAO.ordRemove(0, Integer.parseInt(curLineS[1]), "C");
			}
			if(curLineS[0].equals("CreateOrder")) {
				Order order = new Order();
				order.ordId = Integer.parseInt(curLineS[1]);
				order.customerId = Integer.parseInt(curLineS[2]);
				DAO.ordAdd(order);
				w.println("Order "+ order.ordId + " created");
				System.out.println("Order "+ order.ordId + " created");
			}
			if(curLineS[0].equals("RemoveOrder")) {
				
				DAO.ordRemove(Integer.parseInt(curLineS[1]),0,"O");
				w.println("Order " + Integer.parseInt(curLineS[1]) + " removed");
				System.out.println("Order " + Integer.parseInt(curLineS[1]) + " removed");
			}
			if(curLineS[0].equals("AddPizza")) {
				String pizzaType= curLineS[2];
				String comment = "";
				for (int i = 2; i < curLineS.length; i++) {
					comment  = comment +" "+curLineS[i] ;
				}
				int id = Integer.parseInt(curLineS[1]);
				Pizza pizza = new Pizza();
				pizza.comment = comment;
				Order.addPizza(id, pizza);
				w.println(pizzaType+" pizza added to order "+id);
				System.out.println(pizzaType+" pizza added to order "+id);
			}
			if(curLineS[0].equals("AddDrink")) {
				Order.addDrink(Integer.parseInt(curLineS[1]));
				w.println("Drink added to order "+Integer.parseInt(curLineS[1]));
				System.out.println("Drink added to order "+Integer.parseInt(curLineS[1]));	
			}
			if(curLine.equals("List Customers")) {
				w.println("Customer List:");
				System.out.println("Customer List:");
				try {
					FileReader r1 = new FileReader("customer.txt");
					BufferedReader read1 = new BufferedReader(r1);
					String curLine1;
					while((curLine1 = read1.readLine()) != null) {
						w.println(curLine1);
						System.out.println(curLine1);	
					}
					r1.close();
					read1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//End of try catch block	
			}
			if(curLineS[0].equals("PayCheck")) {
				Order order= DAO.ordGetById(Integer.parseInt(curLineS[1]));
				w.println("PayCheck for order "+order.ordId);
				System.out.println("PayCheck for order "+order.ordId);
				int i = 0;
				int totcost = 0;
				while(order.orders[i] != null) {
					w.println("\t"+order.orders[i]+" "+(int)Pizza.cost(order.orders[i])+"$");
					System.out.println("\t"+order.orders[i]+" "+(int)Pizza.cost(order.orders[i])+"$");
					totcost += Pizza.cost(order.orders[i]);
				i++;
				}
				if(order.softDrink) {
					System.out.println("\tSoftDrink 2$");
					w.println("\tSoftDrink 2$");
					totcost += 2;
				}
				w.println("\tTotal: "+totcost+"$");
				System.out.println("\tTotal: "+totcost+"$");
			}
		}	
			w.close();r.close();read.close();} catch (IOException e) {e.printStackTrace();}//End of try catch block
	}//main sonu
}