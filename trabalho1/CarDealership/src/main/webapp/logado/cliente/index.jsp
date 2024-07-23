<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página do Usuário</h1>
        <p>Olá ${sessionScope.usuarioLogado.email}</p>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
                <a href="${pageContext.request.contextPath}/cliente/listaProposta">Lista Propostas</a>
                <a href="${pageContext.request.contextPath}/cliente/listaCarros">Lista Carros</a>
            </li>
        </ul>
    </body>
</html>