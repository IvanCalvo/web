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
		<h1>Gerenciamento de Usuários</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a>
			<a href="/<%= contextPath%>/admin/cadastroCliente">Cadastra Cliente</a>
			<a href="/<%= contextPath%>/admin/cadastroLoja">Cadastra Loja</a>
			<a href="/<%= contextPath%>/admin/cadastro">Cadastra ADMIN</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Usuários</caption>
			<tr>
				<th>ID</th>
				<th>email</th>
				<th>papel</th>
				<th>ações</th>
			</tr>
			<c:forEach var="usuario" items="${requestScope.listaUsuario}">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.email}</td>
					<td>${usuario.papel}</td>
					<td><a href="/<%= contextPath%>/admin/edicao?id=${usuario.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%= contextPath%>/admin/remocao?id=${usuario.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>