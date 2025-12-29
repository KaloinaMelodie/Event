
<%@page import="com.example.event.admin.Admin"%>
<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));

    Admin use = (Admin) session.getAttribute("admin");

%>
<%if (use == null) {%>
<script>
    window.location.href = "<%=url%>/Event/views/admin/login.jsp?but=typePrestation/generalTypePrestation.do";
</script>
<%}%>
<header class="main-header">
    <a href="#" class="logo"><b>Event</b>Admin</a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <%if (use != null) {%>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="<%=url%>Event/assets/dist/img/profil.png" class="user-image" alt="User Image" />
                        <span class="hidden-xs"><%= use.getPrenom()%></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="<%=url%>Event/assets/dist/img/profil.png" class="img-circle" alt="User Image" />
                            <p>
                                <%= use.getNom()%> <%= use.getPrenom()%>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="/Event/admin/logout/<%= session.getAttribute("iduser")%>.do" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <%}%>
            </ul>
        </div>
    </nav>
</header>

<!-- =============================================== -->

<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
                <a href="#">
                    <span>TypePrestation</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/Event/typePrestation/generalTypePrestation.do"><i class="fa fa-edit"></i> crud</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <span>Prestation</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/Event/prestation/generalPrestation.do"><i class="fa fa-edit"></i> crud</a></li>
                    <li class="treeview">
                        <a href="#"><i class="fa fa-circle-o"></i>Lieu <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="/Event/lieu/generalLieu.do"><i class="fa fa-edit"></i> crud</a></li>    
                            <li class="treeview">
                                <a href="#"><i class="fa fa-circle-o"></i>TypeLieu <i class="fa fa-angle-left pull-right"></i></a>
                                <ul class="treeview-menu">
                                    <li><a href="/Event/typeLieu/generalTypeLieu.do"><i class="fa fa-edit"></i> crud</a></li>                                                 
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#"><i class="fa fa-circle-o"></i>Artiste <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="/Event/artiste/generalArtiste.do"><i class="fa fa-edit"></i> crud</a></li>    
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <span>General</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/Event/typeTarif/generalTypeTarif.do"><i class="fa fa-edit"></i> type tarif</a></li>
                    <li><a href="/Event/uniteTarif/generalUniteTarif.do"><i class="fa fa-edit"></i> unite tarif</a></li>
                    <li><a href="/Event/client/generalClient.do"><i class="fa fa-edit"></i> client</a></li>
                    <!--<li><a href="/Event/categoriePlace/generalCategoriePlace.do"><i class="fa fa-edit"></i> categorie place</a></li>-->
                    <li><a href="/Event/categoriePlaceLieu/generalCategoriePlaceLieu.do"><i class="fa fa-edit"></i> categorie place lieu</a></li>
                    <li><a href="/Event/taxe/generalTaxe.do"><i class="fa fa-edit"></i> taxe </a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>