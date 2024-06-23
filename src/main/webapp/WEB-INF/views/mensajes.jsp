<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="col-md-12">
	<div class="col-xs-12 col-sm-offset-3 col-sm-5" style="margin-bottom:10px;">
	<form action="sendMessage" method="post">
		<label>Enviar mensaje</label>
		<div class="form-group">
			<select class="form-control" name="destinatario">
				<c:forEach items="${usuarios}" var="usuario">
					<option value="${usuario.email}">${usuario.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<textarea rows="8" cols="60" name="mensaje"></textarea>
		</div>
		<button type="submit" class="btn btn-primary btn-outline">Enviar mensaje</button>	
	</form>
    </div>
</div>

<c:forEach items="${mensajes}" var="mensaje">
		<div class="col-xs-12 col-sm-offset-2 col-sm-8">
	            <div class="panel panel-default panel-google-plus">
	               
	                <div class="panel-heading">
	                    <h3 style="margin-bottom:15px;">De: ${mensaje.emisor}</h3>
	                    <h3>Para: ${mensaje.destinatario}</h3>
	                    <h5><span>Enviado el </span> - <span>${mensaje.fecha}</span> </h5>
	                </div>
	                <div class="panel-body">
	                	<label>Mensaje:</label><br/>
	                    <p>${mensaje.mensaje}</p>
	                </div>
	            </div>
	        </div>
</c:forEach>ch>