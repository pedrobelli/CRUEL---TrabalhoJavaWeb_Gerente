<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>
<main class="main">

    <div class="container row">

       <%@ include file="/gerente/actionbutton.jsp"%>


        <h3 class="center">Atendentes</h3>
        <section>
            <div class="container">
                <h4>Novo Atendente</h4>
               <form class="cadast" action="index.html" method="post">
                    <div class="input-field">
                        <label for="atendente-name">Nome:</label>
                        <input type="text" name="atendente-name">
                    </div>
                    <div class="input-field">
                        <label for="atendente-cpf">CPF:</label>
                        <input type="text" name="atendente-cpf">
                    </div>
                    
                    <div class="input-field">
                        <label for="atendente-address_zip">Endereço</label>
                        <input type="number" name="atendente-address_zip">
                    </div>
                    <div class="input-field">
                        <label for="atendente-address_street">Endereço</label>
                        <input type="text" name="atendente-address_street">
                    </div>
                    <div class="input-field">
                        <label for="atendente-address_number">Numero</label>
                        <input type="number" name="atendente-address_number">
                    </div>
                    <div class="input-field">
                        <label for="atendente-address_neighborhood">Bairro</label>
                        <input type="text" name="atendente-address_neighborhood">
                    </div>
                    <div class="input-field">
                        <label for="atendente-address_city">Cidade</label>
                        <input type="text" name="atendente-address_city">
                    </div>
                    <div class="input-field">
                        <label for="atendente-address_state">Estado</label>
                        <input type="text" name="atendente-address_state">
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
