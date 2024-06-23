<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <!-- Modal edit user -->
<div class="modal fade" id="modal-perfil" role="dialog">
  <div class="modal-dialog">
  
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Editar perfil usuario</h4>
      </div>
      <div class="modal-body">
        <form id="form-user" action="editarusuario" method="post">
       	 <div class="form-group">
		    <label for="edit-nombre">Nombre</label>
		    <input type="text" class="form-control" readonly name="edit-nombre" id="edit-nombre" value="${user.nombre}">
		  </div>
		  <div class="form-group">
		    <label for="edit-mail">Correo electrónico</label>
		    <input type="email" class="form-control" readonly name="edit-email" id="edit-email" value="${user.email}">
		  </div>
		  <div class="form-group">
		    <label for="edit-new-pwd">Contraseña nueva</label>
		    <input type="password" class="form-control" id="edit-new-pwd" name="edit-new-pwd" placeholder="Contraseña nueva">
		  </div>
		  <div class="modal-footer">
      		<button type="button" id="btn-send-edit" class="btn btn-success">Guardar</button>
        	<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      	</div>
		</form>
      </div>

    </div>
  </div>
</div>

 <!-- Modal add user -->
<div class="modal fade" id="modal-add-user" role="dialog">
  <div class="modal-dialog">
  
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Añadir usuario</h4>
      </div>
      <div class="modal-body">
        <form id="form-user" action="adminadduser" method="post">
       	 <div class="form-group">
		    <label for="add-nombre">Nombre</label>
		    <input type="text" class="form-control" name="add-nombre" value="">
		  </div>
		  <div class="form-group">
		    <label for="add-email">Correo electrónico</label>
		    <input type="email" class="form-control" name="add-email" value="">
		  </div>
		  <div class="form-group">
		    <label for="add-pwd">Contraseña</label>
		    <input type="password" class="form-control" id="add-pwd" name="add-pwd">
		  </div>
		  <div class="modal-footer">
      		<button type="submit" class="btn btn-success">Guardar</button>
        	<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      	</div>
		</form>
      </div>

    </div>
  </div>
</div>
  