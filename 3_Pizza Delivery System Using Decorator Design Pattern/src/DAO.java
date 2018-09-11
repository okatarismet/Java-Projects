package PA3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DAO {
	static void cusAdd(Customer customer) {
		try {
			FileReader r = new FileReader("customer.txt");
			BufferedReader read = new BufferedReader(r);
			String curLine;
			int position = 0;
			while((curLine = read.readLine()) != null) {
				if(customer.id <= Integer.parseInt(curLine.split(" ")[0])) {
					break;
				}
				else {
					position++;				
				}
			}
			Path path = Paths.get("customer.txt");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			String extraLine = customer.getId() +" "+ customer.getName() +" "+ customer.getSurname() +" "+ customer.getPhone() +" Adress: "+ customer.getAdress();  
			lines.add(position, extraLine);
			Files.write(path, lines, StandardCharsets.UTF_8);
			r.close();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
	}
	static void cusRemove(int id){
		try {
			FileReader r = new FileReader("customer.txt");
			BufferedReader read = new BufferedReader(r);
			String curLine;
			int position = 0;
			while((curLine = read.readLine()) != null) {
				if(id == Integer.parseInt(curLine.split(" ")[0])) {
					break;
				}
				else {
					position++;				
				}
			}
			Path path = Paths.get("customer.txt");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			lines.remove(position);
			Files.write(path, lines, StandardCharsets.UTF_8);
			r.close();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
	}
	static Customer cusGetById(int id) {
		Customer customer = new Customer();
		try {
			FileReader r = new FileReader("customer.txt");
			BufferedReader read = new BufferedReader(r);
			String curLine;
			while((curLine = read.readLine()) != null) {
				String curLineS[] = curLine.split(" ");
				if(id == Integer.parseInt(curLineS[0])) {
					customer.fullString = curLine;
					customer.setId(id);
					customer.setName(curLineS[1]);
					customer.setSurname(curLineS[2]);
					customer.setPhone(curLineS[3]);
					customer.setAdress(curLineS[4]);
					break;
				}
			}
			r.close();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
		return customer;
	}
	static void ordAdd(Order order) {
		try {
			FileReader r = new FileReader("order.txt");
			BufferedReader read = new BufferedReader(r);
			String curLine;
			int position = 0;
			boolean cek = true;
			while((curLine = read.readLine()) != null && cek) {
				String curLineS[] = curLine.split(" ");
				if(curLineS[0].equals("Order:") && order.ordId <= Integer.parseInt(curLineS[1].trim())) {
						cek = false;
						break;
					}
					else {
						position++;				
					}
			}
			Path path = Paths.get("order.txt");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			if(order.softDrink == true) {
				lines.add(position, "softdrink");
			}
			for (int i = 0;(order.orders[i] != null) && i < order.orders.length; i++) {
				lines.add(position, order.orders[i]);				
			}
			int i = 0;
			if(i<1) {
			lines.add(position,"Order: "+order.ordId +" "+ order.customerId);
			}
			
			Files.write(path, lines, StandardCharsets.UTF_8);
			r.close();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
	}
	static void ordRemove(int id,int cusid,String type) {
		try {
			FileReader r = new FileReader("order.txt");
			BufferedReader read = new BufferedReader(r);
			String curLine;
			int position = 0;
			int addition = 0;
			boolean adCheck = false;
			while((curLine = read.readLine()) != null) {
				String curLineS[] = curLine.split(" ");
				if(adCheck) {
					addition++;
				}
				if(adCheck && curLineS[0].equals("Order:")) {
					break;
				}
				if(type.equals("O")) {
					if(curLineS[0].equals("Order:") && id == Integer.parseInt(curLineS[1].trim())) {adCheck = true;}
				}
				if(type.equals("C")) {
					if(curLineS[0].equals("Order:") && cusid == Integer.parseInt(curLineS[2].trim())) {adCheck = true;}
				}
				else {
						if(!adCheck) {
							position++;											
						}
					}
			}
			Path path = Paths.get("order.txt");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			for (int i = 0; i < addition+1; i++) {
				lines.remove(position);
			}
			Files.write(path, lines, StandardCharsets.UTF_8);
			r.close();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
	}
	static Order ordGetById(int id) {
		Order order = new Order();
		try {
			FileReader r = new FileReader("order.txt");
			BufferedReader read = new BufferedReader(r);
			String curLine;
			boolean cek = true;
			int i = 0;
			while((curLine = read.readLine()) != null) {
				String curLineS[] = curLine.split(" ");
				if(curLineS[0].equals("Order:") && id == Integer.parseInt(curLineS[1])) {
					order.ordId = Integer.parseInt(curLineS[1]);
					order.customerId = Integer.parseInt(curLineS[2]);
					while(cek) {
						curLine= read.readLine();
						if(curLine != null && curLine.equals("softdrink")) {
							order.softDrink = true;
							cek = false;
						}
						else if(curLine != null && !curLine.split(" ")[0].equals("Order:")) {
							order.orders[i] = curLine;
							i++;
						}
						else {
							break;
						}
					}
					break;
				}
			}
			r.close();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//End of try catch block
		return order;
	}
}