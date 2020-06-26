<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#mioMenu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home.jsp">Gruppo Treviso</a>
		</div>
		<div class="collapse navbar-collapse" id="mioMenu">
			<%
				String username = (String) session.getAttribute("username");
			if (username == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login.jsp"> <span
						class="glyphicon glyphicon-log-in"></span> Login
				</a></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav">
				<li><a href="report.jsp">Statistiche&nbsp;<i class="glyphicon glyphicon-stats"></i></a></li>
				<li><a href="rimuovicorso.jsp">Corsi futuri&nbsp;<i class="glyphicon glyphicon-book"></i></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <span class="glyphicon glyphicon-user"></span>
						<%=username%>
				</a></li>
				<li><a href="logout.jsp"> <span
						class="glyphicon glyphicon-off"></span> Logout
				</a></li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</nav>