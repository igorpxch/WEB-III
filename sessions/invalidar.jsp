<%
    if (request.getSession().getAttribute("Inválido")== null){
    request.getSession().invalidate();
    response.sendRedirect("index.html");
    }
%>