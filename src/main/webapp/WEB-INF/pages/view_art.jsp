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
            <p>to share you artwork</p>
        </div>
        <ul id="topnav">
            <sec:authorize access="!isAuthenticated()">
                <li class="last"><a href="/login">Login</a></li>
                <li class="last"><a href="/reg">Register</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="/user_details">Profile</a>
                    <ul>
                        <li><a href="/upload_art">Submit art</a></li>
                        <li><c:url value="/logout" var="logoutUrl"/><a href="${logoutUrl}">Log Out</a></li>
                    </ul>
                </li>
            </sec:authorize>
            <li class="active"><a href="/art">Art</a></li>
            <li><a href="/">Main</a></li>
        </ul>
        <br class="clear"/>
    </div>
</div>
<div class="wrapper col3">

</div>
<div class="wrapper col5">
    <div id="container">
        <div id="content">
            <h1>${picture.name} by ${author}</h1>
            <img class="imgr" src="picture/${picture.id}" alt="" width="100%" height="auto"/>
            <c:if test="${picture.description ne null}">
                <div id="column" >
                    <h5 style="border-bottom: 0px;">Description:</h5>
                    <p>${picture.description}</p>
                </div>
            </c:if>

            <div id="comments">
                <h2>Comments</h2>
                <ul class="commentlist">
                    <c:forEach items="${comments}" var="comment">
                        <li class="comment_odd">
                            <div class="author"><span class="name"><a href="#">${comment.author}</a></span> <span
                                    class="wrote">wrote:</span></div>
                            <div class="submitdate"><a href="#">${comment.date}</a></div>
                            <p>${comment.text}</p>
                        </li>
                    </c:forEach>
                </ul>
                <sec:authorize access="!isAuthenticated()">
                    <p>To add a comment, <a href="/login">login</a> or <a href="/reg">register</a></p>
                </sec:authorize>
            </div>
            <sec:authorize access="isAuthenticated()">
                <h2>Write A Comment</h2>
                <div id="respond" style="margin-bottom:50px;">
                    <form role="form" enctype="multipart/form-data" id="form-horizontal" action="/add_comment"
                          method="get"
                          cols="100%" rows="10" style="color:black;">
                        <input type="hidden" name="picture_id" value=${picture.id}>
                <textarea name="comment" id="comment" cols="100%" rows="10" style="color:black;"
                          name="comment" placeholder="Comment"></textarea>
                        <p><input type="submit" id="submit" value="Add comment"></p>
                    </form>


                </div>
            </sec:authorize>
        </div>
    </div>
    <div class="clear"></div>
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

