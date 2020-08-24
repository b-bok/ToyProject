package com.bbok.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.bbok.controller.BookManager;
import com.bbok.model.vo.Book;

public class BookMenu {

	Scanner sc = new Scanner(System.in);
	BookManager bm = new BookManager();

	public void mainMenu() {

		while (true) {

			System.out.println("\n **** 도서 관리 프로그램 ****");

			System.out.println("1. 새 도서 추가");
			System.out.println("2. 도서 삭제");
			System.out.println("3. 도서 검색 출력");
			System.out.println("4. 전체 출력");
			System.out.println("0. 끝내기");

			System.out.print("메뉴 번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				insertBook();
				break;
			case 2:
				deleteBook();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				bm.selectList();
				break;
			case 0:
				System.out.println("프로그램 종료!");
				return;
			default:
				System.out.println("번호 다시 누르세요!");
				return;
			}

		}

	}

	public String inputBookTitle() {

		System.out.print("책 이름을 입력하세요 ! : ");
		return sc.nextLine();

	}

	public int inputCategory() {
		System.out.print("도서 장르(1:인문 / 2: 자연과학/ 3 : 의료 / 4: 기타): ");

		return sc.nextInt();
	}

	public String inputAuthor() {

		System.out.print("도서 저자 : ");
		return sc.nextLine();
	}

	public String inputCategoryName() {

		int num = inputCategory();
		String gern = "";
		switch (num) {
		case 1:
			gern = "인문";
			break;
		case 2:
			gern = "자연과학";
			break;
		case 3:
			gern = "의료";
			break;
		case 4:
			gern = "기타";
			break;

		}

		return gern;

	}

	public void insertBook() {

		
		  System.out.print("책 이름을 입력하세요 ! : "); 
		  String title = sc.nextLine();
		  
		  System.out.print("도서 장르(1:인문 / 2: 자연과학/ 3 : 의료 / 4: 기타): "); 
		  int category = sc.nextInt(); 
		  sc.nextLine();
		  
		  System.out.print("도서 저자 : "); 
		  String author = sc.nextLine();
		  
		  Book book = new Book(title,category,author);
		 


		bm.insertBook(book);

	}

	public void deleteBook() {

		System.out.println("\n == 도서를 삭제 합니다 ==");

		bm.deleteBook(inputBookTitle());

	}

	public void searchBook() {

		System.out.println("\n == 키워드 검색도 가능합니다! ==");

		while (true) {
			System.out.println("\n1. 도서명으로 검색하기");
			System.out.println("2. 카테고리로 검색하기(1:인문 / 2: 자연과학/ 3 : 의료 / 4: 기타) : ");
			System.out.println("3. 작가로 검색하기");
			System.out.println("0. 메인메뉴로 돌아가기");
			System.out.print("번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				bm.searchBook(menu, inputBookTitle());
				break;
			case 2:
				bm.searchBook(menu, inputCategoryName());
				break;
			case 3:
				bm.searchBook(menu, inputAuthor());
				break;
			case 0:
				mainMenu();
				break;
			default:
				System.out.println("번호를 다시 입력해 주세요");
				break;
			}

		}

	}

//==========================================================================

	public void displaySucess(String message) {
		System.out.println("성공 여부 : " + message);
	}

	public void displayFail(String message) {
		System.out.println("실패 여부 : " + message);
	}

	public void displaySelectList(ArrayList<Book> list) {

		System.out.println("\n 책을 조회합니다!");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	public void displayNoData(String message) {
		System.out.println("조회 결과 :" + message);
	}
}
