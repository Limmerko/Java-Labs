package pri117.dao;

import pri117.model.News;

import java.util.List;

public interface INewsDAO {

    News create(News news);

    News getNews(int id);

    List<News> getAllNews();

    void update(News news);

    void delete(int id);
}
