<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dispensação de Medicamentos para Internação</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #E5F7F2;
            font-family: Arial, sans-serif;
            color: #003D3D;
            margin: 0;
        }

        .container {
            padding: 20px;
            max-width: 1200px;
            margin: auto;
        }

        h1 {
            color: #2A8375;
            text-align: center;
            margin-bottom: 30px;
        }

        .card {
            background-color: #C2EAE3;
            padding: 20px;
            border-radius: 12px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .grid-container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            align-items: start;
        }

        .card h3 {
            margin-top: 0;
            color: #005C53;
            border-bottom: 2px solid #B0DDD2;
            padding-bottom: 10px;
            text-align: left;
        }
        
        .card p {
            font-size: 1.1em;
        }
        
        .card p strong {
            color: #005C53;
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
            background-color: #F6FFFD;
        }

        .btn {
            padding: 10px 20px;
            background-color: #90C2B3;
            border: none;
            color: #fff;
            font-weight: bold;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }

        .btn:hover {
            background-color: #5EA395;
        }
        
        .btn-success {
             background-color: #2A8375;
        }
        .btn-success:hover {
             background-color: #005C53;
        }
        
        .btn-danger {
            background-color: #E03D3D;
        }
        .btn-danger:hover {
            background-color: #c0392b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #90C2B3;
        }

        th {
            background-color: #B0DDD2;
            color: #003D3D;
            padding: 10px;
            text-align: left;
        }

        td {
            text-align: left;
            padding: 10px;
            background-color: #F6FFFD;
        }

        #total-carrinho {
            text-align: right;
            font-size: 1.2em;
            font-weight: bold;
            margin-top: 10px;
            color: #005C53;
        }
        
        .mensagem {
            text-align: center;
            color: #E03D3D;
            font-weight: bold;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <jsp:include page="menu.jsp" />

    <div class="container">
        <h1><i class="fas fa-pills"></i> Dispensação de Medicamentos por Internação</h1>

        <c:if test="${not empty mensagem}">
            <h3 class="mensagem">${mensagem}</h3>
        </c:if>

        <c:if test="${empty sessionScope.internacaoAtiva}">
            <div class="card" style="max-width: 600px; margin: auto;">
                <h3>Iniciar Dispensação</h3>
                <form action="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador" method="POST">
                    <input type="hidden" name="opcao" value="iniciarVenda">
                    <label for="internacao">Selecione a Internação:</label>
                    <select id="internacao" name="codInternacao" required>
                        <option value="">-- Selecione um Paciente Internado --</option>
                        <c:forEach var="i" items="${listaInternacoes}">
                            <option value="${i.codInternacao}">
                                Paciente: ${i.pacienteInternacao.nomePaciente} - Data: ${i.dataHora}
                            </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-success">Iniciar</button>
                </form>
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.internacaoAtiva}">
            <div class="card" style="margin-bottom: 20px;">
                <h3>Dispensação em Andamento</h3>
                <p><strong>Paciente:</strong> ${sessionScope.internacaoAtiva.pacienteInternacao.nomePaciente}</p>
                <p><strong>Data da Internação:</strong> ${sessionScope.internacaoAtiva.dataHora}</p>
                <a href="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador?opcao=cancelarVenda" class="btn btn-danger">Cancelar Dispensação</a>
            </div>

            <div class="grid-container">
                <div class="card">
                    <h3>Adicionar Medicamentos</h3>
                    <form action="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador" method="POST">
                        <input type="hidden" name="opcao" value="adicionarCarrinho">
                        <label for="medicacao-select">Medicamento:</label>
                        <select id="medicacao-select" name="medicacaoCod" required>
                             <c:forEach var="m" items="${listaMedicamentos}">
                                <option value="${m.codRemedio}">${m.nomeRemedio} - R$ <fmt:formatNumber value="${m.valorRemedio}" type="number" minFractionDigits="2"/></option>
                            </c:forEach>
                        </select>
                        <label for="quantidade">Quantidade:</label>
                        <input type="number" id="quantidade" name="quantidade" value="1" min="1">
                        <button type="submit" class="btn btn-primary">Adicionar ao Carrinho</button>
                    </form>
                </div>

                <div class="card">
                    <h3><i class="fas fa-shopping-cart"></i> Carrinho de Medicamentos</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Medicamento</th>
                                <th>Qtd.</th>
                                <th>Valor Unit.</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="total" value="0"/>
                            <c:forEach var="item" items="${sessionScope.carrinho}" varStatus="status">
                                <tr>
                                    <td>${item.medicacao.nomeRemedio}</td>
                                    <td>${item.quantidadeRemedio}</td>
                                    <td><fmt:formatNumber value="${item.medicacao.valorRemedio}" type="currency" currencySymbol="R$ "/></td>
                                    <td><a href="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador?opcao=removerCarrinho&index=${status.index}" class="btn btn-danger" style="padding: 5px 10px;">X</a></td>
                                </tr>
                                <c:set var="total" value="${total + (item.medicacao.valorRemedio * item.quantidadeRemedio)}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="total-carrinho">Total: <fmt:formatNumber value="${total}" type="currency" currencySymbol="R$ "/></div>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador?opcao=finalizarVenda" class="btn btn-success" style="width:100%; margin-top: 10px;">Finalizar Dispensação</a>
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>
