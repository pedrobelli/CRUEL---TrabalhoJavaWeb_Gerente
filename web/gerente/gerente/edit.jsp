
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>  
  <main class="main">
    <div class="container row">

       <%@ include file="/gerente/actionbutton.jsp"%>


      <h3 class="center">Gerentes	</h3>
      <section>
        <div class="container">
          <h4>Editar Gerente</h4>
          <form class="cadast" action="index.html" method="post">
                    <div class="input-field">
                        <label for="gerente-name">Nome:</label>
                        <input type="text" name="gerente-name">
                    </div>
                    <div class="input-field">
                        <label for="gerente-cpf">CPF:</label>
                        <input type="text" name="gerente-cpf">
                    </div>
                    
                    <div class="input-field">
                        <label for="gerente-address_zip">CEP</label>
                        <input type="text" name="gerente-address_zip">
                    </div>
                    <div class="input-field">
                        <label for="gerente-address_street">Endereço</label>
                        <input type="text" name="gerente-address_street">
                    </div>
                    <div class="input-field">
                        <label for="gerente-address_number">Numero</label>
                        <input type="number" name="gerente-address_number">
                    </div>
                    <div class="input-field">
                        <label for="gerente-address_neighborhood">Bairro</label>
                        <input type="text" name="gerente-address_neighborhood">
                    </div>
                    <div class="input-field">
                        <label for="gerente-address_city">Cidade</label>
                        <input type="text" name="gerente-address_city">
                    </div>
                    <div class="input-field">
                        <select name="atendete-address_state">
                            <option value="" disabled selected>Escolha seu Estado</option>
                            <option value="1">Option 1</option>
                            <option value="2">Option 2</option>
                            <option value="3">Option 3</option>
                        </select>
                    </div>
                    
                     
                    <div class="input-field">
                        <label for="gerente-address_state">Telefone</label>
                        <input type="text" name="gerente-address_state">
                    </div>
                     
                    <div class="input-field">
                        <label for="gerente-email">Email:</label>
                        <input type="email" name="gerente-email">
                    </div>
                    <div class="input-field">
                        <label for="gerente-senha">Senha</label>
                        <input type="password" name="gerente-seha">
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