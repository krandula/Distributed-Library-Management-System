
package com.controller.action;

import com.model.db.HibernateUtil;
import com.model.entity.Country;
import com.model.entity.UserLogin;
import com.model.entity.UserLoginHistory;
import com.model.entity.UserProfile;
import com.model.entity.UserProfileType;
import com.model.entity.UserRole;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class UserAction {

    //saveNewUser
    public String saveNewUser(String fname, String lname, String mobile, String address, int profileType, int country, int lhid, String uname, String pw, int userRole) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            fname=fname.replaceAll("\\+", " ");
            lname=lname.replaceAll("\\+", " ");
            mobile=mobile.replaceAll("\\+", " ");
            address=address.replaceAll("\\+", " ");
            
            Query qr = s.createQuery("FROM UserLogin a WHERE a.username='" + uname + "'");
            if (!qr.list().isEmpty()) {
                msg = "Username already exists";
            } else {
                UserLoginHistory lh = null;
                if (lhid != 0) {
                    lh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
                }
                UserProfileType upt = (UserProfileType) s.load(UserProfileType.class, profileType);
                Country c = (Country) s.load(Country.class, country);
                UserRole ur = (UserRole) s.load(UserRole.class, userRole);
                Date date = new Date();
                Query q = s.createQuery("FROM UserProfile a");
                int a = q.list().isEmpty() ? 1 : q.list().size() + 1;
                String genID = "USR00" + a;

                UserProfile up = new UserProfile();
                up.setGeneratedId(genID);
                up.setFirstName(fname);
                up.setLastName(lname);
                up.setMobile(mobile);
                up.setAddress(address);
                up.setCreateDateTime(date);
                up.setIsActive(Boolean.TRUE);
                up.setIsApproved(Boolean.FALSE);
                up.setUserLoginHistory(lh);
                up.setUserProfileType(upt);
                up.setCountry(c);

                s.save(up);

                UserLogin ul = new UserLogin();
                ul.setUsername(uname);
                ul.setPassword(pw);
                ul.setCreateDateTime(date);
                ul.setIsActive(Boolean.TRUE);
                ul.setUserRole(ur);
                ul.setUserProfile(up);

                s.save(ul);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }

        return msg;
    }

    //updateUser
    public String updateUser(int userID, String fname, String lname, String mobile, String address, int profileType, int country, int lhid) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            
            fname=fname.replaceAll("\\+", " ");
            lname=lname.replaceAll("\\+", " ");
            mobile=mobile.replaceAll("\\+", " ");
            address=address.replaceAll("\\+", " ");
            
            UserLoginHistory lh = null;
            if (lhid != 0) {
                lh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
            }
            UserProfileType upt = (UserProfileType) s.load(UserProfileType.class, profileType);
            Country c = (Country) s.load(Country.class, country);
            Date date = new Date();


            UserProfile up = (UserProfile) s.load(UserProfile.class, userID);
            up.setFirstName(fname);
            up.setLastName(lname);
            up.setMobile(mobile);
            up.setAddress(address);
            up.setUpdateDateTime(date);
            up.setUserLoginHistory(lh);
            up.setUserProfileType(upt);
            up.setCountry(c);

            s.update(up);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //changePassword
    public String changePassword(int loginID, String currentPassword, String newPassword, String rePassword) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            UserLogin ul = (UserLogin) s.load(UserLogin.class, loginID);
            if (!ul.getPassword().equals(currentPassword)) {
                msg = "Incorrect current password";
            } else if (!newPassword.equals(rePassword)) {
                msg = "Repassword not matched";
            } else {
                Date date = new Date();
                ul.setPassword(newPassword);
                ul.setUpdateDateTime(date);

                s.update(ul);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //deleteUser
    public String deleteUser(int userID) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Query qr1 = s.createQuery("DELETE UserLoginHistory a WHERE a.userLogin.id=(SELECT a.id FROM UserLogin a WHERE a.userProfile.id=" + userID + ")");
            qr1.executeUpdate();
            Query qr2 = s.createQuery("DELETE UserLogin a WHERE a.userProfile.id=" + userID);
            qr2.executeUpdate();
            UserProfile up = (UserProfile) s.load(UserProfile.class, userID);
            s.delete(up);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //loadUserList
    public List<UserProfile> loadUserList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<UserProfile> list;
            Query q = s.createQuery("FROM UserProfile a WHERE a.isActive=1");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //activeAndDeactiveUser
    public String activeAndDeactiveUser(int userID) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            Date date = new Date();
            UserProfile up = (UserProfile) s.load(UserProfile.class, userID);
            up.setIsApproved(Boolean.TRUE);
            up.setUpdateDateTime(date);
            s.update(up);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //userLogin
    public String userLogin(String username, String password) {
        String msg;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            Query qr1 = s.createQuery("FROM UserLogin a WHERE a.isActive=1 AND a.username='" + username + "' AND a.password='" + password + "'");
            if (qr1.list().isEmpty()) {
                msg = "Invalid username or password";
            } else {
                UserLogin ul = (UserLogin) qr1.uniqueResult();
                if (!ul.getUserProfile().getIsActive() || !ul.getUserProfile().getIsApproved()) {
                    msg = "Please contact librian to active your account";
                } else {
                    Date date = new Date();
                    UserLoginHistory ulh = new UserLoginHistory();
                    ulh.setUserLogin(ul);
                    ulh.setLoginDateTime(date);

                    s.save(ulh);

                    msg = ulh.getId() + "";
                }
            }

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //getUserRole
    public String getUserRole(int lhid) {
        String msg;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            
            UserLoginHistory ulh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
            msg = ulh.getUserLogin().getUserRole().getName();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "None";
        }
        return msg;
    }
    
    //getUserRole
    public int getUserRoleID(int userID) {
        int msg=0;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            
            Query qr1 = s.createQuery("FROM UserLogin a WHERE a.isActive=1 AND a.userProfile.id="+userID);
            UserLogin ul = (UserLogin) qr1.uniqueResult();
            msg = ul.getUserRole().getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    //getUserID
    public int getUserID(int lhid) {
        int msg = 0;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            UserLoginHistory ulh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
            msg = ulh.getUserLogin().getUserProfile().getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    //getUsername
    public String getUsername(int lhid) {
        String msg = "Anonymous";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            UserLoginHistory ulh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
            msg = ulh.getUserLogin().getUserProfile().getFirstName() + " " + ulh.getUserLogin().getUserProfile().getLastName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    //getLoginID
    public int getLoginID(int lhid) {
        int msg = 0;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            UserLoginHistory ulh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
            msg = ulh.getUserLogin().getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    //checkForeign
    public String checkForeign(int userType) {
        String msg;
        try {
            msg = userType == 1 ? "No" : "Yes";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "No";
        }
        return msg;
    }

    //getUserDetails
    public UserProfile getUserDetails(int userID) {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            UserProfile up = (UserProfile) s.load(UserProfile.class, userID);

            return up;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //loadUserRoleList
    public List<UserRole> loadUserRoleList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<UserRole> list;
            Query q = s.createQuery("FROM UserRole a");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //loadUserProfileTypeList
    public List<UserProfileType> loadUserProfileTypeList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<UserProfileType> list;
            Query q = s.createQuery("FROM UserProfileType a");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //loadCountryList
    public List<Country> loadCountryList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<Country> list;
            Query q = s.createQuery("FROM Country a");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //userLogOut
    public String userLogOut(int lhid) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            Date date = new Date();
            UserLoginHistory lh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
            lh.setLogoutDateTime(date);

            s.save(lh);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }
}
