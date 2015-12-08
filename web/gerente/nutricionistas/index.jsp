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
                    <input id="search-nutri" type="search" name="searchQuery" required>
                    <label for="search-nutri"><i class="material-icons">search</i><span>Buscar Nutricionista</span></label>
                    <button class="btn waves-effect waves-light blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<Nutricionista> nutricionistas = (List) request.getAttribute("nutricionistas");
                    if(nutricionistas.size()>=0){
                        int index;
                        for(index=0; index < nutricionistas.size(); index++){                   
                            Nutricionista nutricionista = nutricionistas.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + nutricionista.getNome() + " - " + nutricionista.getFormatedCpf()+ "</span>";
                            htmlBody+="<form action='" + url + "nutricionistas' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + nutricionista.getId() + "'>";
                            htmlBody+="<input type='hidden' name='action' value='delete'>";
                            htmlBody+="<button class='btn  blue' type='submit'> <i class='material-icons right'>delete</i></button></form>";
                            htmlBody+="<a href='#'> <i class='material-icons right'>edit</i></a></li>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nao existem Atividades cadastrados.");
                    }
                %>
            </ul>
            <a href="<%=url%>nutricionistas?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
