<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${proposta.id != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${proposta.id != null}">
		<input type="hidden" name="id" value="${proposta.id}" />
	</c:if>
	<tr>
		<td><label for="valor">Valor</label></td>
		<td><input type="number" min="0" step=".01" id="valor" name="valor" size="20" min="0.01" step="any"
			required value="${proposta.valor}" /></td>
	</tr>
	<tr>
		<td><label for="condPagamento">Condição de Pagamento</label></td>
		<td><input type="text" id="condPagamento" name="condPagamento" size="45" required
			value="${proposta.condPagamento}" /></td>
	</tr>
		<c:choose>
			<c:when test="${proposta.status  != NULL }">
				<td><label for="status">Status</label></td>
				<td><select id="status" name="status">
							<option value="NÃO ACEITO" ${proposta.status == 'NÃO ACEITO' ? 'selected' : '' } ${proposta.status == 'NÃO ACEITO' ? 'selected' : '' }>
								NÃO ACEITO</option>
							<option value="ACEITO" ${proposta.status == 'ACEITO' ? 'selected' : '' } ${proposta.status == 'ACEITO' ? 'selected' : '' }>
								ACEITO</option>
				</select></td>
			</c:when>
			<c:otherwise>
				<td><label for="status">Status</label></td>
				<td><input type="text" id="status" name="status" size="50" required readonly value="ABERTO"></td>
			</c:otherwise>
		</c:choose>
	<tr>
		<td><label for="idCarro">idCarro</label></td>
		<td><input type="number" id="idCarro" name="idCarro" size="20" readonly required
			value="${proposta.carro.id}" /></td>
	</tr>
	<tr>
		<td><label for="nomeLoja">Loja</label></td>
		<td><input type="text" id="nomeLoja" name="nomeLoja" size="50" readonly required
			value="${proposta.carro.loja.nome}" /></td>
	</tr>
	<tr>
		<td><label for="idCliente">Cliente</label></td>
		<td><input type="number" id="idCliente" name="idCliente" size="20" readonly required value="${proposta.cliente.id_usuario}"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>