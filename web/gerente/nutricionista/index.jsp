<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="nutri">
    <div class="container row">

    <%@ include file="/gerente/actionbutton.jsp"%>


    <h3 class="center">Nutricionistas</h3>
    <section>
        <div class="container">
            <h4>Lista</h4>
            <form>
                <div class="input-field search-nutri" >
                    <input id="search-nutri" type="search" required>
                    <label for="search-nutri"><i class="material-icons">search</i><span>Buscar Nutricionista</span></label>
                </div>
            </form>
            <ul class="nutri-list">
                <li class="nutri-item">
                   <span>Nome Nutri</span>

                   <a href="#"> <i class="material-icons right">delete</i></a>
                   <a href="#"> <i class="material-icons right">edit</i></a>
                </li>
            </ul>
            <a href="new.html" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>
