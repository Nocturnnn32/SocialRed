<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
	<script src="resources/js/index.js"></script>
	<script type="text/javascript" src="resources/js/password.js"></script>

	<title>redSocial</title>
</head>
<body>
<div class="container">
    	<div class="row">
    		<div class="col-md-6 col-md-offset-3">
    			<c:if test="${!empty message}">
    				<div class="alert alert-danger alert-dismissable">
					  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					 <strong>${message}</strong>
					</div>
   				 </c:if>
    		</div>
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Iniciar sesión</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Registro</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="login" method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="username" tabindex="1" class="form-control" placeholder="Correo Electronico" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Contraseña">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="submit" tabindex="4" class="form-control btn btn-primary">Entra</button>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="viewRecordarPass" tabindex="5" class="forgot-password">Recordar Contraseña</a>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
							<div class="col-lg-12 register-form">
								<form id="register-form" action="registrar" method="post" class="validate" role="register" style="display: none;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Nombre" value="" required>
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Correo Electrónico" value="" required>
										<div id="error-email">
									
										</div>
									</div>
									<div class="form-group" id="#pwd-container">
										<input type="password" id="password-register" name="password-register" tabindex="2" class="form-control" placeholder="Contraseña" required>
										<div class="pwstrength_viewport_progress"></div>
									</div>
									<div class="form-group">
										<input type="password" id="confirm-password" name="confirm-password" tabindex="2" class="form-control" placeholder="Confirmar contraseña" required>
									</div>
									<div id="status">
									
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="submit" id="register-submit" tabindex="4" class="form-control btn btn-register" disabled>Registrar</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

