<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro de Consultas</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">

    <style>
        body {
            background-color: #E5F7F2;
            font-family: Arial, sans-serif;
            color: #003D3D;
            margin: 0;
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
            max-width: 720px;
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

    <h1>Cadastro de Consultas</h1>

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ConsultaControlador">
        <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">
        <input type="hidden" name="codConsulta" value="${codConsulta}">

        <label>Diagnóstico da consulta:</label>
        <input type="text" required name="diagnostico" value="${diagnostico}" size="50">

        <label>Data da consulta:</label>
        <input type="date" name="dataConsulta" value="${dataConsulta}">

        <label>Paciente:</label>
        <select name="pacienteConsulta">
            <c:forEach var="paciente" items="${listaPaciente}">
                <c:choose>
                    <c:when test="${paciente.codPaciente eq pacienteConsulta}">
                        <option selected value="${paciente.codPaciente}">${paciente.nomePaciente}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${paciente.codPaciente}">${paciente.nomePaciente}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <label>Funcionário:</label>
        <select name="funcionarioConsulta">
            <c:forEach var="funcionario" items="${listaFuncionario}">
                <c:choose>
                    <c:when test="${funcionario.codFunc eq funcionarioConsulta}">
                        <option selected value="${funcionario.codFunc}">${funcionario.nomeFunc}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${funcionario.codFunc}">${funcionario.nomeFunc}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <input type="submit" value="Salvar" name="btnSalvar">
    </form>

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ConsultaControlador" style="max-width: 720px; margin: 0 auto;">
        <input type="hidden" name="opcao" value="cancelar">
        <input type="submit" value="Cancelar" name="btnCancelar">
    </form>

    <br>
    <h3>${mensagem}</h3>
    
     <form action="${pageContext.request.contextPath}${URL_BASE}/RelatorioConsultasControlador" method="get" style="max-width: 600px; margin: 20px auto; text-align: center;">
    <button type="submit" style="
        padding: 10px 20px;
        background-color: #2A8375;
        border: none;
        color: #fff;
        font-weight: bold;
        border-radius: 10px;
        cursor: pointer;
        transition: background-color 0.3s ease;">
        Gerar Relatório de Consulta
    </button>
</form>


    <table>
    <c:if test="${not empty listaConsulta}">
        <tr>
            <th>CÓDIGO</th><th>DIAGNÓSTICO</th><th>DATA</th><th>PACIENTE</th><th>FUNCIONÁRIO</th><th>ALTERAR</th><th>EXCLUIR</th>
        </tr>
    </c:if>
    <c:forEach var="consulta" items="${listaConsulta}">
        <tr>
            <td>${consulta.codConsulta}</td>
            <td>${consulta.diagnostico}</td>
            <td>${consulta.dataConsultaFormatado}</td>
            <td>${consulta.pacienteConsulta.nomePaciente}</td>
            <td>${consulta.funcionarioConsulta.nomeFunc}</td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ConsultaControlador">
                    <input type="hidden" name="opcao" value="editar">
                    <input type="hidden" name="codConsulta" value="${consulta.codConsulta}">
                    <input type="hidden" name="diagnostico" value="${consulta.diagnostico}">
                    <input type="hidden" name="dataConsulta" value="${consulta.dataConsultaFormatado}">
                    <input type="hidden" name="pacienteConsulta" value="${consulta.pacienteConsulta.codPaciente}">
                    <input type="hidden" name="funcionarioConsulta" value="${consulta.funcionarioConsulta.codFunc}">
                    <button type="submit">Editar</button>
                </form>
            </td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ConsultaControlador">
                    <input type="hidden" name="opcao" value="excluir">
                    <input type="hidden" name="codConsulta" value="${consulta.codConsulta}">
                    <input type="hidden" name="diagnostico" value="${consulta.diagnostico}">
                    <input type="hidden" name="dataConsulta" value="${consulta.dataConsultaFormatado}">
                    <input type="hidden" name="pacienteConsulta" value="${consulta.pacienteConsulta.codPaciente}">
                    <input type="hidden" name="funcionarioConsulta" value="${consulta.funcionarioConsulta.codFunc}">
                    <button type="submit">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
