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

    <!-- CSS Files -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/style.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/menu/css/simple_menu.css">
    <link rel="stylesheet" href="/resources/css/nivo-slider.css" type="text/css" media="screen">
    <!--    photo header styles 1 -->
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
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
            <form role="search" action="/search" method="get">
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
    <li>
        <form action="/for_sale"><a href="/for_sale" title="Buy art">Buy art</a></form>
    </li>
    <sec:authorize access="isAuthenticated()">
        <li style=" position: absolute; right: 0px"><a>Logged in as: ${user.login}
        </a></li>
    </sec:authorize>
    <div class="clear"></div>
</ul>
<div class="wrapper col5" style="border-bottom: 0">
    <div id="container">
        <div id="content" style="margin-bottom:10%">
            <div>
                <p>Your login: ${user.login}</p>
            </div>
            <div>
                <p>Your balance: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                                   value='${user.balance/100}' pattern='##,###.##'/>
                                                    $</p>
            </div>

            <c:if test="${user.bio ne null}">
                <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add_bio" method="post">
                    <div class="form-group" style="margin-top:5%;"><h3>Update bio</h3><input type="text"
                                                                                             class="form-control"
                                                                                             name="bio"
                                                                                             placeholder="Current bio will be re-written">
                    </div>
                    <div class="form-group"><input type="submit" class="btn btn-primary" value="Update"></div>
                </form>
            </c:if>
            <c:if test="${user.bio == null}">
                <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add_bio" method="post">
                    <div class="form-group" style="margin-top:5%;">Add a bio<input type="text" class="form-control"
                                                                                   name="bio"
                                                                                   placeholder="Tell something about you">
                    </div>
                    <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
                </form>
            </c:if>

            <div class="form-group">
                <h3>Edit gallery</h3>
                <form action="/user_pictures"><input type="submit" class="btn btn-primary"
                                                     value="Click here to edit gallery">
                </form>
            </div>
            <br>
            <br>
            <br>
            <div>
                <h3>Order history</h3>
                <c:if test="${!empty orders}">
                    <table>
                        <tr>
                            <th>Picture name</th>
                            <th>Picture price</th>
                            <th>Date ordered</th>
                            <th>Date of purchase</th>
                            <th>Author</th>
                        </tr>
                        <c:forEach items="${orders}" var="orders">
                            <tr>
                                <td>${orders.picture.name}</td>
                                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                                      value='${orders.picture.price/100}' pattern='##,###.##'/>
                                </td>
                                <td>${orders.date}</td>
                                <td>${orders.purchaseDate}</td>
                                <td>${orders.picture.author.login}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${msg ne null}">
                    <p>${msg}</p>
                </c:if>
            </div>
        </div>

    </div>

    <div class="wrapper col5" style="margin-top:5%;">
        <div id="container">
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
