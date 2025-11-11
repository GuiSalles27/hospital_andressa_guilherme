<%-- 
    Document   : CadastroCargo
    Created on : 25 de mar de 2025, 13:18:30
    Author     : 15114560603
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=Latin1" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro de Cargo</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">

    <style>
        body {
            background-color: #E5F7F2;
            font-family: Arial, sans-serif;
            color: #003D3D;
            margin: 0px;
        }

        h1 {
            color: #2A8375;
            text-align: center;
            margin-bottom: 30px;
        }

        form {
            background-color: #C2EAE3;
            padding: 20px;
            border-radius: 12px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #005C53;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 8px;
            border: 1px solid #90C2B3;
            box-sizing: border-box;
        }

        input[type="submit"],
        button {
            padding: 10px 20px;
            background-color: #90C2B3;
            border: none;
            color: #fff;
            font-weight: bold;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover,
        button:hover {
            background-color: #5EA395;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        table, th, td {
            border: 1px solid #90C2B3;
        }

        th {
            background-color: #B0DDD2;
            color: #003D3D;
            padding: 10px;
        }

        td {
            text-align: center;
            padding: 10px;
            background-color: #F6FFFD;
        }

        h3 {
            text-align: center;
            color: #E03D3D;
        }
    </style>
</head>
<body>

<%@include file="menu.jsp" %>

<h1>Cadastro de Cargo</h1>

<form id="cadastroForm" name="cadastro" 
      method="post"
      action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
    <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">

    <input type="hidden" name="codCargo" value="${codCargo}">

    <p><label>Cargo:</label>
        <input type="text" name="nomeCargo" value="${nomeCargo}" size="40" required placeholder="digite o cargo">
    </p>
    <p><label>Salário:</label>
        <input type="text" name="salarioInicial" value="${salarioInicial}" size="30" required placeholder="digite o salário (apenas números)">
    </p>
    <p><label>Descrição:</label>
        <input type="text" name="descricao" value="${descricao}" size="50" required placeholder="digite a descrição">
    </p>
    <p><label>Bonificação:</label>
        <input type="number" name="bonificacao" value="${bonificacao}" size="30" required placeholder="digite a bonificação (apenas números)">
    </p>

    <input type="submit" value="Salvar" name="btnSalvar">
</form>


<form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador" style="max-width: 600px; margin: 0 auto;">
    <input type="hidden" name="opcao" value="cancelar">
    <input type="submit" value="Cancelar" name="btnCancelar">
</form>

<!-- ? Novo formulário de Relatório -->
<h2 style="text-align:center;">Relatório de Cargos por Faixa Salarial</h2>
<form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador" style="max-width:600px; margin:0 auto;">
    <input type="hidden" name="opcao" value="relatorioCargo">
    <p><label>Salário Mínimo:</label>
        <input type="number" step="0.01" name="salarioMin" required>
    </p>
    <p><label>Salário Máximo:</label>
        <input type="number" step="0.01" name="salarioMax" required>
    </p>
    <input type="submit" value="Gerar Relatório Excel">
</form>

<br>
<h3>${mensagem}</h3>

<table>
    <c:if test="${not empty listaCargo}">
        <tr>
            <th>CÓDIGO</th>
            <th>CARGO</th>
            <th>SALÁRIO</th>
            <th>DESCRIÇÃO</th>
            <th>BONIFICAÇÃO</th>
            <th>ALTERAR</th>
            <th>EXCLUIR</th>
        </tr>
    </c:if>

    <c:forEach var="cargo" items="${listaCargo}">
        <tr>
            <td>${cargo.codCargo}</td>
            <td>${cargo.nomeCargo}</td>
            <td>${cargo.salarioInicial}</td>
            <td>${cargo.descricao}</td>
            <td>${cargo.bonificacao}</td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
                    <input type="hidden" name="codCargo" value="${cargo.codCargo}">
                    <input type="hidden" name="nomeCargo" value="${cargo.nomeCargo}">
                    <input type="hidden" name="salarioInicial" value="${cargo.salarioInicial}">
                    <input type="hidden" name="descricao" value="${cargo.descricao}">
                    <input type="hidden" name="bonificacao" value="${cargo.bonificacao}">
                    <input type="hidden" name="opcao" value="editar">
                    <button type="submit">Editar</button>
                </form>
            </td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
                    <input type="hidden" name="codCargo" value="${cargo.codCargo}">
                    <input type="hidden" name="nomeCargo" value="${cargo.nomeCargo}">
                    <input type="hidden" name="salarioInicial" value="${cargo.salarioInicial}">
                    <input type="hidden" name="descricao" value="${cargo.descricao}">
                    <input type="hidden" name="bonificacao" value="${cargo.bonificacao}">
                    <input type="hidden" name="opcao" value="excluir">
                    <button type="submit">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
