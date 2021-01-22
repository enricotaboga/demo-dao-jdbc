package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	public Connection conn;
	
	public SellerDaoJDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE seller.Id = ?");
		
		st.setInt(1, id);
		
		rs = st.executeQuery();
		
		if (rs.next()) {
			Department depart = instanciateDepartment(rs);
			Seller seller = instanciateSeller(rs, depart);
			
			return seller;
		}
		
		return null;
		
		}	
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

	private Seller instanciateSeller(ResultSet rs, Department depart) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthday(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(depart);
		
		return seller;
	}

	private Department instanciateDepartment(ResultSet rs) throws SQLException {
		Department depart = new Department();
		depart.setId(rs.getInt("DepartmentId"));
		depart.setName(rs.getString("DepName"));
		return depart;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> listSeller = new ArrayList<Seller>();
		Map<Integer, Department> map = new HashMap<>();
		
		try {
		st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id");
		
		rs = st.executeQuery();
		
		
		
		
		while (rs.next()) {
			Department depart = map.get(rs.getInt("DepartmentId"));
			
			if (depart == null) {
				depart = instanciateDepartment(rs);
				map.put(depart.getId(), depart);
			}
			
			
			Seller seller = instanciateSeller(rs, depart);
			listSeller.add(seller);
		}	
		return listSeller;
		
		
		}	
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<Seller> listSeller = new ArrayList<Seller>();
		Map<Integer, Department> map = new HashMap<>();
		
		try {
		st = conn.prepareStatement("SELECT seller.*,department.Name as DepName  "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE DepartmentId = ? "
				+ "ORDER BY Name");
		
		st.setInt(1, department.getId());
		
		rs = st.executeQuery();
		
		
		while (rs.next()) {
			Department depart = map.get(department.getId());
			
			if (depart == null) {
				depart = instanciateDepartment(rs);
				map.put(department.getId(), depart);
			}
			
			
			Seller seller = instanciateSeller(rs, depart);
			listSeller.add(seller);
		}	
		return listSeller;
		
		}	
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
