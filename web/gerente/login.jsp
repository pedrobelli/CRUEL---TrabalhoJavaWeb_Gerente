<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>
<main>
    <div class="container">
      <div class="row">
        <h2 class="center">Login</h2>
        <%
            if (request.getAttribute("mensagem") != null) {
                out.println("<p>"+request.getAttribute("mensagem")+"</p>");
            } 
        %>
        <form class="login-form" action="<%=url%>usuarios?action=login" method="post">
          <div class="input-field">
            <input name="email" type="email" class="validate">
            <label for="email">Email</label>
          </div>
          <div class="input-field">
            <input name="senha" type="password" class="validate">
            <label for="senha">Senha</label>
          </div>

          <button class="btn waves-effect waves-light blue right" type="submit" name="action">Login
            <i class="material-icons right">send</i>
          </button>
        </form>
      </div>
    </div>
  </main>
<%@ include file="/footer.jsp"%>