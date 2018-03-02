package com.koitt.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {
	
	@Scheduled(cron="0 0/1 * 1/1 * *")	// 1분마다 실행
	public void printTest() {
		System.out.println("1분마다 출력되는 메시지");
	}
	
}