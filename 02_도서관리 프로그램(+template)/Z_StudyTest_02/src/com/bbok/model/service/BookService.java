package com.bbok.model.service;

import java.sql.*;
import java.util.ArrayList;

import com.bbok.common.JDBCTemplate;
import com.bbok.model.dao.BookDao;
import com.bbok.model.vo.Book;

public class BookService {

	public int insertBook(Book book) {
		
		int result = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		result = new BookDao().insertBook(conn,book); 
		
		JDBCTemplate.close(conn);
		
		
		
		return result;
	}

	public int deleteBook(String bookTitle) {
		
		int result = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		result = new BookDao().deleteBook(conn,bookTitle);
				
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}

	public ArrayList<Book> searchBook(int menu, String search) {
		

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Book> list = new BookDao().searchBook(conn, menu, search);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Book> selectList() {
		
		ArrayList<Book> list = new ArrayList<>();
		
		Connection conn = JDBCTemplate.getConnection();
		
		list = new BookDao().selectList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

}
