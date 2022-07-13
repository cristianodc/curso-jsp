<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

form{
 position: absolute;
 top: 40%;
 left: 33%;
 right: 33%;
}


h5,h4{
 position: absolute;
 top: 30%;
 left: 33%;
}

.msg{
 position: absolute;
 top: 25%;
 left: 33%;
 font-size: 15px;
 color: red;
}
</style>	
</head>
<body>
<h4>Tela de login: Curso - de - JSP</h4>
<form action="<%=request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate >
<input type="hidden" value="<%= request.getParameter("url") %>" name="url"> 

   <div class="mb-3">
	    <label for="exampleInputEmail1" class="form-label">Login: </label>
	    <input type="text" class="form-control" name="login" required>
	    <div class="invalid-feedback">
	      Obrigatório!!
	    </div>
	    <div class="valid-feedback">
	      Perfeito
	    </div>
  </div>
  
 
  <div class="mb-3">
	    <label for="exampleInputPassword1" class="form-label">Senha :</label>
	    <input type="password" class="form-control" name="senha" required>
	    <div class="invalid-feedback">
	      Obrigatório!!
	    </div>
	    <div class="valid-feedback">
	      Perfeito
	    </div>
	  </div>
 
   <input type="submit" value="Acessar" class="btn btn-primary">
  
</form>

<h4 class="msg">${msg}</h4>
<script type="text/javascript">
//Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()
</script>
</body>
</html>