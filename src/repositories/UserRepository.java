package repositories;
import data.IDB;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection connection;
        try {
            connection = db.getConnection();
            String sql ="INSERT INTO users(username, password, name, surname, birthdate, gender) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getSurname());
            st.setInt(5, user.getBirthdate());
            st.setBoolean(6, user.getGender());

            st.execute();

            return true;
        } catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("birthdate"),
                        rs.getBoolean("gender"));
            }
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT id, username, password, name, surname, bithrdate, gender FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("birthdate"),
                        rs.getBoolean("gender"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM users WHERE username = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("birthdate"),
                        rs.getBoolean("gender"));
            }
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

}
