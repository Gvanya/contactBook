package com.softindustry.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softindustry.dao.UserDAO;
import com.softindustry.to.User;
import com.softindustry.validation.ServerValidation;


public class UserServlet extends HttpServlet {

    private UserDAO dao;
    private static final long serialVersionUID = 1L;
    public static final String lIST_USER = "/WEB-INF/views/listUser.jsp";
    public static final String INSERT_OR_EDIT = "/WEB-INF/views/user.jsp";
    public static final String ERROR_MASSAGE = "/WEB-INF/views/error.jsp";
    public static final String FIND_USER = "/WEB-INF/views/findUser.jsp";

    public UserServlet() {
        dao = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter( "action" );

        if( action.equalsIgnoreCase( "delete" ) ) {
            forward = lIST_USER;
            int userId = Integer.parseInt( request.getParameter("userId") );
            dao.deleteUser(userId);
            request.setAttribute("users", dao.getAllUsers() );
        }
        else if( action.equalsIgnoreCase( "update" ) ) {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt( request.getParameter("userId") );
            User user = dao.getUserById(userId);
            request.setAttribute("user", user);
        }
        else if( action.equalsIgnoreCase( "insert" ) ) {
            forward = INSERT_OR_EDIT;
        }
        else if( action.equalsIgnoreCase( "find" ) ) {
            forward = FIND_USER;
            String filter = request.getParameter("filter");
            String search = request.getParameter("search");
            String gender = request.getParameter("gender");
            request.setAttribute("users", dao.findUser(filter, search, gender));
        }
        else {
            forward = lIST_USER;
            request.setAttribute("users", dao.getAllUsers() );
        }
        RequestDispatcher view = request.getRequestDispatcher( forward );
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        ServerValidation v = new ServerValidation();

        boolean lastName = v.lastNameValidate(request.getParameter("lastName"));
        boolean firstName = v.firstNameValidate(request.getParameter("firstName"));
        boolean age = v.ageValidate(request.getParameter("age"));
        boolean gender = v.genderValidate(request.getParameter("gender"));
        boolean phoneNumber = v.phoneNumberValidate(request.getParameter("phoneNumber"));

        if(!lastName ||  !firstName || !age || !gender || !phoneNumber)
        {
            RequestDispatcher view = request.getRequestDispatcher(ERROR_MASSAGE);
            request.setAttribute("validationMessage", v.validationMessage(lastName,firstName,age,gender,phoneNumber));
            view.forward(request, response);
        }
        else {
            String userId = request.getParameter("userId");

            user.setLastName( request.getParameter( "lastName" ) );
            user.setFirstName( request.getParameter( "firstName" ) );
            user.setAge( Integer.parseInt( request.getParameter( "age" ) ) );
            user.setGender( request.getParameter( "gender" ) );
            user.setPhoneNumber( request.getParameter( "phoneNumber" ) );

            if (userId == null || userId.isEmpty())
                dao.addUser(user);
            else {
                user.setUserId(Integer.parseInt(userId));
                dao.updateUser(user);
            }
            RequestDispatcher view = request.getRequestDispatcher(lIST_USER);
            request.setAttribute("users", dao.getAllUsers());
            view.forward(request, response);
        }
    }
}

