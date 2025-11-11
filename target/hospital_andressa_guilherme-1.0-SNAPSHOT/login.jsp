<%@ page language="java" contentType="text/html; charset=Latin1" pageEncoding="Latin1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login - Sistema Hospitalar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=Latin1">
        <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">

        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
                <div class="text-center mb-4">
                    <img src="${pageContext.request.contextPath}/img/favicon.png" alt="Logo Hospital" style="max-width: 100px;">
                    <h4 class="mt-3">Acesso ao Sistema</h4>
                </div>

                <form method="post" action="${pageContext.request.contextPath}${URL_BASE}/LoginControlador">
                    <div class="mb-3">
                        <label class="form-label">Usuário</label>
                        <input type="text" name="username" class="form-control" placeholder="Digite seu usuário" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Senha</label>
                        <input type="password" name="password" class="form-control" placeholder="Digite sua senha" required>
                    </div>

                    <input type="hidden" name="opcao" value="login" />

                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary">Entrar</button>
                        <a href="${pageContext.request.contextPath}/CadastroUsuario.jsp" class="btn btn-outline-secondary">Cadastrar Usuário</a>
                    </div>
                </form>
                <p class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/esqueci-senha.jsp" 
                       class="text-decoration-none fw-bold">
                        Esqueceu sua senha?
                    </a>
                </p>

                <c:if test="${not empty mensagem}">
                    <div class="alert alert-danger mt-3" role="alert">
                        ${mensagem}
                    </div>
                </c:if>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
