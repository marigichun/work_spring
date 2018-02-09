package bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestDrive {

	@SuppressWarnings("resource")
	public static void main(String[] args, Object name) {
		//1.스프링 설정 파일 가져오기
		ApplicationContext context = 
				new GenericXmlApplicationContext("/config/config.xml");
		
		//2.ApplicationContext 객체 이용하여 PersonService 객체 만들기
		PersonService service = context.getBean("service", PersonService.class);
		
		//3.만든 객체로 2-2에서 구현한 메소드 만들기
		service.printName();
	}
	
	/*
	 * config.xml설정 파일 만들고
	 * xml방식으로 autowire설정하기
	 * (Spring06DIXml예제 참고)
	 * 
	 * config.xml설정으로 1-3의 생성자를 이용해서 name값 초기화 
	 * (Sring03GetBean예제 참고
	 */
}
