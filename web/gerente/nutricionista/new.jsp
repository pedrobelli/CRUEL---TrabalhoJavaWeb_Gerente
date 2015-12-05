<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">

    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

        <h3 class="center">Nutricionistas</h3>
        <section>
            <div class="container">
                <h4>Novo Nutricionista</h4>
               <form class="cadast" action="index.html" method="post">
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
                        <label for="nutri-address_zip">Endereço</label>
                        <input type="number" name="nutri-address_zip">
                    </div>
                    <div class="input-field">
                        <label for="nutri-address_street">Endereço</label>
                        <input type="text" name="nutri-address_street">
                    </div>
                    <div class="input-field">
                        <label for="nutri-address_number">Numero</label>
                        <input type="number" name="nutri-address_number">
                    </div>
                    <div class="input-field">
                        <label for="nutri-address_neighborhood">Bairro</label>
                        <input type="text" name="nutri-address_neighborhood">
                    </div>
                    <div class="input-field">
                        <label for="nutri-address_city">Cidade</label>
                        <input type="text" name="nutri-address_city">
                    </div>
                    <div class="input-field">
                        <label for="nutri-address_state">Estado</label>
                        <input type="text" name="nutri-address_state">
                    </div>
                     
                    <div class="input-field">
                        <label for="nutri-email">Email:</label>
                        <input type="email" name="nutri-email">
                    </div>

                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>nutricionistas" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
</main>
       
<%@ include file="/footer.jsp"%>