<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Gerenciamento de Carros da ${sessionScope.usuarioLogado.email}</title>
</head>
<script>
	function filterFunction() {
	  // Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("modelFilter");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("tabelaCarros");
	  tr = table.getElementsByTagName("tr");
	
	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
	}
</script>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Carros da ${sessionScope.usuarioLogado.email}</h1>
		<h2>
			<a href="${pageContext.request.contextPath}/loja/">Menu Loja</a>
			<a href="${pageContext.request.contextPath}/carro/cadastro">Adicione Novo Carro</a>
		</h2>
	</div>

	<div align="center">
	<input type="text" id="modelFilter" onkeyup="filterFunction()" placeholder ="Procure pelo modelo">
		<table id="tabelaCarros" border="1">
			<caption>Lista de Carros</caption>
			<tr>
				<th>ID</th>
				<th>modelo</th>
				<th>Loja</th>
				<th>placa</th>
				<th>Ano</th>
				<th>Quilometragem</th>
				<th>Descrição</th>
				<th>Valor</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="carro" items="${requestScope.listaCarros}">
				<tr>
					<td>${carro.id}</td>
					<td>${carro.modelo}</td>
					<td>${carro.loja.nome}</td>
					<td>${carro.placa}</td>
					<td>${carro.ano}</td>
					<td>${carro.quilometragem}</td>
					<td>${carro.descricao}</td>
					<td>${carro.valor}</td>
					<td><a href="/<%= contextPath%>/loja/listaProposta?id=${carro.id}">Propostas</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>