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
            <form action="#" method="post">
                <fieldset>
                    <legend>Site Search</legend>
                    <input type="text" value="Search the site&hellip;"
                           onfocus="this.value=(this.value=='Search the site&hellip;')? '' : this.value ;"/>
                    <input type="submit" name="go" id="go" value="GO"/>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<div class="wrapper col2">
    <div id="header">
        <div id="logo">
            <h1><a href="../index.html">Gallery</a></h1>
            <p>to share and sell you artwork</p>
        </div>
        <ul id="topnav">
            <li class="last"><a href="#">Login</a></li>
            <li class="active"><a href="#">Profile</a>
                <ul>
                    <li><a href="/upload_art">Submit art</a></li>
                    <li><a href="html/shop.html">Shop</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
            <li><a href="/artist_gallery">Galleries</a></li>
            <li><a href="/">Main</a></li>
        </ul>
        <br class="clear"/>
    </div>
</div>
<div class="wrapper col3">
    <div id="breadcrumb">
        <ul>
            <li class="first">You Are Here</li>
            <li>&#187;</li>
            <li><a href="#">Home</a></li>
            <li>&#187;</li>
            <li><a href="#">Grand Parent</a></li>
            <li>&#187;</li>
            <li><a href="#">Parent</a></li>
            <li>&#187;</li>
            <li class="current"><a href="#">Child</a></li>
        </ul>
    </div>
</div>
<div class="wrapper col5">
    <div id="container">
        <div id="content">
            <style>
                #holder {
                    border: 10px dashed #ccc;
                    width: 300px;
                    min-height: 300px;
                    margin: 20px auto;
                }

                #holder.hover {
                    border: 10px dashed #0c0;
                }

                #holder img {
                    display: block;
                    margin: 10px auto;
                }

                #holder p {
                    margin: 10px;
                    font-size: 14px;
                }

                progress {
                    width: 100%;
                }

                progress:after {
                    content: '%';
                }

                .fail {
                    background: #c00;
                    padding: 2px;
                    color: #fff;
                }

                .hidden {
                    display: none !important;
                }
            </style>
            <!--та самая форма-->
            <form action="/add" enctype="multipart/form-data" method="POST">

                <label for="exampleInputFile">File input</label>
                <input type="file" id="exampleInputFile" name="file">
                <p class="help-block">Add first file.</p>
                <input type="text" name="picture_name">
                <input type="text" name="fileDescription">


                <input type="submit" class="btn btn-default">Add</input>
            </form>

            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/gallery/add" method="post">
                <div class="form-group"><h3>New Group</h3></div>
                <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name"></div>
                <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
            </form>

            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/set/group" method="post">
                <div class="form-group"><h3>Set all 1 group</h3></div>
                <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
            </form>
            <!--UPLOAD SECTION END-->

            <script src="js/prettify.packed.js"></script>
            <script>
                var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
                document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
            </script>
            <script>
                try {
                    var pageTracker = _gat._getTracker("UA-1656750-18");
                    pageTracker._trackPageview();
                } catch (err) {
                }</script>

        </div>
        <div id="column">
            <div class="subnav">
                <h2>Secondary Navigation</h2>
                <ul>
                    <li><a href="#">Navigation - Level 1</a></li>
                    <li><a href="#">Navigation - Level 1</a>
                        <ul>
                            <li><a href="#">Navigation - Level 2</a></li>
                            <li><a href="#">Navigation - Level 2</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Navigation - Level 1</a>
                        <ul>
                            <li><a href="#">Navigation - Level 2</a></li>
                            <li><a href="#">Navigation - Level 2</a>
                                <ul>
                                    <li><a href="#">Navigation - Level 3</a></li>
                                    <li><a href="#">Navigation - Level 3</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="#">Navigation - Level 1</a></li>
                </ul>
            </div>
            <div class="holder">
                <h2 class="title"><img src="../images/demo/60x60.gif" alt=""/>Nullamlacus dui ipsum conseque loborttis
                </h2>
                <p>Nullamlacus dui ipsum conseque loborttis non euisque morbi penas dapibulum orna. Urnaultrices quis
                    curabitur phasellentesque.</p>
                <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
            </div>
            <div id="featured">
                <ul>
                    <li>
                        <h2>Indonectetus facilis leonib</h2>
                        <p class="imgholder"><img src="../images/demo/240x90.gif" alt=""/></p>
                        <p>Nullamlacus dui ipsum conseque loborttis non euisque morbi penas dapibulum orna. Urnaultrices
                            quis curabitur phasellentesque congue magnis vestibulum quismodo nulla et feugiat.
                            Adipisciniapellentum leo ut consequam ris felit elit id nibh sociis malesuada.</p>
                        <p class="more"><a href="#">Continue Reading &raquo;</a></p>
                    </li>
                </ul>
            </div>
            <div class="holder">
                <h2>Lorem ipsum dolor</h2>
                <p>Nuncsed sed conseque a at quismodo tris mauristibus sed habiturpiscinia sed.</p>
                <ul>
                    <li><a href="#">Lorem ipsum dolor sit</a></li>
                    <li>Etiam vel sapien et</li>
                    <li><a href="#">Etiam vel sapien et</a></li>
                </ul>
                <p>Nuncsed sed conseque a at quismodo tris mauristibus sed habiturpiscinia sed. Condimentumsantincidunt
                    dui mattis magna intesque purus orci augue lor nibh.</p>
                <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
            </div>
        </div>
        <div class="clear"></div>
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
                        <input id="fullname" name="fullname" type="text" value=""/>
                    </label>
                    <label for="emailaddress" class="margin">Email:
                        <input id="emailaddress" name="emailaddress" type="text" value=""/>
                    </label>
                    <label for="message">Message:<br/>
                        <textarea id="message" name="message" cols="40" rows="4"></textarea>
                    </label>
                    <p>
                        <input id="submitform" name="submitform" type="submit" value="Submit"/>
                        &nbsp;
                        <input id="resetform" name="resetform" type="reset" value="Reset"/>
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
        <p>Template by <a target="_blank" href="http://www.os-templates.com/" title="Free Website Templates">OS
            Templates</a></p>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
