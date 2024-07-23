<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página da Loja</h1>
        <p>Olá ${sessionScope.usuarioLogado.email}</p>
        <ul>
            <li>
            	<a href="${pageContext.request.contextPath}/carro/cadastro">Adicione Novo Carro</a>
            	<a href="${pageContext.request.contextPath}/loja/lista">Lista de Carros</a>
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>