<%@ page contentType="text/html;charset=Latin1" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
    <title>Recuperar Senha</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">

    <!-- Estilo padronizado do sistema -->
    <style>
        body {
            background-color: #E5F7F2;
            font-family: Arial, sans-serif;
            color: #003D3D;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .card {
            background-color: #C2EAE3;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        h2 {
            color: #2A8375;
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            text-align: left;
            color: #005C53;
        }

        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 8px;
            border: 1px solid #90C2B3;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 10px;
            background-color: #90C2B3;
            color: #fff;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #5EA395;
        }

        .mensagem {
            margin: 10px 0;
            color: #2A8375;
            font-weight: bold;
        }

        a {
            display: inline-block;
            margin-top: 15px;
            color: #2A8375;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #E03D3D;
        }
    </style>
</head>
<body>
    <div class="card">
        <h2>Recuperar Senha</h2>

        <c:if test="${not empty mensagem}">
            <p class="mensagem">${mensagem}</p>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/esqueci-senha">
            <label for="email">E-mail cadastrado:</label>
            <input type="email" id="email" name="email" required>
            <button type="submit">Enviar link de recuperação</button>
        </form>

        <a href="${pageContext.request.contextPath}/login.jsp">Voltar ao login</a>
    </div>
</body>
</html>
