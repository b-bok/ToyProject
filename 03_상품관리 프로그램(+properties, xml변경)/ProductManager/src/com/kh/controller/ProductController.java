package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	ProductService ps = new ProductService();
	
	
	public void selectList() {
		
		ArrayList<Product> list = ps.selectList();
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("상품이 아직 등록이 안되었소!");
		}else {
			new ProductMenu().displaySelectList(list);
		}
		
		
	}

	public void insertProduct(Product p) {
		
		int result = ps.insertProduct(p);

		if(result > 0) {
			new ProductMenu().displaySucess(p.getpName() + "상품 추가 성공!");
		}else {
			new ProductMenu().displayFail("상품 추가 실패...");
		}
		
		
	}

	public void updateProduct(Product p) {
		
		int result = ps.updateProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySucess(p.getProductId() + " 수정 완료!");
		}else {
			new ProductMenu().displayFail("상품 수정 실패...");
		}
		
	}

	public void deleteProduct(String productId) {
		
		int result = ps.deleteProduct(productId);
		
		if(result > 0) {
			new ProductMenu().displaySucess(productId + "삭제 완료!");
		}else {
			new ProductMenu().displayFail("상품 삭제 실패...");
		}
		
	}

	public void searchProduct(String pName) {
		
		ArrayList<Product> list = ps.searchProduct(pName);
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData(pName + "과 같은 상품은 없어요..");
		}else {
			new ProductMenu().displaySelectList(list);
		}
		
	}
	
}
