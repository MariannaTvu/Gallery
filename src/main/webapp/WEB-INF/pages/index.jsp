<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Gallery</title>


    <link href="/resources/layout/styles/layout.css" rel="stylesheet"  type="text/css"/>
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
<div class="wrapper col2" >
    <div id="header">
        <div id="logo">
            <h1><a href="/">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="/">Login</a></li>
            <li><a href="#">Profile</a>
                <ul>
                    <li><a href="/upload_art">Submit art</a></li>
                    <li><a href="/pages/shop.html">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li><a href="/artist_gallery">Galleries</a></li>
            <li class="active"><a href="/">Main</a></li>
        </ul>
        <br class="clear" />
    </div>
</div>
<ul class="sort_navigation">
    <li><form action="/sort_by_name" ><a href="/sort_by_name" title="Sort by name" >Sort by name</a></form> </li>
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
<div class="wrapper col3">
    <div id="photos">
        <c:forEach items="${pictures}" var="picture">
        <div class="container-fluid">
            <div class="row">
                <div class="hovereffect"  >


                <img  class="img-responsive"  src="picture/${picture.id}" alt="Image 1" >
                <div class="overlay">
                    <h2><c:out value="${picture.name}"/> </h2>
                    <a class="info" href="html/artist_gallery.html" >view userGallery</a>
                </div>


                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>

<div class="wrapper col5" style="margin-top: 5%">
    <div id="container">
        <div id="content">
            <h2>About This Free CSS Template</h2>
            <p>This is a W3C standards compliant free website template from <a href="http://www.os-templates.com/">OS Templates</a>.</p>
            <p>This template is distributed using a <a href="http://www.os-templates.com/template-terms">Website Template Licence</a>, which allows you to use and modify the template for both personal and commercial use when you keep the provided credit links in the footer.</p>
            <p>For more CSS templates visit <a href="http://www.os-templates.com/">Free Website Templates</a>.</p>
            <p>Lacusenim inte trices lorem anterdum nam sente vivamus quis fauctor mauris. Wisinon vivamus wisis adipis laorem lobortis curabiturpiscingilla dui platea ipsum lacingilla.</p>
            <p>Semalique tor sempus vestibulum libero nibh pretium eget eu elit montes. Sedsemporttis sit intesque felit quis elis et cursuspenatibulum tincidunt non curabitae.</p>
        </div>

        <br class="clear" />
    </div>
</div>
<div class="wrapper col6">
    <div id="footer">
        <div id="contactform">
            <h2>Why Not Contact Us Today !</h2>
            <form action="#" method="post">
                <fieldset>
                    <legend>Contact Form</legend>
                    <label for="fullname">Name:
                        <input id="fullname" name="fullname" type="text" value="" />
                    </label>
                    <label for="emailaddress" class="margin">Email:
                        <input id="emailaddress" name="emailaddress" type="text" value="" />
                    </label>
                    <label for="message">Message:<br />
                        <textarea id="message" name="message" cols="40" rows="4"></textarea>
                    </label>
                    <p>
                        <input id="submitform" name="submitform" type="submit" value="Submit" />
                        &nbsp;
                        <input id="resetform" name="resetform" type="reset" value="Reset" />
                    </p>
                </fieldset>
            </form>
        </div>
        <!-- End Contact Form -->
        <div id="compdetails">
            <div id="officialdetails">
                <h2>Company Information !</h2>
                <ul>
                    <li>Copyright &copy; 2014 - All Rights Reserved</li>
                    <li>Company Name Ltd</li>
                    <li>Registered in England &amp; Wales</li>
                    <li>Company No. xxxxxxx</li>
                    <li class="last">VAT No. xxxxxxxxx</li>
                </ul>
                <h2>Stay in The Know !</h2>
                <p><a href="#">Get Our E-Newsletter</a> | <a href="#">Grab The RSS Feed</a></p>
            </div>
            <div id="contactdetails">
                <h2>Our Contact Details !</h2>
                <ul>
                    <li>Company Name</li>
                    <li>Street Name &amp; Number</li>
                    <li>Town</li>
                    <li>Postcode/Zip</li>
                    <li>Tel: xxxxx xxxxxxxxxx</li>
                    <li>Fax: xxxxx xxxxxxxxxx</li>
                    <li>Email: info@domain.com</li>
                    <li class="last">LinkedIn: <a href="#">Company Profile</a></li>
                </ul>
            </div>
            <div class="clear"></div>
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
