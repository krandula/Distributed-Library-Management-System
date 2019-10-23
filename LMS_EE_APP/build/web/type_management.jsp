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

        boolean result = request.getParameter("error") != null;
        String msg = "";
        if (result) {
            msg = request.getParameter("error");
        }

        String URLBA = ResAccessUrl.BOOK_SERVICE + "BookAuthors";
        String METHODBA = "GET";
        String resBA = new ResAccessMethod().callMethod(URLBA, METHODBA);

        String URLBPR = ResAccessUrl.BOOK_SERVICE + "BookPrinters";
        String METHODBPR = "GET";
        String resBPR = new ResAccessMethod().callMethod(URLBPR, METHODBPR);

        String URLBC = ResAccessUrl.BOOK_SERVICE + "BookCategories";
        String METHODBC = "GET";
        String resBC = new ResAccessMethod().callMethod(URLBC, METHODBC);
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
                        <h1><i class="fa fa-lock"></i> Type Management</h1>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Type Management</a></li>
                        </ul>
                    </div>
                </div>

                <div class="row">
                    <%
                        if (result) {
                    %>
                    <div class="col-md-12">
                        <div class="alert alert-dismissible alert-warning">
                            <button class="close" type="button" data-dismiss="alert">×</button>
                            <h4>Alert!</h4>
                            <p><%=msg%></p>
                        </div>
                    </div>
                    <%
                        }
                    %>

                    <div class="col-md-6">
                        <div class="card">
                            <h3 class="card-title">Book Authors</h3>
                            <div class="card-body">
                                <form  action="BS_SaveBookAuthor" method="POST">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="name" placeholder="Book author">
                                            </div>
                                        </div>
                                        <div class="col-md-4" style="text-align: right">
                                            <div class="form-group">
                                                <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>CREATE</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col-md-12">
                                        <table class="table table-hover table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th width="80%">NAME</th>
                                                    <th  width="20%" style="text-align: center"><i class="fa fa-remove"></i></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    JSONArray arrRec = new JSONArray(resBA);
                                                    for (int i = 0; i < arrRec.length(); i++) {
                                                        JSONObject objRec = arrRec.getJSONObject(i);
                                                %>
                                                <tr>
                                                    <td><%=objRec.getString("name")%></td>
                                                    <td style="text-align: center"><a href="BS_DeleteBookAuthor?id=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-remove"></i></a></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                   <div class="col-md-6">
                        <div class="card">
                            <h3 class="card-title">Book Category</h3>
                            <div class="card-body">
                                <form  action="BS_SaveBookCategory" method="POST">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="name" placeholder="Book category">
                                            </div>
                                        </div>
                                        <div class="col-md-4" style="text-align: right">
                                            <div class="form-group">
                                                <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>CREATE</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col-md-12">
                                        <table class="table table-hover table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th width="80%">NAME</th>
                                                    <th  width="20%" style="text-align: center"><i class="fa fa-remove"></i></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    arrRec = new JSONArray(resBC);
                                                    for (int i = 0; i < arrRec.length(); i++) {
                                                        JSONObject objRec = arrRec.getJSONObject(i);
                                                %>
                                                <tr>
                                                    <td><%=objRec.getString("name")%></td>
                                                    <td style="text-align: center"><a href="BS_DeleteBookCategory?id=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-remove"></i></a></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                                            
                    <div class="col-md-6">
                        <div class="card">
                            <h3 class="card-title">Book Printers</h3>
                            <div class="card-body">
                                <form  action="BS_SaveBookPrinters" method="POST">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="name" placeholder="Book printers">
                                            </div>
                                        </div>
                                        <div class="col-md-4" style="text-align: right">
                                            <div class="form-group">
                                                <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>CREATE</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col-md-12">
                                        <table class="table table-hover table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th width="80%">NAME</th>
                                                    <th  width="20%" style="text-align: center"><i class="fa fa-remove"></i></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    arrRec = new JSONArray(resBPR);
                                                    for (int i = 0; i < arrRec.length(); i++) {
                                                        JSONObject objRec = arrRec.getJSONObject(i);
                                                %>
                                                <tr>
                                                    <td><%=objRec.getString("name")%></td>
                                                    <td style="text-align: center"><a href="BS_DeleteBookPrinters?id=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-remove"></i></a></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
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


