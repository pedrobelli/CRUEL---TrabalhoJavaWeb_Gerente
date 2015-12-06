<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>  
<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

        <h3 class="center">Nutricionistas</h3>
        <section>
            <div class="container">
                <h4>Editar Nutricionista</h4>
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
                        <label for="nutri-address_zip">CEP</label>
                        <input type="text" name="nutri-address_zip">
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
                        <label for="nutri-address_number">Complemento</label>
                        <input type="text" name="nutri-address_number">
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
                        <select name="nutri-address_state">
                            <option value="" disabled selected>Escolha seu Estado</option>
                            <option value="1">Option 1</option>
                            <option value="2">Option 2</option>
                            <option value="3">Option 3</option>
                        </select>
                       <label for="nutri-address_state">Estado</label>
                    </div>
                      <div class="input-field">
                        <label for="nutri-address_number">Telefone</label>
                        <input type="text" name="nutri-address_number">
                     </div>
                     
                    <div class="input-field">
                        <label for="nutri-email">Email:</label>
                        <input type="email" name="nutri-email">
                    </div>
                    <div class="input-field">
                        <label for="nutri-email">Senha</label>
                        <input type="password" name="nutri-email">
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
