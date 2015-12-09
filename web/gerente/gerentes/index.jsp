<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="gerentes.Gerente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

    <h3 class="center">gerentes</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>gerentes?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-gerente" type="search" name="searchQuery">
                    <label for="search-gerente"><span>Buscar Gerente</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<Gerente> gerentes = (List) request.getAttribute("gerentes");
                    if(gerentes.size()>0){
                        int index;
                        for(index=0; index < gerentes.size(); index++){                   
                            Gerente Gerente = gerentes.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + Gerente.getNome() + " - " + Gerente.getFormatedCpf()+ "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "gerentes' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + Gerente.getId() + "'>";
                            htmlBody+="<input type='hidden' name='action' value='delete'>";
                            htmlBody+="<button class='btn btn-delete  blue' type='submit'> <i class='material-icons'>delete</i></button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "gerentes?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + Gerente.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nenhum Gerente foi encontrado.<br>");
                    }
                %>
            </ul>
            <a href="<%=url%>gerentes?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
