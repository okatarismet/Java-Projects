package PA3;



public class Order {
	int ordId,customerId,totalCost;
	boolean softDrink;
	public String[] orders = new String[12];
	public double[] costs = new double[12];
	public static void addDrink(int id) {
		Order order = new Order();
		order =  DAO.ordGetById(id);
		order.softDrink = true;
		DAO.ordRemove(id,0,"O");
		DAO.ordAdd(order);
	}
	public static void addPizza(int id,Pizza pizza) {

		Order order = new Order();
		order = DAO.ordGetById(id);
		boolean cek = true;
		for (int i = 0; cek && i <order.orders.length; i++) {
			if(order.orders[i] == null) {
					order.orders[i] = pizza.comment;
					double cost= 0;
					order.costs[i] = cost;
				cek = false;
				break;
			}
		}
		order.totalCost += pizza.cost;
		DAO.ordRemove(id,0,"O");
		DAO.ordAdd(order);
	}
	
}