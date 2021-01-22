package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		System.out.println("=== TEST 1 findById Seller ===");
		System.out.println(seller);
		System.out.println("");
		System.out.println("");
		
		List<Seller> listSeller = sellerDao.findByDepartment(new Department(2, null));
		System.out.println("=== TEST 2 findByDepartment Seller ===");
		for (Seller obj : listSeller) {
			System.out.println(obj);
		}
		
		System.out.println("");
		System.out.println("");
		
		listSeller = sellerDao.findAll();
		System.out.println("=== TEST 3 findAll Seller ===");
		for (Seller obj : listSeller) {
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 4 insert Seller ===");
		Seller newSeller = new Seller(null, "Greg", "greg@mail.com", new Date(), 3000.00, new Department(1, "Computers"));
		sellerDao.insert(newSeller);
		System.out.println("The new seller is " + newSeller.getId());

	}

}
