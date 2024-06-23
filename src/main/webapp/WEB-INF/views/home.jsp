<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- link css -->
    <link rel="stylesheet" href="resources/css/style1.css">
    <!-- link icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css" integrity="sha512-ZnR2wlLbSbr8/c9AgLg3jQPAattCUImNsae6NHYnS9KrIwRdcY9DxFotXhNAKIKbAXlRnujIqUWoXXwqyFOeIQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>redSocial</title>
    <style>
        .header {
            text-align: center;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .header h1 {
            font-family: 'Arial', sans-serif;
            font-size: 48px;
            font-weight: bold;
            color: #333;
        }
        .pwstrength_viewport_progress {
            height: 20px; /* Altura de la barra de progreso */
            background-color: #f0f0f0; /* Color de fondo */
            border-radius: 5px; /* Bordes redondeados */
            overflow: hidden; /* Para recortar el contenido que se desborde */
            margin-top: 10px;
        }
        .progress-bar {
            height: 100%; /* Altura completa */
            width: 0%; /* Ancho inicial */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="box">
            <div class="form sign_in">
                <div class="header">
                    <center><img src="resources/css/logo.png" alt="img" style="height:80px;"></center>
                </div>
                <br><br>
                <h3>Iniciar sesión</h3>
                <span>o usa tu cuenta</span>
                <form action="login" method="post" id="login-form">
                    <div class="type">
                        <input type="email" placeholder="Correo Electrónico" name="username" class="form-control" required>
                    </div>
                    <div class="type">
                        <input type="password" placeholder="Contraseña" name="password" id="password" class="form-control" required>
                    </div>
                 
                    <button type="submit" class="btn bkg">INICIAR SESION</button>
                </form>
            </div>
            <div class="form sign_up">
                <center><img src="resources/css/logo.png" alt="img" style="height:80px;"></center>
                <br><br>
                <h3>Registrarse</h3>
                <span>o utiliza tu correo electrónico para registrarte</span>
                <form id="register-form" action="registrar" method="post" class="validate">
                    <div class="type">
                        <input type="text" name="username" id="username" placeholder="Nombre">
                    </div>
                    <div class="type">
                        <input type="email" name="email" id="email" placeholder="Correo Electrónico">
                        <div id="error-email"></div>
                    </div>
                    <div class="type">
                        <input type="password" id="password-register" name="password-register" placeholder="Contraseña" required>
                        <input type="password" id="confirm-password" name="confirm-password" placeholder="Confirmar contraseña" required>
                    </div>
                    <div class="type" id="pwd-container">
                        <div class="pwstrength_viewport_progress">
                            <div id="password-strength" class="progress-bar" role="progressbar"></div>
                        </div>
                    </div>
                    <div id="status"></div>
                    <button type="submit" id="register-submit" class="btn bkg" disabled>REGISTRARSE</button>
                </form>
            </div>
        </div>
        <div class="overlay">
            <div class="page page_signIn">
                <h3>¡Bienvenido de nuevo!</h3>
                <p>Para seguir con nosotros, inicia sesión con tu información personal.</p>
                <button class="btn btnSign-in">REGISTRARSE <i class="bi bi-arrow-right"></i></button>
            </div>
            <div class="page page_signUp">
                <h3>¡Hola, amigo!</h3>
                <p>Introduce tus datos personales y comienza tu viaje con nosotros.</p>
                <button class="btn btnSign-up"><i class="bi bi-arrow-left"></i> INICIAR SESION</button>
            </div>
        </div>
    </div>
    <!-- link script -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/password.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const container = document.querySelector('.container');
            const btnSignIn = document.querySelector('.btnSign-in');
            const btnSignUp = document.querySelector('.btnSign-up');
            const passwordRegister = document.getElementById('password-register');
            const passwordStrength = document.getElementById('password-strength');
            const registerSubmit = document.getElementById('register-submit');

            if (container && btnSignIn && btnSignUp) {
                btnSignIn.addEventListener('click', () => {
                    container.classList.add('active');
                });

                btnSignUp.addEventListener('click', () => {
                    container.classList.remove('active');
                });
            } else {
                console.error('One or more elements not found in the DOM');
            }

            const checkPasswordStrength = (password) => {
                let strength = 0;
                if (password.length >= 8)
                    strength += 1;
                if (password.match(/[a-z]+/))
                    strength += 1;
                if (password.match(/[A-Z]+/))
                    strength += 1;
                if (password.match(/[0-9]+/))
                    strength += 1;
                if (password.match(/[\W]+/))
                    strength += 1;

                return strength;
            };

            passwordRegister.addEventListener('input', () => {
                const strength = checkPasswordStrength(passwordRegister.value);
                passwordStrength.style.width = `${strength * 50}%`;

                let color;
                switch (strength) {
                    case 1:
                        color = 'red';
                        break;
                    case 2:
                        color = 'orange';
                        break;
                    case 3:
                        color = 'yellow';
                        break;
                    case 4:
                        color = 'lightgreen';
                        break;
                    case 5:
                        color = 'green';
                        break;
                    default:
                        color = 'gray';
                }

                passwordStrength.style.backgroundColor = color;
                registerSubmit.disabled = strength < 3;
            });
        });
    </script>
</body>
</html>
