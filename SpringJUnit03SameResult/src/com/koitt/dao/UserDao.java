package com.koitt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.koitt.vo.User;

/*
 * UserDao 테스트 목적
 * 1. 데이터베이스에 정보를 저장할 add 메소드 테스트
 * 2. 저장된 정보를 가져오는 get 메소드 테스트
 */
public class UserDao<PreparedStstement> {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void add(User user) throws SQLException {
		Connection c = this.dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO user(id, name, password) VALUES (?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws SQLException {
		Connection c = this.dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM user WHERE id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	public void delectAll() throws SQLException{
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("DELETE FROM user");
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	@Test	// JUnit에게 테스트용 메소드임을 알려준다.
	public int getCount() throws SQLException{
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM user");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		ps.close();
		rs.close();
		c.close();
		
		return count;
		
	}
}






