<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="fixed-action-btn">
    <a class="btn-floating btn-large blue">
      <i class="large material-icons">menu</i>
    </a>
    <ul>
      <li><a href="<%=getServletContext().getContextPath()%>/atendentes" class="btn-floating blue lighten-2 ">ATE</a></li>
      <li><a href="<%=getServletContext().getContextPath()%>/gerentes" class="btn-floating blue lighten-2 ">GER</a></li>
      <li><a href="<%=getServletContext().getContextPath()%>/nutricionistas" class="btn-floating blue lighten-2">NUT</a></li>
      <li><a href="<%=getServletContext().getContextPath()%>/tiposCliente" class="btn-floating blue lighten-2">CAT</a></li>
      <li><a href="#" class="btn-floating blue lighten-2 ">REL</a></li>
    </ul>
</div>
