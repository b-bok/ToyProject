package com.bbok.model.dao;

import java.sql.*;
import java.util.ArrayList;

import com.bbok.model.vo.Book;

public class BookDao {

	
	public int insertBook(Book book) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO TB_BOOK VALUES (SEQ_BNO.NEXTVAL, ?, ?, ?)";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getCategory());
			pstmt.setString(3, book.getAuthor());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				conn.commit();
			}else {
				
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	
	public int deleteBook(String bookTitle) { //dml => 행 => 트랜잭션
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM TB_BOOK WHERE TITLE = ?";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bookTitle);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}
	
	
	public ArrayList<Book> searchBook(int menu, String search) { // select => reset => 배열 또는 어레이
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		if(menu == 1) {
				sql = "SELECT * FROM TB_BOOK WHERE TITLE LIKE '%' || ? || '%'";
			
			try {
				
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
				
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
				
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					rset.close();
					pstmt.close();
					conn.close();
					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			
		} else if (menu == 2) {
			
	
			
			
				sql = "SELECT BNO, TITLE, CATEGORY, AUTHOR"
						+ " FROM TB_BOOK "
						+ "JOIN TB_CATEGORY ON (CATEGORY = CATEGORY_NO)"
						+ "WHERE CATEGORY_NAME LIKE '%' || ? || '%'";
			
			try {
				
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
				
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
				
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					rset.close();
					pstmt.close();
					conn.close();
					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			
			
		} else {

			
			sql = "SELECT * FROM TB_BOOK WHERE AUTHOR LIKE '%' || ? || '%'";
		
		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
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
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	
		}
		

		return list;
	}
	
	
	public ArrayList<Book> selectList() { // select => rset으로 받기 
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM TB_BOOK";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
					
			while(rset.next()) {
					list.add(new Book (  rset.getInt(1)
									   , rset.getString(2)
									   , rset.getInt(3)
									   , rset.getString(4))
							);
					
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		return list;
	}
	
}
