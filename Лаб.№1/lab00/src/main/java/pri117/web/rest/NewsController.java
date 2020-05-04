package pri117.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pri117.dao.INewsDAO;
import pri117.dao.NewsDAO;
import pri117.model.News;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private final INewsDAO newsDAO;

    public NewsController(INewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @GetMapping
    public String menu(Model ui) {
        ui.addAttribute("newsList", newsDAO.getAllNews());
        return "list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "create";
    }

    @PostMapping("/create")
    public String createNews(
            @RequestParam("title") String title,
            @RequestParam("text") String text) {
        News news = new News();
        news.setTitle(title);
        news.setText(text);
        news = newsDAO.create(news);

        return "redirect:edit/"+news.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model ui) {
        News news = newsDAO.getNews(id);
        ui.addAttribute("news", news);
        return "edit";
    }

    @GetMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        newsDAO.delete(id);
        return "redirect:";
    }

    @GetMapping("/update/{id}")
    public String updateFrom(@PathVariable("id") int id, Model ui) {
        ui.addAttribute("news", newsDAO.getNews(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id,  @RequestParam("title") String title, @RequestParam("text") String text) {
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setText(text);
        newsDAO.update(news);
        return "redirect:/news/edit/"+news.getId();
    }
}
