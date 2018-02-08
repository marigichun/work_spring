package di;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DaoFactory {
	//dao 클래스들의 이름을 저장하는 Map
	private static Map<String, String> daos = new HashMap<String, String>();
	
	// 1. DaoFactory 클래스가 메모리에 올라가게 되면 실행되는 static 블럭 >> static을 제일 먼저 실행
	static {
		
		try {//2.3.4 Dom Parser을 생성 사용하기 위한 과정.  factory 생성 >의존관계를 떼어내기 위행 
			//2.Dom Parser를 위한 factory 객체 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			//3. Dom Parser를 위한 factory에서  Aom Parser 객체 생성
			DocumentBuilder parser = factory.newDocumentBuilder();
			
			
			//4. Dom Parser를 이용하여 config.xml 파일을 불러온다.>> 파일 내용을 dom객체화
			Document dom = parser.parse(new FileInputStream("./src/di/config.xml"));
			
			//5. 블러온 xml에서 최상위 root 엘리먼트를 가져온다. getDocumentElement>>config.xml의 내용을 가져오는 메소드
			Element root = dom.getDocumentElement();
			
			//6. root 엘리먼트(노드) 아래의 자식 엘리먼트(노드)들을 가져온다. ArrayList인 NodeList를 불러온다.
			NodeList list = root.getChildNodes();
			
			//7.자식 엘리먼트(노드)들을 순회한다.
			for (int i = 0; i < list.getLength(); i++) {
				//8. 만약 해당아이테밍 엘리먼트이라면
				if (list.item(i) instanceof Element) {
					//9.해당 엘리먼트를 가져온다. (list에 객체화된 엘리먼트를 가져옴)
					Element el = (Element) list.item(i);
					
					//10.엘리먼트에 정의된 id속성(Attribute)값을 가져온다.
					String id = el.getAttribute("id");
					
					//11. 엘리먼트 사이의 텍스트 값을 가져온다.        1.<dao id="my"> di.MyUserDao<<내용을 가져온다. </dao>
					String className = el.getTextContent();//2.<dao id="ora">di.OraUserDao<<내용을 가져온다.</dao>순서대로
					
					//12. daos Map에 id값과 className값을 저장한다.
					daos.put(id, className);
					
					//13. 파싱한 id값과 classNmae을 테스트 삼아 출력해본다.
					System.out.println(id + ":" + className);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * getDao는 dao Map에 저장된dao 클래스 이름으로 가져온 뒤
	 * 해당 클래스의 객체를 만들어서 리턴해주는 메소드
	 */
	public static UserDao getDao(String type) {
		/*
		 * 1.typr은 "my"혹은 "ora"값이 들어온다.
		 * 각 해당하는 typy값을 이용하여 dao Map에서  className 값을 가져온다.
		 */
		String className = daos.get(type);
		System.out.println("가져온 클래스 이름: " + className);
		
		try {
			//2. 가져온 클래스 이름으로 클래스의 정보를 가져온다.
			Class<?> daoClass = Class.forName(className);
			
			/*
			 * 3. 가져온 클래스 정보 객체에 있는 newInstance 메소드를 이용하여
			 * 해당 클래스의 객체를 생성한다.
			 */
			Object obj = daoClass.newInstance();
			
			
			//4. 생성한 객체가 UserDao타입인지 확인 후 다운 캐스팅하여 dao를 리턴한다.
			if(UserDao.class.isInstance(obj)) {
				UserDao dao = (UserDao) obj;
				return dao;
			}
		}	catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
