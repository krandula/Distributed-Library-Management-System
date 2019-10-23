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

        int book = 0;
        if (request.getParameter("book") != null && !request.getParameter("book").isEmpty()) {
            book = Integer.parseInt(request.getParameter("book"));
        }
        
        String action="BS_SaveBook";
        if(book!=0){
            action="BS_UpdateBook";
        }

        String URL = ResAccessUrl.BOOK_SERVICE + "BookProfile/" + book;
        String METHOD = "GET";
        String res = new ResAccessMethod().callMethod(URL, METHOD);

        String genID = "";
        String name = "";
        String isbn = "";
        String description = "";
        int printedYear = 0;
        int bookCount = 0;
        String author = "";
        String category = "";
        String printers = "";
        int bookAuthor = 0;
        int bookCategory = 0;
        int bookPrinters = 0;

        if (!res.isEmpty()) {
            JSONObject objRec = new JSONObject(res);
            if (objRec.getBoolean("result")) {
                genID = objRec.getString("genID");
                name = objRec.getString("name");
                isbn = objRec.getString("isbn");
                description = objRec.getString("description");
                printedYear = objRec.getInt("printedYear");
                bookCount = objRec.getInt("bookCount");
                author = objRec.getString("author");
                category = objRec.getString("category");
                printers = objRec.getString("printers");
                bookAuthor = objRec.getInt("authorId");
                bookCategory = objRec.getInt("categoryId");
                bookPrinters = objRec.getInt("printersId");
            }
        }

        String URLBP = ResAccessUrl.BOOK_SERVICE + "BookProfiles";
        String METHODBP = "GET";
        String resBP = new ResAccessMethod().callMethod(URLBP, METHODBP);

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
                        <h1><i class="fa fa-book"></i> Book Management</h1>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Book Management</a></li>
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
                    <div class="col-md-12">
                        <div class="card">
                            <h3 class="card-title">Create/Update Book</h3>
                            <div class="card-body">
                                <form  action="<%=action %>" method="POST">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=name%>" name="name" placeholder="Book name">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=isbn%>" name="isbn" placeholder="ISBN number">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=(printedYear==0?"":printedYear+"")%>" name="printedYear" placeholder="Printed Year">
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=(bookCount==0?"":bookCount)%>" name="bookCount" placeholder="Book Count">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
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
                                        </div>
                                        <div class="col-md-4">
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
                                        </div>
                                        <div class="col-md-4">
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
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <textarea class="form-control" name="description" rows="4" placeholder="Description"><%=description %></textarea>
                                            </div>
                                        </div>
                                    </div>
                                                <input type="hidden" value="<%=book %>" name="book"/>
                                    <div class="row">
                                        <div class="col-md-9"></div>
                                        <div class="col-md-3" style="text-align: right">
                                            <div class="form-group">
                                                <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>CREATE / UPDATE</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card">
                            <h3 class="card-title">Book Store</h3>
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
                            %>
                            <table class="table table-hover table-bordered" >
                                <thead>
                                    <tr>
                                        <th>NAME</th>
                                        <th>ISBN</th>
                                        <th>YEAR</th>
                                        <th>AUTHOR</th>
                                        <th>CATEGORY</th>
                                        <th>PRINTER</th>
                                        <th><i class="fa fa-edit"></i></th>
                                        <th><i class="fa fa-remove"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (int i = 1; i < arrRec.length(); i++) {
                                            objRec = arrRec.getJSONObject(i);
                                    %>
                                    <tr>
                                        <td><%=objRec.getString("name")%></td>
                                        <td><%=objRec.getString("isbn")%></td>
                                        <td><%=objRec.getInt("printedYear")%></td>
                                        <td><%=objRec.getString("author")%></td>
                                        <td><%=objRec.getString("category")%></td>
                                        <td><%=objRec.getString("printers")%></td>
                                        <td><a href="book_management.jsp?book=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-edit"></i></a></td>
                                        <td><a href="BS_DeleteBook?book=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-remove"></i></a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                            <%}
                                }%>
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


