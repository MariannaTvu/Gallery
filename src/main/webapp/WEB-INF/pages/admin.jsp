<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 23.06.2016
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Gallery</title>
    <link href="/resources/layout/styles/layout.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="/resources/layout/styles/layout.css" type="text/css"/>


    <!-- CSS Files -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/style.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/menu/css/simple_menu.css">
    <link rel="stylesheet" href="/resources/css/nivo-slider.css" type="text/css" media="screen">
    <!--    photo header styles 1 -->
    <link rel='stylesheet' type='text/css'
          href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,400italic'>
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/ekko-lightbox.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <!--    photo header styles 1 end -->
    <!-- userGallery -->
    <!-- animate.css -->
    <link rel="stylesheet" href="/resources/assets/animate/animate.css"/>
    <link rel="stylesheet" href="/resources/assets/animate/set.css"/>

    <!-- userGallery -->
    <link rel="stylesheet" href="/resources/assets/userGallery/blueimp-userGallery.min.css">
    <link rel="stylesheet" href="/resources/assets/style.css">
    <link rel="stylesheet" href="/resources/css/table.css">

    <!--userGallery end-->
</head>
<body id="top">
<div class="wrapper col1">
    <div id="topbar">
        <div id="search">
            <form role="search" action="/search" method="post">
                <fieldset>
                    <legend>Site Search</legend>
                    <input type="text" name="pattern" placeholder="Search"/>
                    <input type="submit" name="go" id="go" value="GO"/>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<div class="wrapper col2">
    <div id="header">
        <div id="logo">
            <h1><a href="/">Gallery</a></h1>
            <p>to share and sell your artwork</p>
        </div>
        <ul id="topnav">
            <li><c:url value="/logout" var="logoutUrl"/><a href="${logoutUrl}">Log Out</a></li>
            <li><a href="/art">Art</a></li>
            <li class="active"><a href="/admin">Admin page</a></li>
            <li><a href="/">Main</a></li>
        </ul>
        <br class="clear"/>
    </div>
</div>
<ul class="sort_navigation">
    <li>
        <form action="/sort_by_comments"><a href="/sort_by_comments" title="Sort by comments">View most commented
            art</a></form>
    </li>
    <li>
        <form action="/sort_by_date"><a href="/sort_by_date" title="Sort by date">See latest updates</a></form>
    </li>
    <sec:authorize access="isAuthenticated()">
        <li style=" position: absolute; right: 0px"><a>Logged in as: ${user.username}
        </a></li>
    </sec:authorize>
    <div class="clear"></div>
</ul>
<div class="wrapper col5" style="border-bottom: 0">
    <div id="container">
        <div id="content" style="margin-bottom:10%">
            <div>
                <p>Your login: ${user.username}</p>
            </div>

        </div>
    </div>

</div>


<div class="wrapper col5" style="margin-top:5%;">
    <div id="container">

        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/delete_user" method="post">
            <div class="form-group" style="margin-top:5%;"><p>Enter user's id</p><input type="text" class="form-control" name="user_id">
            </div>
            <div class="form-group"><input type="submit" class="btn btn-primary" value="delete"></div>
        </form>

        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/delete_picture" method="post">
            <div class="form-group" style="margin-top:5%;"><p>Enter picture id</p><input type="text" class="form-control" name="picture_id">
            </div>
            <div class="form-group"><input type="submit" class="btn btn-primary" value="delete"></div>
        </form>

        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/set_balance" method="get">
            <div class="form-group" style="margin-top:5%;"><p>Enter user's id</p><input type="text" class="form-control" name="user_id">
            </div>
            <div class="form-group" style="margin-top:5%;"><p>Enter new balance</p><input type="text" class="form-control" name="new_balance">
            </div>
            <div class="form-group"><input type="submit" class="btn btn-primary" value="set"></div>
        </form>
        <div id="content">
            <h2>About </h2>
            <p>Sedsemporttis sit intesque felit
                quis elis et cursuspenatibulum tincidunt non curabitae.</p>
            <p>Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis
                adipis
                laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.</p>
            Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis adipis
            laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.
            <p>Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis
                adipis
                laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.</p>
            <p>Semalique tor sempus vestibulum libero nibh pretium eget eu elit montes. Sedsemporttis sit intesque
                felit
                quis elis et cursuspenatibulum tincidunt non curabitae.</p>
        </div>

        <br class="clear"/>
    </div>
</div>
<div class="wrapper col7">
    <div id="copyright">
        <ul>
            <li><a href="#">Online Privacy Policy</a></li>
            <li><a href="#">Terms of Use</a></li>
            <li><a href="#">Permissions &amp; Trademarks</a></li>
            <li class="last"><a href="#">Product License Agreements</a></li>
        </ul>
        <div class="clear"></div>
    </div>
</div>
</div>
</body>
</html>
