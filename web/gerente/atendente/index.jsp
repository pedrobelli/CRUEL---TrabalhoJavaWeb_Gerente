
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

    <main class="main">
    <div class="container row">

      <%@ include file="/gerente/actionbutton.jsp"%>

      <h3 class="center">Atendente</h3>
      <section>
        <div class="container">
          <h4>Lista</h4>
          <form>
            <div class="input-field search-nutri" >
              <input id="search-atendente" type="search" required>
              <label for="search-nutri"><i class="material-icons">search</i><span>Buscar Atendente</span></label>
            </div>
          </form>
          <ul class="atendente-list">
            <li class="atendente-item">
              <span>Nome Nutri</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
             <a href="edit.html"> <i class="material-icons right">edit</i></a>
            </li>
            
          </ul>
          <a href="new.html" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>
