<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro de Plano</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">

    <style>
        body {
            background-color: #E5F7F2;
            font-family: Arial, sans-serif;
            color: #003D3D;
            margin: 0px;
        }
        h1 { color: #2A8375; text-align: center; margin-bottom: 30px; }
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
        label { font-weight: bold; display: block; margin-bottom: 5px; color: #005C53; }
        input[type="text"], input[type="number"], input[type="time"], select {
            width: 100%; padding: 8px; margin-bottom: 15px;
            border-radius: 8px; border: 1px solid #90C2B3; box-sizing: border-box;
        }
        input[type="submit"], button {
            padding: 10px 20px; background-color: #90C2B3;
            border: none; color: #fff; font-weight: bold;
            border-radius: 10px; cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover, button:hover { background-color: #5EA395; }
        table { width: 100%; border-collapse: collapse; margin-top: 30px; }
        table, th, td { border: 1px solid #90C2B3; }
        th { background-color: #B0DDD2; color: #003D3D; padding: 10px; }
        td { text-align: center; padding: 10px; background-color: #F6FFFD; }
        h3 { text-align: center; color: #E03D3D; }
    </style>
</head>
<body>

    <%@include file="menu.jsp" %>

    <h1>Cadastro de Plano</h1>

    <!-- Formulário de cadastro -->
    <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/PlanoControlador">
        <input type="hidden" name="opcao" value="${opcao}">
        <input type="hidden" name="codPlano" value="${codPlano}">

        <p>
            <label>Nome do plano:</label>
            <input type="text" name="nomePlano" value="${nomePlano}" required placeholder="Digite o nome do plano">
        </p>
        <p>
            <label>Tipo do plano:</label>
            <input type="text" name="tipoPlano" value="${tipoPlano}" required placeholder="Digite o tipo do plano">
        </p>
        <p>
            <label>Valor do plano:</label>
            <input type="text" name="valorPlano" value="${valorPlano}" required placeholder="Digite o valor do plano">
        </p>
        <input type="submit" value="Salvar" name="btnSalvar">
    </form>

    <!-- Cancelar -->
    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PlanoControlador" style="max-width: 600px; margin: 0 auto;">
        <input type="hidden" name="opcao" value="cancelar">
        <input type="submit" value="Cancelar" name="btnCancelar">
    </form>

    <br>
    <h3>${mensagem}</h3>

    <!-- Formulário de filtro/exportação -->
    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PlanoControlador" 
          style="max-width: 600px; margin: 20px auto; background: #C2EAE3; padding: 20px; border-radius: 12px;">
        <input type="hidden" name="opcao" value="exportarExcel">

        <label>Filtrar por tipo de plano:</label>
            <select name="tipoPlano">
            <option value="">-- Todos --</option>
                <c:forEach var="tipo" items="${listaTipos}">
                    <option value="${tipo}" <c:if test="${tipo == filtroTipo}">selected</c:if>>
                    ${tipo}
                    </option>
                </c:forEach>
            </select>


        <button type="submit">Exportar para Excel</button>
    </form>

    <!-- Tabela -->
    <table>
        <c:if test="${not empty listaPlano}">
            <tr>
                <th>CÓDIGO</th>
                <th>PLANO</th>
                <th>TIPO</th>
                <th>VALOR</th>
                <th>ALTERAR</th>
                <th>EXCLUIR</th>
            </tr>
        </c:if>

        <c:forEach var="plano" items="${listaPlano}">
            <tr>
                <td>${plano.codPlano}</td>
                <td>${plano.nomePlano}</td>
                <td>${plano.tipoPlano}</td>
                <td>${plano.valorPlano}</td>
                <td> 
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PlanoControlador">
                        <input type="hidden" name="codPlano" value="${plano.codPlano}">
                        <input type="hidden" name="nomePlano" value="${plano.nomePlano}">
                        <input type="hidden" name="tipoPlano" value="${plano.tipoPlano}">
                        <input type="hidden" name="valorPlano" value="${plano.valorPlano}">
                        <input type="hidden" name="opcao" value="editar">
                        <button type="submit">Editar</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/PlanoControlador">
                        <input type="hidden" name="codPlano" value="${plano.codPlano}">
                        <input type="hidden" name="nomePlano" value="${plano.nomePlano}">
                        <input type="hidden" name="tipoPlano" value="${plano.tipoPlano}">
                        <input type="hidden" name="valorPlano" value="${plano.valorPlano}">
                        <input type="hidden" name="opcao" value="excluir">
                        <button type="submit">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
