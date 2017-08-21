<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Todo's for ${name}</title>
</head>
<body>
	<h3>Your Todo's</h3>	
		<table>
			<caption>Your todos are</caption>
				<thead>
					<tr>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is it Done?</th>
					</tr>
				</thead>
				<tbody>
				 	<c:forEach items ="${todos}" var="todo">
				 		<tr>
							<td>${todo.desc}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>						
						</tr>
				 	</c:forEach>
					 
					<tr>
						<td></td>
						<td></td>
						<td></td>						
					</tr>
				</tbody>
		</table>
	 
	 <BR/>
	 <a href="/add-todo">Add a Todo</a>
	 
</body>
</html>