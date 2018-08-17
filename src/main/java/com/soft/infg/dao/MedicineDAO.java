package com.soft.infg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.soft.infg.dao.entity.BeanClass;




@Transactional
@Repository
public class MedicineDAO implements MedicineIntDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanClass> getAllMedicine() {
		String hql = "FROM BeanClass as atcl ORDER BY atcl.id";
		return (List<BeanClass>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public BeanClass getMedicineById(int medicineId) {
		return entityManager.find(BeanClass.class, medicineId);
	}


	/*@Override
	public void  addUser(User user)
	{
		 entityManager.persist(user);
	}*/
	
	
	@Override
	public void addMedicine(BeanClass medicine) {
		entityManager.persist(medicine);

	}

	@Override
	public void updateMedicine(BeanClass medicine) {
		BeanClass artcl = getMedicineById(medicine.getId());
		artcl.setName(medicine.getName());
		artcl.setPrice(medicine.getPrice());
		artcl.setQuantity(medicine.getQuantity());
		entityManager.flush();

	}

	@Override
	public void deleteMedicine(int medicineId) {
		entityManager.remove(getMedicineById(medicineId));

	}

	@Override
	public boolean medicineExists(String name) {
		String hql = "FROM BeanClass WHERE name = '"+name+"'"; 
		return entityManager.createQuery(hql).getResultList().size() > 0 ? true : false;
	}

	@Override
	public boolean med(int id) {
		String hql = "FROM BeanClass WHERE id = '"+id+"'"; 
		return entityManager.createQuery(hql).getResultList().size() > 0 ? true : false;
	}

	@Override
	public boolean medicineExists1(int id) {
		String hql = "FROM BeanClass WHERE id = '"+id+"'"; 
		return entityManager.createQuery(hql).getResultList().size() > 0 ? true : false;
	}
	/*@Override
	public boolean userExists(String name, String password) {
		// TODO Auto-generated method stub
		return false;
	}*/

	/*@Override
	public boolean userExists(String name,String password) {
		String hql = "FROM User WHERE username = '"+name+"' and password ='"+password+"'"; 
		return entityManager.createQuery(hql).getResultList().size() > 0 ? true : false;
	}*/
}
