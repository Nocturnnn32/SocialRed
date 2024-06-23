<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="nav-side-menu" style="margin-top:50px">
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
  
        <div class="menu-list">
  
            <ul id="menu-content" class="menu-content collapse out">
                <li>
                  <a href="wall">
               <i class="fa fa-home fa-lg" aria-hidden="true"></i> Publicaciones
                  </a>
                </li>
                
                <li>
                  <a href="messages">
              		<i class="fa fa-comments fa-lg" aria-hidden="true"></i> Mensajes <span class="label label-danger notifications">
                     ${totalMensajes}</span>
                  </a>
                </li>
				<c:if test="${user.email=='admin@hotmail.com'}">
                 <li>
                  <a href="listadousuarios">
                  	<i class="fa fa-users fa-lg"></i> Usuarios
                  </a>
                </li>
                </c:if>
            </ul>
     </div>
</div>