
<%@page import="com.example.event.sousDevis.SousDevis"%>
<%@page import="source.Base"%>
<%@page import="script.InputTypes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.sql.Time"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="script.Form"%>
<jsp:include page="../../header/header.jsp"/>
<%
    SousDevis object = new SousDevis();
    String error = null;
    if (request.getParameter("error") != null) {
        error = request.getParameter("error");
    }
    if (request.getAttribute("error") != null) {
        error = request.getAttribute("error").toString();
    }

    Form form = (Form) request.getAttribute("searchform");
    ArrayList list = (ArrayList) request.getAttribute("list");

    Form insertform = new Form(object, null);
    insertform.setAdddef(true);

    insertform.initChampsmainform();
    insertform.getChamps().remove(object.getIdName());
    insertform.addCol("col-sm-4");


%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            General SousDevis
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container">
            <div class="row justify-content-center">
                <%if (error != null) {%>
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><i class="icon fa fa-warning"></i> </h4>
                    <%= error%>
                </div>
                <%}%>
                <!-- Insert -->
                <div class="row no-print">
                    <div class="col-xs-12">
                        <button type="button" class="btn btn-default " data-bs-toggle="modal"
                                data-bs-target="#insertModal">
                            <i class="fa fa-plus"></i> Add
                        </button>
                        <div class="modal" id="insertModal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-bs-dismiss="modal"
                                                aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">Insert</h4>
                                    </div>
                                    <form action="/Event/sousDevis/create.do" method="POST">
                                        <div class="modal-body">
                                            <div class="row justify-content-center">
                                                <%= insertform.getHtml()%> 
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default pull-left"
                                                    data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Save</button>
                                        </div>
                                    </form>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->
                    </div>
                    <br>
                </div><br>
                <!-- END Insert -->

                <!-- Filtre -->
                <div class="box collapsed-box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Filtre</h3>
                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                    title="Collapse" collapsed><i class="fa fa-plus"></i></button>
                        </div>
                    </div>
                    <form role="form" action="/Event/sousDevis/generalSousDevis.do" method="POST">
                        <div class="box-body">
                            <div class="row">
                                <%= form.getHtml()%>
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary">Recherche</button>
                        </div>
                    </form>
                </div><!-- /.box 1-->

                <h4 class="page-header">
                    Liste
                </h4>
                <div class="box">
                    <div class="box-body">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>idSousDevis</th>
                                    <th>idDevis</th>
                                    <th>designation</th>
                                    <th>idPrestation</th>
                                    <th>prix</th>
                                    <th>quantite</th>
                                    <th>duree</th>

                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (Object obj : list) {
                                        SousDevis cast = (SousDevis) obj;
                                        SousDevis mod = new SousDevis();
                                        mod.setIdSousDevis(cast.getIdSousDevis());
                                        mod = (SousDevis) mod.select(null, null).get(0);
                                        Form modifform = new Form(mod, null);
                                        modifform.setModifmode(true);
                                        modifform.setPrefix(cast.getIdSousDevis().toString());

                                        modifform.initChampsmainform();
                                        modifform.getChamps().get(object.getIdName()).getContent().setAttribute("type", "hidden");
                                        modifform.getChamps().get(object.getIdName()).getMainform().getFirstChild().setTextContent("");
                                        //modifform.addCol("col-md-6");
%>
                                <tr>
                                    <td><%= cast.getIdSousDevis()%></td>
                                    <td><%= cast.getIdDevis()%></td>
                                    <td><%= cast.getDesignation()%></td>
                                    <td><%= cast.getIdPrestation()%></td>
                                    <td><%= Base.formatD(cast.getPrix())%></td>
                                    <td><%= Base.formatD(cast.getQuantite())%></td>
                                    <td><%= Base.formatD(cast.getDuree())%></td>


                                    <td>
                                        <button type="button" class="btn btn-default "
                                                data-bs-toggle="modal" data-bs-target="#modifModal<%= cast.getIdSousDevis()%>">
                                            <i class="fa fa-edit"></i> 
                                        </button>
                                        <div class="modal" id="modifModal<%= cast.getIdSousDevis()%>" tabindex="-1">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close"
                                                                data-bs-dismiss="modal" aria-label="Close"><span
                                                                aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title">Modification</h4>
                                                    </div>
                                                    <form  method="POST" action="/Event/sousDevis/update/<%= cast.getIdSousDevis()%>.do">                                                        
                                                        <div class="modal-body ">
                                                            <%= modifform.getHtml()%>
                                                        </div>
                                                        <script type="text/javascript"><%= modifform.getJs()%></script>
                                                        <div class="modal-footer">
                                                            <button type="button"
                                                                    class="btn btn-default pull-left"
                                                                    data-bs-dismiss="modal">Close</button>
                                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                                        </div>
                                                    </form>
                                                </div> 
                                            </div> 
                                        </div> 

                                    </td>
                                    <td><a class="btn btn-default" onclick="return confirmLink();" href="/Event/sousDevis/delete/<%= cast.getIdSousDevis()%>.do"><i class="fa fa-trash-o"></i>
                                        </a></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div><!-- /.row -->
        </div>



    </section><!-- /.content -->
</div>

<script type="text/javascript">
    $("#example1").dataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
        }
    });
    <%=  insertform.getJs()%>
    <%=  form.getJs()%>
</script>
