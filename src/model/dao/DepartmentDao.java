package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	
	void insert(Department depart);
	void update(Department depart);
	void delete(Department depart);
	Department findById(Integer id);
	List<Department> findAll();

}
