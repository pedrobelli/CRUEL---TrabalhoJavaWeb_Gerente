<%@page import="usuarios.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%    
    HttpSession httpSession = request.getSession(false); 
    Usuario usuarioSession = (Usuario) httpSession.getAttribute("usuarioSession");

    boolean isLogin = false;
    if (usuarioSession == null) {
        isLogin = true;
    }
    
    String url = getServletContext().getContextPath() + "/"; 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Sistema CRUEL | admin </title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Sigmar+One|Alegreya+Sans+SC:400,300,700,400italic' rel='stylesheet' type='text/css'>
        <link href="<%=url%>assets/stylesheets/admin.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        
        <% 
            String loginUrl = new String();
            if (isLogin) {
                loginUrl = "";
            } else {
                loginUrl = "<li><a href='" + getServletContext().getContextPath() + "/logout'>Logout</a></li>" ; 
            }
        %>
        <header>
            <nav>
              <div class="nav-wrapper">
                <p class='brand-logo left'>Gerencia</p>
                <ul class="right">
                  <%=loginUrl%>
                </ul>
              </div>
            </nav>
        </header>
    