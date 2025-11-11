<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro de Paciente</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">

    <!--  Estilo padronizado do sistema  -->
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

    <h1>Cadastro de Paciente</h1>

    <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador">
        <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">
        <input type="hidden" name="codPaciente" value="${codPaciente}">

        <p>
            <label>Nome Paciente:</label>
            <input type="text" name="nomePaciente" value="${nomePaciente}" required>
        </p>
        <p>
            <label>CPF:</label>
            <input type="number" name="cpf" value="${cpf}" required>
        </p>
        <p>
            <label>Nascimento:</label>
            <input type="date" name="dataNascimento" value="${dataNascimento}">
        </p>
        <p>
            <label>Filiação:</label>
            <input type="text" name="filiacao" value="${filiacao}" required>
        </p>
        <p>
            <label>RG:</label>
            <input type="text" name="rg" value="${rg}" required>
        </p>
        <p>
            <label>Endereço:</label>
            <input type="text" name="endereco" value="${endereco}" required>
        </p>
        <p>
            <label>Bairro:</label>
            <input type="text" name="bairro" value="${bairro}" required>
        </p>
        <p>
            <label>Cidade:</label>
            <input type="text" name="cidade" value="${cidade}" required>
        </p>
        <p>
            <label>CEP:</label>
            <input type="number" name="cep" value="${cep}" required>
        </p>
        <p>
            <label>UF:</label>
            <input type="text" name="uf" value="${uf}" required>
        </p>
        <p>
            <label>Telefone:</label>
            <input type="number" name="telefone" value="${telefone}" required>
        </p>
        <p>
            <label>Plano:</label>
            <select name="planoPaciente">
                <c:forEach var="plano" items="${listaPlano}">
                    <c:choose>
                        <c:when test="${plano.codPlano eq planoPaciente}">
                            <option selected value="${plano.codPlano}">${plano.nomePlano}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${plano.codPlano}">${plano.nomePlano}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="Salvar" name="btnSalvar">
    </form>

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador" style="max-width: 600px; margin: 0 auto;">
        <input type="hidden" name="opcao" value="cancelar">
        <input type="submit" value="Cancelar" name="btnCancelar">
    </form>

    <br>
    <h3>${mensagem}</h3>
    
    <form action="${pageContext.request.contextPath}${URL_BASE}/RelatorioPacienteControlador" method="get" style="max-width: 600px; margin: 20px auto; text-align: center;">
    <button type="submit" style="
        padding: 10px 20px;
        background-color: #2A8375;
        border: none;
        color: #fff;
        font-weight: bold;
        border-radius: 10px;
        cursor: pointer;
        transition: background-color 0.3s ease;">
        Gerar Relatório de Pacientes
    </button>
</form>


    <table>
        <c:if test="${not empty listaPaciente}">
            <tr>
                <th>CÓDIGO</th>
                <th>NOME PACIENTE</th>
                <th>CPF</th>
                <th>DATA DE NASCIMENTO</th>
                <th>FILIAÇÃO</th>
                <th>RG</th>
                <th>ENDEREÇO</th>
                <th>BAIRRO</th>
                <th>CIDADE</th>
                <th>CEP</th>
                <th>UF</th>
                <th>TELEFONE</th>
                <th>PLANO</th>
                <th>ALTERAR</th>
                <th>EXCLUIR</th>
            </tr>
        </c:if>

        <c:forEach var="paciente" items="${listaPaciente}">
            <tr>
                <td>${paciente.codPaciente}</td>
                <td>${paciente.nomePaciente}</td>
                <td>${paciente.cpf}</td>
                <td>${paciente.nascimentoFormatado}</td>
                <td>${paciente.filiacao}</td>
                <td>${paciente.rg}</td>
                <td>${paciente.endereco}</td>
                <td>${paciente.bairro}</td>
                <td>${paciente.cidade}</td>
                <td>${paciente.cep}</td>
                <td>${paciente.uf}</td>
                <td>${paciente.telefone}</td>
                <td>${paciente.planoPaciente.nomePlano}</td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador">
                        <input type="hidden" name="codPaciente" value="${paciente.codPaciente}">
                        <input type="hidden" name="nomePaciente" value="${paciente.nomePaciente}">
                        <input type="hidden" name="cpf" value="${paciente.cpf}">
                        <input type="hidden" name="dataNascimento" value="${paciente.nascimentoFormatado}">
                        <input type="hidden" name="filiacao" value="${paciente.filiacao}">
                        <input type="hidden" name="rg" value="${paciente.rg}">
                        <input type="hidden" name="endereco" value="${paciente.endereco}">
                        <input type="hidden" name="bairro" value="${paciente.bairro}">
                        <input type="hidden" name="cidade" value="${paciente.cidade}">
                        <input type="hidden" name="cep" value="${paciente.cep}">
                        <input type="hidden" name="uf" value="${paciente.uf}">
                        <input type="hidden" name="telefone" value="${paciente.telefone}">
                        <input type="hidden" name="planoPaciente" value="${paciente.planoPaciente.codPlano}">
                        <input type="hidden" name="opcao" value="editar">
                        <button type="submit">Editar</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador">
                        <input type="hidden" name="codPaciente" value="${paciente.codPaciente}">
                        <input type="hidden" name="nomePaciente" value="${paciente.nomePaciente}">
                        <input type="hidden" name="cpf" value="${paciente.cpf}">
                        <input type="hidden" name="dataNascimento" value="${paciente.nascimentoFormatado}">
                        <input type="hidden" name="filiacao" value="${paciente.filiacao}">
                        <input type="hidden" name="rg" value="${paciente.rg}">
                        <input type="hidden" name="endereco" value="${paciente.endereco}">
                        <input type="hidden" name="bairro" value="${paciente.bairro}">
                        <input type="hidden" name="cidade" value="${paciente.cidade}">
                        <input type="hidden" name="cep" value="${paciente.cep}">
                        <input type="hidden" name="uf" value="${paciente.uf}">
                        <input type="hidden" name="telefone" value="${paciente.telefone}">
                        <input type="hidden" name="planoPaciente" value="${paciente.planoPaciente.codPlano}">
                        <input type="hidden" name="opcao" value="excluir">
                        <button type="submit">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
