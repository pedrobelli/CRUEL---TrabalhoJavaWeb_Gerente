<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="nutricionistas.Nutricionista"%>
<%@page import="usuarios.Usuario"%>
<%@page import="utils.EstadoEnum.Estado"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>  

<% 
    Nutricionista nutricionista = (Nutricionista) request.getAttribute("nutricionista");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

        <h3 class="center">Nutricionistas</h3>
        <section>
            <div class="container">
                <h4>Editar Nutricionista</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>nutricionistas?action=update" method="post">
                    <input type="hidden" name="id" value="${nutricionista.id}">
                      
                    <div class="input-field">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" value="${nutricionista.nome}">
                    </div>
                    <div class="input-field">
                        <label for="cpf">CPF:</label>
                        <input type="text" name="cpf" class="federal-id" value="${nutricionista.cpf}">
                    </div>
                    <div class="input-field">
                        <label for="crn">CRN:</label>
                        <input type="text" name="crn" value="${nutricionista.crn}">
                    </div>
                   
                    <div class="input-field">
                        <label for="cep">CEP</label>
                        <input type="text" name="cep" class="zip-code" value="${nutricionista.cep}">
                    </div>
                    <div class="input-field">
                        <label for="nrua">Endereço</label>
                        <input type="text" name="rua" value="${nutricionista.rua}">
                    </div>
                    <div class="input-field">
                        <label for="numeroEndereco">Numero</label>
                        <input type="number" name="numeroEndereco" value="${nutricionista.numeroEndereco}">
                    </div>
                    <div class="input-field">
                        <label for="complemento">Complemento</label>
                        <input type="text" name="complemento" value="${nutricionista.complemento}">
                    </div>
                    <div class="input-field">
                        <label for="bairro">Bairro</label>
                        <input type="text" name="bairro" value="${nutricionista.bairro}">
                    </div>
                    <div class="input-field">
                        <label for="cidade">Cidade</label>
                        <input type="text" name="cidade" value="${nutricionista.cidade}">
                    </div>
                   
                    <div class="input-field">
                        <select name="estado" class="browser-default" value="">
                           <option value="" disabled selected>Estados</option>
                            <%
                                for(Estado estado : Estado.values()){ 
                                    if (estado.getCod() == nutricionista.getEstado()) {
                                        out.println("<option value='" + estado.getCod() + "' selected>" + estado.getNome() + "</option>");
                                    } else {
                                        out.println("<option value='" + estado.getCod() + "'>" + estado.getNome() + "</option>");
                                    }

                                }
                            %>
                         </select>
                    </div>
                   
                    <div class="input-field">
                        <label for="numeroTelefone">Telefone</label>
                        <input type="text" name="numeroTelefone" class ="cellphones" value="<%=nutricionista.getTelefone()%>">
                    </div>
                    <div class="input-field">
                        <label for="numeroCelular">Celular</label>
                        <input type="text" name="numeroCelular" class ="cellphones" value="<%=nutricionista.getCelular()%>">
                    </div>
                     
                    <div class="input-field">
                        <label for="email">Email:</label>
                        <input type="email" name="email" value="${usuario.email}">
                    </div>
                    <div class="input-field">
                        <label for="senha">Senha Antiga</label>
                        <input type="password" name="senhaAntiga">
                    </div>
                    <div class="input-field">
                        <label for="senha">Nova Senha</label>
                        <input type="password" name="senha">
                    </div>
                    <div class="input-field">
                        <label for="confirmSenha">Confirmar Nova Senha</label>
                        <input type="password" name="confirmSenha">
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
