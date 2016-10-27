<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Gallery</title>
    <link href="/resources/layout/styles/layout.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">

    <link rel="stylesheet" href="/resources/assets/animate/animate.css"/>
    <link rel="stylesheet" href="/resources/assets/animate/set.css"/>

    <!-- userGallery -->
    <link rel="stylesheet" href="/resources/assets/style.css">

    <!--userGallery end-->
    <script type="text/javascript" src="/resources/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/resources/freewall.js"></script>
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
                <li><a href="/user_details">Profile</a>
                    <ul>
                        <li><a href="/user_pictures">Edit gallery</a></li>
                        <li><a href="/user_details">User info</a></li>
                        <li><a href="/shop">Shop</a></li>
                    </ul>
                </li>
            </sec:authorize>
            <li><a href="/art">Art</a></li>
            <li class="active"><a href="/">Main</a></li>
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
        <li style=" position: absolute; right: 0px"><a>Logged in as: ${login}</a></li>
    </sec:authorize>

    <div class="clear"></div>
</ul>
<div class="clear"></div>
<div class="wrapper col3">

    <script type="text/javascript">

    var temp = "<div class='cell' style='width:{width}px; height: {height}px; background-image: url(picture/{index}.jpg)'></div>";

    var w = 1, html = '', limitItem = 5;
        for (var i = 0; i < limitItem; ++i) {
            w = 200 +  200 * Math.random() << 0;
            html += temp.replace(/\{height\}/g, 200).replace(/\{width\}/g, w).replace("{index}", i + 1);
        }
        $("#freewall").html(html);

    var wall = new Freewall("#freewall");
        wall.reset({
            selector: '.cell',
            animate: true,
            cellW: 20,
            cellH: 200,
            onResize: function() {
                wall.fitWidth();
            }
        });
        wall.fitWidth();
        // for scroll bar appear;
        $(window).trigger("resize");
    </script>
    </div>


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
        <div class="clear"></div>
    </div>
</div>
</body>
</html>