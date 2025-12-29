<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));

    String but = "views/accueil.jsp";
    if (request.getParameter("but") != null) {
        but = request.getParameter("but");
    }
    out.println(but);
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title><c:out value="${pagetitle}"/></title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.2 -->
        <link href="<%=url%>Event/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Font Awesome Icons -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css" />
        <!-- Ionicons -->
        <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="<%=url%>Event/assets/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link href="<%=url%>Event/assets/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

        <!-- daterange picker -->
        <link href="<%=url%>Event/assets/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- iCheck for checkboxes and radio inputs -->
        <link href="<%=url%>Event/assets/plugins/iCheck/all.css" rel="stylesheet" type="text/css" />
        <!-- Bootstrap Color Picker -->
        <link href="<%=url%>Event/assets/plugins/colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet" />
        <!-- Bootstrap time Picker -->
        <link href="<%=url%>Event/assets/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet" />
        <!-- Theme style -->
        <link href="<%=url%>Event/assets/plugins/iCheck/all.css" rel="stylesheet" type="text/css" />
        <!-- DATA TABLES -->
        <link href="<%=url%>Event/assets/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="<%=url%>Event/assets/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="<%=url%>Event/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />


        <!--        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.0/html2canvas.min.js"></script>-->

        <script src="<%=url%>Event/assets/js/confirm.js"></script>

        <jsp:include page="/footjs.jsp"/>

        <%if (request.getParameter("bo") != null) {%>
        <script>
            window.location.href = "<%=url%>Event/views/admin/login.jsp?but=typePrestation/generalTypePrestation.do";
        </script>
        <%}%>
        <%if (request.getParameter("fo") != null) {%>
        <script>
            window.location.href = "<%=url%>Event/views/employe/login.jsp?but=devis/insertionDevis.do";
        </script>
        <%}%>

    </head>

    <body class="skin-black fixed">
        <!-- Site wrapper -->
        <div class="wrapper">

            <!-- =============================================== -->

            <!--body-->
            <jsp:include page="<%=but%>"/>
        </div><!-- ./wrapper -->


        <script type="text/javascript">
            <c:out value="${scripts}" escapeXml="false"/>
        </script>


    </body>

</html>