package com.soft.infg.security.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soft.infg.dao.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long>{
	Role findRoleByName(String roleName);
}
