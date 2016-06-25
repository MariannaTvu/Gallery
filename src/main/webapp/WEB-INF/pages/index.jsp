<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Gallery</title>


    <link href="${pageContext.request.contextPath}../layout/styles/layout.css" rel="stylesheet"  type="text/css"/>
    <!--    photo header styles 1 -->
    <link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,400italic'>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../css/ekko-lightbox.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../../css/style.css">
    <!--    photo header styles 1 end -->

    <!-- userGallery -->
    <!-- animate.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../assets/animate/animate.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../assets/animate/set.css" />

    <!-- userGallery -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../assets/userGallery/blueimp-userGallery.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}../../assets/style.css">

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
            <h1><a href="index.html">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="#">Login</a></li>
            <li><a href="#">Profile</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}../../pages/upload_art.html">Submit art</a></li>
                    <li><a href="${pageContext.request.contextPath}../../pages/shop.html">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li><a href="userGallery.html">Galleries</a></li>
            <li class="active"><a href="index.html">Main</a></li>
        </ul>
        <br class="clear" />
    </div>
</div>
<div class="wrapper col3">
    <div id="userGallery">




        <div class="container-fluid">
            <div class="row">

                <div class="hovereffect" style="width:33.333333%" >
                    <div class="33">
                        <img  class="img-responsive"  src="images/tm-sigma-01.jpg" alt="Image 1" >
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html" >view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:33.333333%" >
                    <div class="33">
                        <img src="${pageContext.request.contextPath}../../images/tm-sigma-02.jpg" alt="Image 2" class="img-fluid sigmapad col-lg-4 col-md-6 col-sm-6">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="${pageContext.request.contextPath}../../artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:33.333333%" >
                    <div class="33">
                        <img src="${pageContext.request.contextPath}../../images/tm-sigma-03.jpg" alt="Image 3" class="img-fluid sigmapad col-lg-4 col-md-8 col-sm-12">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:66.666666%" >
                    <div class="33">
                        <img src="images/tm-sigma-04.jpg" alt="Image 4" class="img-fluid sigmapad col-lg-8 col-md-4 col-sm-6">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:33.333333%" >
                    <div class="33">
                        <img src="images/tm-sigma-05.jpg" alt="Image 5" class="img-fluid sigmapad col-lg-4 col-md-4 col-sm-6">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:33.333333%" >
                    <div class="33">
                        <img src="images/tm-sigma-06.jpg" alt="Image 6" class="img-fluid sigmapad col-lg-4" title="Bicycle">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:50%" >
                    <div class="33">
                        <img src="images/tm-sigma-07.jpg" alt="Image 7" class="img-fluid sigmapad col-sm-6" title="Walking">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:50%" >
                    <div class="33">
                        <img src="images/tm-sigma-08.jpg" alt="Image 8" class="img-fluid sigmapad col-sm-6" title="Camera">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:25%" >
                    <div class="33">
                        <img src="images/tm-sigma-01.jpg" alt="Image 9" class="img-fluid sigmapad col-lg-3 col-md-8 col-sm-12">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:25%" >
                    <div class="33">
                        <img src="images/tm-sigma-02.jpg" alt="Image 10" class="img-fluid sigmapad col-lg-3 col-md-4 col-sm-6">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:25%" >
                    <div class="33">
                        <img src="images/tm-sigma-03.jpg" alt="Image 11" class="img-fluid sigmapad col-lg-3 col-md-4 col-sm-6">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>

                <div class="hovereffect" style="width:25%" >
                    <div class="33">
                        <img src="images/tm-sigma-04.jpg" alt="Image 12" class="img-fluid sigmapad col-lg-3 col-sm-12">
                        <div class="overlay">
                            <h2>Picture name by Author</h2>
                            <a class="info" href="artist_gallery.html">view userGallery</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="wrapper col4">
    <div id="services">




        <br class="clear" />
    </div>
</div>
<div class="wrapper col5">
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
