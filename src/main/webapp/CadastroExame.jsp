<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
        <title>Cadastro Exame</title>
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

        <h1>Cadastro de Exame</h1>

        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ExameControlador">
            <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">
            <input type="hidden" name="codExame" value="${codExame}">

            <label>Tipo Exame</label>
            <input type="text" name="tipoExame" value="${tipoExame}" required>

            <label>Valor:</label>
            <input type="number" name="valor" value="${valor}" required>

            <label>Resultado:</label>
            <input type="text" name="resultado" value="${resultado}" required>

            <label>Paciente:</label>
            <select name="pacienteExame">
                <c:forEach var="paciente" items="${listaPaciente}">
                    <c:choose>
                        <c:when test="${paciente.codPaciente eq pacienteExame}">
                            <option selected value="${paciente.codPaciente}">${paciente.nomePaciente}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${paciente.codPaciente}">${paciente.nomePaciente}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

            <label>Funcionário:</label>
            <select name="funcionarioExame">
                <c:forEach var="funcionario" items="${listaFuncionario}">
                    <c:choose>
                        <c:when test="${funcionario.codFunc eq funcionarioExame}">
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

        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ExameControlador" style="max-width: 700px; margin: 0 auto;">
            <input type="hidden" name="opcao" value="cancelar">
            <input type="submit" value="Cancelar" name="btnCancelar">
        </form>

        <br>
        <h3>${mensagem}</h3>

        <!-- ? NOVO FORMULÁRIO DE RELATÓRIO -->
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ExameControlador" style="max-width: 700px; margin: 20px auto;">
            <input type="hidden" name="opcao" value="exportarExcel">
            <label>Selecione os pacientes:</label><br>
            <c:forEach var="paciente" items="${listaPaciente}">
                <input type="checkbox" name="pacientesSelecionados" value="${paciente.codPaciente}">
                ${paciente.nomePaciente}<br>
            </c:forEach>
            <br>
            <button type="submit">Gerar Relatório Excel</button>
        </form>


        <table>
            <c:if test="${not empty listaExame}">
                <tr>
                    <th>CÓDIGO</th>
                    <th>TIPO EXAME</th>
                    <th>VALOR</th>
                    <th>RESULTADO</th>
                    <th>PACIENTE</th>
                    <th>FUNCIONÁRIO</th>
                    <th>ALTERAR</th>
                    <th>EXCLUIR</th>
                </tr>
            </c:if>

            <c:forEach var="exame" items="${listaExame}">
                <tr>
                    <td>${exame.codExame}</td>
                    <td>${exame.tipoExame}</td>
                    <td>${exame.valor}</td>
                    <td>${exame.resultado}</td>
                    <td>${exame.pacienteExame.nomePaciente}</td>
                    <td>${exame.funcionarioExame.nomeFunc}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ExameControlador">
                            <input type="hidden" name="codExame" value="${exame.codExame}">
                            <input type="hidden" name="tipoExame" value="${exame.tipoExame}">
                            <input type="hidden" name="valor" value="${exame.valor}">
                            <input type="hidden" name="resultado" value="${exame.resultado}">
                            <input type="hidden" name="pacienteExame" value="${exame.pacienteExame.codPaciente}">
                            <input type="hidden" name="funcionarioExame" value="${exame.funcionarioExame.codFunc}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ExameControlador">
                            <input type="hidden" name="codExame" value="${exame.codExame}">
                            <input type="hidden" name="tipoExame" value="${exame.tipoExame}">
                            <input type="hidden" name="valor" value="${exame.valor}">
                            <input type="hidden" name="resultado" value="${exame.resultado}">
                            <input type="hidden" name="pacienteExame" value="${exame.pacienteExame.codPaciente}">
                            <input type="hidden" name="funcionarioExame" value="${exame.funcionarioExame.codFunc}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
