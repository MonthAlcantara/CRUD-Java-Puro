<!DOCTYPE html>
<%@page import="br.com.monthalcantara.service.ClienteService"%>
<%@page import="br.com.monthalcantara.model.Cliente"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function confirma(pi){
if(window.confirm("Vocês está certo?")){
	location.href= "cliente?id="+ pi+"&acao=esc";
}
	}
</script>
<body bgcolor="">
	<h1 align="center" >Cadastro de Clientes</h1>
	<hr>
	<div>
		<%
			String vazio = (String) request.getAttribute("vazio");
			String email = request.getParameter("email");
			if (vazio == null) {
				out.print("Voce deve preencher todos os campos");
			} else {
				out.print("Cadastrado com sucesso");
			}
			Cliente cli = (Cliente) request.getAttribute("cli");
			Cliente cliEditado; 
			if(cli!=null){
			%>
			<form  action="cliente">
			<input type="text" name="nome" value="<%=cli.getNome() %>" /> <br />
			<input type="text" name="login" value="<%=cli.getLogin() %>" /> <br />
			<input type="text" name="email" value="<%=cli.getEmail() %>" /> <br />
			<input type="password" name="senha" value="<%=cli.getSenha() %>" />
			<input type="hidden" name="i" value="<%=cli.getId()%>"/>
			<input type="hidden" name="acao2" value="<%="salva"%>"/>
			<br> <br> <input type="submit" value="Salvar" />
		</form>		
				<% 
		}else{
		%>
	</div>
	<form method="post" action="cliente">
		<input type="text" name="nome" placeholder="Digite seu nome" /> <br />
		<input type="text" name="login" placeholder="Digite seu login" /> <br />
		<input type="text" name="email" placeholder="Digite seu Email" /> <br />
		<input type="password" name="senha" placeholder="Digite sua senha" />


		<br> <br> <input type="submit" value="Salvar" />

	</form>
	<%
	}
		%>
	<table border="1" align="center">
		<tr>
			<h2 align="center">Emails Cadastrados</h2>
		</tr>
		<tr>
			<th><h5 align="center">ID</h5></th>
			<th><h5 align="center">Nome</h5></th>
			<th><h5 align="center">Email</h5></th>
			<th><h5 align="center">Login</h5></th>
			<th><h5 align="center">Senha</h5></th>
			<th><h5 align="center">Operação</h5></th>
		</tr>
		<%
			List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
			int i = 0;
			for (Cliente li : lista) {
		li.setId(i);
		%>
		<tr>
			<td><%=i%></td>
			<td><%=li.getNome()%></td>
			<td><%=li.getEmail()%></td>
			<td><%=li.getLogin()%></td>
			<td><%=li.getSenha()%></td>
			<td>
				<a href ="javascript:confirma(<%= i %>)"> Excluir </a>
				<a href ="cliente?id=<%=i%>&acao=edit"> Editar </a>
				<%
				i++;
			}
	%>
			</td>
		</tr>
	</table>
</body>
</html>