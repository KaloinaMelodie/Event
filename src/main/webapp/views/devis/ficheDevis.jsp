
<%@page import="com.example.event.devis.DevisService"%>
<%@page import="com.example.event.Color"%>
<%@page import="com.example.event.lieu.Lieu"%>
<%@page import="com.example.event.v_categorieplacelieudevis.V_categoriePlaceLieuDevis"%>
<%@page import="com.example.event.v_categoriePlaceDevis.V_categoriePlaceDevis"%>
<%@page import="com.example.event.v_lieu.V_lieu"%>
<%@page import="com.example.event.v_artiste.V_artiste"%>
<%@page import="source.ObjectBdd"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.example.event.sousDevis.SousDevis"%>
<%@page import="com.example.event.typePrestation.TypePrestation"%>
<%@page import="com.example.event.client.Client"%>
<%@page import="com.example.event.devis.FicheDevis"%>
<%@page import="com.example.event.v_prestation.V_prestation"%>
<%@page import="source.Base"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="script.Form"%>
<jsp:include page="../../header/headeremploye.jsp"/>
<%
    FicheDevis object = (FicheDevis) request.getAttribute("ficheDevis");
    Client client = new Client();
    client.setIdClient(object.getDevis().getIdClient());
    client = (Client) client.findById();

    HashMap<String, ArrayList<V_prestation>> selectListTypeV_prestation = (HashMap<String, ArrayList<V_prestation>>) request.getAttribute("selectListTypeV_prestation");
    HashMap<String, V_artiste> selectListV_artiste = (HashMap<String, V_artiste>) request.getAttribute("selectListV_artiste");
    HashMap<String, V_lieu> selectListV_lieu = (HashMap<String, V_lieu>) request.getAttribute("selectListV_lieu");
    HashMap<String, ArrayList<V_categoriePlaceLieuDevis>> selectListTypeV_placeLieuDevis = (HashMap<String, ArrayList<V_categoriePlaceLieuDevis>>) request.getAttribute("selectListTypeV_placeLieuDevis");

    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));
    ArrayList color = new Color().select(null, "select * from color order by \"idColor\" asc");
    HashMap<String, Double> datadonut = DevisService.selectListTypeSousDevis(object, null);
%>
<div class="content-wrapper">
    <section class="content">
        <div class="container">
            <div class="row justify-content-center">
                <!-- Default box -->

                <h4 class="page-header">
                    Devis
                </h4>
                <div class="box box-secondary">
                    <div class="box-header">
                        <h3 class="box-title">Fiche Devis</h3>
                    </div><!-- /.box-header -->
                    <!-- form start -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">REF</label></div>
                            <div class="col-md-6"><p ><%= object.getIdDevis()%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Spectacle</label></div>
                            <div class="col-md-6"><p ><%= object.getSpectacle().getTitre() %></p></div>
                        </div>
<!--                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label"> Client</label></div>
                            <div class="col-md-6"><p ><%//= client.getIntitule()%> <%//= client.getContact()%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Adresse Client</label></div>
                            <div class="col-md-6"><p ><%//= client.getAdresse()%></p></div>
                        </div>-->
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Creer le </label></div>
                            <div class="col-md-6"><p ><%= object.getDevis().getCreate_at()%></p></div>
                        </div>
                        <!--                        <div class="row">
                                                    <div class="col-md-6"><label class="col-form-label">Modifier le </label></div>
                                                    <div class="col-md-6"><p ><%//= (object.getDevis().getUpdate_at() != null) ? object.getDevis().getUpdate_at() : ""%></p></div>
                                                </div>-->
                    </div>
                    <div class="box-footer">
                                                <a class="btn btn-secondary " href="/Event/devis/bis/<%= object.getIdDevis() %>.do">Bis</a>
                                                <!--<button class="btn btn-secondary ">Submit</button>-->
                        <!--<a class="btn btn-app"><i class="fa fa-edit"></i> Modifier</a>-->
                    </div>
                </div><!-- /.box -->
                <h4 class="page-header">
                    Recap
                </h4>
                <div class="box box-secondary">
                    <!-- form start -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Recette Provisoire</label></div>
                            <div class="col-md-6"><p ><%= Base.formatD(object.getRecetteProvisoire())%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label"> Depense</label></div>
                            <div class="col-md-6"><p ><%= Base.formatD(object.getMontantTotal())%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Benefice </label></div>
                            <div class="col-md-6"><p ><%= Base.formatD(object.getBeneficeProvisoire())%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Recette Vrai</label></div>
                            <div class="col-md-6"><p ><%= Base.formatD(object.getRecetteVrai())%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Benefice Reel sans Taxe</label></div>
                            <div class="col-md-6"><p ><%= Base.formatD(object.getBeneficeVrai())%></p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Taxe</label></div>
                            <div class="col-md-6"><p ><%= object.getTaxe().getPourc()%>%</p></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><label class="col-form-label">Benefice Net</label></div>
                            <div class="col-md-6"><p ><%= Base.formatD(object.getBeneficeNet())%></p></div>
                        </div>
                    </div>
                    <div class="box-footer">
                                                <!--<button class="btn btn-success">Submit</button>-->
                                                
                        <!--<a class="btn btn-app"><i class="fa fa-edit"></i> Modifier</a>-->
                    </div>
                </div><!-- /.box -->
                <h4 class="page-header">
                    Prix place
                </h4>
                <div class="box box-secondary">
                    <% for (Map.Entry<String, ArrayList<V_categoriePlaceLieuDevis>> map
                                : selectListTypeV_placeLieuDevis.entrySet()) {
                            Lieu lieu = new Lieu();
                            lieu.setIdLieu(map.getKey());
                            lieu = (Lieu) lieu.findById();

                    %>
                    <%if (request.getParameter("errorPlace") != null) {%>
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-warning"></i> </h4>
                        <%= request.getParameter("errorPlace")%>
                    </div>
                    <%}%>
                    <div class="box-header">
                        <h3 class="box-title"><%= lieu.getNomLieu()%></h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Categorie place</th>
                                    <th>Nbre place</th>
                                    <th>Prix</th>
                                    <th>Montant</th>
                                    <th>Nbre place vendu</th>
                                    <th>Montant vrai</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <% for (V_categoriePlaceLieuDevis v_categoriePlaceLieuDevis : map.getValue()) {%>
                                <tr>
                            <form action="/Event/categoriePlaceLieuDevis/insererModifier.do" method="POST">
                                <input type="hidden" class="form-control" value="<%= v_categoriePlaceLieuDevis.getIdCategoriePlace()%>" name="idCategoriePlace">
                                <input type="hidden" class="form-control" value="<%= object.getIdDevis()%>" name="idDevis">
                                <input type="hidden" class="form-control" value="<%= lieu.getIdLieu()%>" name="idLieu">
                                <input type="hidden" class="form-control" value="<%= v_categoriePlaceLieuDevis.getNbrePlace()%>" name="nbrePlace">
                                <%if (v_categoriePlaceLieuDevis.getIdCategoriePlaceLieuDevis() != null) {%><input type="hidden" class="form-control" value="<%=v_categoriePlaceLieuDevis.getIdCategoriePlaceLieuDevis()%>" name="idCategoriePlaceLieuDevis"><%}%>
                                <td><%= v_categoriePlaceLieuDevis.getCategoriePlace()%></td>
                                <td><%= v_categoriePlaceLieuDevis.getNbrePlace()%></td>
                                <td><input type="text" class="form-control" name="prix" value="<%= v_categoriePlaceLieuDevis.getPrix()%>"></td>
                                <td><%= Base.formatD(v_categoriePlaceLieuDevis.getMontant())%></td>
                                <td><input type="text" class="form-control"name="nbrePlaceVendu" value="<%= v_categoriePlaceLieuDevis.getNbrePlaceVendu()%>"></td>
                                <td><%= Base.formatD(v_categoriePlaceLieuDevis.getMontantVrai())%></td>
                                <td><button class="btn btn-success">Modifier</button></td>

                            </form>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>

                    </div>
                    <%}%> 
                </div>
            </div><!-- /.box -->



            <select class="form-control" id="datapresta" style="display:none">
                <%for (Object obj
                            : new V_prestation()
                                    .select(null, null)) {
                        V_prestation v_prestation = (V_prestation) obj;%>
                <option value="<%= v_prestation.getIdPrestation()%>"><%= new Gson().toJson(v_prestation)%></option>
                <%}%>
            </select>
            <select class="form-control" id="dataartiste" style="display:none">
                <%for (Map.Entry<String, V_artiste> objartiste
                            : selectListV_artiste.entrySet()) {
                        V_artiste v_artiste = objartiste.getValue();%>
                <option value="<%= v_artiste.getIdArtiste()%>"><%= new Gson().toJson(v_artiste)%> </option>
                <%}%>
            </select>
            <select class="form-control" id="datalieu" style="display:none">
                <%for (Map.Entry<String, V_lieu> objlieu
                            : selectListV_lieu.entrySet()) {
                        V_lieu v_lieu = objlieu.getValue();%>
                <option value="<%= v_lieu.getIdLieu()%>"><%= new Gson().toJson(v_lieu)%> </option>
                <%}%>
            </select>

            <h4 class="page-header">
                Ajout Prestation
            </h4>

            <div class="box collapsed-box"> 
                <div class="box-header with-border">
                    <h3 class="box-title">Formulaire</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse" collapsed><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <!--Artiste-->
                <div class="box-header">
                    <h3 class="box-title">Artiste</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form action="/Event/sousDevis/insererSousDevis/.do" method="POST">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3"><label class="col-form-label">Artiste</label></div>
                            <div class="col-md-3"><label class="col-form-label">Prix</label></div>
                            <div class="col-md-3"><label class="col-form-label">Quantite</label></div>
                            <div class="col-md-3"><label class="col-form-label">Duree</label></div>
                        </div>
                        <div class="row">
                            <input type="hidden" class="form-control" value="<%= object.getIdDevis()%>" name="idDevis">
                            <input type="hidden" class="form-control" id="inputArtistedesignation" name="designation">
                            <div class="col-md-3">
                                <select class="form-control" id="inputArtisteidArtiste" name="idArtiste" onchange="onchangeArtistetype()" required>
                                    <option>artiste</option> 
                                    <%for (Map.Entry<String, V_artiste> objartiste
                                                : selectListV_artiste.entrySet()) {
                                            V_artiste v_artiste = objartiste.getValue();%>
                                    <option value="<%= v_artiste.getIdArtiste()%>"><%= v_artiste.getNomArtiste()%> </option>
                                    <%}%>
                                </select>

                            </div>

                            <div class="col-md-3"><input type="text" class="form-control" id="inputArtisteprix" name="prix"></div>
                            <div class="col-md-3"><input type="text" class="form-control" id="inputArtistequantite" name="quantite" value="1"></div>
                            <div class="col-md-3"><input type="text" class="form-control" id="inputArtisteduree" name="duree" value="1"></div>

                        </div>
                    </div>
                    <div class="box-footer">
                        <button class="btn btn-success">Ajouter</button>
                    </div>
                </form>
                <script type="text/javascript">
                    function getOptionTextByValueArtiste(value) {
                        var selectElement = document.getElementById("dataartiste");
                        for (var i = 0; i < selectElement.options.length; i++) {
                            var option = selectElement.options[i];
                            console.log("hitaa");
                            if (option.value === value) {
                                return option.textContent;
                            }
                        }
                        return null; // Value not found
                    }
                    function onchangeArtistetype() {
                        let idd = document.getElementById("inputArtisteidArtiste");
                        var value = idd.value;
                        var text = idd.options[idd.selectedIndex].text;
                        console.log(value);
                        if (idd.value != null) {
                            var content = getOptionTextByValueArtiste(idd.value);
                            console.log(content);
                            var object = JSON.parse(content);
                            let idprix = "inputArtisteprix";
                            let idduree = "inputArtisteduree";
                            let iddesignation = "inputArtistedesignation";
                            document.getElementById(iddesignation).value = object["nomArtiste"];

                            // prix default tarif readonly
                            document.getElementById(idprix).value = object["tarifArtiste"];
                            document.getElementById(idprix).setAttribute("readonly", "readonly");
                            // duree placeholder unitetarif
                            document.getElementById(idduree).placeholder = object["uniteTarifArtiste"];


                        }
                    }
                </script>
                <!--End Artiste-->

                <!--Lieu-->
                <% if (object.isHasLieu()
                            == false) {%>
                <div class="box-header">
                    <h3 class="box-title">Lieu</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form action="/Event/sousDevis/insererSousDevis/.do" method="POST">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3"><label class="col-form-label">Lieu</label></div>
                            <div class="col-md-3"><label class="col-form-label">Prix</label></div>
                            <div class="col-md-3"><label class="col-form-label">Quantite</label></div>
                            <div class="col-md-3"><label class="col-form-label">Duree</label></div>
                        </div>
                        <div class="row">
                            <input type="hidden" class="form-control" value="<%= object.getIdDevis()%>" name="idDevis">
                            <input type="hidden" class="form-control" id="inputLieudesignation" name="designation">
                            <div class="col-md-3">
                                <select class="form-control" id="inputLieuidLieu" name="idLieu" onchange="onchangeLieutype()" required>
                                    <option>lieu</option> 
                                    <%for (Map.Entry<String, V_lieu> objlieu : selectListV_lieu.entrySet()) {
                                            V_lieu v_lieu = objlieu.getValue();%>
                                    <option value="<%= v_lieu.getIdLieu()%>"><%= v_lieu.getNomLieu()%> </option>
                                    <%}%>
                                </select>

                            </div>

                            <div class="col-md-3"><input type="text" class="form-control" id="inputLieuprix" name="prix"></div>
                            <div class="col-md-3"><input type="text" class="form-control" id="inputLieuquantite" name="quantite" value="1"></div>
                            <div class="col-md-3"><input type="text" class="form-control" id="inputLieuduree" name="duree" value="1"></div>

                        </div>
                    </div>
                    <div class="box-footer">
                        <button class="btn btn-success">Ajouter</button>
                    </div>
                </form>
                <script type="text/javascript">
                    function getOptionTextByValueLieu(value) {
                        var selectElement = document.getElementById("datalieu");
                        for (var i = 0; i < selectElement.options.length; i++) {
                            var option = selectElement.options[i];
                            console.log("hitaa");
                            if (option.value === value) {
                                return option.textContent;
                            }
                        }
                        return null; // Value not found
                    }
                    function onchangeLieutype() {
                        let idd = document.getElementById("inputLieuidLieu");
                        var value = idd.value;
                        var text = idd.options[idd.selectedIndex].text;
                        console.log(value);
                        if (idd.value != null) {
                            var content = getOptionTextByValueLieu(idd.value);
                            console.log(content);
                            var object = JSON.parse(content);
                            let idprix = "inputLieuprix";
                            let idduree = "inputLieuduree";
                            let iddesignation = "inputLieudesignation";
                            document.getElementById(iddesignation).value = object["nomLieu"];

                            // duree value = 1 hidden
                            document.getElementById(idduree).value = 1;
                            document.getElementById(idduree).type = 'hidden';
                        }
                    }
                </script>
                <%}%>
                <!--End Lieu-->


                <% for (Map.Entry<String, ArrayList<V_prestation>> map
                            : selectListTypeV_prestation.entrySet()) {
                        TypePrestation type = new TypePrestation();
                        type.setIdTypePrestation(map.getKey());
                        type = (TypePrestation) type.findById();
                %>
                <div class="box-header">
                    <h3 class="box-title"><%= type.getTypePrestation()%></h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form action="/Event/sousDevis/insererSousDevis/<%= type.getIdTypePrestation()%>.do" method="POST">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3"><label class="col-form-label">Prestation</label></div>
                            <div class="col-md-3"><label class="col-form-label">Prix</label></div>
                            <div class="col-md-3"><label class="col-form-label">Quantite</label></div>
                            <div class="col-md-3"><label class="col-form-label">Duree</label></div>
                        </div>
                        <div class="row">
                            <input type="hidden" class="form-control" value="<%= object.getIdDevis()%>" name="idDevis">
                            <input type="hidden" class="form-control" id="<%= type.getIdTypePrestation()%>designation" name="designation">
                            <div class="col-md-3">
                                <select class="form-control" id="<%= type.getIdTypePrestation()%>idPrestation" name="idPrestation" onchange="onchange<%= type.getIdTypePrestation()%>type()" required>
                                    <option>prestation</option> 
                                    <%for (V_prestation v_prestation : map.getValue()) {%>
                                    <option value="<%= v_prestation.getIdPrestation()%>"><%= v_prestation.getPrestation()%> <%= (v_prestation.getTypeTarif() != null) ? v_prestation.getTypeTarif() : ""%></option>
                                    <%}%>
                                </select>

                            </div>

                            <div class="col-md-3"><input type="text" class="form-control" id="<%= type.getIdTypePrestation()%>prix" name="prix"></div>
                            <div class="col-md-3"><input type="text" class="form-control" id="<%= type.getIdTypePrestation()%>quantite" name="quantite" value="1"></div>
                            <div class="col-md-3"><input type="text" class="form-control" id="<%= type.getIdTypePrestation()%>duree" name="duree" value="1"></div>
                            <script type="text/javascript">
                                function getOptionTextByValue(value) {
                                    var selectElement = document.getElementById("datapresta");
                                    for (var i = 0; i < selectElement.options.length; i++) {
                                        var option = selectElement.options[i];
                                        if (option.value === value) {
                                            return option.textContent;
                                        }
                                    }
                                    return null; // Value not found
                                }
                                function onchange<%= type.getIdTypePrestation()%>type() {
                                    let idd = document.getElementById("<%= type.getIdTypePrestation()%>idPrestation");
                                    var value = idd.value;
                                    var text = idd.options[idd.selectedIndex].text;
                                    console.log(text);
                                    if (idd.value != null) {
                                        var content = getOptionTextByValue(idd.value);
                                        console.log(content);
                                        var object = JSON.parse(content);
                                        let idprix = "<%= type.getIdTypePrestation()%>prix";
                                        let idduree = "<%= type.getIdTypePrestation()%>duree";
                                        let iddesignation = "<%= type.getIdTypePrestation()%>designation";
                                        document.getElementById(iddesignation).value = object["prestation"];
                                        if (object["isFixe"] == 1) {
                                            // prix default tarif readonly
                                            document.getElementById(idprix).value = object["tarif"];
                                            document.getElementById(idprix).setAttribute("readonly", "readonly");
                                            // duree placeholder unitetarif
                                            document.getElementById(idduree).placeholder = object["uniteTarif"];

                                        } else {
                                            // duree value = 1 hidden
                                            document.getElementById(idduree).value = 1;
                                            document.getElementById(idduree).type = 'hidden';
                                        }
                                    }
                                }
                            </script>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button class="btn btn-success">Ajouter</button>
                    </div>
                </form>
                <%}%>     
            </div><!-- /.box -->

            <div class="box box-secondary">
                <div class="box-header">
                    <h3 class="box-title">Detaile</h3>
                </div>
                <div class="box-body">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>designation</th>
                                <th>prix</th>
                                <th>quantite</th>
                                <th>duree</th>
                                <th></th>
                                <th>montant</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Object obj
                                        : object.getListeSousDevis()) {
                                    SousDevis cast = (SousDevis) obj;
                                    SousDevis mod = new SousDevis();
                                    mod = cast;
//                                        mod.setIdSousDevis(cast.getIdSousDevis());
//                                        mod = (SousDevis) mod.select(null, null).get(0);
                                    Form modifform = new Form(mod, null);
                                    modifform.setModifmode(true);
                                    modifform.setPrefix(cast.getIdSousDevis().toString());

                                    modifform.initChampsmainform();
                                    modifform.getChamps().get(cast.getIdName()).getContent().setAttribute("type", "hidden");
                                    modifform.getChamps().get(cast.getIdName()).getMainform().getFirstChild().setTextContent("");
                                    modifform.getChamps().get("idPrestation").getMainform().getFirstChild().setTextContent("");;
                                    modifform.getChamps().get("idPrestation").getContent().setAttribute("type", "hidden");
                                    modifform.getChamps().get("idArtiste").getMainform().getFirstChild().setTextContent("");;
                                    modifform.getChamps().get("idArtiste").getContent().setAttribute("type", "hidden");
                                    if (mod.getIdLieu() == null) {
                                        modifform.getChamps().remove("idLieu");
                                    }

                                    if (cast.getIdArtiste() != null || (cast.getIdPrestation() != null && cast.getPrestation().getIsFixe() == 1)) {
                                        modifform.getChamps().get("prix").getContent().setAttribute("readonly", "readonly");
                                    } else {
                                        modifform.getChamps().get("duree").getContent().setAttribute("type", "hidden");

                                    }

                                    //modifform.addCol("col-md-6");
%>
                            <tr>
                                <td><%= cast.getIdSousDevis()%></td>
                                <td><%= cast.getDesignation()%></td>
                                <td><%= Base.formatD(cast.getPrix())%></td>
                                <td><%= Base.formatD(cast.getQuantite())%></td>
                                <td><%= Base.formatD(cast.getDuree())%></td>
                                <td><%= (cast.getIdPrestation() != null) ? ((cast.getPrestation().getUniteTarif() != null) ? cast.getPrestation().getUniteTarif() : "") : ((cast.getIdArtiste() != null) ? cast.getArtiste().getUniteTarif() : "")%></td>
                                <td><%= Base.formatD(cast.getMontant())%></td>


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
                </div>
                <div class="box-footer">
                    Total <%= Base.formatD(object.getMontantTotal())%>
                </div>
            </div>

            <div class="row justify-content-center">
                <!-- Default box -->

                <h4 class="page-header">
                    Statistique
                </h4>
                <div class="box box-default">
                    <div class="box-header with-border">
                        <h3 class="box-title"></h3>
                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                    title="Collapse" collapsed><i class="fa fa-minus"></i></button>
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
                                        for (Map.Entry<String, Double> cast : datadonut.entrySet()) {
                                    %>
                                    <li><i class="fa fa-circle-o text" style="color: <%=((Color) color.get(ii)).getColor()%>;"></i><%= cast.getKey()%> <%= Base.formatD(cast.getValue()) %></li>
                                        <%ii++;
                                            }%>        
                                </ul>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>

        </div>
</div>

</section><!-- /.content -->
</div><!-- /.content-wrapper -->
<script type="text/javascript">
    //$(function () {
    $("#example1").dataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
        }
    });
    //});
</script>
<script type="text/javascript" src="<%=url%>Event/assets/js/donut.js"/>

<script>console.log("PieData");</script>
<script type="text/javascript" >
    console.log("ato");
    var PieData = [];
    <%int i = 0;
        for (Map.Entry<String, Double> cast : datadonut.entrySet()) {

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