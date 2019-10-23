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

        int user = 0;
        if (request.getParameter("user") != null && !request.getParameter("user").isEmpty()) {
            user = Integer.parseInt(request.getParameter("user"));
        }

        boolean isSave=true;
        String action = "US_AdminSaveUser";
        if (user != 0) {
            action = "US_AdminUpdateUser";
            isSave=false;
        }

        String URL = ResAccessUrl.USER_SERVICE + "UserProfile/" + user;
        String METHOD = "GET";
        String res = new ResAccessMethod().callMethod(URL, METHOD);

        String genID = "";
        String fname = "";
        String lname = "";
        String mobile = "";
        String address = "";
        String userProfileType = "";
        int userProfileTypeId = 0;
        int countryId = 0;
        int userRoleId = 0;

        if (!res.isEmpty()) {
            JSONObject objRec = new JSONObject(res);
            if (objRec.getBoolean("result")) {
                genID = objRec.getString("genID");
                fname = objRec.getString("fname");
                lname = objRec.getString("lname");
                mobile = objRec.getString("mobile");
                address = objRec.getString("address");
                userProfileType = objRec.getString("userProfileType");
                userProfileTypeId = objRec.getInt("userProfileTypeId");
                countryId = objRec.getInt("countryId");
                userRoleId = objRec.getInt("userRoleId");
            }
        }

        String URLUP = ResAccessUrl.USER_SERVICE + "UserProfiles";
        String METHODUP = "GET";
        String resUP = new ResAccessMethod().callMethod(URLUP, METHODUP);

        String URLUR = ResAccessUrl.USER_SERVICE + "UserRoles";
        String METHODUR = "GET";
        String resUR = new ResAccessMethod().callMethod(URLUR, METHODUR);

        String URLUPT = ResAccessUrl.USER_SERVICE + "UserProfileTypes";
        String METHODUPT = "GET";
        String resUPT = new ResAccessMethod().callMethod(URLUPT, METHODUPT);

        String URLC = ResAccessUrl.USER_SERVICE + "Countries";
        String METHODC = "GET";
        String resC = new ResAccessMethod().callMethod(URLC, METHODC);
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
                        <h1><i class="fa fa-book"></i> User Management</h1>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">User Management</a></li>
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
                            <h3 class="card-title">Create/Update User</h3>
                            <div class="card-body">
                                <form  action="<%=action%>" method="POST">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=fname%>" name="fname" placeholder="First name">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=lname%>" name="lname" placeholder="Last name">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input class="form-control" type="text" value="<%=mobile%>" name="mobile" placeholder="Mobile">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <textarea class="form-control" name="address" rows="4" placeholder="Address"><%=address%></textarea>
                                            </div>
                                        </div>
                                    </div> 
                                    <%
                                    if(isSave){
                                    %>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="uname" placeholder="Username">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input class="form-control" type="password" name="pw" placeholder="Password">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <select class="form-control" name="userRole" >
                                                    <option value="0">--User Role--</option>
                                                    <%
                                                        JSONArray arrRec = new JSONArray(resUR);
                                                        for (int i = 0; i < arrRec.length(); i++) {
                                                            JSONObject objRec = arrRec.getJSONObject(i);
                                                            if (userRoleId == objRec.getInt("id")) {
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
                                    <%
                                    }
                                    %>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <select class="form-control" name="userProfileType" >
                                                    <option value="0">--User Profile Type--</option>
                                                    <%
                                                        JSONArray arrRec = new JSONArray(resUPT);
                                                        for (int i = 0; i < arrRec.length(); i++) {
                                                            JSONObject objRec = arrRec.getJSONObject(i);
                                                            if (userProfileTypeId == objRec.getInt("id")) {
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
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <select class="form-control" name="country" >
                                                    <option value="0">--Country--</option>
                                                    <%
                                                        arrRec = new JSONArray(resC);
                                                        for (int i = 0; i < arrRec.length(); i++) {
                                                            JSONObject objRec = arrRec.getJSONObject(i);
                                                            if (countryId == objRec.getInt("id")) {
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

                                    <input type="hidden" value="<%=user%>" name="user"/>
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
                            <h3 class="card-title">User Data</h3>
                            <%
                                if (resUP.isEmpty()) {
                            %>
                            <h2>No records found</h2>
                            <%
                            } else {
                                arrRec = new JSONArray(resUP);
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
                                        <th>MOBILE</th>
                                        <th>ADDRESS</th>
                                        <th><i class="fa fa-edit"></i></th>
                                        <th><i class="fa fa-user"></i></th>
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
                                        <td><%=objRec.getString("mobile")%></td>
                                        <td><%=objRec.getString("address")%></td>
                                        <td><a href="user_management.jsp?user=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-edit"></i></a></td>
                                        <%
                                        if(objRec.getBoolean("isApproved")){
                                        %>
                                        <td><i class="fa fa-fw fa-lg fa-user"></i> Active</td>
                                        <%
                                        }else{
                                        %>
                                        <td><a href="US_ActiveDeactiveUser?user=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-user"></i> Inactive</a></td>
                                        <%
                                        }
                                        %>
                                        
                                        <td><a href="US_DeleteUser?user=<%=objRec.getInt("id")%>"><i class="fa fa-fw fa-lg fa-remove"></i></a></td>
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


