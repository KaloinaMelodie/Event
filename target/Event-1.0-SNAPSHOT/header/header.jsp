<%@page import="com.example.Event.utilisateur.Utilisateur"%>
<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));

    Utilisateur use = (Utilisateur) session.getAttribute("user");

%>
<%if (use == null) {%>
<script >
    window.location.href = "<%=url%>/Event/views/utilisateur/login.jsp?indexpage=true&but=views/test/donut.jsp";
</script>
<%}%>
<header class="main-header">
    <a href="#" class="logo"><b>Admin</b>LTE</a>
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

                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="<%=url%>Event/assets/dist/img/profil.png" class="user-image" alt="User Image" />
                        <span class="hidden-xs"><%= use.getNom()%></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="<%=url%>Event/assets/dist/img/profil.png" class="img-circle" alt="User Image" />
                            <p>
                                <%= use.getNom()%>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="/Event/user/logout/<%= session.getAttribute("iduser")%>.do" class="btn btn-default btn-flat">Sign out</a>
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
                    <span>Andrana</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=url%>Event/andrana/insertionAndrana.do"><i class="fa fa-edit"></i> Insertion</a></li>
                    <li><a href="<%=url%>Event/andrana/generalAndrana.do"><i class="fa fa-table"></i> General</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <span>Multilevel</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
                    <li class="treeview">
                        <a href="#"><i class="fa fa-circle-o"></i> Level One <i
                                class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                            <li class="treeview">
                                <a href="#"><i class="fa fa-circle-o"></i> Level Two <i
                                        class="fa fa-angle-left pull-right"></i></a>
                                <ul class="treeview-menu">
                                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
                </ul>
            </li>
            <li><a href="<%=url%>Event/views/utilisateur/login.jsp?indexpage=true&but=views/test/donut.jsp"> Login </a></li>
            <li><a href="<%=url%>Event/assets/documentation/index.html"><i class="fa fa-book"></i> Documentation</a></li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

<script>
//var treeviewItems = document.querySelectorAll('.treeview > a');
//  
//  treeviewItems.forEach(function(item) {
//    item.addEventListener('click', function(event) {
//      event.preventDefault();
//      var parentLi = this.parentNode;
//      
//      // Remove 'active' class from all other treeview items
//      treeviewItems.forEach(function(treeviewItem) {
//        if (treeviewItem.parentNode !== parentLi) {
//          treeviewItem.parentNode.classList.remove('active');
//        }
//      });
//      
//      // Toggle 'active' class for the clicked treeview item
//      parentLi.classList.toggle('active');
//      
//      // Store the ID of the clicked treeview item in browser storage
//      var itemId = parentLi.id;
//      localStorage.setItem('selectedTreeviewItem', itemId);
//    });
//  });
//  
//  // Retrieve the ID of the previously selected treeview item from browser storage
//  var selectedItemId = localStorage.getItem('selectedTreeviewItem');
//  if (selectedItemId) {
//    var selectedLi = document.getElementById(selectedItemId);
//    if (selectedLi) {
//      selectedLi.classList.add('active');
//    }
//  }
</script>