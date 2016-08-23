$(document).ready(function(){
    $('#btnSignIn').click(function() {
       console.log('ok call login');       
	   login();
        return false;
    });
	
    $('#btnCloseAlert').click(function() {
        $('#authErrorMsg').hide();
          return false;
    });		

    function login() {   
       console.log('login'); 
       console.log(formToJSON());	   
	   $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url:  'http://localhost:8080/samples/webapi/login',
            dataType: "text",
            data: formToJSON(),
            success: 
			    function(data){
                if(data == 'User Verified'){   
			        window.location.href = "welcome.html"; 
				}else{
					$('#authErrorMsg').show();					
				}
			
            },
           error: function(jqXHR, textStatus, errorThrown){
               alert('Error Sign in: ' + textStatus + ' error : '+errorThrown);
           }
        }); 
    }

    function formToJSON() {
		console.log('formToJson');
        return JSON.stringify({
            "userId": $('#inputEmail').val(),
            "userPwd": $('#inputPassword').val()
        });
    }
	


});