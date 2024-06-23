<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-12 content-container">
    <div class="col-xs-12 col-sm-offset-3 col-sm-5 post-form-container">
        <form action="publicar" method="post">
            <label>¿Qué estás pensando?</label>
            <div class="form-group">
                <textarea class="form-control" rows="5" name="mensaje" placeholder="Escribe tu mensaje..."></textarea>
            </div>
            <button type="submit" class="btn btn-primary btn-outline">Publicar</button>
        </form>
    </div>
</div>

<c:forEach items="${publicaciones}" var="publicacion">
    <div class="col-xs-12 col-sm-offset-2 col-sm-8">
        <div class="panel panel-default panel-google-plus">
            <c:if test="${publicacion.email==user.email || user.email=='admin@hotmail.com'}">
                <div class="dropdown edit-delete-buttons">
                    <a href="editarpublicacion?id=${publicacion.idPublicacion}" class="btn btn-link"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                    <button type="button" class="btn btn-link delete-publicacion" data-id="${publicacion.idPublicacion}"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button>
                </div>
            </c:if>
            <div class="panel-heading">
                <h3>${publicacion.nombre}</h3>
                <h5>Publicado el - ${publicacion.fecha}</h5>
            </div>
            <div class="panel-body">
                <p>${publicacion.mensaje}</p>
                <div class="row">
                    <div class="col-md-12">
                        <c:forEach items="${respuestas[publicacion.idPublicacion]}" var="respuesta">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12">
                                    <div class="panel panel-default panel-google-plus">
                                        <div class="panel-heading">
                                            <h3 class="inline">${respuesta.nombre} - </h3><span>${respuesta.fecha}</span>
                                        </div>
                                        <div class="panel-body">
                                            <p>${respuesta.mensaje}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <form action="darlike" method="post" class="like-form">
                    <button type="submit" class="btn btn-default">
                        <i class="fa fa-thumbs-o-up" aria-hidden="true"></i> 
                        <span class="like-count">(0)</span>
                    </button>
                    <input type="hidden" name="like-publicacion" value="4">
                </form>
            </div>

            <div class="panel-footer">
                <div class="input-placeholder">Agregar un comentario...</div>
            </div>
            <div class="panel-google-plus-comment">
                <div class="panel-google-plus-textarea">
                    <form action="comentar" method="post">
                        <textarea class="form-control" rows="4" name="respuesta" placeholder="Escribe tu comentario..." style="height: 100px;"></textarea><br>
                        <input type="hidden" name="respuesta-publicacion" value="${publicacion.idPublicacion}">
                        <button type="submit" class="btn btn-success">Comentar</button>
                        <button type="reset" class="btn btn-default">Cancelar</button>
                    </form>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:forEach>

<style>
    .content-container {
        margin-top: 20px;
    }
    .post-form-container {
        margin-bottom: 20px;
        padding: 20px;
        background-color: #e0f7fa; /* Azul pálido */
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .post-form-container label {
        font-weight: bold;
        color: #333;
    }
    .panel-google-plus {
        margin-bottom: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        background-color: #e0f7fa; /* Azul pálido */
    }
    .panel-google-plus .panel-heading {
        background-color: #BFF4FF;
        color: #fff;
        padding: 10px 15px;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
    }
    .panel-google-plus .panel-body {
        padding: 15px;
    }
    .panel-google-plus .panel-footer {
        background-color: #f8f9fa;
        padding: 10px 15px;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
    }
    .input-placeholder {
        color: #aaa;
        font-style: italic;
    }
    .edit-delete-buttons {
        float: right;
        margin-top: 10px;
    }
    .edit-delete-buttons .btn-link {
        color: #333;
    }
    .edit-delete-buttons .btn-link:hover {
        color: #007bff;
    }
    .btn-outline {
        border: 1px solid #007bff;
        background-color: transparent;
        color: #007bff;
    }
    .btn-outline:hover {
        background-color: #007bff;
        color: #fff;
    }
    .inline {
        display: inline;
    }
</style>
