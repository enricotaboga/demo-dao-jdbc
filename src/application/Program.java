package application;

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
		
		

	}

}
