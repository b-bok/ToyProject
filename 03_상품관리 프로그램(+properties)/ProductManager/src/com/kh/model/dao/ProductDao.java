package com.kh.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Product;

import static com.kh.common.JDBCTemplate.*;


public class ProductDao {

	private Properties prop = new Properties();
	
	public ProductDao() {
		
		try {
			prop.load(new FileReader("resources/query.properties"));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public ArrayList<Product> selectList(Connection conn) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Product (rset.getString("product_id")
									, rset.getString("p_name")
									, rset.getInt("price")
									, rset.getString("description")
									, rset.getInt("stock"))
						);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		
		return list;
		
	}
	
	
}
