<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
			integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
			crossorigin="anonymous">
		<title>Students List</title>
	</head>
	
	<body>
		<div class="container">

			<h3>Students List</h3>
			<hr>

			<!-- Add a search form -->

			<form action="/CollegeFest/student/search" class="form-inline">

			<!-- Add a button -->
			<a href="/CollegeFest/student/showFormForAdd" class="btn btn-primary btn-sm mb-3">Add Student</a>

			</form>
			<c:choose>
			<c:when test="${fn:length(Students) > 0}">

				<table class="table table-bordered table-striped">
					<thead class="thead-dark">
						<tr>
							<th>Student Id</th>
							<th>Name</th>
							<th>Course</th>
							<th>Country</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${Students}" var="tempStudent">
							<tr>
								<td><c:out value="${tempStudent.id}" /></td>
								<td><c:out value="${tempStudent.firstName} ${tempStudent.lastName}" /></td>
								<td><c:out value="${tempStudent.course}" /></td>
								<td><c:out value="${tempStudent.country}" /></td>
								<td>
									<!-- Add "update" button/link --> <a
									href="/CollegeFest/student/showFormForUpdate?studentId=${tempStudent.id}"
									class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
									<a
									href="/CollegeFest/student/delete?studentId=${tempStudent.id}"
									class="btn btn-danger btn-sm"
									onclick="if (!(confirm('Are you sure you want to delete this Student?'))) return false">
										Delete </a>

								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				</c:when>
			<c:otherwise>
				No Students. To Add Click on ADD STUDENT.
			</c:otherwise>
		</c:choose>
			
	</div>
</body>

</html>
