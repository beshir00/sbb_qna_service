package com.ll.exam.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Article {
    private static int lastId = 0;

    private final int id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this(++lastId, title, body);
    }
}


