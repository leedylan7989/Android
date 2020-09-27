/*
    Heon Lee
    991280638

    Final Project
    2020-04-17

 */
package com.example.heonlee_finalproject

class NewsItem {

    var title : String = ""
    var description : String = ""
    var sourceName : String = ""
    var author : String = ""

    constructor(title : String, description : String, sourceName : String, author : String) {
        this.title = title
        this.description = description
        this.sourceName = sourceName
        this.author = author
    }
}