<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="atendentes.Atendente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

    <h3 class="center">atendentes</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>atendentes?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-atendente" type="search" name="searchQuery">
                    <label for="search-atendente"><span>Buscar Atendente</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<Atendente> atendentes = (List) request.getAttribute("atendentes");
                    if(atendentes.size()>0){
                        int index;
                        for(index=0; index < atendentes.size(); index++){                   
                            Atendente Atendente = atendentes.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + Atendente.getNome() + " - " + Atendente.getFormatedCpf()+ "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "atendentes' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + Atendente.getId() + "'>";
                            htmlBody+="<input type='hidden' name='action' value='delete'>";
                            htmlBody+="<button class='btn btn-delete  blue' type='submit'> <i class='material-icons'>delete</i></button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "atendentes?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + Atendente.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nenhum Atendente foi encontrado.<br>");
                    }
                %>
            </ul>
            <a href="<%=url%>atendentes?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
