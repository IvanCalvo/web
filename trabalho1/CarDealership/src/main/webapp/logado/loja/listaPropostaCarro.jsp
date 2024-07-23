<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CarDealership</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Lista de propostas</h1>
		<h2>
			<a href="${pageContext.request.contextPath}/loja">Menu Loja</a>
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
				<th>Status</th>
				<th>Data de Proposta</th>
			</tr>
			<c:forEach var="Proposta" items="${requestScope.listaPropostas}">
				<tr>
					<td>${Proposta.id}</td>
					<td>${Proposta.carro.modelo}</td>
					<td>${Proposta.carro.loja.nome}</td>
					<td>${Proposta.condPagamento}</td>
					<td>${Proposta.status}</td>
					<td>${Proposta.dataProposta}
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>