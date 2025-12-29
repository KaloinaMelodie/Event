<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../header/headeremploye.jsp"/>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Blank page
            <small>it all starts here</small>
            <form action="/Event/test.do" method="POST">
                <input type="submit" value="ok">
            </form>
            <%if(request.getParameter("error")!=null){%>
            <b>error</b>
            <%}%>
        </h1>
    </section>
</div>

