<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
														<h4 class="sub-title">Cadastro de Telefone</h4>
														
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletTelefone"
															method="post"  id="formFone">
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${model.id}"> <span class="form-bar"></span>
																<label class="float-label">Id User:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome" readonly="readonly"
																	class="form-control" required="required"
																	value="${model.nome}"> <span class="form-bar"></span>
																<label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="number" name="numero" id="numero" 
																	class="form-control" required="required"> <span class="form-bar"></span>
																<label class="float-label">Numero:</label>
															</div>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
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
													<th scope="col">Numero</th>
													<th scope="col">Excluir</th>

												</tr>
											</thead>
											<tbody>
												<!-- SERA PREENCHIDO COM JSTL -->
												<c:forEach items="${lista}" var="fone">
													<tr>
														<td><c:out value="${fone.id}"></c:out></td>
														<td><c:out value="${fone.numero}"></c:out></td>
														<td><a class="btn btn-success"
															href="<%= request.getContextPath()%>/ServletTelefone?acao=excluir&id=${fone.id}&userPai=${model.id}">Excluir</a></td>
													</tr>

												</c:forEach>
											</tbody>
										</table>

									</div>
									</div>
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
</body>

</html>
