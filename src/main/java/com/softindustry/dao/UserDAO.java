package com.softindustry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.softindustry.to.User;
import com.softindustry.db.DBUtil;

public class UserDAO {

    private static final String ADD_USER_QUERY = "INSERT INTO contactbook.user (lastName, firstName, age, gender, phonenumber) values (?,?,?,?,?)";
    private static final String DELETE_USER_QUERY = "DELETE from contactbook.user WHERE userId=?";
    private static final String UPDATE_USER_QUERY = "UPDATE contactbook.user set lastName=?, firstName=?, age=?, gender=?, phonenumber=?  WHERE userId=?";
    private static final String SELECT_USERS_QUERY = "SELECT * FROM contactbook.user";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM contactbook.user WHERE userId=?";

    private Connection conn;

    public UserDAO() {
        conn = DBUtil.getConnection();
    }

    public void addUser( User user ) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement( ADD_USER_QUERY );

            preparedStatement.setString( 1, user.getLastName());
            preparedStatement.setString( 2, user.getFirstName());
            preparedStatement.setInt( 3, user.getAge());
            preparedStatement.setString( 4, user.getGender());
            preparedStatement.setString( 5, user.getPhoneNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser( int userId ) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser( User user ) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement( UPDATE_USER_QUERY );
            preparedStatement.setString( 1, user.getLastName());
            preparedStatement.setString( 2, user.getFirstName());
            preparedStatement.setInt( 3, user.getAge());
            preparedStatement.setString( 4, user.getGender());
            preparedStatement.setString( 5, user.getPhoneNumber());
            preparedStatement.setInt( 6, user.getUserId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( SELECT_USERS_QUERY );
            users = getUsersList(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<User> getUsersList(ResultSet resultSet)throws SQLException {
        List<User> users = new ArrayList<>();
        while(resultSet.next()) {
            User user = new User();
            user.setUserId( resultSet.getInt( "userId" ) );
            user.setLastName( resultSet.getString( "lastName" ) );
            user.setFirstName( resultSet.getString( "firstName" ) );
            user.setAge( resultSet.getInt( "age" ) );
            user.setGender( resultSet.getString( "gender" ) );
            user.setPhoneNumber( resultSet.getString( "phoneNumber" ) );
            users.add(user);
        }
        return users;
    }

    public List<User> findUser(String filter, String search, String gender) {
        List<User> users = new ArrayList<>();
        try {
                String query = "SELECT * FROM contactbook.user WHERE gender=" + "'" + gender + "' and "+ filter +" like " + "'%"+ search + "%'";
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                ResultSet resultSet = preparedStatement.executeQuery();
                users = getUsersList(resultSet);
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
        }

    public User getUserById( int userId ) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement( GET_USER_BY_ID_QUERY );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                user.setUserId( resultSet.getInt( "userId" ) );
                user.setLastName( resultSet.getString( "lastName" ) );
                user.setFirstName( resultSet.getString( "firstName" ) );
                user.setAge( resultSet.getInt( "age" ) );
                user.setGender( resultSet.getString( "gender" ) );
                user.setPhoneNumber( resultSet.getString( "phoneNumber" ) );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
