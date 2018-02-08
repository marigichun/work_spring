package bean;

import org.springframework.stereotype.Component;

@Component("engDao")
public class EngMessageDaoImpl implements MessageDao {

	@Override
	public String getMessage() {
		return "Hello. Nice to meet you.";
	}

}
