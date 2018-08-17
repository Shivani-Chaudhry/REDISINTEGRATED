package com.soft.infg.demo.service;

import java.util.List;

import com.soft.infg.dao.entity.BeanClass;





public interface ServiceInterface {
	List<BeanClass> getAllMedicine();
	BeanClass getMedicineById1(int medicineId);

	boolean addMedicine(BeanClass medicineId);
	void updateMedicine(BeanClass medicine);
	void deleteMedicine(int medicineId);
	boolean checkMedicineById(int medicineId);


}
