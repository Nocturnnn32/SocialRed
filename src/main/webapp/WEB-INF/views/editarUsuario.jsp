<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-primary">
			 <div class="panel-heading"><h4>Editar usuario</h4></div>
  			<div class="panel-body">
  				<form action="updateuser" method="post">
       	 <div class="form-group">
		    <label for="edit-nombre">Nombre</label>
		    <input type="text" class="form-control" name="update-name" value="${usuario.nombre}">
		  </div>
		  <div class="form-group">
		    <label for="edit-mail">Correo electrónico</label>
		    <input type="email" class="form-control"  name="update-email" value="${usuario.email}">
		  </div>
		  <div class="form-group">
		    <label for="edit-pwd">Contraseña</label>
		    <input type="password" class="form-control" name="update-pwd" value="">
		  </div>
		  <div class="modal-footer">
		  	<input type="hidden" name="idUsuario" value="${usuario.id}">
      		<button type="submit" class="btn btn-success">Guardar</button>
        	<a href="listadousuarios" class="btn btn-default" >Volver</a>
      	</div>
		</form>
  			</div>
	</div>
</div>