<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SocialRed</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="resources/css/wall.css"/>
	<link rel="stylesheet" type="text/css" href="resources/css/menulateral.css"/>
	<link rel="stylesheet" type="text/css" href="resources/css/template.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="resources/js/publicaciones.js"></script>
</head>
<body style="min-height: 600px;">
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<section class="sidebar">
		<jsp:include page="menu.jsp" />
	</section>	
	<div class="container">

	<div class="col-md-12">
		<aside style="margin-left: 180px;min-height: 600px;">
			<div style="margin-top: 100px;">
				<c:if test="${!empty body}">
					<jsp:include page="${body}.jsp" />
	   			</c:if>
			</div>
		</aside>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
 	
	
	
</body>
</html>