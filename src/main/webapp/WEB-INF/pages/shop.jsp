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

    <link rel="stylesheet" type="text/css" href="/resources/css/table.css">
    <!-- CSS Files -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/style.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/menu/css/simple_menu.css">
    <link rel="stylesheet" href="/resources/css/nivo-slider.css" type="text/css" media="screen">
    <!--    photo header styles 1 -->

    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/layout.css">


    <link rel="stylesheet" href="/resources/assets/style.css">


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
        <li style=" position: absolute; right: 0px"><a>Logged in as: <sec:authentication property="principal.username" />
        </a></li>
    </sec:authorize>
    <div class="clear"></div>
</ul>
<div class="wrapper col5" style="border-bottom: 0; width:100%">
    <table border="0" width="100%" cellpadding="0" cellspacing="0" id="shop">
        <tr>
            <td>
                <br>
                <c:if test="${msg ne null}"><p>${msg}</p></c:if>
                <div>
                    <form role="form" enctype="multipart/form-data" id="form1"
                          class="form-horizontal" action="/buy" method="get">
                        <table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
                            <tr>
                                <th class="table-header-check"><a id="toggle-all"></a></th>
                                <th class="table-header-repeat line-left minwidth-1"><a href="">Name</a></th>
                                <th class="table-header-repeat line-left minwidth-1"><a href="">Author</a></th>
                                <th class="table-header-repeat line-left minwidth-1"><a href="">Link</a></th>
                                <th class="table-header-repeat line-left"><a href="">Price</a></th>
                                <th class="table-header-options line-left"><a href="">Options</a></th>
                            </tr>
                            <c:forEach items="${orders}" var="cart">
                                <tr>
                                    <td><input type="checkbox" name="selectedItems"
                                               value="${cart.id}"/></td>
                                    <td>${cart.picture.name}</td>
                                    <td>${cart.picture.author.login}</td>
                                    <td><a href="/view_art?picture_id=${cart.picture.id}">View art</a></td>
                                    <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                                          value='${cart.picture.price/100}' pattern='##,###.##'/></td>
                                    <td class="options-width">
                                        <a href="/remove/${cart.id}" var="idToRemove" class="icon-1 info-tooltip"
                                           value="${cart.id}">Remove from cart</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <td>
                                    <script>function toggle(source) {
                                        checkboxes = document.getElementsByName('selectedItems');
                                        for (var i = 0, n = checkboxes.length; i < n; i++) {
                                            checkboxes[i].checked = source.checked;
                                        }
                                    }</script>
                                    <input type="checkbox" onClick="toggle(this)"/> Select all<br/>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>
                                    <button class="buy-btn" type="submit"> Buy</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <div class="clear"></div>
                </div>
            </td>
        </tr>
        <div class="clear">&nbsp;</div>
    </table>
    </form>

</div>

<div class="wrapper col5" style="margin-top:5%; color:#CCCCCC">
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
        <div class="clear"></div>
    </div>
</div>
</div>
</body>
</html>
