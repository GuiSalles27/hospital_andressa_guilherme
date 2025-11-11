<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
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
            max-width: 600px;
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
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #005C53;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
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
            background-color: #2A8375;
            border: none;
            color: #fff;
            font-weight: bold;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        .btn:hover {
            background-color: #005C53;
        }

        .mensagem {
            text-align: center;
            color: #E03D3D;
            font-weight: bold;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><i class="fas fa-user-plus"></i> Cadastro de Usuário</h1>

        <div class="card">
            <form id="cadastroForm" name="cadastro" method="post" action="${pageContext.request.contextPath}${URL_BASE}/LoginControlador">
                <label>Username:</label>
                <input type="text" name="username" required>

                <label>Senha:</label>
                <input type="password" name="password" required>

                <label>Email:</label>
                <input type="email" name="email" required>

                <input type="hidden" name="opcao" value="cadastrar" />
                <button type="submit" class="btn"><i class="fas fa-save"></i> Cadastrar</button>
            </form>

            <c:if test="${not empty mensagem}">
                <p class="mensagem">${mensagem}</p>
            </c:if>
        </div>
    </div>
</body>
</html>
