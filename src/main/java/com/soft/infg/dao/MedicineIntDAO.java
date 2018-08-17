package com.soft.infg.dao;

import java.util.List;

import com.soft.infg.dao.entity.BeanClass;




public interface MedicineIntDAO {
	List<BeanClass> getAllMedicine();
	BeanClass getMedicineById(int medicineId);
	void addMedicine(BeanClass medicine);
	
	void updateMedicine(BeanClass medicine);
	void deleteMedicine(int medicineId);
	boolean medicineExists( String name);
	boolean med(int id);
/*	boolean userExists(String name,String password);*/
/*	void addUser(User user);*/
	boolean medicineExists1(int id);

	
}
