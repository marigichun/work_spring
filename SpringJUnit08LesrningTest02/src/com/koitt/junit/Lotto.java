package com.koitt.junit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*public class Pub {
	String name;
	int age;
	
	public Pub() {}
	
	public Pub (String name, int age) {
		this.name = name;
		this.age = age;
	}	
	public Pub (int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public Pub (String name) {
		this.name = name;
	}
	
	public Pub (int age) {
		this.age = age;
	}
}*/

/*public class Lotto	{
	
	public static void main(String [] args) {
		Random rd = new Random();
		Set<Integer> lotto = new HashSet<Integer>();
		while(lotto.size() < 6) {
			Integer num = rd.nextInt(44) + 1;
			lotto.add(num);
		}
		
		for (Integer num : lotto) {
			System.out.println(num + " ");
		}
	}
}*/

/*public class PhoneBook{
	
	public static void main(String[] args) {
	Map<String, String> phoneBook = new HashMap<String, String>();
	phoneBook.put("한국아이티", "010 1122 7777");
	phoneBook.put("제이플라츠", "010 3344 7777");
	
	Scanner input = new Scanner(System.in);
	System.out.println("검색할 이름을 적으세요>>");
	String name = input.nextLine();
	
	System.out.println("입력하신 " + name + " 의 전화번호는 ");
	System.out.println("입력하신 " + phoneBook.get(name) + "입니다.");
	}
}*/

/*public class Lotto	{
	
	public static void main(String [] args) {
		Random rd = new Random();
		Set<Integer> set = new TreeSet<Integer>();// 컬렉션, TreeSet은 오름차순으로 정렬되서 나옴
		
		while(set.size() < 6) {
			int num = rd.nextInt(44) + 1;
			set.add(num);
		}
		
		for (Integer n : set) { // set은 for each문으로 실행하면 편하다.
			System.out.print(n + " ");
		}
	}
}*/

public class Lotto	{
	
	public static void main(String [] args) {
		Random rd = new Random();
		Set<Integer> set = new TreeSet<Integer>();// 컬렉션, TreeSet은 오름차순으로 정렬되서 나옴
		
		while(set.size() < 6) {
			int num = rd.nextInt(45) + 1;
			set.add(num);
		}
		
		for (Integer n : set) { // set은 for each문으로 실행하면 편하다.
			System.out.print(n + " ");
		}
	}
}
