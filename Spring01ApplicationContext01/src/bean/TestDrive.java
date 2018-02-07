package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestDrive {
	
	public static void main(String[] args) {
		//MyBeean과 TestDrive간의 의존성이 생겼다.
		MyBean bean = new MyBean();
		bean.sayHello();
		
		//의존성이 없게 만들기 위해 Spring의 DI를 사용해보자
		//1.스프링
		ApplicationContext context =
				new GenericXmlApplicationContext("/confkg/applicationContext.xml");
		
		MyBean bean02 = (MyBean) context.getBean("mybean");
		//3.bean01객체를 이용하영 메소드 호출
		bean02.sayHello();
		
	}
}
