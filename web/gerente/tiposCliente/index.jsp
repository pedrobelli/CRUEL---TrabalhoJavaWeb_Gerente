<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiposCliente.TipoCliente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

    <h3 class="center">Tipos de Cliente</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>tiposCliente?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-tipo-cliente" type="search" name="searchQuery">
                    <label for="search-tipo-cliente"><span>Buscar Tipo de Cliente</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<TipoCliente> tiposCliente = (List) request.getAttribute("tiposCliente");
                    if(tiposCliente.size()>0){
                        int index;
                        for(index=0; index < tiposCliente.size(); index++){                   
                            TipoCliente tipoCliente = tiposCliente.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + tipoCliente.getNome() + "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "tiposCliente?action=delete' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + tipoCliente.getId() + "'>";
                            htmlBody+="<button class='btn btn-delete  blue' type='submit'> <i class='material-icons'>delete</i></button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "tiposCliente?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + tipoCliente.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nao existem Tipos de Cliente cadastrados.");
                    }
                %>
            </ul>
            <a href="<%=url%>tiposCliente?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
