package com.bbok.model.dao;

import java.sql.*;
import java.util.ArrayList;

import com.bbok.common.JDBCTemplate;
import com.bbok.model.vo.Book;

public class BookDao {

	public int insertBook(Connection conn, Book book) {

		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO TB_BOOK VALUES (SEQ_BNO.NEXTVAL, ? , ? , ?)";
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getCategory());
			pstmt.setString(3, book.getAuthor());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
			
		}
		
		
		return result;
	}

	public int deleteBook(Connection conn, String bookTitle) {
	
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM TB_BOOK WHERE TITLE = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bookTitle);
			
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
			
		}
		

		return result;
	}

	public ArrayList<Book> searchBook(Connection conn, int menu, String search) {
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		
		
		if(menu == 1) {
			
			try {
				
				sql = "SELECT * FROM TB_BOOK WHERE TITLE LIKE '%' || ? || '%'";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, search);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Book(rset.getInt("bno")
									, rset.getString("title")
									, rset.getInt("category")
									, rset.getString("author"))
							);
					
				}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
				
			}
			
			
			
		} else if(menu == 2) {
			
			sql = "SELECT BNO, TITLE, CATEGORY, AUTHOR "
					+ "FROM TB_BOOK "
					+ "JOIN TB_CATEGORY ON(CATEGORY = CATEGORY_NO)"
					+ "WHERE CATEGORY_NAME = ? ";
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, search);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
						list.add(new Book(rset.getInt(1)
										, rset.getString(2)
										, rset.getInt(3)
										, rset.getString(4)
										 )
								);
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
				
			}

		} else {
			
			try {
				
				sql = "SELECT * FROM TB_BOOK WHERE AUTHOR LIKE '%' || ? || '%'";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, search);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Book(rset.getInt(1)
									, rset.getString(2)
									, rset.getInt(3)
									, rset.getString(4)
									 )
							);
				}
			
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
				
			}

			
		}
		
		
		return list;
	}

	public ArrayList<Book> selectList(Connection conn) {
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = "SELECT * FROM TB_BOOK";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Book(rset.getInt(1)
									, rset.getString(2)
									, rset.getInt(3)
									, rset.getString(4)
								  )
						);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		

		return list;
	}

}
