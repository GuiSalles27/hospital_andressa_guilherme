<%-- 
    Document   : menu.jsp
    Description: Componente de menu de navegação para ser incluído em outras páginas.
--%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    /* Estilos para a barra de navegação */
    .navbar {
        background-color: #B0DDD2; /* Tom escuro da paleta verde-água */
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        display: flex;
        justify-content: center; /* Centraliza os itens do menu */
    }
    
    .navbar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        display: flex;
    }

    .navbar ul li a, .navbar ul li .dropbtn {
        display: block;
        color: #005C53;
        text-align: center;
        padding: 14px 20px;
        text-decoration: none;
        font-weight: bold;
        font-family: Arial, sans-serif;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    /* Container do dropdown */
    .navbar ul li.dropdown {
        display: inline-block;
    }

    /* Conteúdo do dropdown (escondido por padrão) */
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f6fffd;
        min-width: 240px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1000;
        border-radius: 0 0 8px 8px;
        border: 1px solid #B0DDD2;
    }

    /* Links dentro do dropdown */
    .dropdown-content a {
        color: #003D3D;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        text-align: left;
    }
    .dropdown-content a i {
        margin-right: 10px;
    }

    /* Muda a cor dos links do dropdown ao passar o mouse */
    .dropdown-content a:hover {
        background-color: #C2EAE3;
    }

    /* Mostra o menu dropdown ao passar o mouse */
    .dropdown:hover .dropdown-content {
        display: block;
    }

    /* Muda a cor do botão dropdown quando o conteúdo do dropdown é mostrado */
    .dropdown:hover .dropbtn, .navbar ul li a:hover {
        background-color: #5EA395; /* Verde mais claro para hover */
        color: #003D3D;
        border-radius: 4px;
    }
    
    .login{
        padding-right: 4px;
    }
    
</style>

<nav class="navbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/homepage.jsp"><i class="fas fa-home"></i> Início</a></li>
        <li class="dropdown">
            <a href="javascript:void(0)" class="dropbtn"><i class="fas fa-edit"></i> Cadastros</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}${URL_BASE}/HospitalControlador"><i class="fas fa-hospital"></i> Hospitais</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/AmbulatorioControlador"><i class="fas fa-ambulance"></i> Ambulatórios</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/CargoControlador"><i class="fas fa-id-badge"></i> Cargos</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador"><i class="fas fa-user-md"></i> Funcionários</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/PlanoControlador"><i class="fas fa-file-medical-alt"></i> Planos de Saúde</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador"><i class="fas fa-user-injured"></i> Pacientes</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/MedicacaoControlador"><i class="fas fa-capsules"></i> Medicamentos</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="javascript:void(0)" class="dropbtn"><i class="fas fa-procedures"></i> Processos</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}${URL_BASE}/ConsultaControlador"><i class="fas fa-stethoscope"></i> Registrar Consulta</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/ExameControlador"><i class="fas fa-vial"></i> Registrar Exame</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/InternacaoControlador"><i class="fas fa-bed"></i> Registrar Internação</a>
                <a href="${pageContext.request.contextPath}${URL_BASE}/MedicacaoInternacaoControlador"><i class="fas fa-pills"></i> Dispensar Medicamentos</a>
            </div>
        </li>
        <li><a href="${pageContext.request.contextPath}/login.jsp"><i class="fas fa-user login"></i>Login</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/LoginControlador"><i class="fas fa-user login"></i>Logout</a></li>
        

    </ul>
</nav>
