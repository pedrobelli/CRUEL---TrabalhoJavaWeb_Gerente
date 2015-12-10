<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiposCliente.TipoCliente"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    TipoCliente tipoCliente = (TipoCliente) request.getAttribute("tipoCliente"); 
%>

<main class="main">

    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

        <h3 class="center">Tipos de Cliente</h3>
        <section>
            <div class="container">
                <h4>Editar Tipo</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>tiposCliente?action=update" method="post">
                    <input type="hidden" name="id" value="${tipoCliente.id}">
                      
                    <div class="input-field">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" value="${tipoCliente.nome}">
                    </div>
                    <div class="input-field">
                        <label for="valorRefeicao">Valor:</label>
                        <input type="text" name="valorRefeicao" class="currency" value="<%= tipoCliente.getValorRefeicaoWithTwoDecimals()%>">
                    </div>
                    
                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>tiposCliente" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
  </main>
<%@ include file="/footer.jsp"%>
