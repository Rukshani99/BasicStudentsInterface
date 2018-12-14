<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web Student Tracker</title>
</head>
<body>
	<!-- ${STUDENT_LIST}-->
	<div id="wrapper">
		<div id="header">
			<h2>ESILV Engineer School</h2>
		</div>
		<div style="clear: both;"></div>
		  <a href="AddStudentServlet">Add New Student</a>
	   </div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempStudent" items="${STUDENT_LIST }">
					<c:url var="EditLink" value="EditStudentServlet">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>
					<c:url var="DeleteLink" value="DeleteStudentServlet">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>
					<tr>
						<td>${tempStudent.firstName}</td>
						<td>${tempStudent.lastName}</td>
						<td>${tempStudent.email}</td>
						<td><a href="${EditLink }"> Edit</a></td>
						<td><a href="${DeleteLink }">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
