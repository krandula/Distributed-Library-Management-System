<%
    boolean result=request.getParameter("error")!=null;
    String msg="";
    if(result){
        msg=request.getParameter("error");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
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
    <body>
        <section class="material-half-bg">
            <div class="cover"></div>
        </section>
        <section class="login-content">
            <div class="logo">
                <h1>Online Library</h1>
            </div>
            <%
                if (result) {
            %>
            <div class="alert alert-dismissible alert-warning">
                <button class="close" type="button" data-dismiss="alert">×</button>
                <h4>Alert!</h4>
                <p><%=msg%></p>
            </div>
            <%
                }
            %>
            <div class="card">
                <div class="card-title">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-3">
                            <h3><i class="fa fa-lg fa-fw fa-user"></i>Create New Account</h3>
                        </div>
                    </div>
                </div>

                <form class="form-horizontal" action="US_SaveUser" method="POST">
                    <div class="card-body">
                        <hr/>
                        <div class="form-group">
                            <label class="control-label col-md-3">User Type</label>
                            <div class="col-md-9">
                                <div class="radio-inline">
                                    <label>
                                        <input type="radio" value="1" name="pt" checked="true">Local
                                    </label>
                                </div>
                                <div class="radio-inline">
                                    <label>
                                        <input type="radio" value="2" name="pt" disabled="true" title="Are you foreigner, Please contact librarian">Foreign
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">First Name</label>
                            <div class="col-md-8">
                                <input class="form-control" name="fname" type="text" placeholder="First Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Last Name</label>
                            <div class="col-md-8">
                                <input class="form-control" name="lname" type="text" placeholder="Last Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Mobile</label>
                            <div class="col-md-8">
                                <input class="form-control" name="mobile" type="text" placeholder="Mobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Address</label>
                            <div class="col-md-8">
                                <textarea class="form-control" name="address" rows="4" placeholder="Postal Address"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Username</label>
                            <div class="col-md-8">
                                <input class="form-control" name="uname" type="text" placeholder="Username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Password</label>
                            <div class="col-md-8">
                                <input class="form-control" name="pw" type="password" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3"></div>
                            <div class="col-md-8" style="text-align: right">
                                <div class="utility">
                                    <p class="semibold-text mb-0"><a href="index.jsp">Have a User Account ?</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-3">
                                <button class="btn btn-primary icon-btn" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Register</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-default icon-btn" href="register.jsp"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </body>
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/pace.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
    
    <script type="text/javascript">
        
    </script>
</html>