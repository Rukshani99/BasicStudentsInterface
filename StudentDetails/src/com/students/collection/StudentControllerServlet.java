package com.students.collection;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		try {
			listStudents(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void listStudents(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		StudentDBUtil studentDbUtil = new StudentDBUtil();
		
		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
	ServletException, IOException {
		StudentDBUtil studentDbUtil = new StudentDBUtil();
		String fn= req.getParameter("firstName");
		String ln= req.getParameter("lastName");
		String email = req.getParameter("email");
		Student student = new Student(fn,ln,email);
		studentDbUtil.addStudent(student);
		try {
			listStudents(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}


