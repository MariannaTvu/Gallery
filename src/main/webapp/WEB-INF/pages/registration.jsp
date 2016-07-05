<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 28.06.2016
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Gallery</title>
<link href="/resources/layout/styles/layout.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="/resources/layout/styles/layout.css" type="text/css" />


<!-- CSS Files -->
<link rel="stylesheet" type="text/css" media="screen" href="/resources/css/style.css">
<link rel="stylesheet" type="text/css" media="screen" href="/resources/menu/css/simple_menu.css">
<link rel="stylesheet" href="/resources/css/nivo-slider.css" type="text/css" media="screen">
<!--    photo header styles 1 -->
<link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,400italic'>
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/ekko-lightbox.min.css">
<link rel="stylesheet" type="text/css" href="/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<!--    photo header styles 1 end -->
<!-- userGallery -->
<!-- animate.css -->
<link rel="stylesheet" href="/resources/assets/animate/animate.css" />
<link rel="stylesheet" href="/resources/assets/animate/set.css" />

<!-- userGallery -->
<link rel="stylesheet" href="/resources/assets/userGallery/blueimp-userGallery.min.css">
<link rel="stylesheet" href="/resources/assets/style.css">

<!--userGallery end-->
    <link rel="stylesheet" href="/resources/css/screen.css" type="text/css" media="screen" title="default" />

    <link rel="stylesheet" href="/resources/css/layout.css" type="text/css" />

    <link rel="stylesheet" href="/resources/css/registration.css">

</head>
<body id="top">
<div class="wrapper col1">
    <div id="topbar">
        <div id="search">
            <form action="#" method="post">
                <fieldset>
                    <legend>Site Search</legend>
                    <input type="text" value="Search the site&hellip;"  onfocus="this.value=(this.value=='Search the site&hellip;')? '' : this.value ;" />
                    <input type="submit" name="go" id="go" value="GO" />
                </fieldset>
            </form>
        </div>
    </div>
</div>
<div class="wrapper col2">
    <div id="header">
        <div id="logo">
            <h1><a href="../index.html">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="#">Login</a></li>
            <li class="active"><a href="#">Profile</a>
                <ul>
                    <li ><a href="pages/upload_art.html">Submit art</a></li>
                    <li><a href="shop.html">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li><a href="../gallery.html">Galleries</a></li>
            <li ><a href="../index.html">Main</a></li>
        </ul>
        <br class="clear" />
    </div>
</div>

<!-- REGISTRATION FORM-->

<h1 class="register-title">Welcome</h1>
<div class="register">

    <spring:url var="userActionUrl" value="/registration" />

    <div class="container">

        <form:form action="${userActionUrl}" method="POST" modelAttribute="user" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="login">
                <div class="form-group ">
                    <form:input type="text" path="login" class="form-control" placeholder="Username" autofocus="true"></form:input>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>

</div>

<!--REGISTRATION FORM END-->


<%--<div class="wrapper col7" style="  margin: auto;  position: absolute; bottom: 0;">--%>
    <%--<div id="copyright">--%>
        <%--<ul>--%>
            <%--<li><a href="#">Online Privacy Policy</a></li>--%>
            <%--<li><a href="#">Terms of Use</a></li>--%>
            <%--<li><a href="#">Permissions &amp; Trademarks</a></li>--%>
            <%--<li class="last"><a href="#">Product License Agreements</a></li>--%>
        <%--</ul>--%>
        <%--<p>Template by <a target="_blank" href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>--%>
        <%--<div class="clear"></div>--%>
    <%--</div>--%>
<%--</div>--%>
</body>
</html>
