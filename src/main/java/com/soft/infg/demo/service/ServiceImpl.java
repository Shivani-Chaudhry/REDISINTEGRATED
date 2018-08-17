package com.soft.infg.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.infg.dao.MedicineIntDAO;
import com.soft.infg.dao.entity.BeanClass;

@Service
public class ServiceImpl implements ServiceInterface
{
	@Autowired
	private MedicineIntDAO medicineDAO;

	@Override
	public List<BeanClass> getAllMedicine() {
		return medicineDAO.getAllMedicine();
	}
	@Override
	public boolean checkMedicineById(int medicineId) {
		if (medicineDAO.medicineExists1(medicineId)){
			
			return true;
			
		} else {
			return false;
		}
			
		}	
		@Override
		public BeanClass getMedicineById1(int medicineId) {
			BeanClass obj=medicineDAO.getMedicineById(medicineId);
			return obj;
		}

	/*@Override
	public BeanClass getMedicineById(int medicineId) {
		BeanClass obj=medicineDAO.getMedicineById(medicineId);
		return obj;
	}*/
	/*@Override
	public boolean addUser(User user)
	{
	if (medicineDAO.userExists(user.getUsername(), user.getPassword())) {
			return false;
		} else {
			medicineDAO.addUser(user);
			return true;
		}	
		}*/
	
	@Override
	public boolean addMedicine(BeanClass medicine) {
		if (medicineDAO.medicineExists(medicine.getName())) {
			return false;
		} else {
			medicineDAO.addMedicine(medicine);
			return true;
		}
	}




	@Override
	public void updateMedicine(BeanClass medicine) {
		medicineDAO.updateMedicine(medicine);

	}

	@Override
	public void deleteMedicine(int medicineId) {
		medicineDAO.deleteMedicine(medicineId);

	}


}
