package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import model.Job;
import service.TxService;

public class TestTx {
	
	public static void main(String[] args) {
		//1.스프인 설정 파일
		ApplicationContext context = 
				new GenericXmlApplicationContext("/config/config.xml");
		
		//2.애플리케이션 컨텍스트를 이용하여 빈 객체 생성
		TxService service = context.getBean(TxService.class);	//type에  빈 객체(@붙은)  @Service
		
		//3.service 객체를 이용하여 Job 객체 내뇽을 데이터베이스에 저장. Service클래스 15~18라인
		service.save(new Job("AAA_", "title", 200, 800));
	}
}
