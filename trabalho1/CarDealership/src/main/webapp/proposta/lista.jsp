<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CarDealership</title>
</head>
<body>
	<c:if test="${mensagens.existeErros}">
	    <div id="erro">
	        <ul>
           		<c:forEach var="erro" items="${mensagens.erros}">
	               <li> ${erro} </li>
              	</c:forEach>
	        </ul>	
	    </div>
    </c:if>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Lista de propostas</h1>
		<h2>
			<c:if test="${Usuario != null }">
				<c:if test="${Usuario.papel == 'LOJA'}">
					<a href="/<%=contextPath%>/loja">Menu Loja</a>
				</c:if>
				<c:if test="${Usuario.papel == 'CLIENTE'}">
					<a href="/<%=contextPath%>/cliente">Menu Cliente</a>
				</c:if>
			</c:if>
			
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Propostas</caption>
			<tr>
				<th>ID</th>
				<th>Modelo</th>
				<th>Loja</th>
				<th>Condição de Pagamento</th>
				<th>valor</th>
				<th>Status</th>
				<th>Data de Proposta</th>
				<c:if test="${Usuario != null }">
					<c:if test="${Usuario.papel == 'LOJA'}">
						<th>Ações</th>
					</c:if>
				</c:if>
			</tr>
			<c:forEach var="Proposta" items="${requestScope.listaProposta}">
				<tr>
					<td>${Proposta.id}</td>
					<td>${Proposta.carro.modelo}</td>
					<td>${Proposta.carro.loja.nome}</td>
					<td>${Proposta.condPagamento}</td>
					<td>${Proposta.valor}</td>
					<td>${Proposta.status}</td>
					<td>${Proposta.dataProposta}</td>
					<c:if test="${Usuario != null }">
							<c:if test="${Usuario.papel == 'LOJA'}">
								<td><a href="/<%=contextPath%>/propostas/edicao?id=${Proposta.id}">Responder Proposta</a></td>
							</c:if>
						</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>