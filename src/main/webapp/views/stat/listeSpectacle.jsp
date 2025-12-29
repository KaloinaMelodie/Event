
<%@page import="com.example.event.devis.FicheDevis"%>
<%@page import="com.example.event.devis.Devis"%>
<%@page import="source.Base"%>
<%@page import="script.InputTypes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.sql.Time"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="script.Form"%>
<jsp:include page="../../header/headeremploye.jsp"/>
<%
    //Test objfiltre = (Test) request.getAttribute("objfiltre");

//    objfiltre.setTexte("exemple");
//    objfiltre.setNbre(0);
//    objfiltre.setIdcheck("2");
//    objfiltre.setDaty(new Date());
//    //objfiltre.setDatetime(new Timestamp(System.currentTimeMillis()));
//    objfiltre.setDatetime(LocalDateTime.now());
//    objfiltre.setColor("#f3ff");
//    objfiltre.setTime(Time.valueOf(LocalTime.now()));
    ArrayList<FicheDevis> listeFicheDevis = (ArrayList<FicheDevis>) request.getAttribute("listeFicheDevis");
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));

%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Liste
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container">
            <div class="row justify-content-center">               



                <h4 class="page-header">
                    Liste Spectacle
                </h4>
                <div class="box">
                    <div class="box-body">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Montant Recette</th>
                                    <th>Montant Depense</th>
                                    <th>Montant Benefice Brut avant Taxe</th>
                                    <th>Taxe</th>
                                    <th>Benefice Net</th>
                                    <th>Devis</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (FicheDevis obj : listeFicheDevis) {
                                        FicheDevis cast = (FicheDevis) obj;
                                        Devis devis = cast.getDevis();
                                %>
                                <tr>

                                    <td><%= (devis.getIdSpectacle()!=null)?devis.getJidSpectacle().get(devis.getIdSpectacle()):"" %></td> 
                                    <td><%= Base.formatD(cast.getRecetteVrai())%></td> 
                                    <td><%= Base.formatD(cast.getMontantTotal())%></td> 
                                    <td><%= Base.formatD(cast.getBeneficeVrai())%></td> 
                                    <td><%= cast.getTaxe().getPourc() %>%</td> 
                                    <td><%= Base.formatD(cast.getBeneficeNet())%></td> 
                                    <td><a href="/Event/devis/ficheDevis/<%= cast.getIdDevis()%>.do"><%= cast.getIdDevis()%></a></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div>
    </section><!-- /.content -->
</div>

<!--<script src="<%= url%>Event/assets/plugins/jspdf/jspdf.js"></script>
<script src="<%=url%>Event/assets/plugins/jspdf/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.16/jspdf.plugin.autotable.min.js"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.0/html2canvas.min.js"></script>
<script>
                                        function generate(idDevis) {
                                            // Make an AJAX request to fetch the JSP content
                                            $.ajax({
                                                url: 'affiche/generate.do',
                                                method: 'GET',
                                                data: {idDevis: idDevis},
                                                success: function (data) {
                                                    // Convert HTML to PDF
                                                    html2pdf().set({
                                                        margin: 8, // Réduire les marges à 5 mm
                                                        filename: 'generated.pdf',
                                                        image: {
                                                            type: 'jpeg',
                                                            quality: 0.98
                                                        },
                                                        html2canvas: {
                                                            scale: 2, // Réduire l'échelle à 2 pour s'assurer que le contenu s'adapte à une page
                                                            letterRendering: true
                                                        },
                                                        jsPDF: {
                                                            unit: 'mm',
                                                            format: 'a4',
                                                            orientation: 'portrait'
                                                        }
                                                    }).from(data).save();

                                                }
                                            });
                                        }
</script>

<script type="text/javascript">
    <% //System.out.println(listmodifjs); %>
//$(function () {
    $("#example1").dataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
        }
    });
    <%//= listmodifjs %>
    //});
</script>