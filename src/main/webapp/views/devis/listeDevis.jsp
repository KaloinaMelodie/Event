
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
    ArrayList list = new Devis().select(null, null);
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
                    Liste Devis
                </h4>
                <div class="box">
                    <div class="box-body">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>idDevis</th>
                                    <th>Client</th>
                                    <th>creer le</th>
                                    <th>Spectacle</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (Object obj : list) {
                                        Devis cast = (Devis) obj;
                                %>
                                <tr>

                                    <td><a href="/Event/devis/ficheDevis/<%= cast.getIdDevis()%>.do"><%= cast.getIdDevis()%></a></td>
                                    <td><%= (cast.getIdClient() != null) ? cast.getJidClient().get(cast.getIdClient()) : ""%></td>
                                    <td><%= cast.getCreate_at()%></td>
                                    <td><%= (cast.getIdSpectacle() != null) ? cast.getJidSpectacle().get(cast.getIdSpectacle()) : ""%></td>  
                                    <td><button id="generate-pdf-button" class="btn btn-outline-dark" onclick="generate('<%= cast.getIdDevis()%>')">Affiche</button></td>
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
                                                    var pdf = html2pdf().set({
//                                                        margin: 2, // Réduire les marges à 5 mm
                                                        filename: 'generated.pdf',
//                                                        width: 210, // A4 width in mm
                                                        height: 1112, // A4 height in mm,
                                                        image: {
                                                            type: 'jpeg',
                                                            quality: 0.98
                                                        },
//                                                        html2canvas: {
//                                                            scale: 2, // Réduire l'échelle à 2 pour s'assurer que le contenu s'adapte à une page
//                                                            letterRendering: true
//                                                        },
                                                        jsPDF: {
//                                                            unit: 'mm',
                                                            format: 'a4',
                                                            orientation: 'portrait'
                                                        },
                                                        pagebreak: {// Customize page break behavior
                                                            mode: ['avoid-all'], // Avoid page breaks inside elements
                                                            after: '.html2pdf__page-break' // Specify the class for page breaks
                                                        },
                                                        html2pdf: {
                                                            // Customize the page size
                                                            page: {
                                                                width: 210, // A4 width in mm
                                                                height: 100 // A4 height in mm
                                                            }
                                                        }
                                                    }).from(data);
//                                                    var pages = pdf.internal.pages;
//var pages = pdf.internal.pages;
//      for (var i = 0; i < pages.length; i++) {
//        pages[i].height = 1122;
//      }
// Remove the page break class
                                                    var pageBreaks = document.getElementsByClassName('html2pdf__page-break');
                                                    while (pageBreaks.length > 0) {
                                                        pageBreaks[0].parentNode.removeChild(pageBreaks[0]);
                                                    }

                                                    pdf.save();
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