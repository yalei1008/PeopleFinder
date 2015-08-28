package model;

import java.math.BigDecimal;

import javax.persistence.EntityManager; 


public class getContent {  
	public static void main(String[] args) {  
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();   
		
		try {    
			
			Microblog content = new Microblog();
			content.setContect("jhasjfjal");
			content.setId("1");
		
			DBUtil.insert(content);
		} 
		
		catch (Exception e){    System.out.println(e);   } 
		
		finally {    em.close();    System.out.println("cerrado!");  
		}  
		} }