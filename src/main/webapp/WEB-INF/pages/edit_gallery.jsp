<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 18.06.2016
  Time: 23:46
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


    <!-- CSS Files -->
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css"
          href="/resources/menu/css/simple_menu.css"/>
    <link rel="stylesheet" href="/resources/css/nivo-slider.css" type="text/css"
          media="screen"/>
    <!--    photo header styles 1 -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,400italic"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/ekko-lightbox.min.css"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <!--    photo header styles 1 end -->
    <!-- userGallery -->
    <!-- animate.css -->
    <link rel="stylesheet" href="/resources/assets/animate/animate.css"/>
    <link rel="stylesheet" href="/resources/assets/animate/set.css"/>

    <!-- userGallery -->
    <link rel="stylesheet" type="text/css" href="/resources/assets/userGallery/blueimp-userGallery.min.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/assets/style.css"/>

    <!--userGallery end-->

</head>
<body id="top">
<div class="wrapper col1">
    <div id="topbar">
        <div id="search">
            <form role="search" action="/search" method="go">
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
            <sec:authorize access="!isAuthenticated()">
                <li class="last"><a href="/login">Login</a></li>
                <li class="last"><a href="/reg">Register</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><c:url value="/logout" var="logoutUrl"/><a href="${logoutUrl}">Logout</a></li>
                <li><a href="/upload_art">Submit art</a></li>
                <li class="active"><a href="/user_details">Profile</a>
                    <ul>
                        <li><a href="/user_pictures">Edit gallery</a></li>
                        <li><a href="/user_details">User info</a></li>
                        <li><a href="/shop">Shop</a></li>
                    </ul>
                </li>
            </sec:authorize>
            <li><a href="/art">Art</a></li>
            <li ><a href="/">Main</a></li>
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
    <li>
        <form action="/for_sale"><a href="/for_sale" title="Buy art">Buy art</a></form>
    </li>
    <sec:authorize access="isAuthenticated()">
        <li style=" position: absolute; right: 0px"><a>Logged in as: ${author.login}</a></li>
    </sec:authorize>
    <div class="clear"></div>
</ul>

<div class="wrapper col5">
    <div id="container">
        <div id="content">
            <h2>About ${author.login} <a href="/user_details">Edit bio</a></h2>
            <p>${author.bio}</p>
        </div>

        <br class="clear"/>
    </div>
</div>

<div class="wrapper col3">
    <div id="container">
        <div id="content">
            <div class="form-group" style="float:none; text-aling:center">
                <h3>Upload</h3>
                <form action="/upload_art"><input type="submit" class="btn btn-primary"
                                                  value="Click here to upload pictures">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="wrapper col4"style="margin-top:10%; ">

    <div id="works" class="artist_grid">
        <div class="artist_grid">
            <c:forEach items="${pictures}" var="picture">
                <figure class="art_hovereffect" style="width: 25%">
                    <div id="crop" class="crop-image-box">
                        <div id="image" class="crop-image" style="background-image: url(<c:url value='picture/${picture.id}'/>);" />
                    </div>
                    <a href="<c:url value='/view_art/${picture.id}'/>">
                        <div class="overlay">

                            <h2><c:out value="${picture.name}"/></h2>
                            <br>
                            <br>
                            <p><a href="/delete_picture/${picture.id}" var="picture_id" value="${picture.id}">Delete</a></p>
                        </div>
                    </a>
                </figure>
            </c:forEach>
        </div>
    </div>
    <!-- works -->


</div>
<!-- close container -->


</div>

<br class="clear"/>

<div class="wrapper col5" style="margin-top:5%;">

    <div id="container">
        <div id="content">

        <h2>About </h2>
        <p>Sedsemporttis sit intesque felit
            quis elis et cursuspenatibulum tincidunt non curabitae.</p>
        <p>Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis adipis
            laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.</p>
        Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis adipis
        laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.
        <p>Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis adipis
            laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.</p>
        <p>Semalique tor sempus vestibulum libero nibh pretium eget eu elit montes. Sedsemporttis sit intesque felit
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
        <p>Template by <a target="_blank" href="http://www.os-templates.com/" title="Free Website Templates">OS
            Templates</a></p>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>


