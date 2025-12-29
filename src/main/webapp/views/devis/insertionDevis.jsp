
<%@page import="com.example.event.devis.Devis"%>
<%@page import="script.InputTypes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="script.Form"%>
<jsp:include page="../../header/headeremploye.jsp"/>
<%
    Devis object = (Devis) request.getAttribute("obj");
    Form insertform = new Form(object, null);
    insertform.setAdddef(true);
    insertform.getChamps().remove("idEmploye");
    insertform.getChamps().remove("update_at");
    insertform.getChamps().remove("create_at");
    insertform.initChampsmainform();
    insertform.getChamps().remove(object.getIdName());
    

%>
<div class="content-wrapper">
    <section class="content">
        <div class="container">
            <div class="row justify-content-center">
                <!-- Default box -->
                <%if (request.getParameter("error") != null) {%>
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><i class="icon fa fa-warning"></i> </h4>
                    <%= request.getParameter("error")%>
                </div>
                <%}%>
                <h4 class="page-header">
                    Insertion
                </h4>
                <div class="box box-secondary">
                    <div class="box-header">
                        <h3 class="box-title">Insertion Devis</h3>
                    </div><!-- /.box-header -->
                    <!-- form start -->
                    <form role="form" action="/Event/devis/createAuthorize.do" method="POST">
                        <div class="box-body"> 
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-8">
                                        <%= insertform.getHtml()%>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div  class="box-footer">
                            <button type="submit" class="btn btn-primary">Creer</button>
                        </div>
                    </form>
                </div>
            </div><!-- /.box -->
        </div>
    </section><!-- /.content -->
</div><!-- /.content-wrapper -->
<script type="text/javascript">
    //$(function () {
    <%=  insertform.getJs()%>
    //});
</script>