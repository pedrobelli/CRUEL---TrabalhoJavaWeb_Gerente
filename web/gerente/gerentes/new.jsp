<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="gerentes.Gerente"%>
<%@page import="usuarios.Usuario"%>
<%@page import="utils.EstadoEnum.Estado"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<% 
    Gerente gerente = (Gerente) request.getAttribute("gerente"); 
    Usuario usuario = (Usuario) request.getAttribute("usuario"); 
%>

<main class="main">
    <div class="container row">
        
        <%@ include file="/gerente/actionbutton.jsp"%>

        <h3 class="center">Gerentes</h3>
        <section>
            <div class="container">
                <h4>Novo Gerente</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>gerentes?action=create" method="post">
                      
                    <div class="input-field">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" value="${gerente.nome}">
                    </div>
                    <div class="input-field">
                        <label for="cpf">CPF:</label>
                        <input type="text" name="cpf" class="federal-id" value="${gerente.cpf}">

                    </div>
                                       
                    <div class="input-field">
                        <label for="cep">CEP</label>
                        <input type="text" name="cep" class="zip-code" value="${gerente.cep}">

                    </div>
                    <div class="input-field">
                        <label for="rua">Endereço</label>
                        <input type="text" name="rua" value="${gerente.rua}">
                    </div>
                    <div class="input-field">
                        <label for="numeroEndereco">Numero</label>
                        <input type="number" name="numeroEndereco" value="${gerente.numeroEndereco}">
                    </div>
                    <div class="input-field">
                        <label for="complemento">Complemento</label>
                        <input type="text" name="complemento" value="${gerente.complemento}">
                    </div>
                    <div class="input-field">
                        <label for="bairro">Bairro</label>
                        <input type="text" name="bairro" value="${gerente.bairro}">
                    </div>
                    <div class="input-field">
                        <label for="cidade">Cidade</label>
                        <input type="text" name="cidade" value="${gerente.cidade}">
                     </div>
                     
                    <div class="input-field">
                        <select name="estado" class="browser-default" value="">
                            <option value="" disabled selected>Estados</option>
                            <%
                                for(Estado estado : Estado.values()){ 
                                    if (estado.getCod() == gerente.getEstado()) {
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

                        <input type="text" name="numeroTelefone" class ="cellphones" value="${gerente.numeroTelefone}">
                    </div>
                    <div class="input-field">
                        <label for="numeroCelular">Celular</label>
                        <input type="text" name="numeroCelular" class ="cellphones" value="${gerente.numeroCelular}">

                    </div>
                     
                    <div class="input-field">
                        <label for="email">Email</label>
                        <input type="email" name="email" value="${usuario.email}">
                    </div>
                    <div class="input-field">
                        <label for="senha">Senha</label>
                        <input type="password" name="senha">
                    </div>
                    <div class="input-field">
                        <label for="confirmSenha">Confirmar Senha</label>
                        <input type="password" name="confirmSenha">
                    </div>

                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>gerentes" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
</main>
       
<%@ include file="/footer.jsp"%>