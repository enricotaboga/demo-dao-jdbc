package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department depart = new Department(1, "Books");
		
		Seller seller = new Seller(1, "Bob", "bob@gmail.com", new Date(), 3000.00, depart);
		
		System.out.println(depart);
		
		System.out.println(seller);

	}

}
