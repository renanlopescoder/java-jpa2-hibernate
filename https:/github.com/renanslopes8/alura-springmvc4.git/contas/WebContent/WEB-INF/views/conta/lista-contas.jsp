<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC</title>
</head>
<body>
Lista de Contas Cadastradas
<script src="resources/js/jquery.js"></script>
<script type="text/javascript">
	function contaPaga(dadosDaResposta){
		alert("Conta Paga Com Sucesso");
	}
	
	function pagaAgora(id) {
		$.post("pagaConta", {'id' : id}, function(paga) {
		  $("#conta_"+id).html("Paga");
		});
		}
</script>
<br/>
<br/>
<a href="efetuaLogout">Logout</a>  
<table>
<tr>
	<th>Código</th>
	<th>Descrição</th>
	<th>Valor</th>
	<th>Tipo</th>
	<th>Status Pagamento</th>
	<th>Data Pagamento</th>
	<th>Deletar</th>
</tr>

<c:forEach items="${todasContas}" var="conta"> 
<tr>
	<td>${conta.id}</td>
	<td>${conta.descricao}</td>
	<td>${conta.valor}</td>
	<td>${conta.tipo}</td>
	<td><c:if test="${conta.paga eq false}">
	Não Paga
	</c:if>
	<c:if test="${conta.paga eq true}">
	Paga
	</c:if>
	</td>
	<td><fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy"/></td>
	<td><a href="removeConta?id=${conta.id}">Remover</a> | </td>  
	<td><a href="mostraConta?id=${conta.id}">Alterar | </a></td> 
	<c:if test="${conta.paga eq false}">
	<td><a href="#" onclick="pagaAgora(${conta.id})">Pagar</a></td>
	</c:if>
	<c:if test="${conta.paga eq true}">
	<td><a href="#">Conta Paga</a>
	</c:if>
	</tr>
	</c:forEach>
</table>
</body>
</html>