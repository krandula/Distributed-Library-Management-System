<%@page import="com.controller.common.CommonMethods"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.controller.common.ResAccessMethod"%>
<%@page import="com.controller.common.ResAccessUrl"%>
<%@page import="org.json.JSONObject"%>
<%
    if (request.getSession().getAttribute("user") != null) {
        JSONObject obj = new JSONObject(request.getSession().getAttribute("user").toString());
        int lhid = obj.getInt("id");
        int loginID = obj.getInt("loginID");
        int userID = obj.getInt("userID");
        String userRole = obj.getString("userRole");
        String username = obj.getString("username");

        String URLBA = ResAccessUrl.BOOK_SERVICE + "BookAuthors";
        String METHODBA = "GET";
        String resBA = new ResAccessMethod().callMethod(URLBA, METHODBA);

        String URLBPR = ResAccessUrl.BOOK_SERVICE + "BookPrinters";
        String METHODBPR = "GET";
        String resBPR = new ResAccessMethod().callMethod(URLBPR, METHODBPR);

        String URLBC = ResAccessUrl.BOOK_SERVICE + "BookCategories";
        String METHODBC = "GET";
        String resBC = new ResAccessMethod().callMethod(URLBC, METHODBC);

        String book = "none";
        String isbn = "none";
        int bookAuthor = 0;
        int bookCategory = 0;
        int bookPrinters = 0;
        if (request.getParameter("book") != null && !request.getParameter("book").isEmpty()) {
            book = request.getParameter("book");
        }
        if (request.getParameter("isbn") != null && !request.getParameter("isbn").isEmpty()) {
            isbn = request.getParameter("isbn");
        }
        if (request.getParameter("bookAuthor") != null && !request.getParameter("bookAuthor").isEmpty() && CommonMethods.isNumber(request.getParameter("bookAuthor"))) {
            bookAuthor = Integer.parseInt(request.getParameter("bookAuthor"));
        }
        if (request.getParameter("bookCategory") != null && !request.getParameter("bookCategory").isEmpty() && CommonMethods.isNumber(request.getParameter("bookCategory"))) {
            bookCategory = Integer.parseInt(request.getParameter("bookCategory"));
        }
        if (request.getParameter("bookPrinters") != null && !request.getParameter("bookPrinters").isEmpty() && CommonMethods.isNumber(request.getParameter("bookPrinters"))) {
            bookPrinters = Integer.parseInt(request.getParameter("bookPrinters"));
        }

        String URLBP = ResAccessUrl.BOOK_SERVICE + "BookProfiles/" + book + "/" + isbn + "/" + bookAuthor + "/" + bookCategory + "/" + bookPrinters;
        String METHODBP = "GET";
        String resBP = new ResAccessMethod().callMethod(URLBP, METHODBP);

        book = book.equals("none") ? "" : book;
        isbn = isbn.equals("none") ? "" : isbn;
%>
<!--Start - Common-->     
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- CSS-->
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Online Library</title>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
        <!--if lt IE 9
        script(src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
        script(src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
        -->
    </head>
    <body class="sidebar-mini fixed">
        <div class="wrapper">
            <!-- Navbar-->
            <header class="main-header hidden-print"><a class="logo" href="#">Online Library</a>
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button--><a class="sidebar-toggle" href="#" data-toggle="offcanvas"></a>
                    <!-- Navbar Right Menu-->
                    <div class="navbar-custom-menu">
                        <ul class="top-nav">

                            <!-- User Menu-->
                            <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user fa-lg"></i></a>
                                <ul class="dropdown-menu settings-menu">
                                    <li><a href="user_profile.jsp"><i class="fa fa-user fa-lg"></i> Profile</a></li>
                                    <li><a href="US_Logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Side-Nav-->
            <aside class="main-sidebar hidden-print">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image"><img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg" alt="User Image"></div>
                        <div class="pull-left info">
                            <p><%=username%></p>
                            <p class="designation"><%=userRole%></p>
                        </div>
                    </div>
                    <!-- Sidebar Menu-->
                    <ul class="sidebar-menu">
                        <li class="active"><a href="home.jsp"><i class="fa fa-book"></i><span>Library</span></a></li>
                        <%
                            if(!userRole.equals("Reader")){
                        %>
                        <li><a href="user_management.jsp"><i class="fa fa-user"></i><span>User Management </span></a></li>
                        <li><a href="book_management.jsp"><i class="fa fa-book"></i><span>Book Management</span></a></li>
                        <li><a href="type_management.jsp"><i class="fa fa-lock"></i><span>Type Management</span></a></li>
                        <%
                            }
                        %>
                    </ul>
                </section>
            </aside>

            <!--End - Common-->
            <div class="content-wrapper">
                <div class="page-title">
                    <div>
                        <h1><i class="fa fa-book"></i> Library</h1>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Library</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <h3 class="card-title">SEARCH</h3>
                            <div class="card-body3">
                                <form class="form-inline" action="home.jsp" method="POST">
                                    <div class="form-group">
                                        <input class="form-control" type="text" value="<%=book%>" name="book" placeholder="Book name">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="text" value="<%=isbn%>" name="isbn" placeholder="ISBN number">
                                    </div>
                                    <div class="form-group">
                                        <select class="form-control" name="bookAuthor" >
                                            <option value="0">--Book Author--</option>
                                            <%
                                                JSONArray arrRec = new JSONArray(resBA);
                                                for (int i = 0; i < arrRec.length(); i++) {
                                                    JSONObject objRec = arrRec.getJSONObject(i);
                                                    if (bookAuthor == objRec.getInt("id")) {
                                            %>
                                            <option value="<%=objRec.getInt("id")%>" selected="true"><%=objRec.getString("name")%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value="<%=objRec.getInt("id")%>"><%=objRec.getString("name")%></option>
                                            <%
                                                    }
                                                }
                                            %>

                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <select class="form-control" name="bookCategory" >
                                            <option value="0">--Book Category--</option>
                                            <%
                                                arrRec = new JSONArray(resBC);
                                                for (int i = 0; i < arrRec.length(); i++) {
                                                    JSONObject objRec = arrRec.getJSONObject(i);
                                                    if (bookCategory == objRec.getInt("id")) {
                                                    %>
                                                    <option value="<%=objRec.getInt("id")%>" selected="true"><%=objRec.getString("name")%></option>
                                                    <%
                                                    } else {
                                                    %>
                                                    <option value="<%=objRec.getInt("id")%>"><%=objRec.getString("name")%></option>
                                                    <%
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <select class="form-control" name="bookPrinters" value="<%=bookPrinters%>">
                                            <option value="0">--Book Printers--</option>
                                            <%
                                                arrRec = new JSONArray(resBPR);
                                                for (int i = 0; i < arrRec.length(); i++) {
                                                    JSONObject objRec = arrRec.getJSONObject(i);
                                                    if (bookPrinters == objRec.getInt("id")) {
                                                    %>
                                                    <option value="<%=objRec.getInt("id")%>" selected="true"><%=objRec.getString("name")%></option>
                                                    <%
                                                    } else {
                                                    %>
                                                    <option value="<%=objRec.getInt("id")%>"><%=objRec.getString("name")%></option>
                                                    <%
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>SEARCH</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="user">
                            <div class="timeline">
                                <%
                                    if (resBP.isEmpty()) {
                                %>
                                <h2>No records found</h2>
                                <%
                                } else {
                                    arrRec = new JSONArray(resBP);
                                    JSONObject objRec = arrRec.getJSONObject(0);
//                                    System.out.println(objRec);
                                    if (!objRec.getBoolean("result")) {
                                %>
                                <h2>No records found</h2>
                                <%
                                } else {
                                    for (int i = 1; i < arrRec.length(); i++) {
                                        objRec = arrRec.getJSONObject(i);
                                %>
                                <div class="post">
                                    <div class="post-media"><a href="#"><img src="http://pngwebicons.com/uploads/book/ico/book_icon6765.ico" width="48"></a>
                                        <div class="content">
                                            <h5><a href="#"><%=objRec.getString("name")%></a></h5>
                                            <p class="text-muted"><small><%=objRec.getString("isbn")%></small></p>
                                        </div>
                                    </div>
                                    <div class="post-content">
                                        <p><%=objRec.getString("description")%></p>
                                    </div>
                                    <ul class="post-utility">
                                        <li class="comments"><a href="single_book.jsp?book=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-eye"></i> View</a></li>
                                    </ul>
                                </div>
                                <%
                                            }
                                        }
                                    }
                                %>



                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Javascripts-->
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/pace.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("index.jsp");
    }
%>


