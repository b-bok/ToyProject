package com.bbok.controller;

import java.util.ArrayList;

import com.bbok.model.dao.BookDao;
import com.bbok.model.vo.Book;
import com.bbok.view.BookMenu;

public class BookManager {

	public void insertBook(Book book) {
		
		int result = new BookDao().insertBook(book);
		
		if (result > 0) {
			
			new BookMenu().displaySucess("데이터 생성 성공!");
			
		}else {
			
			new BookMenu().displayFail("데이터 생성 실패...");
		}
		
	}

	
	public void deleteBook(String bookTitle) {
		
		int result = new BookDao().deleteBook(bookTitle);
		
		
		if(result > 0) {
			new BookMenu().displaySucess("책 삭제 성공!");
		} else {
			new BookMenu().displayFail("책 삭제 실패...");
		}
		
	}
	
	public void searchBook(int menu, String search) {
		
		ArrayList<Book> list = new BookDao().searchBook(menu,search);
		
		
		if(list.isEmpty()) {
			
			new BookMenu().displayNoData("조회 결과가 없어요...");
			
		}else {
			new BookMenu().displaySelectList(list);
			
		}
		
	}
	
	public void selectList() {
		ArrayList<Book> list = new BookDao().selectList();
		
		if(list.isEmpty()) {
			new BookMenu().displayNoData("책이 없는 뎁쏭?");
		} else {
			new BookMenu().displaySelectList(list);
		}
	}
	
}
