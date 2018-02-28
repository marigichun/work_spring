package com.koitt.test;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Example {
	
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	public void execute() {
		String message01 =
				this.messages.getMessage("greegitng", null, "hello", Locale.KOREA);
		
		String message02 =
				this.messages.getMessage("argument.required", new Object[] {"Apple"},
						"Banana", Locale.KOREAN);//argument.required가 없을때는 "Bnana"가 출력됨
			System.out.println(message01);
			System.out.println(message02);
	}
}
