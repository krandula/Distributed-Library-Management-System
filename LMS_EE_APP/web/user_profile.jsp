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

        String URL = ResAccessUrl.USER_SERVICE + "UserProfile/" + userID;
        String METHOD = "GET";
        String res = new ResAccessMethod().callMethod(URL, METHOD);
        
        String fname="";
        String lname="";
        String mobile="";
        String address="";
        String userProfileType="";
        int country=0;
       
        if(!res.isEmpty()){
             JSONObject obj2 = new JSONObject(res);
             if(obj2.getBoolean("result")){
                 fname=obj2.getString("fname");
                 lname=obj2.getString("lname");
                 mobile=obj2.getString("mobile");
                 address=obj2.getString("address");
                 userProfileType=obj2.getString("userProfileType");
                 country=obj2.getInt("countryId");
             }
        }


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
                <div class="row user">
                    <div class="col-md-12">
                        <div class="profile">
                            <div class="info"><img class="user-img" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg">
                                <h4><%=username%></h4>
                                <p><%=userRole%></p>
                            </div>
                            <div class="cover-image"></div>
                        </div>
                    </div>
                    <%
                        if (result) {
                    %>
                    <div class="col-md-12"><br/>
                    <div class="alert alert-dismissible alert-warning">
                        <button class="close" type="button" data-dismiss="alert">×</button>
                        <h4>Alert!</h4>
                        <p><%=msg%></p>
                    </div>
                    </div>
                    <%
                        }
                    %>

                    <div class="col-md-12"><br/></div>

                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-title">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-3">
                                        <h3><i class="fa fa-lg fa-fw fa-user"></i>Update Account</h3>
                                    </div>
                                </div>
                            </div>

                            <form class="form-horizontal" action="US_UpdateUser" method="POST">
                                <div class="card-body">
                                    <hr/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">User Type</label>
                                        <div class="col-md-9">
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" value="1" name="pt" checked="true"><%=userProfileType %>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">First Name</label>
                                        <div class="col-md-8">
                                            <input class="form-control" value="<%=fname %>" name="fname" type="text" placeholder="First Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Last Name</label>
                                        <div class="col-md-8">
                                            <input class="form-control" value="<%=lname %>" name="lname" type="text" placeholder="Last Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Mobile</label>
                                        <div class="col-md-8">
                                            <input class="form-control" value="<%=mobile %>" name="mobile" type="text" placeholder="Mobile">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Address</label>
                                        <div class="col-md-8">
                                            <textarea class="form-control" name="address" rows="4" placeholder="Postal Address"><%=address %></textarea>
                                        </div>
                                    </div>
                                        
                                            <input value="<%=country %>" name="country" type="hidden"/>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-md-8 col-md-offset-3">
                                            <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>UPDATE</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-default icon-btn" href="user_profile.jsp"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-title">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-3">
                                        <h3><i class="fa fa-lg fa-fw fa-user"></i>Change Password</h3>
                                    </div>
                                </div>
                            </div>

                            <form class="form-horizontal" action="US_ChangePassword" method="POST">
                                <div class="card-body">
                                    <hr/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Current Password</label>
                                        <div class="col-md-8">
                                            <input class="form-control" name="cpw" type="password" placeholder="Current Password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">New Password</label>
                                        <div class="col-md-8">
                                            <input class="form-control" name="npw" type="password" placeholder="New Password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Re Password</label>
                                        <div class="col-md-8">
                                            <input class="form-control" name="rpw" type="password" placeholder="Re Password">
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-md-8 col-md-offset-3">
                                            <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>UPDATE</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-default icon-btn" href="user_profile.jsp"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
<%    }
%>
