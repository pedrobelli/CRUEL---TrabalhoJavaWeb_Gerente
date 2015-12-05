<%@ include file="/header.jsp"%>
<main>
    <div class="container">
      <div class="row">
        <h2 class="center">Login</h2>
        <form class="login-form" action="index.html" method="post">
          <div class="input-field">
            <input id="email" type="email" class="validate">
            <label for="email">Email</label>
          </div>
          <div class="input-field">
            <input id="password" type="password" class="validate">
            <label for="password">Senha</label>
          </div>

          <button class="btn waves-effect waves-light blue right" type="submit" name="action">Ok
            <i class="material-icons right">send</i>
          </button>
        </form>
      </div>
    </div>
  </main>
<%@ include file="/footer.jsp"%>