<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro Hospital</title>
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
            max-width: 700px;
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

<h1>Cadastro Hospital</h1>

<form method="get" action="${pageContext.request.contextPath}${URL_BASE}/HospitalControlador">
    <input type="hidden" name="opcao" value="${opcao}">
    <input type="hidden" name="codHospital" value="${codHospital}">

    <label>CNPJ:</label>
    <input type="text" name="cnpjHospital" value="${cnpjHospital}" required placeholder="digite o CNPJ do hospital">

    <label>Endereço:</label>
    <input type="text" name="endereco" value="${endereco}" required placeholder="digite o endereço">

    <label>Bairro:</label>
    <input type="text" name="bairro" value="${bairro}" required placeholder="digite o bairro">

    <label>Cidade:</label>
    <input type="text" name="cidade" value="${cidade}" required placeholder="digite a cidade">

    <label>CEP:</label>
    <input type="text" name="cep" value="${cep}" required placeholder="digite o CEP">

    <label>UF:</label>
    <input type="text" name="uf" value="${uf}" required placeholder="digite a UF">

    <label>Telefone:</label>
    <input type="text" name="telefone" value="${telefone}" required placeholder="digite o telefone">

    <label>Descrição:</label>
    <input type="text" name="descricao" value="${descricao}" required placeholder="digite a descrição">

    <input type="submit" value="Salvar" name="btnSalvar">
</form>

<form method="get" action="${pageContext.request.contextPath}${URL_BASE}/HospitalControlador" style="max-width: 700px; margin: 0 auto;">
    <input type="hidden" name="opcao" value="cancelar">
    <input type="submit" value="Cancelar" name="btnCancelar">
</form>

<br>
<h3>${mensagem}</h3>

<table>
    <c:if test="${not empty listaHospital}">
        <tr>
            <th>CÓDIGO</th>
            <th>CNPJ</th>
            <th>ENDEREÇO</th>
            <th>BAIRRO</th>
            <th>CIDADE</th>
            <th>CEP</th>
            <th>UF</th>
            <th>TELEFONE</th>
            <th>DESCRIÇÃO</th>
            <th>ALTERAR</th>
            <th>EXCLUIR</th>
        </tr>
    </c:if>
    <c:forEach var="hospital" items="${listaHospital}">
        <tr>
            <td>${hospital.codHospital}</td>
            <td>${hospital.cnpjHospital}</td>
            <td>${hospital.endereco}</td>
            <td>${hospital.bairro}</td>
            <td>${hospital.cidade}</td>
            <td>${hospital.cep}</td>
            <td>${hospital.uf}</td>
            <td>${hospital.telefone}</td>
            <td>${hospital.descricao}</td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/HospitalControlador">
                    <input type="hidden" name="codHospital" value="${hospital.codHospital}">
                    <input type="hidden" name="cnpjHospital" value="${hospital.cnpjHospital}">
                    <input type="hidden" name="endereco" value="${hospital.endereco}">
                    <input type="hidden" name="bairro" value="${hospital.bairro}">
                    <input type="hidden" name="cidade" value="${hospital.cidade}">
                    <input type="hidden" name="cep" value="${hospital.cep}">
                    <input type="hidden" name="uf" value="${hospital.uf}">
                    <input type="hidden" name="telefone" value="${hospital.telefone}">
                    <input type="hidden" name="descricao" value="${hospital.descricao}">
                    <input type="hidden" name="opcao" value="editar">
                    <button type="submit">Editar</button>
                </form>
            </td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/HospitalControlador">
                    <input type="hidden" name="codHospital" value="${hospital.codHospital}">
                    <input type="hidden" name="cnpjHospital" value="${hospital.cnpjHospital}">
                    <input type="hidden" name="endereco" value="${hospital.endereco}">
                    <input type="hidden" name="bairro" value="${hospital.bairro}">
                    <input type="hidden" name="cidade" value="${hospital.cidade}">
                    <input type="hidden" name="cep" value="${hospital.cep}">
                    <input type="hidden" name="uf" value="${hospital.uf}">
                    <input type="hidden" name="telefone" value="${hospital.telefone}">
                    <input type="hidden" name="descricao" value="${hospital.descricao}">
                    <input type="hidden" name="opcao" value="excluir">
                    <button type="submit">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
