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
			case 2 : insertProduct(); break;
			case 3 : updateProduct(inputPid()); break;
			case 4 : deleteProduct(inputPid()); break;
			case 5 : searchProduct(inputPname());break;
			case 0 : System.out.print("정말로 그만할거야? (y/n) : ");
					 switch(sc.nextLine().toUpperCase()) {
					 case "Y" : System.out.print("그럼 안! 뇽!");return;
					 case "N" : mainMenu(); break;
					 }
			default : System.out.println("번호 다시 확인해!"); break;
			}
			

		}
		

	}
	
	
	

	public String inputPid() {
		System.out.print("상품 아이디 입력! : ");
		return sc.nextLine();
	}
	
	public String inputPname() {
		System.out.print("상품 이름 입력! : ");
		return sc.nextLine();
	}

	public void selectList() {
		
		pc.selectList();
		
	}
	
	
	
	public void insertProduct() {
		
		Product p = new Product();
		
		p.setProductId(inputPid());
		p.setpName(inputPname());
		
		System.out.print("상품 가격 입력! : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		
		System.out.print("상품 상세 정보 입력! : ");
		p.setDescription(sc.nextLine());
		
		System.out.print("재고 입력! : ");
		p.setStock(sc.nextInt());

		pc.insertProduct(p);
		
		
		
	}
	
	
	public void updateProduct(String productId) {
		
		System.out.println("\n== 상품 수정 페이지 ==");
		System.out.println("지금 부터 상품을 수정합니다!\n");
		
		Product p = new Product();
		
		p.setProductId(productId);
		
		System.out.print("상품 가격 수정! : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		
		System.out.print("상품 상세 정보 수정! : ");
		p.setDescription(sc.nextLine());
		
		System.out.print("재고 수정! : ");
		p.setStock(sc.nextInt());
		
		pc.updateProduct(p);
		
	}
	
	
	public void deleteProduct(String productId) {
		
		System.out.println("\n== 상품을 삭제 합니다! ==");
		System.out.println("지금 부터 상품을 삭제 합니다! \n");
		
		pc.deleteProduct(productId);
		
		
	}
	
	
	private void searchProduct(String pName) {
		
		System.out.println("\n== 상품을 검색 합니다! ==");
		System.out.println("지금 부터 상품을 검색 합니다!");
		
		pc.searchProduct(pName);
		
		
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
	
	public void displaySucess(String message) {
		
		System.out.println("성공 여부  : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("실패 여부 : " + message);
	}
	
}
