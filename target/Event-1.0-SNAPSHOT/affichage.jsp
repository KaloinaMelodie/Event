<%@page import="source.Base"%>
<%@page import="com.example.event.v_categoriePlaceDevis.V_categoriePlaceDevis"%>
<%@page import="com.example.event.lieu.Lieu"%>
<%@page import="com.example.event.artiste.Artiste"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.event.devis.FicheDevis"%>
<%
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf("Event"));

    FicheDevis object = (FicheDevis) request.getAttribute("ficheDevis");
    ArrayList<Artiste> listeArtiste = (ArrayList<Artiste>) request.getAttribute("listeArtiste");
    ArrayList<Lieu> listeLieu = (ArrayList<Lieu>) request.getAttribute("listeLieu");
    String date = (String) request.getAttribute("date");
    String heure = (String) request.getAttribute("heure");
    String titre = (String) request.getAttribute("titre");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
        <link rel="stylesheet" href="<%=url%>Event/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=url%>Event/assets/css/Data-Table-1.css">
        <link rel="stylesheet" href="<%=url%>Event/assets/css/Data-Table.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" href="<%=url%>Event/assets/css/styles.css">
        <link rel="stylesheet" href="<%=url%>Event/css/style.css">
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .header {
                text-align: center;
                margin-bottom: 20px;
            }
            .venue {
                text-align: center;
                margin-bottom: 10px;
            }
            .artist {
                margin-bottom: 20px;
                display: flex;
                justify-content: center;
            }
            .artist-row {
                display: flex;
                justify-content: center;
            }
            .artist-column {
                width: 25%;
            }
            .artist-item {
                margin-bottom: 10px;
                text-align: center;
            }
            .artist-item img {
                margin-bottom: 10px;
                display: block;
                margin-left: auto;
                margin-right: auto;
            }
            .venue {
                text-align: center;
                margin-bottom: 10px;
            }
            .price {
                margin-bottom: 20px;
            }
            .price-category {
                margin-bottom: 10px;
            }
            .price-category h4 {
                margin-bottom: 5px;
            }
        </style>
    </head>
    <body>
    <page size="A4">        
        <div class="container"><br>
            <div class="row">
                <div class="col">
                    <h2 style="margin-bottom: 32px;margin-left: 280px;margin-top: 10px;"><b><%= titre%> </b></h2>
                    <div class="info">
                        <h5>Date: <span id="date"><%= date%></span></h5>
                        <h5>Heure: <span id="heure"><%= heure%></span></h5>
                    </div>
                </div>

                <div class="row">

                    <div style="width: 775px;height: 700px;border: 1px solid var(--bs-secondary) ; margin-left: 15px;">
                        <% for (Lieu lieu : listeLieu) {%>
                        <div class="venue">
                            <h4>Lieu: <span id="lieu"><%= lieu.getNomLieu()%></span></h4>
                            <img src="data:image/png;base64,<%= lieu.getPhotoLieu()%>"  alt="Photo du lieu" width="200" height="100">
                        </div>
                        <% } %>
                        <h3> </h3>
                        <% for (int i = 0; i < listeArtiste.size(); i += 2) {
                                Artiste artiste = listeArtiste.get(i);
                                Artiste artiste1 = null;
                                if (i + 1 < listeArtiste.size()) {
                                    artiste1 = listeArtiste.get(i + 1);
                                }
                        %>
                        <div class="artist">
                            <div class="artist-column">
                                <div class="artist-item">
                                    <img src="data:image/png;base64,<%= artiste.getPhotoArtiste()%>"  alt="Photo du lieu" width="150" height="175">
                                    <span><%= artiste.getNomArtiste()%></span>
                                </div>
                            </div>  
                                <%if( artiste1!=null ){%>
                            <div class="artist-column">
                                <div class="artist-item">
                                    <img src="data:image/png;base64,<%= artiste1.getPhotoArtiste()%>"  alt="Photo du lieu" width="150" height="175">
                                    <span><%= artiste1.getNomArtiste()%></span>
                                </div>
                            </div>  
                                <%}%>
                        </div>
                        <%}%>
                    </div>

                </div>

                <div class="signature" style="margin-left:0px">
                    <h4>Prix des places:</h4>
                    <% for (V_categoriePlaceDevis v_categorie : object.getListePlaceDevis()) {%>
                    <div class="row">
                        <div class="col-md-3"><label class="col-form-label"> <%= v_categorie.getCategoriePlace()%>:</label></div>
                        <div class="col-md-9"><label class="col-form-label"><%= v_categorie.getPrix()%> Ar</label></div>
                    </div>
                    <%}%>
                </div>



            </div>
        </div>        
    </page> 
</body>
</html>
