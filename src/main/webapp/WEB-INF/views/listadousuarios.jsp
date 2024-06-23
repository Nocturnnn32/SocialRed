<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-md-12">

<c:if test="${!empty message}">
<c:if test="${status==1}">
	<div class="row">
  			<div class="alert alert-success alert-dismissable" >
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			 <strong>${message}</strong>
			</div>
	</div>
</c:if>
<c:if test="${status==0}">
  <div class="row">
  			<div class="alert alert-danger alert-dismissable" >
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			 <strong>${message}</strong>
			</div>
	</div>
</c:if>
</c:if>


<div class="row">
	<div class="table-responsive">
		<table class="table table-hover table-striped">
	<thead>
		<tr>
			<th colspan="2"><h2>Listado de usuarios</h2></th>
			<th style="text-align:center;"><button type="button" id="btn-add-user" class="btn btn-outline btn-primary"><i class="fa fa-plus" aria-hidden="true"></i> Añadir usuario</button></th>
		</tr>
		<tr>
		<th style="background-color:black;color:white;">Nombre</th>
		<th style="background-color:black;color:white;">Correo electrónico</th>
		<th style="background-color:black;color:white;">Acciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="usuario">
			<tr>
				<td>${usuario.nombre}</td>
				<td>${usuario.email}</td>
				<td>
					<a href="admineditaruser?id=${usuario.id}" class="btn btn-success btn-outline"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
					<button type="button" data-id="${usuario.id}" class="btn btn-danger btn-outline admin-delete-user"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	</div>
</div>
</div>
<script>

$( document ).ready(function() {

	$('.admin-delete-user').click(function(){
		var idUsuario = $(this).data("id");
		if (confirm("¿Estás seguro de eliminar la cuenta de este usuario?")){
			$.get( "admindeleteuser?id="+idUsuario, function() {
				location.href="listadousuarios";
			});
		}
	});
	
	$('#btn-add-user').click(function(){
		$('#modal-add-user').modal('show');
	});
	
});

</script>