<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="nutricionistas.Nutricionista"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

    <h3 class="center">Nutricionistas</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>nutricionistas?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-nutri" type="search" name="searchQuery">
                    <label for="search-nutri"><span>Buscar Nutricionista</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<Nutricionista> nutricionistas = (List) request.getAttribute("nutricionistas");
                    if(nutricionistas.size()>0){
                        int index;
                        for(index=0; index < nutricionistas.size(); index++){                   
                            Nutricionista nutricionista = nutricionistas.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + nutricionista.getNome() + " - " + nutricionista.getFormatedCpf()+ "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "nutricionistas?action=delete' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + nutricionista.getId() + "'>";
                            htmlBody+="<button class='btn btn-delete  blue' type='submit'> <i class='material-icons'>delete</i></button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "nutricionistas?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + nutricionista.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nenhum nutricionista foi encontrado.<br>");
                    }
                %>
            </ul>
            <a href="<%=url%>nutricionistas?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
