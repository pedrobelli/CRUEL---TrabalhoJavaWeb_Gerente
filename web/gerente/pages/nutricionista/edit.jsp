<%-- 
    Document   : edit
    Created on : 24/11/2015, 22:21:32
    Author     : Layla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/gerente/pages/header.jsp"%>  
  <main class="nutri">
    <div class="container row">

       <%@ include file="/gerente/pages/actionbutton.jsp"%>


      <h3 class="center">Nutricionistas</h3>
      <section>
        <div class="container">
          <h4>Editar Nutricionista</h4>
          <form class="cad-nutri" action="index.html" method="post">
            <div class="input-field">
              <label for="nutri-name">Nome:</label>
              <input type="text" name="nutri-name">
            </div>
            <div class="input-field">
              <label for="nutri-cpf">CPF:</label>
              <input type="text" name="nutri-cpf">
            </div>
            <div class="input-field">
              <label for="nutri-crn">CRN:</label>
              <input type="text" name="nutri-crn">
            </div>
            <div class="input-field">
              <label for="nutri-address">Endereço</label>
              <input type="text" name="nutri-address">
            </div>
            <div class="input-field">
              <label for="nutri-email">Emai:</label>
              <input type="email" name="nutri-email">
            </div>

              <button class="btn blue right" type="submit" name="action">Ok
               <i class="material-icons right">send</i>
             </button>
             <a class="waves-effect waves-teal btn-flat right">Cancelar</a>
          </form>
        </div>
      </section>
    </div>
  </main>
<%@ include file="/gerente/pages/footer.jsp"%>
