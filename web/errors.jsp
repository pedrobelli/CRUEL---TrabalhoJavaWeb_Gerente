<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<div>
<% 
    List<String> errors = (List) request.getAttribute("errors"); 
    
    if (errors != null) {
        String title = errors.get(errors.size() - 1);
        out.println(title+"<br/>");
        int index;
        for(index=0; index < errors.size()-1; index++){
            out.println(" - "+errors.get(index)+"<br/>");
        }
    }
%>    
</div>
