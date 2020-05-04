package pri117.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pri117.config.SpringJdbcConfig;
import pri117.model.News;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class NewsDAO implements INewsDAO {

    @Autowired
    SpringJdbcConfig springJdbcConfig;

    Logger logger = LoggerFactory.getLogger(NewsDAO.class);

    @Override
    public News create(News news) {
        logger.info("Запрос на создание новости");
        try {
            String query = "INSERT INTO news (title, text)" +
                    "VALUES (?, ?)";
            PreparedStatement stmt =
                    springJdbcConfig.dataSource().getConnection().prepareStatement(query,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getText());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next())
                news.setId(resultSet.getInt(1));
            logger.info("Добавлена новость " + news.toString());
            return news;
        } catch (SQLException e) {
            logger.error("Ошибка при добавлении новости" + e.getMessage());
            return null;
        }
    }

    @Override
    public News getNews(int id) {
        logger.info("Запрос на получение новости с id = " + id);
        try {
            String query = "SELECT * FROM news where id="+id;
            PreparedStatement stmt =
                    springJdbcConfig.dataSource().getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            News news = new News();
            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
                news.setTitle(resultSet.getString(2));
                news.setText(resultSet.getString(3));
            }
            logger.info("Возвращена новость " + news.toString());
            return news;

        } catch (SQLException e) {
            logger.error("Ошибка при получении новости" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<News> getAllNews() {
        logger.info("Запрос на получение списка новостей");
        try {
            ArrayList<News> newsList = new ArrayList<News>();
            String query = "SELECT * FROM news";
            PreparedStatement stmt =
                    springJdbcConfig.dataSource().getConnection().prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getInt(1));
                news.setTitle(resultSet.getString(2));
                news.setText(resultSet.getString(3));
                newsList.add(news);
            }
            return newsList;

        } catch (SQLException e) {
            logger.error("Ошибка при при запросе на получение списка новостей "+e.getMessage());
            return null;
        }
    }

    @Override
    public void update(News news) {
        try {
            String query = "UPDATE news SET title='"+news.getTitle()+"', text='"+news.getText()+"' WHERE id="+news.getId();
            PreparedStatement stmt =
                    springJdbcConfig.dataSource().getConnection().prepareStatement(query,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        logger.info("Запрос на удаление новости с id = " + id);
        try {
            String query = "DELETE FROM news where id="+id;
            PreparedStatement stmt =
                    springJdbcConfig.dataSource().getConnection().prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при запросе на удаление новости с id = " + id);
        }
    }
}
