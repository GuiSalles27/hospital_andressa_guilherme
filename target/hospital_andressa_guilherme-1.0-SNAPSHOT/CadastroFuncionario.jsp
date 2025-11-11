<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro de Funcionário</title>
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
        input[type="number"],
        input[type="date"],
        select {
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
    <h1>Cadastro de Funcionário</h1>
    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
        <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">
        <input type="hidden" name="codFunc" value="${codFunc}">

        <label>Funcionário:</label>
        <input type="text" required name="nomeFunc" value="${nomeFunc}">

        <label>CPF do funcionário:</label>
        <input type="text" required name="cpf" value="${cpf}">

        <label>RG do funcionário:</label>
        <input type="text" required name="rg" value="${rg}">

        <label>Número de registro:</label>
        <input type="number" required name="numRegistro" value="${numRegistro}">

        <label>Data de admissão:</label>
        <input type="date" name="dataAdmissao" value="${dataAdmissao}">

        <label>Horário de trabalho:</label>
        <input type="text" name="horarioTrabalho" value="${horarioTrabalho}">

        <label>Cargo:</label>
        <select name="cargoFuncionario">
            <c:forEach var="cargo" items="${listaCargo}">
                <c:choose>
                    <c:when test="${cargo.codCargo eq cargoFuncionario}">
                        <option selected value ="${cargo.codCargo}">${cargo.nomeCargo}</option>
                    </c:when>
                    <c:otherwise>
                        <option value ="${cargo.codCargo}">${cargo.nomeCargo}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <label>Hospital:</label>
        <select name="hospitalFuncionario">
            <c:forEach var="hospital" items="${listaHospital}">
                <c:choose>
                    <c:when test="${hospital.codHospital eq hospitalFuncionario}">
                        <option selected value ="${hospital.codHospital}">${hospital.descricao}</option>
                    </c:when>
                    <c:otherwise>
                        <option value ="${hospital.codHospital}">${hospital.descricao}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <input type="submit" value="Salvar" name="btnSalvar">
    </form>

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" style="max-width: 700px; margin: 0 auto;">
        <input type="hidden" name="opcao" value="cancelar">
        <input type="submit" value="Cancelar" name="btnCancelar">
    </form>

    <br>
    <h3>${mensagem}</h3>
    
    <form action="${pageContext.request.contextPath}${URL_BASE}/RelatorioFuncionarioControlador" method="get" style="max-width: 600px; margin: 20px auto; text-align: center;">
    <button type="submit" style="
        padding: 10px 20px;
        background-color: #2A8375;
        border: none;
        color: #fff;
        font-weight: bold;
        border-radius: 10px;
        cursor: pointer;
        transition: background-color 0.3s ease;">
        Gerar Relatório de Funcionários
    </button>
</form>


    <table>
        <c:if test="${not empty listaFuncionario}">
            <tr><th>CÓDIGO</th><th>NOME</th><th>CPF</th><th>RG</th><th>NÚMERO DE REGISTRO</th><th>DATA DE ADMISSÃO</th><th>HORÁRIO DE TRABALHO</th><th>CARGO</th><th>HOSPITAL</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
        </c:if>
        <c:forEach var="funcionario" items="${listaFuncionario}">
            <tr>
                <td>${funcionario.codFunc}</td>
                <td>${funcionario.nomeFunc}</td>
                <td>${funcionario.cpf}</td>
                <td>${funcionario.rg}</td>
                <td>${funcionario.numRegistro}</td>
                <td>${funcionario.dataAdmissaoFormatado}</td>
                <td>${funcionario.horarioTrabalho}</td>
                <td>${funcionario.cargoFuncionario.nomeCargo}</td>
                <td>${funcionario.hospitalFuncionario.descricao}</td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                        <input type="hidden" name="codFunc" value="${funcionario.codFunc}">
                        <input type="hidden" name="nomeFunc" value="${funcionario.nomeFunc}">
                        <input type="hidden" name="cpf" value="${funcionario.cpf}">
                        <input type="hidden" name="rg" value="${funcionario.rg}">
                        <input type="hidden" name="numRegistro" value="${funcionario.numRegistro}">
                        <input type="hidden" name="dataAdmissao" value="${funcionario.dataAdmissaoFormatado}">
                        <input type="hidden" name="horarioTrabalho" value="${funcionario.horarioTrabalho}">
                        <input type="hidden" name="cargoFuncionario" value="${funcionario.cargoFuncionario.codCargo}">
                        <input type="hidden" name="hospitalFuncionario" value="${funcionario.hospitalFuncionario.codHospital}">
                        <input type="hidden" name="opcao" value="editar">
                        <button type="submit">Editar</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                        <input type="hidden" name="codFunc" value="${funcionario.codFunc}">
                        <input type="hidden" name="nomeFunc" value="${funcionario.nomeFunc}">
                        <input type="hidden" name="cpf" value="${funcionario.cpf}">
                        <input type="hidden" name="rg" value="${funcionario.rg}">
                        <input type="hidden" name="numRegistro" value="${funcionario.numRegistro}">
                        <input type="hidden" name="dataAdmissao" value="${funcionario.dataAdmissaoFormatado}">
                        <input type="hidden" name="horarioTrabalho" value="${funcionario.horarioTrabalho}">
                        <input type="hidden" name="cargoFuncionario" value="${funcionario.cargoFuncionario.codCargo}">
                        <input type="hidden" name="hospitalFuncionario" value="${funcionario.hospitalFuncionario.codHospital}">
                        <input type="hidden" name="opcao" value="excluir">
                        <button type="submit">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>