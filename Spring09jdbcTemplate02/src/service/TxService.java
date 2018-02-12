package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TxDao;
import model.Job;

@Service				//Service 클래스를 빈 객채로 만들고 싶을때는  @Service 애노테이션 사용, 빈 객체를 만들겠다는 것
@Transactional				//해당 클래스는 트랜젝션 처리를 하겠다는 뜻. SQL을 실행을 유보하다가 적용
public class TxService {
	
	@Autowired
	private TxDao dao;	//-->> new TxDao(); 와 같은  @Service 애노테이션을 시용해서 
	
	//@Transactional				//해당 메소드는 트랜젝션 처리를 하겠다는 뜻. 해당 메소드에만 적용하는 부분. SQL을 실행을 유보하다가 적용
	public void save(Job job) {
		dao.update(job);
		job.setJobId(job.getJobId() + "_");	//첫번째 실행 시 활성화
		//job.setJobId(job.getJobId());		//두번째 실행 시 활성화
		dao.insert(job);
	}
}
