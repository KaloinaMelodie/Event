# Java Web Project – Event Management System / Projet Java Web – Gestion Événementielle

## Introduction / Introduction

**EN:**
To streamline the management of its activities, an **event management company** needs a dedicated application capable of centralizing the organization, monitoring, and financial tracking of its events.
This project was designed to provide a **structured, scalable, and automated solution** for managing events, services, and financial performance within a single web platform.

**FR :**
Pour faciliter la gestion de ses activités, une **société événementielle** doit disposer d’une application capable de centraliser l’organisation, le suivi et l’analyse financière de ses événements.
Ce projet a été conçu pour offrir une **solution structurée, évolutive et automatisée** permettant de gérer les événements, les prestations et les performances financières sur une seule plateforme web.

---

## Project Objective / Objectif du projet

**EN:**
Event-based businesses handle multiple types of services such as shows, tours, ticket sales, and equipment rentals. Managing these activities manually can lead to errors and lack of visibility.
The goal of this project is to:

* Centralize event and service management.
* Track **revenues, expenses, and profits** for each event.
* Provide decision-support through clear financial summaries.

**FR :**
Les entreprises événementielles gèrent plusieurs types de prestations comme les fêtes, spectacles, tournées, ventes de billets ou locations de matériel. Une gestion manuelle rend le suivi complexe et peu fiable.
L’objectif de ce projet est de :

* Centraliser la gestion des événements et des prestations.
* Suivre précisément les **recettes, dépenses et bénéfices**.
* Fournir une aide à la décision grâce à des récapitulatifs financiers clairs.

---

## User Profiles / Profils utilisateurs

| Profile / Profil                   | Role / Rôle                                                                                                                                                                                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Administrator / Administrateur** | Manages the backoffice, configuration data, and system parameters. / Gère le backoffice, les données de référence et les paramètres du système.                                             |
| **Employee / Employé**             | Uses the application to create events, manage quotes, and monitor financial results. / Utilise l’application pour créer des événements, gérer les devis et suivre les résultats financiers. |

---

## Core Technical Concept / Concept technique central

**EN:**
The most distinctive aspect of this project is its **highly generalized and automated Java Web architecture**.
Instead of manually coding CRUD pages for each database entity, the system relies on:

* **Java inheritance** to factorize business logic.
* **Reflection and annotations** to dynamically analyze database-mapped objects.
* A custom generic base class (`ObjetBdd`) that centralizes:

  * Insert
  * Create
  * Update
  * Select with filters

From these objects, the system dynamically generates:

* Backend services and controllers.
* Frontend JSP pages with minimal manual effort.

Using **ScriptEngine**, the application automatically generates:

* HTML forms
* JavaScript validation logic

Each field is generated based on the object attributes and annotations, supporting multiple input types such as:

* Text
* Textarea
* Radio buttons
* Date & time fields
* CKEditor
* Select lists
* File upload
* Number fields

This approach allows the automatic creation of:

* Dynamic CRUD pages
* Filterable listing tables
* Insert and update forms with default values

As a result, **any new database object can instantly have a fully functional CRUD interface** with minimal additional code.

**FR :**
L’aspect le plus distinctif de ce projet repose sur une **architecture Java Web fortement généralisée et automatisée**.
Au lieu de coder manuellement les pages CRUD pour chaque entité, le système s’appuie sur :

* L’**héritage Java** pour factoriser la logique métier.
* La **réflexion et les annotations** pour analyser dynamiquement les objets liés à la base de données.
* Une classe générique centrale (`ObjetBdd`) permettant de gérer :

  * Insert
  * Create
  * Update
  * Select avec filtres

À partir de ces objets, le système génère dynamiquement :

* Les services et contrôleurs côté serveur.
* Les pages JSP côté front-end avec un effort minimal.

Grâce à **ScriptEngine**, l’application génère automatiquement :

* Le HTML des formulaires
* Le JavaScript de validation

Chaque champ est créé selon les attributs et annotations de l’objet, prenant en charge différents types :

* Texte
* Textarea
* Radio
* Date et heure
* CKEditor
* Select
* Fichier
* Numérique

Cette approche permet de générer automatiquement :

* Des pages CRUD dynamiques
* Des tableaux listant les éléments avec filtres
* Des formulaires d’insertion et de modification avec valeurs par défaut

Ainsi, **chaque nouvel objet issu de la base de données peut disposer instantanément d’une interface CRUD complète** avec un minimum d’effort.

---

## Technologies Used / Technologies utilisées

| Category / Catégorie | Details / Détails                                 |
| -------------------- | ------------------------------------------------- |
| Language / Langage   | **Java**                                          |
| Application Type     | **Java Web Application**                          |
| Architecture         | **MVC (Model – View – Controller)**               |
| Backend              | **Java Servlets**                                 |
| Frontend View        | **JSP (JavaServer Pages)**                        |
| ORM Concept          | **Custom Generic ORM (Reflection & Annotations)** |
| Dynamic Rendering    | **ScriptEngine (HTML & JavaScript generation)**   |
| Database             | **Postgres Relational Database**               |

---

## Administrator Backoffice / Backoffice Administrateur

**EN:**
The administrator manages all reference data required for event creation:

* Management of **service types** (logistics, communication, shows, etc.).
* Management of **venues and locations** (halls, open spaces, stadiums).
* Management of **artists**, including pricing per hour or per day.
* Management of **client information**.

**FR :**
L’administrateur gère l’ensemble des données de référence nécessaires à la création d’événements :

* Gestion des **types de prestations** (logistique, communication, spectacle, etc.).
* Gestion des **lieux et espaces** (salles, terrains, espaces ouverts).
* Gestion des **artistes**, avec leurs tarifs par heure ou par jour.
* Gestion des **informations clients**.

---

## Employee Features / Fonctionnalités Employé

**EN:**
Employees use the application to manage operational event workflows:

* Creation of **client events**.
* Management of **event quotations**, including:

  * Adding multiple service types with price, quantity, and/or duration.
  * Visualizing cost distribution per service through graphs.
  * Managing venue pricing by seat category and number of tickets sold.
  * Displaying a full financial summary:

    * Total revenue
    * Total expenses
    * Gross profit (before tax)
    * Net profit

**FR :**
Les employés utilisent l’application pour gérer les opérations événementielles :

* Création des **événements clients**.
* Gestion des **devis événementiels**, incluant :

  * L’ajout de plusieurs prestations avec prix, quantité et/ou durée.
  * La visualisation graphique de la répartition des montants par prestation.
  * La gestion des prix des lieux selon les catégories de places et le nombre de billets vendus.
  * L’affichage d’un récapitulatif financier complet :

    * Recettes totales
    * Dépenses totales
    * Bénéfice brut (hors taxes)
    * Bénéfice net

---

## Conclusion / Conclusion

**EN:**
This **Event Management System** provides a powerful and extensible Java Web solution for managing events, services, and financial performance.
Its generalized architecture significantly reduces development time by automatically generating CRUD interfaces from database objects.
Future enhancements may include advanced reporting, taxation management, and multi-company support.

**FR :**
Ce **système de gestion événementielle** offre une solution Java Web puissante et évolutive pour la gestion des événements, des prestations et des performances financières.
Son architecture généralisée réduit considérablement le temps de développement grâce à la génération automatique des interfaces CRUD à partir des objets de la base de données.
Les évolutions futures pourraient inclure des rapports avancés, la gestion fiscale et le support multi-entreprises.
