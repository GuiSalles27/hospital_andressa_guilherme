<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Cadastro Ambulatório</title>
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

        input[type="time"],
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

    <h1>Cadastro de Ambulatório</h1>

    <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/AmbulatorioControlador">
         <input type="hidden" name="opcao" value="${empty opcao ? 'cadastrar' : opcao}">
        <input type="hidden" name="codAmbulatorio" value="${codAmbulatorio}">

        <p>
            <label>Horário Atendimento:</label>
            <input type="time" name="horarioAtendimento" value="${horarioAtendimento}" required>
        </p>

        <p>
            <label>Quantidade de Ambulâncias:</label>
            <input type="number" name="quantAmbulancias" value="${quantAmbulancias}" required placeholder="Digite a quantidade de ambulâncias" size="60">
        </p>

        <p>
            <label>Hospital:</label>
            <select name="hospitalAmbulatorio">
                <c:forEach var="hospital" items="${listaHospital}">
                    <c:choose>
                        <c:when test="${hospital.codHospital eq hospitalAmbulatorio}">
                            <option selected value="${hospital.codHospital}">${hospital.descricao}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${hospital.codHospital}">${hospital.descricao}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="Salvar" name="btnSalvar">
    </form>

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/AmbulatorioControlador" style="max-width: 600px; margin: 0 auto;">
        <input type="hidden" name="opcao" value="cancelar">
        <input type="submit" value="Cancelar" name="btnCancelar">
    </form>

    <br>
    <h3>${mensagem}</h3>
    
    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/AmbulatorioControlador" style="max-width: 600px; margin: 20px auto;">
    <input type="hidden" name="opcao" value="relatorioExcel">

    <label>Gerar Relatório de Ambulatório por Hospital:</label>
    <select name="hospitalRelatorio" required>
        <c:forEach var="hospital" items="${listaHospital}">
            <option value="${hospital.codHospital}">${hospital.descricao}</option>
        </c:forEach>
    </select>

    <button type="submit">Gerar Excel</button>
</form>

    <table>
        <c:if test="${not empty listaAmbulatorio}">
            <tr>
                <th>CÓDIGO</th>
                <th>HORÁRIO ATENDIMENTO</th>
                <th>QUANTIDADE AMBULÂNCIAS</th>
                <th>HOSPITAL</th>
                <th>ALTERAR</th>
                <th>EXCLUIR</th>
            </tr>
        </c:if>

        <c:forEach var="ambulatorio" items="${listaAmbulatorio}">
            <tr>
                <td>${ambulatorio.codAmbulatorio}</td>
                <td>${ambulatorio.horarioAtendimento}</td>
                <td>${ambulatorio.quantAmbulancias}</td>
                <td>${ambulatorio.hospitalAmbulatorio.descricao}</td>
                <td> 
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/AmbulatorioControlador"> 
                        <input type="hidden" name="codAmbulatorio" value="${ambulatorio.codAmbulatorio}">
                        <input type="hidden" name="horarioAtendimento" value="${ambulatorio.horarioAtendimento}">
                        <input type="hidden" name="quantAmbulancias" value="${ambulatorio.quantAmbulancias}">
                        <input type="hidden" name="hospitalAmbulatorio" value="${ambulatorio.hospitalAmbulatorio.codHospital}">                                 
                        <input type="hidden" name="opcao" value="editar">
                        <button type="submit">Editar</button>
                    </form>
                </td>                      
                <td>
                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/AmbulatorioControlador">
                        <input type="hidden" name="codAmbulatorio" value="${ambulatorio.codAmbulatorio}">
                        <input type="hidden" name="horarioAtendimento" value="${ambulatorio.horarioAtendimento}">
                        <input type="hidden" name="quantAmbulancias" value="${ambulatorio.quantAmbulancias}">
                        <input type="hidden" name="hospitalAmbulatorio" value="${ambulatorio.hospitalAmbulatorio.codHospital}">   
                        <input type="hidden" name="opcao" value="excluir">
                        <button type="submit">Excluir</button>
                    </form>
                </td>                                                    
            </tr>
        </c:forEach>
    </table>

</body>
</html>
