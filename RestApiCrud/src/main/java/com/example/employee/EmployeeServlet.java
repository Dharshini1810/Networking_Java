package com.example.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeServlet extends HttpServlet{
	
	HashMap<Integer,Employee> a = new HashMap<Integer,Employee>();
	int id=0;
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		id++;
		String name=req.getParameter("name");
		String dept=req.getParameter("dept");
		Employee emp = new Employee(id,name,dept);
		a.put(id, emp);
		PrintWriter p = res.getWriter();
		p.println("New Employee Added!Employee Id: "+id);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String action = req.getParameter("action");
		PrintWriter p = res.getWriter();
		if(action.equals("get")) {
			String i = req.getParameter("id");
			int id=Integer.parseInt(i);
			if(a.containsKey(id)) {
				p.println("Id: "+a.get(id).id+"\n"+"Name: "+a.get(id).name+"\n"+"Dept: "+a.get(id).dept);
			}else {
				p.println("Id: "+id+" Not found!");
			}
			
		}	
		else if(action.equals("getAll")){
			for (Entry<Integer, Employee> entry : a.entrySet()) {
	            Integer key = entry.getKey();
	            Employee value = entry.getValue();
	            p.println("Id: "+value.id+"\n"+"Name: "+value.name+"\n"+"Dept: "+value.dept);
	            p.println();
	        }
		}
	} 
	
	@Override 
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String i = req.getParameter("id");
		int id=Integer.parseInt(i);
		PrintWriter p = res.getWriter();
		if(a.containsKey(id)) {
			Employee delData=(Employee)a.remove(id);
			p.println("Id: "+delData.id+" Data Deleted!");
		}else {
			p.println("No data found");
		}
	}
	
	@Override 
	public void doPut(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		BufferedReader reader = req.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
		String[] params = requestBody.toString().split("&");
	    Map<String, String> paramMap = new HashMap<>();
	    for (String param : params) {
	        String[] keyValue = param.split("=");
	        if (keyValue.length == 2) {
	            paramMap.put(keyValue[0], keyValue[1]);
	        }
	    }
		
		String i=paramMap.get("id");
		String name=paramMap.get("name");
		String dept=paramMap.get("dept");
		System.out.println(i+name+dept);
		int id=Integer.parseInt(i);
		Employee emp = new Employee(id,name,dept);
		if(a.containsKey(id)) {
			a.put(id, emp);
			PrintWriter p = res.getWriter();
			p.println("Emp Id: "+id+" modified successfully! "+"New Value: "+"\n"+"Id: "+a.get(id).id+"\n"+"Name: "+a.get(id).name+"\n"+"Dept: "+a.get(id).dept);
		}	
	}
}