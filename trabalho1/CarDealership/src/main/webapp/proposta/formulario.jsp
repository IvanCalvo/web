<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

	<head>
		<title>CarDealership</title>
		<link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
	</head>
	
	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<c:if test="${mensagens.existeErros}">
		    <div id="erro">
		        <ul>
	           		<c:forEach var="erro" items="${mensagens.erros}">
	               		<li> ${erro} </li>
	              	</c:forEach>
		        </ul>	
		    </div>
	    </c:if>
		<div align="center">
			<h1>Gerenciamento de propostas</h1>
			<h2>
				<c:if test="${Usuario.papel == 'CLIENTE'}">
					<a href="lista">Lista de propostas</a>
				</c:if>
				<c:if test="${Usuario.papel == 'LOJA'}">
					<a href="listaPorLoja">Lista de propostas</a>
				</c:if>
				<a href="/<%=contextPath%>/usuario/listaCarros">Lista de carros</a>
			</h2>
		</div>
		<div align="center">
			<c:choose>
				<c:when test="${proposta.id != null}">
					<form action="atualizacao" method="post">
						<%@include file="campos.jsp"%>
					</form>
				</c:when>
				<c:otherwise>
					<form action="insercao" method="post">
						<%@include file="campos.jsp"%>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</body>

</html>