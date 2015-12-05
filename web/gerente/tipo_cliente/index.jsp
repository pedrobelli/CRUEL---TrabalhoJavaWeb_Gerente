<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

    <%@ include file="/gerente/actionbutton.jsp"%>


    <h3 class="center">Tipos de Cliente</h3>
    <section>
        <div class="container">
            <form>
                <div class="input-field main-search" >
                    <input id="search-tipo-cliente" type="search" required>
                    <label for="search-tipo-cliente"><i class="material-icons">search</i><span>Buscar Tipos de Cliente</span></label>
                </div>
            </form>
            <ul class="main-list">
                <li class="main-item">
                   <span>Tipo</span>

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
