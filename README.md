# books.app

Books.app is a simple application to manage books for a personnal library. I created it to test the CQRS/DDD/Event sourcing framwork : Axonframework. 

## Used Framework
* AngularJS 1.5.7
* Bootstrap 3.3.6
* jQuery 2.2.4
* Eclipselink 2.6.3
* Axonframework 2.4.4
* See pom.xml for other frameworks

## Functionnalities

This webapp can :
* Create book pages,
* Create auteur pages,
* Create saga pages,
* Search through google books repository to make easier filling up informations on books.
* Explore book collection.

## Roadmap and possible improvements
* Make generic REST services for basic operations.
* Some UI improvements can be done : sort, filtering...

## Deployement
* To deploy this webapp, create a java DataSource to the database (jdbc/books) in server configuration file.
* Run SQL script to create database schema and run schema update file to match the last schema version.
* Build with maven the application as war file.
* Put war file in server deployement folder.

Tested on Tomcat 7 with MySQL 5.
