
package com.controller.service;

import com.controller.action.CommonAction;
import com.controller.action.UserAction;
import com.model.entity.Country;
import com.model.entity.UserProfile;
import com.model.entity.UserProfileType;
import com.model.entity.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Path("userService")
public class UserService {

    private UserAction ua;

    @Context
    private UriInfo context;

    
    public UserService() {
        ua = new UserAction();
    }

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

 
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void putJson(String content) {
    }

    //saveNewUser
    @POST 
    @Path("UserProfile/{fname}/{lname}/{mobile}/{address}/{profileType}/{country}/{lhid}/{uname}/{pw}/{userRole}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String saveNewUser(
            @PathParam("fname") String fname,
            @PathParam("lname") String lname,
            @PathParam("mobile") String mobile,
            @PathParam("address") String address,
            @PathParam("profileType") int profileType,
            @PathParam("country") int country,
            @PathParam("lhid") int lhid,
            @PathParam("uname") String uname,
            @PathParam("pw") String pw,
            @PathParam("userRole") int userRole) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.saveNewUser(fname, lname, mobile, address, profileType, country, lhid, uname, pw, userRole);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

    //updateUser
    @PUT
    @Path("UserProfile/{userID}/{fname}/{lname}/{mobile}/{address}/{profileType}/{country}/{lhid}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String updateUser(
            @PathParam("userID") int userID,
            @PathParam("fname") String fname,
            @PathParam("lname") String lname,
            @PathParam("mobile") String mobile,
            @PathParam("address") String address,
            @PathParam("profileType") int profileType,
            @PathParam("country") int country,
            @PathParam("lhid") int lhid) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.updateUser(userID, fname, lname, mobile, address, profileType, country, lhid);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

    //changePassword
    @PUT
    @Path("ChangePassword/{loginID}/{currentPassword}/{newPassword}/{rePassword}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String changePassword(
            @PathParam("loginID") int loginID,
            @PathParam("currentPassword") String currentPassword,
            @PathParam("newPassword") String newPassword,
            @PathParam("rePassword") String rePassword) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.changePassword(loginID, currentPassword, newPassword, rePassword);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

    //deleteUser
    @DELETE
    @Path("UserProfile/{userID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String deleteUser(
            @PathParam("userID") int userID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.deleteUser(userID);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }
    
    //loadUserList
    @GET
    @Path("UserProfiles")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadUserList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<UserProfile> list = new ArrayList<>(ua.loadUserList());
            String response = list.isEmpty() ? "No Data" : "Done";
            objSend = new JSONObject();
            objSend.put("result", !list.isEmpty());
            objSend.put("id", 0);
            objSend.put("msg", response);

            arrSend.put(objSend);

            for (UserProfile up : list) {
                objSend = new JSONObject();
                objSend.put("id", up.getId());
                objSend.put("genID", up.getGeneratedId());
                objSend.put("name", up.getFirstName() + " " + up.getLastName());
                objSend.put("mobile", up.getMobile());
                objSend.put("address", up.getAddress());
                objSend.put("country", up.getCountry().getName());
                objSend.put("isApproved", up.getIsApproved());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            try {
                objSend = new JSONObject();

                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

                arrSend.put(objSend);
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
        msg = arrSend.toString();
        return msg;

    }

    //activeAndDeactiveUser
    @PUT
    @Path("UserProfile/{userID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String activeAndDeactiveUser(
            @PathParam("userID") int userID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.activeAndDeactiveUser(userID);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

    //userLogin
    @GET
    @Path("UserLogin/{username}/{password}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String userLogin(
            @PathParam("username") String username,
            @PathParam("password") String password) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.userLogin(username, password);
            if (CommonAction.isNumber(response)) {
                objSend.put("result", true);
                objSend.put("id", response);
                objSend.put("userRole", ua.getUserRole(Integer.parseInt(response)));
                objSend.put("userID", ua.getUserID(Integer.parseInt(response)));
                objSend.put("username", ua.getUsername(Integer.parseInt(response)));
                objSend.put("loginID", ua.getLoginID(Integer.parseInt(response)));
                objSend.put("msg", "Done");
            } else {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", response);
            }
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

    //checkForeign
    @GET
    @Path("UserProfileType/{userType}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String checkForeign(
            @PathParam("userType") int userType) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.checkForeign(userType);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

    //getUserDetails
    @GET
    @Path("UserProfile/{userID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String getUserDetails(
            @PathParam("userID") int userID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            UserProfile up = ua.getUserDetails(userID);

            if (up != null) {
                objSend.put("result", true);
                objSend.put("id", up.getId());
                objSend.put("genID", up.getGeneratedId());
                objSend.put("fname", up.getFirstName());
                objSend.put("lname", up.getLastName());
                objSend.put("mobile", up.getMobile());
                objSend.put("address", up.getAddress());
                objSend.put("userProfileType", up.getUserProfileType().getName());
                objSend.put("userProfileTypeId", up.getUserProfileType().getId());
                objSend.put("countryId", up.getCountry().getId());
                objSend.put("country", up.getCountry().getName());
                objSend.put("userRoleId", ua.getUserRoleID(userID));
                objSend.put("msg", "Done");
            } else {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "No user found");
            }

        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }
    
    
    //loadUserRoleList
    @GET
    @Path("UserRoles")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadUserRoleList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<UserRole> list = new ArrayList<>(ua.loadUserRoleList());

            for (UserRole a : list) {
                objSend = new JSONObject();
                objSend.put("id", a.getId());
                objSend.put("name", a.getName());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = arrSend.toString();
        return msg;

    }
    
    //loadUserProfileTypeList
    @GET
    @Path("UserProfileTypes")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadUserProfileTypeList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<UserProfileType> list = new ArrayList<>(ua.loadUserProfileTypeList());

            for (UserProfileType a : list) {
                objSend = new JSONObject();
                objSend.put("id", a.getId());
                objSend.put("name", a.getName());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = arrSend.toString();
        return msg;

    }
    
    //loadCountryList
    @GET
    @Path("Countries")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadCountryList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<Country> list = new ArrayList<>(ua.loadCountryList());

            for (Country a : list) {
                objSend = new JSONObject();
                objSend.put("id", a.getId());
                objSend.put("name", a.getName());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = arrSend.toString();
        return msg;

    }
    
    //userLogOut
    @PUT
    @Path("UserLogOut/{lhid}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String userLogOut(
            @PathParam("lhid") int lhid) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response = ua.userLogOut(lhid);

            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");

            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        msg = objSend.toString();
        return msg;
    }

}
