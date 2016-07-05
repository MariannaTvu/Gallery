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
            <h1><a href="/">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="#">Login</a></li>
            <li class="active"><a href="#">Profile</a>
                <ul>
                    <li ><a href="/upload_art">Submit art</a></li>
                    <li><a href="html/shop.html">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li><a href="/artist_gallery">Galleries</a></li>
            <li ><a href="/">Main</a></li>
        </ul>
        <br class="clear" />
    </div>
</div>
<div class="wrapper col3">

</div>


<div class="wrapper col5">
    <div id="container">
        <div id="content">
            <h1>${picture.name}</h1>
            <img class="imgr" src="picture/${picture.id}" alt="" width="100%" height="auto" />


            <div id="comments">
                <h2>Comments</h2>
                <ul class="commentlist">
                    <c:forEach items="${comments}" var="comment">
                    <li class="comment_odd">
                        <div class="author"><img class="avatar" src="../images/demo/avatar.gif" width="32" height="32" alt="" /><span class="name"><a href="#">A Name</a></span> <span class="wrote">wrote:</span></div>
                        <div class="submitdate"><a href="#">August 4, 2009 at 8:35 am</a></div>
                        <p>${comment.text}</p>
                    </li>
                    </c:forEach>
                </ul>
            </div>
            <h2>Write A Comment</h2>
            <div id="respond">
                <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add_comment" method="get">
                    <input type="hidden" name="picture_id" value=${picture.id}>
                    <div class="form-group"><input type="text" class="form-control" name="comment" placeholder="Comment"></div>
                    <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
                </form>


            </div>
        </div>
        <div id="column" style="margin-top: 5%">
            <div class="subnav">
                <h2>Buy This Print</h2>
                <div class="dev-view-meta">
                    <div id="buy-tabs" class="newprintbutton open">
                        <div class="product-tab-all-container">
                            <div align="center">
                                <h3><b><span class="symbol">$</span>
                                    <span class="dollars">22</span>
                                    <span class="cents">.80</span></b></h3>
                            </div>

                            <div class="action dual">
                                <a href="" class="smbutton smbutton-blue addToCart"><span><i class="icon icon-pluswhite"></i>Add to Cart</span></a>
                                <a href="" onclick="" class="smbutton smbutton-lightgreen smbutton-shadow wish-button addToWishlist"><span>Add to Wishlist</span></a>
                            </div>
                        </div>
                    </div>


                    <div id="fave-btn-wrap">
                        <a name="gmi-ResourceViewFavouriteButton" id="gmi-ResourceViewFavouriteButton" href="#" class="dev-page-button ">
                            <span><em class="iconx-favesstar"></em><b>Add to Favourites</b></span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
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
        <p>Template by <a target="_blank" href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>

