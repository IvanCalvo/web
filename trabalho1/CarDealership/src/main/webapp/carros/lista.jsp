<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CarDealership</title>
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
		<h1>Gerenciamento de Carros</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a>
			<c:if test="${Usuario != null }">
				<c:if test="${Usuario.papel == 'CLIENTE'}">
					<a href="/<%=contextPath%>/cliente/">Menu Cliente</a>
				</c:if>
				<c:if test="${Usuario.papel == 'LOJA'}">
					<a href="${pageContext.request.contextPath}/loja/">Menu Loja</a>
					<a href="${pageContext.request.contextPath}/carro/cadastro">Adicione Novo Carro</a>
				</c:if>
			</c:if>
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
				<c:choose>
					<c:when test="${Usuario != null}">
						<th>Ações</th>
					</c:when>
				</c:choose>
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
					<c:if test="${Usuario != null }">
						<c:if test="${Usuario.papel == 'CLIENTE'}">
							<td><a href="/<%=contextPath%>/propostas/cadastro?id=${carro.id}">Fazer Proposta</a></td>
						</c:if>
						<c:if test="${Usuario.papel == 'LOJA'}">
							<td><a href="/<%= contextPath%>/loja/listaProposta?id=${carro.id}">Propostas</a></td>
						</c:if>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>