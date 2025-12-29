
<%@page import="com.example.event.employe.Employe"%>
<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));

    Employe use = (Employe) session.getAttribute("employe");

%>
<%if (use == null) {%>
<script >
    window.location.href = "<%=url%>/Event/views/employe/login.jsp?but=devis/insertionDevis.do";
</script>
<%}%>
<header class="main-header">
    <a href="#" class="logo"><b>Event</b></a>
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
                                <a href="/Event/employe/logout/<%= session.getAttribute("iduser")%>.do" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <%}%>
            </ul>
        </div>
    </nav>
</header>

<style>
    .sidebar-menu li.active > a {
        border: 1px solid #000; /* Replace #000 with the desired border color */
    }
    .sidebar-menu li ul li.active > a {
        border: 1px solid #000; /* Replace #000 with the desired border color */
    }
    .sidebar-menu li > a {
        color:lightgray;

    }
</style>
<!-- =============================================== -->

<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section >

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview" id="devis">
                <a href="#">
                    <span>Devis</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li id="insertionDevis-link"><a href="/Event/devis/insertionDevis.do"><i class="fa fa-edit"></i> insertion</a></li>
                    <li id="listeDevis-link"><a href="/Event/?but=views/devis/listeDevis.jsp"><i class="fa fa-edit"></i> liste</a></li>
                </ul>
            </li>
            <li class="treeview" id="general">
                <a href="#">
                    <span>General</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li id="generalDevis-link"><a href="/Event/devis/generalDevis.do"><i class="fa fa-table"></i>devis</a></li>
                    <li id="generalSpectacle-link"><a href="/Event/spectacle/generalSpectacle.do"><i class="fa fa-table"></i>Spectacle</a></li>
                </ul>

            </li>
            <li class="treeview" id="statistique">
                <a href="#">
                    <span>Statistique</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li id="listeSpectacle-link"><a href="/Event/stat/listeSpectacle.do"><i class="fa fa-table"></i>liste Spectacle</a></li>
                    <li id="generalSpectacle-link"><a href="/Event/stat/generalSpectacle.do"><i class="fa fa-table"></i>Spectacle</a></li>
                </ul>

            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

<script>
    // Fonction pour gérer les clics sur les liens du sidebar
    function handleSidebar(event) {
        console.log("tafiditra");
        // Retirer la classe "active" de tous les éléments de la barre latérale
        const sidebarItems = document.querySelectorAll('.sidebar-menu li');
        sidebarItems.forEach((item) => {
            item.classList.remove('active');
        });
        // Ajouter la classe "active" à l'élément li cliqué
        const clickedItem = event.target.closest('li');

        clickedItem.classList.add('active');
        var parentItem = clickedItem.parentElement;
        while (parentItem && !parentItem.classList.contains('sidebar-menu')) {
            if (parentItem.tagName.toLowerCase() === 'li') {
                parentItem.classList.add('active');
            }
            parentItem = parentItem.parentElement;
        }
        const itemId = clickedItem.getAttribute('id');
        //clickedItem.click();
        localStorage.setItem('activeSidebarItem', itemId);

        document.addEventListener('DOMContentLoaded', restoreActiveSidebarItem);

    }

// Fonction pour restaurer l'état actif du sidebar lors du chargement de la page
    function restoreActiveSidebarItem() {
        const activeItemId = localStorage.getItem('activeSidebarItem');
        if (activeItemId) {
            const activeItem = document.getElementById(activeItemId);
            if (activeItem) {
                activeItem.classList.add('active');
                // Ouvrir les éléments treeview parents
                let parentElement = activeItem.closest('.treeview');
                while (parentElement && parentElement.classList.contains('treeview')) {
                    parentElement.classList.add('active');
                    parentElement = parentElement.closest('.treeview').parentNode.closest('.treeview');
                }
            }
        }
    }


// Attacher un gestionnaire d'événements aux liens du sidebar
    const sidebarLinks = document.querySelectorAll('.sidebar-menu li a');
    sidebarLinks.forEach((link) => {
        link.addEventListener('click', handleSidebar);
//        restoreActiveSidebarItem();
    }
    );
// Restaurer l'état actif du sidebar lors du chargement de la page
    document.addEventListener('DOMContentLoaded', restoreActiveSidebarItem);

</script>
