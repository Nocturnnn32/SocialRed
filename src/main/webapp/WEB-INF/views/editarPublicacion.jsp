<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-primary">
			 <div class="panel-heading"><h4>Editar publicación</h4></div>
  			<div class="panel-body">
  				<form action="updatepublicacion" method="post">
       	 <div class="form-group">
		    <label for="edit-nombre">Nombre</label>
		    <input type="text" class="form-control" readonly name="update-name" readonly value="${publi.nombre}">
		  </div>
		  <div class="form-group">
		    <label for="edit-mail">Correo electrónico</label>
		    <input type="email" class="form-control" readonly name="update-email" readonly value="${publi.email}">
		  </div>
		  <div class="form-group">
		    <label for="edit-pwd">Fecha</label>
		    <input type="text" class="form-control" readonly name="update-fecha" placeholder="${publi.fecha}">
		  </div>
		  <div class="form-group">
		    <label for="edit-new-pwd">Mensaje</label><br/>
		   <textarea name="update-mensaje" style="width:400px;height:150px;" rows="4" cols="50">${publi.mensaje}</textarea>
		   <input type="hidden" name="idPublicacion" value="${id}">
		  </div>
		  <div class="modal-footer">
      		<button type="submit" class="btn btn-success">Guardar</button>
        	<a href="wall" class="btn btn-default" >Volver</a>
      	</div>
		</form>
  			</div>
	</div>
</div>
