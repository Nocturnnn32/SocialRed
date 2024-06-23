var tab=1;
$( document ).ready(function() {
	$(function() {

	    $('#login-form-link').click(function(e) {
			$("#login-form").delay(100).fadeIn(100);
	 		$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			tab=1;
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
	 		$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			tab=2;
			e.preventDefault();
		});

	});
	
	$(document).keypress(function (e){
		if(e.which == 13){
			if (tab==1){
				sendForm();
				
			}else if(tab==2){
				sendForm();
			}
		
		}
	});
	
	$('#password').keyup(function(){
		$('.error-list').hide();
	})
	
	$( "#password-register" ).keyup(function() {
		$('.error-list').show();
	});
		
	$( "#confirm-password" ).keyup(function() {
		 
		var password1 = $("#password-register").val();
		var password2 = $("#confirm-password").val();

	    if(password1 === password2 && password2!='') {
	    	$("#status").css("color","green");
	    	$("#password-register").css('border-color', 'green');
	    	$("#confirm-password").css('border-color', 'green');
	    	$("#status").text("Contraseña correcta");
	    	$('#register-submit').removeAttr('disabled');
	    }
	    else {
	    	$("#status").css("color","red");
	    	$('#register-submit').attr('disabled','disabled');
	    	$("#password-register").css('border-color', 'red');
	    	$("#confirm-password").css('border-color', 'red');
	    	$("#status").text("No coinciden las contraseñas");  
	    };
	});
	
	$("#register-form").validate({
		rules: {
            "username": { required:true },
            "email-register": { 
            	required:true,
            	email: true
            }
        },
        messages: {
            "username": { required: "Introduzca el nombre" },
            "email": {required: "Introduzca el email" }
        },
		  submitHandler: function(form) {
			  var password = $('#password-register').val();
			  var confirmpwd = $('#confirm-password').val();
			  if (password!=confirmpwd){
				  alert("Las contraseñas tienen que ser iguales");
			  }else if (password.length<6){
				  alert("La contraseña tiene que tener más de 6 caracteres");
			  }else{
				  form.submit();
			  }
			  
		  }
		 });
	
});

function sendForm(){
	if(tab==1){
		$('#login-form').submit();
	}else{
		$('#register-form').submit();
	}
}
