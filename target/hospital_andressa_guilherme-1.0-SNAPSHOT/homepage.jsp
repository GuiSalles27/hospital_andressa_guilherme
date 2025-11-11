<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema de Gestão Hospitalar</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #E5F7F2;
            font-family: Arial, sans-serif;
            color: #003D3D;
            margin: 0;
        }
        .main-container {
            padding: 20px;
        }
        .header {
            background: linear-gradient(135deg, #2A8375, #5EA395);
            color: white;
            padding: 40px 20px;
            text-align: center;
            border-radius: 0 0 20px 20px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            margin: -20px -20px 40px -20px;
        }
        .header h1 {
            margin: 0;
            font-size: 2.8em;
            font-weight: bold;
            text-shadow: 1px 1px 2px rgba(0,0,0,0.2);
        }
        .header p {
            font-size: 1.2em;
            opacity: 0.9;
        }
        
        .quick-access {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 25px;
            max-width: 1200px;
            margin: 0 auto;
        }
        .access-card {
            background-color: #C2EAE3;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: center;
            text-decoration: none;
            color: #003D3D;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border-top: 4px solid #90C2B3;
        }
        .access-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 12px 24px rgba(0,0,0,0.15);
        }
        .access-card i {
            font-size: 3.5em;
            color: #2A8375;
            margin-bottom: 20px;
        }
        .access-card h3 {
            margin: 0;
            color: #005C53;
            font-size: 1.3em;
        }
    </style>
</head>
<body>
    <jsp:include page="menu.jsp" />

    <div class="main-container">
        <div class="header">
            <h1>Sistema de Gestão Hospitalar</h1>
            <p>Acesso rápido às principais funcionalidades do sistema.</p>
        </div>

        <div class="quick-access">
            <a href="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador" class="access-card">
                <i class="fas fa-user-injured"></i>
                <h3>Gerenciar Pacientes</h3>
            </a>
            <a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" class="access-card">
                <i class="fas fa-user-md"></i>
                <h3>Gerenciar Funcionários</h3>
            </a>
            <a href="${pageContext.request.contextPath}${URL_BASE}/ConsultaControlador" class="access-card">
                <i class="fas fa-stethoscope"></i>
                <h3>Registrar Consulta</h3>
            </a>
            <a href="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador" class="access-card">
                <i class="fas fa-pills"></i>
                <h3>Dispensar Medicamentos</h3>
            </a>
        </div>
    </div>
</body>
</html>
