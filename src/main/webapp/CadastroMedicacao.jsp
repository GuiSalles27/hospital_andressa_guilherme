<%-- 
    Document   : CadastroMedicacao
    Created on : 25 de mar de 2025, 13:18:30
    Author     : 15114560603
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
        <title>Cadastro de Medicações</title>
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
        <h1>Cadastro de Medicações</h1>
        <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/MedicacaoControlador">
             <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">
            <input type="hidden" name="codRemedio" value="${codRemedio}">
            <p><label>Tipo do remédio:</label>
                <input type="text" name="tipoRemedio" value="${tipoRemedio}" required placeholder="digite o tipo do remédio">
            </p>
            <p><label>Valor do remédio:</label>
                <input type="number" name="valorRemedio" value="${valorRemedio}" required placeholder="digite o valor do remédio">
            </p>
            <p><label>Nome do remédio:</label>
                <input type="text" name="nomeRemedio" value="${nomeRemedio}" required placeholder="digite o nome do remédio">
            </p>
            <p><label>Fabricação:</label>
                <input type="text" name="fabricacao" value="${fabricacao}" required placeholder="digite a fabricante do remédio">
            </p>
            <p><label>Desconto:</label>
                <input type="number" name="desconto" value="${desconto}" required placeholder="digite o desconto em %">
            </p>
            <input type="submit" value="Salvar" name="btnSalvar">
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/MedicacaoControlador" style="max-width: 600px; margin: 0 auto;">
            <input type="hidden" name="opcao" value="cancelar">
            <input type="submit" value="Cancelar" name="btnCancelar">
        </form>
        <br>
        <h3>${mensagem}</h3>
        
        <form action="${pageContext.request.contextPath}/RelatorioMedicacaoControlador" method="get">
            <label for="tipo">Selecione o tipo:</label>
            <select name="tipo" id="tipo">
                <option value="Antibiótico">Antibiótico</option>
                <option value="Anti-inflamatório">Anti-inflamatório</option>
                <option value="Soro">Soro</option>
                <option value="Pomada">Pomada</option>
            </select>
        <button type="submit">Gerar Relatório</button>
        </form>

        <table>
            <c:if test="${not empty listaMedicacao}">
                <tr>
                    <th>CÓDIGO</th>
                    <th>TIPO</th>
                    <th>VALOR</th>
                    <th>NOME</th>
                    <th>FABRICAÇÃO</th>
                    <th>DESCONTO</th>
                    <th>ALTERAR</th>
                    <th>EXCLUIR</th>
                </tr>
            </c:if>
            <c:forEach var="medicacao" items="${listaMedicacao}">
                <tr>
                    <td>${medicacao.codRemedio}</td>
                    <td>${medicacao.tipoRemedio}</td>
                    <td>${medicacao.valorRemedio}</td>
                    <td>${medicacao.nomeRemedio}</td>
                    <td>${medicacao.fabricacao}</td>
                    <td>${medicacao.desconto}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/MedicacaoControlador">
                            <input type="hidden" name="codRemedio" value="${medicacao.codRemedio}">
                            <input type="hidden" name="tipoRemedio" value="${medicacao.tipoRemedio}">
                            <input type="hidden" name="valorRemedio" value="${medicacao.valorRemedio}">
                            <input type="hidden" name="nomeRemedio" value="${medicacao.nomeRemedio}">
                            <input type="hidden" name="fabricacao" value="${medicacao.fabricacao}">
                            <input type="hidden" name="desconto" value="${medicacao.desconto}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/MedicacaoControlador">
                            <input type="hidden" name="codRemedio" value="${medicacao.codRemedio}">
                            <input type="hidden" name="tipoRemedio" value="${medicacao.tipoRemedio}">
                            <input type="hidden" name="valorRemedio" value="${medicacao.valorRemedio}">
                            <input type="hidden" name="nomeRemedio" value="${medicacao.nomeRemedio}">
                            <input type="hidden" name="fabricacao" value="${medicacao.fabricacao}">
                            <input type="hidden" name="desconto" value="${medicacao.desconto}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
