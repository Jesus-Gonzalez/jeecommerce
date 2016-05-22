# Introduction

*jeecommerce* is an ecommerce platform running Java as backend.

# Tecnologies Used
On the backend:
* Java EE 7
* PostgreSQL 9.5

On the frontend:
* jQuery 1.x
* AngularJS 1.x
* Bootstrap 3.x

# Libraries used

I have used various libraries which are available under Debian repos directly.

These libraries are:

* servlet-api-3.1 (libservlet3.1-java package)
* websocket-api (included in libservlet3.1-java package)
* mailapi (included by default in JDK)
* smtp (included by default in JDK)
* libpostgresql-jdbc (jdbc4-9.2)
* libgoogle-gson-java (GSON)
* standard.jar (includes various libraries, such as xerces or jsp-api)
* stripe-2.x.jar (downloaded from Stripe.com, used to process credit-card payments, find it in /lib directory)

Every library (but Stripe) is available at Debian repositories.

# How am I developing?

I'm using the latest Eclipse IDE: Neon release.

I'm also using Atom in order to write HTML/CSS/Javascript.

I use pgadmin3 to manage databases, run test queries and fast-edit tables/constraints/etc

I'm using PostgreSQL 9.5 as DBMS and OpenJDK 8.

All of this under Debian 9 Stretch in both my laptop and desktop computer. Sometimes I use Ubuntu 16.04 Xenial as well.

# Work in progress ...

As I'm writting this, I'm still developing the project, and I can say it's not a stable project at all, it's under active development nowadays.

# Credits

This is a student project, but anyone willing to fork this, contribute or cherry-picking whatever you like, you are allowed to.
