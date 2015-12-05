
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>  
  <main class="atendente">
    <div class="container row">

       <%@ include file="/gerente/actionbutton.jsp"%>


      <h3 class="center">Atendentes</h3>
      <section>
        <div class="container">
          <h4>Editar Atendente</h4>
          <form class="cad-atendente" action="#" method="post">
            <div class="input-field">
              <label for="atendente-name">Nome:</label>
              <input type="text" name="atendente-name">
            </div>
            <div class="input-field">
              <label for="atendete-cpf">CPF:</label>
              <input type="text" name="atendente-cpf">
            </div>           
            <div class="input-field">
              <label for="atendente-address">Endereço</label>
              <input type="text" name="atendente-address">
            </div>
            <div class="input-field">
              <label for="atendente-email">Email:</label>
              <input type="email" name="atendente-email">
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
<%@ include file="/footer.jsp"%>