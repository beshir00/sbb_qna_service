package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    private List<Article> articles = new ArrayList();

    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {

        Article article = new Article(title, body);
        articles.add(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }

    @GetMapping("/getArticle/{id}")
    @ResponseBody
    public Article getArticle(@PathVariable int id) {
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        return article;
    }

    @GetMapping("/modifyArticle/{id}")
    @ResponseBody
    public String modifyArticle(@PathVariable int id, String title, String body){
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null){
            return "해당 게시물은 없는 게시물 입니다.";
        }

        article.setTitle(title);
        article.setBody(body);

        return "%d번 게시물을 수정하였습니다.".formatted(article.getId());
    }

    @GetMapping("/deleteArticle/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id){
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null){
            return "해당 게시물은 없는 게시물 입니다.";
        }
        articles.remove(article);

        return "%d번 게시물을 삭제하였습니다.".formatted(article.getId());
    }
}
