<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>
<main class="main">

    <div class="container row">

       <%@ include file="/gerente/actionbutton.jsp"%>


        <h3 class="center">Tipos de Cliente</h3>
        <section>
            <div class="container">
                <h4>Novo Tipo</h4>
               <form class="cadast" action="index.html" method="post">
                    <div class="input-field">
                        <label for="tipo-cli-name">Nome:</label>
                        <input type="text" name="tipo-cli-name">
                    </div>
                    <div class="input-field">
                        <label for="tipo-cli-valor">Valor:</label>
                        <input type="text" name="tipo-cli-valor">
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

