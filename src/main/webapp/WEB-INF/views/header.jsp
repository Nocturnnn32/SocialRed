<nav>
    <div class="col-md-12" style="position: fixed; z-index: 100; width: 100%;">
        <div class="row">
            <nav class="navbar navbar-inverse" style="margin-bottom: -20px; background: #337ab7;">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand"><a><img src="resources/img/sis.ico" alt="logo" style="height: 70px; width: 70px;"></a> <span style="font-size: 35px;color: #ffffff;">SocialRed</span></a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#"  class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" id="perfil-user" aria-expanded="true" style="color: #04ec34;font-size: 30px;">
                                <span class="glyphicon glyphicon-user"></span> 
                                ${user.nombre} 
                                <span class="caret"></span>
                            </a>
                                <ul style="min-width:250px; font-size: 20px;" class="dropdown-menu" aria-labelledby="perfil-user">
                                <li><label>Nombre: ${user.nombre}</label></li>
                                <li><label>Email: ${user.email}</label></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#" id="showPerfil" style="font-size: 20px; color: #d093f6"><i class="fa fa-pencil" aria-hidden="true"></i> Editar perfil</a></li>
                                <c:if test="${user.email!='admin@hotmail.com'}">
                                    <li><a href="borrarusuario" style=" color: #d093f6; font-size: 20px;"><i class="fa fa-trash-o" aria-hidden="true"></i> Borrar cuenta</a></li>
                                </c:if>
                            </ul>
                        </li>
                        <li><a href="logout"><span class="glyphicon glyphicon-log-in" style="color: red; font-size: 30px;">.Salir</span> </a></li>
                    </ul>   
                </div>
            </nav>
        </div>
    </div>
</nav>