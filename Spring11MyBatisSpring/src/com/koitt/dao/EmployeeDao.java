package com.koitt.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.model.Employee;

@Repository
public class EmployeeDao {
	
	@Autowired  //빈객체를 연결시키는 애노테이션, config.xml에서 밴 객체를 찾음
	private SqlSessionFactory factory;
	
	public Employee getEmployee(int empno) {
		SqlSession session = factory.openSession();
		Employee emp = session.selectOne("com.koitt.model.Employee.select", empno);
		session.close();
		
		return emp;
		
	}
}
