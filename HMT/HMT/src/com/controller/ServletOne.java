package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAOOperationImpl;
import com.model.User;


public class ServletOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.sendRedirect("Register.jsp");
		String email=request.getParameter("email");
		String password=request.getParameter("psw");
		
		System.out.println(email);
		System.out.println(password);
		
		
		
		User user=new User();
		user.setEmail(email);
		user.setPassword(password);
		
		DAOOperationImpl daoImpl=new DAOOperationImpl();
		String returnedValue=daoImpl.authenticateUser(user);
		
		if(returnedValue.equals("welcome"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("username", email);
			RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
			 rd.forward(request, response);  
			//response.sendRedirect("Home.jsp");
		}
		else
		{
			response.sendRedirect("Login.jsp");
		}
	}

		
		
	}

	


