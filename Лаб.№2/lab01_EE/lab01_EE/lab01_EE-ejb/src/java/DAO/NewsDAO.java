/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import model.News;
import singleton.Interceptor;

/**
 *
 * @author Limmerko
 */
@Stateless
public class NewsDAO implements IDAO <News>{
    
    private static final Logger LOGGER = Logger.getLogger(NewsDAO.class.getName());
    
    private final String url = "jdbc:mysql://localhost:3306/lab01?useSSL=false&useUnicode=true&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "12232";
    private static Connection connection;
    
    @PostConstruct
    private void init() {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            LOGGER.warning("Error getting connection");
        }
    }
    
    @Override
    public List<News> getAll() {
       List<News> newsList = new ArrayList<>();
       String request = "SELECT*FROM NEWS";
       try {
           PreparedStatement stmt = connection.prepareStatement(request);
           ResultSet result = stmt.executeQuery();
           while (result.next()) {
               News news = new News();
               news.setId(result.getInt(1));
               news.setTitle(result.getString(2));
               news.setText(result.getString(2));
               newsList.add(news);
           }
           return newsList;
       } catch (Exception e) {
           LOGGER.warning("ERROR" + e.getMessage());
           return null;
       }
    }

    @Override
    public News getById(int id) {
       String request = "SELECT*FROM NEWS WEHERE id="+id;
       try {
           PreparedStatement stmt = connection.prepareStatement(request);
           ResultSet result = stmt.executeQuery();
           News news = new News();
           if (result.next()) {
               news.setId(result.getInt(1));
               news.setTitle(result.getString(2));
               news.setText(result.getString(2));
           }
           return news;
       } catch (Exception e) {
           LOGGER.warning("ERROR" + e.getMessage());
           return null;
       }
    }

    @Override
    public void delete(int id) {
        String request = "DELETE FROM NEWS WEHERE id="+id;
       try {
           PreparedStatement stmt = connection.prepareStatement(request);
           stmt.setInt(1,id);
           stmt.executeUpdate();
       } catch (Exception e) {
           LOGGER.warning("ERROR" + e.getMessage());
       }
    }

    @Override
    public void update(News model) {
        String request = "UPDATE NEWS SET title = ?, text = ? WHERE id ="+model.getId();
       try {
           PreparedStatement stmt = connection.prepareStatement(request);
           stmt.setString(1,model.getTitle());
           stmt.setString(2, model.getText());
           stmt.executeUpdate();
       } catch (Exception e) {
           LOGGER.warning("ERROR" + e.getMessage());
       }
    }
    
    @Override
    @Interceptors(Interceptor.class)
    public News create(News model) {
       String request = "INSERT INTO NEWS (title, text) VALUES(?,?)";
       try {
           PreparedStatement stmt = connection.prepareStatement(request,
                   PreparedStatement.RETURN_GENERATED_KEYS);
           stmt.setString(1, model.getTitle());
           stmt.setString(2, model.getText());
           stmt.executeUpdate();
           ResultSet resultSet = stmt.getGeneratedKeys();
           if (resultSet.next()) {
               model.setId(resultSet.getInt(1));     
           }
           return model;
       } catch (Exception e) {
           LOGGER.warning("ERROR" + e.getMessage());
           return null;
       }
    }
}
