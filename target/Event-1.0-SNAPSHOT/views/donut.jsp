
<%@page import="java.util.Map"%>
<%@page import="com.example.event.Color"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.event.devis.FicheDevis"%>
<%@page import="com.example.event.devis.DevisService"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<jsp:include page="../header/headeremploye.jsp"/>
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
    FicheDevis object = new FicheDevis("DVI000005");

    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));
     HashMap<String, Double> datadonut = DevisService.selectListTypeSousDevis(object, null);
    ArrayList color = new Color().select(null, "select * from color order by \"idColor\" asc");

%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Blank page
            <small>it all starts here</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container">
            <div class="row justify-content-center">
                <!-- Default box -->

                <h4 class="page-header">
                    Donut
                    <small>Exemple de donut</small>
                </h4>
                <div class="box box-default">
                    <div class="box-header with-border">
                        <h3 class="box-title">Browser Usage</h3>
                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i></button>
                        </div>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="chart-responsive">
                                    <canvas id="pieChart" height="150" ></canvas>
                                </div><!-- ./chart-responsive -->
                            </div><!-- /.col -->
                            <div class="col-md-4">
                                <ul class="chart-legend clearfix">
                                    <%int ii = 0;
                                        for (Map.Entry<String,Double> cast : datadonut.entrySet()) {
                                    %>
                                    <li><i class="fa fa-circle-o text" style="color: <%=((Color) color.get(ii)).getColor()%>;"></i><%= cast.getKey()%> <%= cast.getValue()%></li>
                                        <%ii++;
                                }%>        
                                </ul>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.box-body -->
                    <div class="box-footer no-padding">
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="#">United States of America <span class="pull-right text-red"><i
                                            class="fa fa-angle-down"></i> 12%</span></a></li>
                            <li><a href="#">India <span class="pull-right text-green"><i
                                            class="fa fa-angle-up"></i> 4%</span></a></li>
                            <li><a href="#">China <span class="pull-right text-yellow"><i
                                            class="fa fa-angle-left"></i> 0%</span></a></li>
                        </ul>
                    </div><!-- /.footer -->
                </div><!-- /.box -->
            </div>
        </div>

    </section><!-- /.content -->
</div><!-- /.content-wrapper -->
<script type="text/javascript" src="<%=url%>Event/assets/js/donut.js"/>

<script>console.log("PieData");</script>
<script type="text/javascript" >
    console.log("ato");
    var PieData = [];
    <%int i = 0;
         for (Map.Entry<String,Double> cast : datadonut.entrySet()) {
                                   
    %>
    PieData.push({
        value: <%= cast.getValue()%>,
        color: "<%=((Color) color.get(i)).getColor()%>",
        label: "<%= cast.getKey()%>"
    });
    <%i++;
        }%>
    donaty(PieData);
</script>