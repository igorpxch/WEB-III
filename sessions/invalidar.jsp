<%
    if (request.getSession().getAttribute("Inv�lido")== null){
    request.getSession().invalidate();
    response.sendRedirect("index.html");
    }
%>