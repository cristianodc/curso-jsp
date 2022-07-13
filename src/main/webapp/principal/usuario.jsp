<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set scope="session" var="perfil"
	value='<%=request.getSession().getAttribute("perfil").toString()%>'></c:set>
<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->


	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- Inicio barra de navegaçao     -->

			<jsp:include page="navbar.jsp"></jsp:include>

			<!-- end navbar -->

			<div class="pcoded-main-container">

				<div class="pcoded-wrapper">
					<!-- INICIO NAVBAR pcoded-navbar -->

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>
					<!-- END OF NAVBAR  -->
					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">

								<div class="page-wrapper">

									<!-- Page-body start -->
									<div class="page-body">

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													
													<div class="card-block">
														<h4 class="sub-title">Cadastro de Usuario</h4>

														<!--  project and team member end -->
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuControler"
															method="post" enctype="multipart/form-data" id="formUser">
															<input type="hidden" name="acao" id="acao" value="">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${model.id}"> <span class="form-bar"></span>
																<label class="float-label">Id:</label>
															</div>

															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<c:if
																		test="${model.fotoUser != '' && model.fotoUser != null}">
																		<a
																			href="<%= request.getContextPath()%>/ServletUsuControler?acao=dowloadFoto&id=${model.id}">
																			<img alt="Imagem User" id="fotoembase64"
																			src="${model.fotoUser}" width="70px">
																		</a>
																	</c:if>

																	<c:if
																		test="${model.fotoUser == '' || model.fotoUser == null}">
																		<img alt="Imagem User" id="fotoembase64"
																			src="assets/images/avatar-1.jpg" width="70px">
																	</c:if>

																</div>
																<input type="file" id="fileFoto" name="fileFoto"
																	accept="image/*"
																	onchange="visualizarImg('fotoembase64', 'fileFoto');"
																	class="form-control-file"
																	style="margin-top: 15px; margin-left: 5px;">
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${model.nome}"> <span class="form-bar"></span>
																<label class="float-label">Nome:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.email}"> <span
																	class="form-bar"></span> <label class="float-label">Email:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="dtnasc" id="dtnasc"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.dataNascimento}"> <span
																	class="form-bar"></span> <label class="float-label">Dt. Nascimento:</label>
															</div>

															<div class="form-group form-default form-static-label ">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">

																	<option selected>[Selecione o Perfil]</option>
																	<option value="ADMIN"
																															<%ModelLogin model = (ModelLogin) request.getAttribute("model");
															if (model != null && model.getPerfil().equalsIgnoreCase("ADMIN")) {
																out.print(" ");
																out.print("selected=\"selected\"");
																out.print(" ");
															}%>>Adminstrador</option>
		
																			<option value="SECRETARIA"
																																		<%model = (ModelLogin) request.getAttribute("model");
																if (model != null && model.getPerfil().equalsIgnoreCase("SECRETARIA")) {
																	out.print(" ");
																	out.print("selected=\"selected\"");
																	out.print(" ");
																}%>>Secretária</option>
		
																			<option value="AUXILIAR"
																				<%model = (ModelLogin) request.getAttribute("model");
																		if (model != null && model.getPerfil().equalsIgnoreCase("AUXILIAR")) {
																			out.print(" ");
																			out.print("selected=\"selected\"");
																			out.print(" ");
																		}%>>Auxiliar</option>

																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>



															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	value="${model.login}"> <span class="form-bar"></span>
																<label class="float-label">Login:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha</label>
															</div>

															<div class="form-group form-default form-static-label">

																<input type="radio" name="sexo" value="MASCULINO"
																	<%model = (ModelLogin) request.getAttribute("model");
if (model != null && model.getSexo().equalsIgnoreCase("MASCULINO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");
}%>>Masculino</>

																<input type="radio" name="sexo" value="FEMININO"
																	<%model = (ModelLogin) request.getAttribute("model");
if (model != null && model.getSexo().equalsIgnoreCase("FEMININO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");
}%>>Feminino</>

															</div>

															<div class="form-group form-default form-static-label">
																<input onblur="pesquisaCep();" type="text" name="cep"
																	id="cep" class="form-control" required="required"
																	autocomplete="off" value="${model.cep}"> <span
																	class="form-bar"></span> <label class="float-label">Cep:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="logradouro" id="logradouro"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.logradouro}">
																<span class="form-bar"></span> <label
																	class="float-label">Logradouro:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.bairro}"> <span
																	class="form-bar"></span> <label class="float-label">Bairro:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="localidade" id="localidade"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.localidade}">
																<span class="form-bar"></span> <label
																	class="float-label">Localidade:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="uf" id="uf"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.uf}"> <span
																	class="form-bar"></span> <label class="float-label">UF:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nro" id="nro"
																	class="form-control" required="required"
																	autocomplete="off" value="${model.numero}"> <span
																	class="form-bar"></span> <label class="float-label">Número:</label>
															</div>
													</div>
													<button type="button" 	class="btn btn-primary waves-effect waves-light"onclick="limparForm();">Novo</button>
													<button class="btn btn-success waves-effect waves-light">Salvar</button>
													<button type="button"
														class="btn btn-info waves-effect waves-light"
														onclick="criaDeleteComAjax();">Excluir</button>
													<c:if test="${model.id > 0}">
													  <a href="<%=request.getContextPath()%>/ServletTelefone?idUser=${model.id}" class="btn btn-primary waves-effect waves-light">Telefone</a>	
													</c:if>	
													
													<button type="button" class="btn btn-primary"
														data-toggle="modal" data-target="#exampleModalUsu">
														Pesquisar</button>
													</form>
												</div>
											</div>
										</div>
									</div>
									<span id="msg">${msg}</span>

									<div style="height: 300px; overflow: scroll;">
										<table class="table" id="tabelaViews">
											<thead>
												<tr>
													<th scope="col">Id</th>
													<th scope="col">Nome</th>
													<th scope="col">Ver</th>

												</tr>
											</thead>
											<tbody>
												<!-- SERA PREENCHIDO COM JSTL -->
												<c:forEach items="${modelLogins}" var="ml">
													<tr>
														<td><c:out value="${ml.id}"></c:out></td>
														<td><c:out value="${ml.nome}"></c:out></td>
														<td><a class="btn btn-success"
															href="<%=request.getContextPath()%>/ServletUsuControler?acao=buscarEditar&id=${ml.id}">Ver</a></td>
													</tr>

												</c:forEach>
											</tbody>
										</table>

									</div>
									<nav aria-label="Page navigation example">
										<ul class="pagination">
											<%
											int totalP = (int) request.getAttribute("totalPaginas");
											for (int p = 0; p < totalP; p++) {
												String url = request.getContextPath() + "/ServletUsuControler?acao=paginar&pagina=" + (p * 5);
												out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\">" + (p + 1) + "</a></li>");
											}
											%>


										</ul>
									</nav>
								</div>
								<!-- Page-body end -->
							</div>
							<div id="styleSelector"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<!-- Warning Section Ends -->

	<!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalUsu" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Pesquisa de
						Usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="nomeBusca" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success" type="button"
								onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>

					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaresultado">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>

								</tr>
							</thead>
							<tbody>
								<!-- SERA PREENCHIDO COM JAVASCRIPT -->
							</tbody>
						</table>

					</div>
					<nav aria-label="Page navigation example">
						<ul class="pagination" id="ulPaginacaoAjax">
							
						</ul>
					</nav>

					<span id="total"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	$("#nro").keypress(function (event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	})
	$("#cep").keypress(function(event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
		function pesquisaCep() {
			var cep = $("#cep").val();
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#cep").val(dados.cep);
							$("#logradouro").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#localidade").val(dados.localidade);
							$("#uf").val(dados.uf);

						} //end
					});
		}
		function visualizarImg(fotoembase64, filefoto) {
			/*AULA 22.56*/
			var preview = document.getElementById(fotoembase64);
			var fileUser = document.getElementById(filefoto).files[0];

			var reader = new FileReader();
			reader.onloadend = function() {
				preview.src = reader.result;
			};

			if (fileUser) {
				reader.readAsDataURL(fileUser);
			} else {
				preview.src = '';
			}
		}
		function verEditar(id) {
			var urlAction = document.getElementById('formUser').action;
			/*redirecinamentos com javaScript*/
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;

		}
		function buscaUserPagAjax(url) {
			
			
			var urlAction = document.getElementById('formUser').action;
			var nomeBusca = document.getElementById('nomeBusca').value;
			$.ajax(	{

						method : "get",
						 url : urlAction,
					     data : url,
						success : function(response, textStatus,
								xhr) {

							var json = JSON.parse(response);

							$('#tabelaresultado > tbody > tr')
									.remove();
							$("#ulPaginacaoAjax > li").remove();
							for (var p = 0; p < json.length; p++) {
								$('#tabelaresultado tbody')
										.append(
												'<tr><td>'
														+ json[p].id
														+ '</td><td>'
														+ json[p].nome
														+ '</td> <td><button onclick="verEditar('
														+ json[p].id
														+ ')"type="button" class="btn btn-info">Ver</button> </td> </tr>');
							}
							document.getElementById('total').textContent = 'Resultados '
									+ json.length;

							var totalPagina = xhr.getResponseHeader("totalPagina");
								for(var p=0;  p < totalPagina; p++)
									{
									 var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
										//alert(url);
										 //$("#ulPaginacaoAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
									    $("#ulPaginacaoAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>'); 
									}
							
						}
					}).fail(
					function(xhr, status, errorThrown) {

						alert('Erro ao buscar usuario '
								+ xhr.responseText);
					});
		}
		
		
		function buscarUsuario() {

			var nomeBusca = document.getElementById('nomeBusca').value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '')/*Validando que tem que ter valor*/
			{
				var urlAction = document.getElementById('formUser').action;

				$.ajax(
								{

									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=buscarUser',
									success : function(response, textStatus,
											xhr) {

										var json = JSON.parse(response);

										$('#tabelaresultado > tbody > tr')
												.remove();
										$("#ulPaginacaoAjax > li").remove();
										for (var p = 0; p < json.length; p++) {
											$('#tabelaresultado tbody')
													.append(
															'<tr><td>'
																	+ json[p].id
																	+ '</td><td>'
																	+ json[p].nome
																	+ '</td> <td><button onclick="verEditar('
																	+ json[p].id
																	+ ')"type="button" class="btn btn-info">Ver</button> </td> </tr>');
										}
										document.getElementById('total').textContent = 'Resultados '
												+ json.length;

										var totalPagina = xhr.getResponseHeader("totalPagina");
											for(var p=0;  p < totalPagina; p++)
												{
												   var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
													
													  $("#ulPaginacaoAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
													//$("#ulPaginacaoAjax").append('<li class="page-item"><a class="page-link" onclick="buscaUserAjax(\''+url+'\')">'+(p+1)+'</a></li>');
												}
										
									}
								}).fail(
								function(xhr, status, errorThrown) {

									alert('Erro ao buscar usuario '
											+ xhr.responseText);
								});
			}
		}

		function criaDeleteComAjax() {

			if (confirm('Dejesa realmente excluir o registro ?')) {
				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;
				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarAjax',
					success : function(response) {
						limparForm();
						document.getElementById('msg').textContent = response;
					}
				}).fail(function(xhr, status, errorThrown) {
					alert('Erro ao deletar usuario ' + xhr.responseText);
				});
			}

		}

		function criarDelete() {
			if (confirm('Deseja realmente excluir o registro?')) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}

		}
		$( function() {
			  
			  $("#dtnasc").datepicker({
				    dateFormat: 'dd/mm/yy',
				    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
				    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
				    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
				    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
				    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
				    nextText: 'Próximo',
				    prevText: 'Anterior'
				});
		} );

		function limparForm() {

			var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
	</script>
</body>

</html>
