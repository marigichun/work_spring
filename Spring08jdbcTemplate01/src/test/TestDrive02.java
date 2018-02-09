package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import model.Department;
import model.Employee;

public class TestDrive02 {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext("/config/config.xml");
		JdbcTemplate template = context.getBean(JdbcTemplate.class);
		
		// 1. 7839 사원에 대한 정보를 Employee 객체에 저장
		String sql = "SELECT * FROM emp WHERE empno = ?";
		Employee emp = template.queryForObject(sql, new MyRowMapper(), 7839);
		System.out.println(emp);
		System.out.println();
		
		// 2.
		sql = "SELECT * FROM emp WHERE deptno = ?";
		List<Employee> empList = template.query(sql, new MyRowMapper(), 10);
		for (Employee item : empList) {
			System.out.println(item);
		}
		System.out.println();
		
		// 3.
		sql = "SELECT * FROM emp WHERE empno = ?";
		emp = template.queryForObject(sql, 
				new BeanPropertyRowMapper<Employee>(Employee.class), 7839);
		System.out.println(emp);
		System.out.println();
		
		// 4.
		sql = "SELECT * FROM emp WHERE deptno = ?";
		empList = template.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class), 10);
		for (Employee item : empList) {
			System.out.println(item);
		}
		System.out.println();
		
		// 5. 테이블 2개를 조인(JOIN)한 결과를 객체에 어떻게 담아야 하는지에 대한 예제
		StringBuilder sql02 = new StringBuilder();
		sql02.append("SELECT * FROM dept d, emp e ");
		sql02.append("WHERE d.deptno = e.deptno ");
		sql02.append("AND d.deptno = ?");
		
		/*
		 * ResultSetExtractor 제네릭에 들어갈 타입은
		 * JOIN 테이블 중 부모 엔티티에 해당하는 클래스 타입을 넣는다.
		 */
		
		/*
		 *  익명 클래스을 생성(Anonymous class):줄번호66~101까지									
		 */													//객체 생성
		ResultSetExtractor<Department> extractor = new ResultSetExtractor<Department>() { //{}이후는 익명클래스 생성

			@Override
			public Department extractData(ResultSet rs) throws SQLException, DataAccessException {
				Department dept = null;
				
				if (rs.next()) {//부서정보 한번만 
					dept = new Department();
					dept.setDeptno(rs.getInt("deptno"));
					dept.setDname(rs.getString("dname"));
					dept.setLoc(rs.getString("loc"));
					
					do {//직원은 다수라 list에 저장해서 반복문에 저장해서 실행
						Employee emp = new Employee();
						emp.setComm(rs.getInt("comm"));
						emp.setDeptno(rs.getInt("deptno"));
						emp.setEmpno(rs.getInt("empno"));
						emp.setEname(rs.getString("ename"));
						emp.setHireDate(rs.getDate("hiredate"));
						emp.setJob(rs.getString("job"));
						emp.setMgr(rs.getInt("mgr"));
						emp.setSal(rs.getInt("sal"));
						dept.getEmployeeList().add(emp);//직원정보를 부서정보 객체에 담아서 
						
					} while (rs.next());//정보을 계속 순서대로로 반복
				}
				
				return dept;//최종적으로 정보를 리턴-->72번 줄로 
			}
		};//익명 클래스 68줄의 ;을 여기서 실행 익명 클래스의 종료 방식이며 독특한 형식--> 꼭 익명 클래스라서가 아님
		
		Department dept = template.query(sql02.toString(), extractor, 10);//71번줄 extractData의 최종적으로 호출
		System.out.println(dept);
		System.out.println();
	}
}

/*
 * RowMapper: SQL문 실행 결과를 VO 객체와 연결시켜
 * VO객체에 결과를 담는 것을 도와주는 인터페이스
 */
class MyRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// 1. VO 객체 생성
		Employee emp = new Employee();
		
		// 2. 생성한 객체에 ResultSet을 이용하여 SQL문 결과를 저장한다.
		emp.setComm(rs.getInt("comm"));
		emp.setDeptno(rs.getInt("deptno"));
		emp.setEmpno(rs.getInt("empno"));
		emp.setEname(rs.getString("ename"));
		emp.setHireDate(rs.getDate("hiredate"));
		emp.setJob(rs.getString("job"));
		emp.setMgr(rs.getInt("mgr"));
		emp.setSal(rs.getInt("sal"));
		
		// 3. 저장한 객체를 리턴한다.
		return emp;
	}
}









