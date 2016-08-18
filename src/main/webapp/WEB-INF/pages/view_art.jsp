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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            <sec:authorize access="!isAuthenticated()">
                <li class="last"><a href="/login">Login</a></li>
                <li class="last"><a href="/reg">Register</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li ><a href="/shop">Cart</a></li>
                <li><a href="/upload_art">Submit art</a></li>
                <li ><a href="/user_details">Profile</a>
                    <ul>
                        <li><a href="/user_pictures">Edit profile</a></li>
                        <li><a href="/user_details">User info</a></li>
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


            <div id="comments">
                <h2>Comments</h2>
                <c:if test="${empty comments}"><p>No comments yet</p></c:if>
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

        <div id="column">
            <div class="subnav">
                <c:if test="${picture.description ne null}">
                    <div>
                        <h5>Description:</h5>
                        <p style=" margin: 2%">${picture.description}</p>
                        <p style="border-bottom: 1px dashed #666666;"></p>
                    </div>
                </c:if>
            </div>
            <br>
            <c:if test="${(picture.price ne 0) && (same_user ne null)}">
                <h2 style="text-align: center; color:white">Buy This Print
                    <a href="#" data-toggle="popover" data-trigger="hover"
                       data-content="Buy a printed version of this art">*</a>
                </h2>

                <script>
                    $(document).ready(function () {
                        $('[data-toggle="popover"]').popover();
                    });
                </script>

                <div class="dev-view-meta">
                    <div id="buy-tabs" class="newprintbutton open">
                        <div class="product-tab-all-container">
                            <div align="center">
                                <h3><b><span class="symbol">$</span>
                                    <span class="dollars">
                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                                      value='${picture.price/100}' pattern='##,###.##'/>
                                   </span>
                                </b></h3>
                            </div>
                            <sec:authorize access="isAuthenticated()">
                                <form role="form" enctype="multipart/form-data" id="form1" class="form-horizontal"
                                      action="/add_to_cart" method="post">
                                    <input type="hidden" name="picture_id" value=${picture.id}>
                                    <div class="action dual" style="text-align: center">
                                        <a href="" class="smbutton smbutton-blue addToCart"
                                           onclick="document.getElementById('form1').submit();"><b>Add to Cart</b></a>
                                    </div>
                                </form>
                            </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                                <p>To be able to add to cart, <a href="/login">login</a> or <a href="/reg">register</a></p>
                            </sec:authorize>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>

                        </div>
                    </div>
                </div>
            </c:if> <a href="/artist_gallery/${picture.userGallery.id}" style="margin:37%">Back
            to ${picture.author.login}'s gallery</a>
        </div>
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

