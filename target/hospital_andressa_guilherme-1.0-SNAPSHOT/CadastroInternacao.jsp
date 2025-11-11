<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro de Internações</title>
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
        input[type="datetime-local"],
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
    <h1>Cadastro de Internações</h1>
    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/InternacaoControlador">
       <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">

        <input type="hidden" name="codInternacao" value="${codInternacao}">

        <label>Data e hora da internação:</label>
        <input type="datetime-local" required name="dataHora" value="${dataHora}">

        <label>Sala da internação:</label>
        <input type="number" required name="sala" value="${sala}">

        <label>Descrição:</label>
        <input type="text" required name="descricao" value="${descricao}">

        <label>Paciente:</label>
        <select name="pacienteInternacao">
            <c:forEach var="paciente" items="${listaPaciente}">
                <c:choose>
                    <c:when test="${paciente.codPaciente eq pacienteInternacao}">
                        <option selected value="${paciente.codPaciente}">${paciente.nomePaciente}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${paciente.codPaciente}">${paciente.nomePaciente}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <label>Funcionário:</label>
        <select name="funcionarioInternacao">
            <c:forEach var="funcionario" items="${listaFuncionario}">
                <c:choose>
                    <c:when test="${funcionario.codFunc eq funcionarioInternacao}">
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

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/InternacaoControlador" style="max-width: 700px; margin: 0 auto;">
        <input type="hidden" name="opcao" value="cancelar">
        <input type="submit" value="Cancelar" name="btnCancelar">
    </form>

    <br>
    <h3>${mensagem}</h3>

    <!-- ? NOVO FORMULÁRIO PARA EXPORTAÇÃO POR SALA -->
    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/InternacaoControlador" style="max-width: 700px; margin: 20px auto;">
        <input type="hidden" name="opcao" value="exportarExcel">
        <label for="salaFiltro">Selecione a sala:</label>
        <select name="salaFiltro" id="salaFiltro">
            <option value="">Todas</option>
            <c:forEach var="sala" items="${listaSalas}">
                <option value="${sala}">${sala}</option>
            </c:forEach>
        </select>
        <button type="submit">Exportar Excel</button>
    </form>

    <table>
        <c:if test="${not empty listaInternacao}">
            <tr>
                <th>CÓDIGO</th><th>DATA/HORA</th><th>SALA</th><th>DESCRIÇÃO</th><th>PACIENTE</th><th>FUNCIONÁRIO</th><th>ALTERAR</th><th>EXCLUIR</th>
            </tr>
        </c:if>
        <c:forEach var="internacao" items="${listaInternacao}">
            <tr>
                <td>${internacao.codInternacao}</td>
                <td>${internacao.dataHora}</td>
                <td>${internacao.sala}</td>
                <td>${internacao.descricao}</td>
                <td>${internacao.pacienteInternacao.nomePaciente}</td>
                <td>${internacao.funcionarioInternacao.nomeFunc}</td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/InternacaoControlador">
                        <input type="hidden" name="codInternacao" value="${internacao.codInternacao}">
                        <input type="hidden" name="dataHora" value="${internacao.dataHora}">
                        <input type="hidden" name="sala" value="${internacao.sala}">
                        <input type="hidden" name="descricao" value="${internacao.descricao}">
                        <input type="hidden" name="pacienteInternacao" value="${internacao.pacienteInternacao.codPaciente}">
                        <input type="hidden" name="funcionarioInternacao" value="${internacao.funcionarioInternacao.codFunc}">
                        <input type="hidden" name="opcao" value="editar">
                        <button type="submit">Editar</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/InternacaoControlador">
                        <input type="hidden" name="codInternacao" value="${internacao.codInternacao}">
                        <input type="hidden" name="dataHora" value="${internacao.dataHora}">
                        <input type="hidden" name="sala" value="${internacao.sala}">
                        <input type="hidden" name="descricao" value="${internacao.descricao}">
                        <input type="hidden" name="pacienteInternacao" value="${internacao.pacienteInternacao.codPaciente}">
                        <input type="hidden" name="funcionarioInternacao" value="${internacao.funcionarioInternacao.codFunc}">
                        <input type="hidden" name="opcao" value="excluir">
                        <button type="submit">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
