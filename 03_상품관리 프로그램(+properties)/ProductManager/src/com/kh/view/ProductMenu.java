package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {

	Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();
	
	
	public void mainMenu() {
		
		
		while(true) {
			
			System.out.println("\n ===상품을 관리하자!===");
			
			System.out.println("1. 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기");		// 상품 id로 조회 하고 수정
			System.out.println("4. 상품 삭제 하기");		// 상품 id로 조회 하고 삭제
			System.out.println("5. 상품 검색 하기");		// 상품 이름으로 키워드 검색
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print("메뉴를 골라라! : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : selectList(); break;
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 0 : System.out.print("정말로 그만할거야? (y/n) : ");
					 switch(sc.nextLine().toUpperCase()) {
					 case "Y" : System.out.print("그럼 안! 뇽!");return;
					 case "N" : mainMenu(); break;
					 }
			default : System.out.println("번호 다시 확인해!"); break;
			}
			

		}
		

	}

	public void selectList() {
		
		pc.selectList();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//----------------------------------------------------------------------
	
	public void displaySelectList(ArrayList<Product> list) {
		
		System.out.println("\n=== 상품 전체 조회 ==");
		
		for(Product p : list) {
			System.out.println(p);
		}
		
	}
	
	
	public void displayNoData(String message) {
		
		
		System.out.println("조회 성공 여부 : " + message);
		
		
	}
	
	
}
