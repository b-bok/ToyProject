package com.kh.controller;

import com.kh.model.service.ProductService;

public class ProductController {
	
	ProductService ps = new ProductService();
	
	public void selectList() {
		
		ps.selectList();
		
	}
	
}
