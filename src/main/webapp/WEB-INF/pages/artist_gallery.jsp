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

    <link href="${pageContext.request.contextPath}../layout/styles/layout.css" rel="stylesheet"  type="text/css"/>


    <!-- CSS Files -->
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}../../css/style.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}../../menu/css/simple_menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../css/nivo-slider.css" type="text/css"
          media="screen"/>
    <!--    photo header styles 1 -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,400italic"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../css/ekko-lightbox.min.css"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../css/style.css"/>
    <!--    photo header styles 1 end -->
    <!-- userGallery -->
    <!-- animate.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../assets/animate/animate.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../assets/animate/set.css"/>

    <!-- userGallery -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../assets/userGallery/blueimp-userGallery.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../assets/style.css"/>

    <!--userGallery end-->

</head>
<body id="top">

<div class="wrapper col1">
    <div id="topbar">
        <div id="search">
            <form action="#" method="post">
                <fieldset>
                    <legend>Site Search</legend>
                    <input type="text" value="Search the site"
                           onfocus="this.value=(this.value=='Search the site;')? '' : this.value ;"/>
                    <input type="submit" name="go" id="go" value="GO"/>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<div class="wrapper col2">
    <div id="header">
        <div id="logo">
            <h1><a href="../../../../../../../ProjectPro/front/index.html">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="#">Login</a></li>
            <li><a href="#">Profile</a>
                <ul>
                    <li><a href="../../../../../../../ProjectPro/front/pages/upload_art.html">Submit art</a></li>
                    <li><a href="../../../../../../../ProjectPro/front/pages/shop.html">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li class="active"><a href="../../../../../../../ProjectPro/front/userGallery.html">Galleries</a></li>
            <li><a href="../../../../../../../ProjectPro/front/index.html">Main</a></li>
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

    <!-- works -->

    <div id="works" class=" clearfix grid">
        <div class="artist_grid">
            <c:forEach items="${file_name}" var="file">

            <figure class="art_hovereffect">
                <img src=${file} />
                <a href="">
                    <div class="overlay">
                        <h2>Picture name</h2>
                    </div>
                </a>
            </figure>
            </c:forEach>

        </div>
    </div>
    <!-- works -->


    <!-- close container -->


</div>

<br class="clear"/>


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
