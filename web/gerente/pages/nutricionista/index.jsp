<%-- 
    Document   : index
    Created on : 24/11/2015, 22:05:58
    Author     : Layla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/gerente/pages/header.jsp"%>
<main>
    <main class="nutri">
    <div class="container row">

      <div class="fixed-action-btn">
        <a class="btn-floating btn-large blue">
          <i class="large material-icons">menu</i>
        </a>
        <ul>
          <li><a href="nutri\nutri_list.html" class="btn-floating blue lighten-2">NUT</a></li>
          <li><a href="ger_aten.html" class="btn-floating blue lighten-2 ">ATE</a></li>
          <li><a href="ger_geren.html" class="btn-floating blue lighten-2 ">GER</a></li>
          <li><a href="index.html" class="btn-floating blue lighten-2 ">REL</a></li>
        </ul>
      </div>

      <h3 class="center">Nutricionistas</h3>
      <section>
        <div class="container">
          <h4>Lista</h4>
          <form>
            <div class="input-field search-nutri" >
              <input id="search-nutri" type="search" required>
              <label for="search-nutri"><i class="material-icons">search</i><span>Buscar Nutricionista</span></label>
            </div>
          </form>
          <ul class="nutri-list">
            <li class="nutri-item">
              <span>Nome Nutri</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
             <a href="edit.html"> <i class="material-icons right">edit</i></a>
            </li>
            <li class="nutri-item">
              <span>Nome Nutri</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
              <a href="edit.html"> <i class="material-icons right">edit</i></a>
            </li>
            <li class="nutri-item">
              <span>Nome Nutri</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
             <a href="edit.html"> <i class="material-icons right">edit</i></a>
            </li>
            <li class="nutri-item">
              <span>Nome Nutri</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
             <a href="edit.html"> <i class="material-icons right">edit</i></a>
            </li>
            <li class="nutri-item">
              <span>Nome Nutri</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
             <a href="edit.html"> <i class="material-icons right">edit</i></a>
            </li>
          </ul>
          <a href="new.html" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
  </main>
</main>
    
<%@ include file="/gerente/pages/footer.jsp"%>
