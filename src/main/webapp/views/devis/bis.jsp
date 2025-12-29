
<%@page import="com.example.event.devis.FicheDevis"%>
<%@page import="com.example.event.devis.Devis"%>
<%@page import="script.InputTypes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="script.Form"%>
<jsp:include page="../../header/headeremploye.jsp"/>
<%
    FicheDevis object = (FicheDevis) request.getAttribute("ficheDevis");

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
                    Bis
                </h4>
                <div class="box box-secondary">
                    <div class="box-header">
                        <h3 class="box-title">Bis</h3>
                    </div><!-- /.box-header -->
                    <!-- form start -->
                    <form role="form" action="/Event/devis/doBis/<%= object.getIdDevis() %>.do" method="POST">
                        <div class="box-body"> 
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-8">
                                        <div class="form-group">
                                                <label>Date Time</label>
                                                <input type="datetime-local" class="form-control" name="dateHeure" />
                                            </div><!-- /.form group -->

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
    //});
</script>