package com.kh.model.service;

import java.sql.Connection;
import java.util.*;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductService {

	public void selectList() {
		
		Connection conn = null;
		
		ArrayList<Product> list = new ProductDao().selectList(conn);
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("상품이 아직 등록이 안되었소!");
		}else {
			new ProductMenu().displaySelectList(list);
		}
		
	}
	
	
	
}
