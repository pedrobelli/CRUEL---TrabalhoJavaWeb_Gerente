<%-- 
    Document   : header
    Created on : 21/11/2015, 19:26:49
    Author     : Layla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Sistema CRUEL | admin </title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Sigmar+One|Alegreya+Sans+SC:400,300,700,400italic' rel='stylesheet' type='text/css'>
        <link href="assets/stylesheets/admin.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        <% String loginUrl = "<a href='" + getServletContext().getContextPath() + "/login'>Login</a>" ; %>
        <% String relatoriosUrl = "<a class='brand-logo left' href='" + getServletContext().getContextPath() + "/relatorios'>Gerencia</a>" ; %>
        <header>
            <nav>
              <div class="nav-wrapper">
                <%=relatoriosUrl%>
                <ul class="right">
                  <li><%=loginUrl%></li>
                </ul>
              </div>
            </nav>
        </header>
    