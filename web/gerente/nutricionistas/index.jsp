<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/gerente/actionbutton.jsp"%>

    <h3 class="center">Nutricionistas</h3>
    <section>
        <div class="container">
            <form>
                <div class="input-field main-search" >
                    <input id="search-nutri" type="search" required>
                    <label for="search-nutri"><i class="material-icons">search</i><span>Buscar Nutricionista</span></label>
                    <button class="btn waves-effect waves-light blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <li class="main-item">
                   <span>Nome Nutri</span>

                   <a href="#"> <i class="material-icons right">delete</i></a>
                   <a href="#"> <i class="material-icons right">edit</i></a>
                </li>
            </ul>
            <a href="<%=url%>nutricionistas?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
