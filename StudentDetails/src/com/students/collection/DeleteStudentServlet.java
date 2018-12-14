package com.students.collection;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DeleteStudentServlet
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int id;

	public DeleteStudentServlet() {
		super();
		// TODO Auto-generated constructor stub}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {

		StudentDBUtil studentDbUtil = new StudentDBUtil();
		try {
			if (request.getParameter("studentId")!=null){
				id = Integer.parseInt(request.getParameter("studentId"));
			}
		} catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}

		Student student= studentDbUtil.fetchStudent(id);
		request.setAttribute("Student", student);
		request.getRequestDispatcher("delete-student.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		StudentDBUtil studentDbUtil = new StudentDBUtil();
		String fn= request.getParameter("firstName");
		String ln= request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(id,fn,ln,email);
		studentDbUtil.deleteStudent(student);
		response.sendRedirect("StudentControllerServlet");
	}

}
