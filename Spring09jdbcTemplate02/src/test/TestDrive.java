package test;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import dao.TxDao;

public class TestDrive {
	public static void main(String[] args) {
		//1.스프링 설정 파일 가져오기
		ApplicationContext context =
				new GenericApplicationContext("/config/config.xml");
		
		//2.TxDao 빈 객체 가져오기
		TxDao dao = context.getBean(TxDao.class);
		
		Job job = new Job("A01", "개발자", 200, 10000);
		
		dao
	}
	
}
