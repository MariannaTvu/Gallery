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
            <form role="search" action="/search" method="post">
                <fieldset>
                    <legend>Site Search</legend>
                    <input type="text" name="pattern" placeholder="Search" />
                    <input type="submit" name="go" id="go" value="GO" />
                </fieldset>
            </form>
        </div>
    </div>
</div>
<div class="wrapper col2">
    <div id="header">
        <div id="logo">
            <h1><a href="/">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="#">Login</a></li>
            <li><a href="#">Profile</a>
                <ul>
                    <li><a href="/upload_art">Submit art</a></li>
                    <li><a href="/pages/shop.jsp">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li class="active"><a href="artist_gallery">Galleries</a></li>
            <li><a href="/">Main</a></li>
        </ul>
        <br class="clear"/>
    </div>


</div>

<div class="wrapper col5">
    <div id="container">
        <div id="content">
            <h2>About artist</h2>
            <p>Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis adipis
                laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.</p>
            <p>Semalique tor sempus vestibulum libero nibh pretium eget eu elit montes. Sedsemporttis sit intesque felit
                quis elis et cursuspenatibulum tincidunt non curabitae.</p>
        </div>
        <div id="column">
            <div class="flickrbox">

                <ul>
                    <li><a href="#"><img src="" alt="" style="width: auto; height: 100%;"/></a>
                    </li>

                </ul>
                <br class="clear"/>
            </div>
        </div>
        <br class="clear"/>
    </div>
</div>

<div class="wrapper col3">


    <ul class="sort_navigation">
        <li><a href="" title="Home">Home</a></li>
        <li><a href="" title="About us">About us</a></li>
        <li><a href="" title="Portfolio">Portfolio</a>
            <!--  <ul>
                <li><a href="" title="Websites">Websites</a></li>
                <li><a href="" title="Webshops">Webshops</a></li>
                <li><a href="" title="SEO">SEO</a></li>
                <li><a href="" title="Responsive webdesign">Responsive webdesign</a></li>
              </ul> -->
        </li>
        <li><a href="" title="Contact">Contact</a></li>
        <div class="clear"></div>
    </ul>
</div>
    <!-- works -->
    <div class="wrapper col4">
        <div id="works" class="artist_grid">
            <div class="artist_grid">
                <c:forEach items="${pictures}" var="picture">

                        <figure class="art_hovereffect" style="width: 25%">
                            <img src=picture/${picture.id} />

                            <a href="<c:url value='/view_art/${picture.id}'/>">
                                <div class="overlay">

                                    <h2 > <c:out value="${picture.name}"/> </h2>

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


<div class="wrapper col7" >
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
