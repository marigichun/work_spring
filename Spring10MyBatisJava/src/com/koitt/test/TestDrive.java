package com.koitt.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.koitt.model.Employee;

public class TestDrive {
	
	public static void main(String[] args) {
		//1.MyBatis 설정 파일 경로 저장
		String resource = "com/koitt/config/mybatis.xml";
		
		try {
			//2. 경로에 있는 mybatis.xml 파일 불러오기
			Reader reader = Resources.getResourceAsReader(resource);
			
			//3. MyBatis의  Sqlsession객체를 가져오기 위한 과정
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(reader, "development");//"development"안의 내용을 사용(mybatis.xml의 <environment id="development">데이터베이스의 환경 설정)
			SqlSession session = factory.openSession();
			
			/*
			 * 4.SqlSession 객체를 이용하여 SQL문 실행(결과가 하나일 경우에 selectOne을 사용)
			 * selectOne:select SQL문 사용할 때, select 결과가 하나의 행인 경우
			 * -첫번째 파라미터: mapper의 namespace + select 엘리먼트의 id 값
			 * -두번째 파라미터: select 앨리먼트로 전달할 값
			 */																	//parameter int 값
			Employee emp = session.selectOne("com.koitt.model.Employee.select", 7698);//mapper.xml의 <mapper namespace="com.koitt.model.Employee">의 28~30번줄사용
			Employee emp2 = session.selectOne("com.koitt.model.Employee.select2", 7698);//mapper.xml의 32~34줄 사용
			
			//5.사용 후 객체 정리
			session.close();
			
			//6.SQL문 실행 후 결과 출력
			System.out.println(emp);
			System.out.println(emp2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






