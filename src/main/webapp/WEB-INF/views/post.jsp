<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
			<h1>Post</h1>
			<p>
				titulo: <c:out value="${post.titulo}"/>
			</p>
			<p>
				contenido: <c:out value="${post.contenido}"/>
			</p>

</body>
</html>
