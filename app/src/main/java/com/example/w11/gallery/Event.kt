package com.example.w11.gallery

import java.io.Serializable

class Event : Serializable {
    var images: ArrayList<String>? = null
    var description: String? = null
    var title: String? = null
    var date: String? = null

    var imagesID: ArrayList<Int> = ArrayList()
    var avgC: Int = -1
    var fav = false


    constructor() : super()

    constructor(images: ArrayList<String>, description: String, title: String, date: String) : super() {
        this.images = images
        this.description = description
        this.title = title
        this.date = date
    }

}
