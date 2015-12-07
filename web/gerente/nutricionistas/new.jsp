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
               <form class="cadast" action="<%=url%>nutricionistas?action=create" method="post">
                      
                    <div class="input-field">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome">
                    </div>
                    <div class="input-field">
                        <label for="cpf">CPF:</label>
                        <input type="text" name="cpf">
                    </div>
                    <div class="input-field">
                        <label for="crn">CRN:</label>
                        <input type="text" name="crn">
                    </div>
                   
                    <div class="input-field">
                        <label for="cep">CEP</label>
                        <input type="text" name="cep">
                    </div>
                    <div class="input-field">
                        <label for="nrua">Endereço</label>
                        <input type="text" name="rua">
                    </div>
                    <div class="input-field">
                        <label for="numeroEndereco">Numero</label>
                        <input type="number" name="numeroEndereco">
                    </div>
                   <div class="input-field">
                        <label for="complemento">Complemento</label>
                        <input type="text" name="complemento">
                   </div>
                    <div class="input-field">
                        <label for="bairro">Bairro</label>
                        <input type="text" name="bairro">
                    </div>
                    <div class="input-field">
                        <label for="cidade">Cidade</label>
                        <input type="text" name="cidade">
                    </div>
                   <div>
                   <div class="input-field">
                        <select name="estado" class="browser-default">
                           <option value="" disabled selected>Estado</option>
                           <option value="1">Option 1</option>
                           <option value="2">Option 2</option>
                           <option value="3">Option 3</option>
                         </select>
                    </div>
                   </div>
                   
                    <div class="input-field">
                        <label for="numeroTelefone">Telefone</label>
                        <input type="text" name="numeroTelefone">
                     </div>
                    <div class="input-field">
                        <label for="numeroCelular">Celular</label>
                        <input type="text" name="numeroCelular">
                     </div>
                     
                    <div class="input-field">
                        <label for="email">Email:</label>
                        <input type="email" name="email">
                    </div>
                    <div class="input-field">
                        <label for="senha">Senha</label>
                        <input type="password" name="senha">
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