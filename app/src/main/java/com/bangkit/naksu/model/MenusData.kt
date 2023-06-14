package com.bangkit.naksu.model

import com.bangkit.naksu.R

object MenusData {
    val menus = listOf(
        Menus(
            id = 1,
            title = "Gallery",
            image = R.drawable.ic_galeri,
            route = "gallery_screen"
        ),
        Menus(
            id = 2,
            title = "Study",
            image = R.drawable.ic_kamera,
            route = "camera_screen"
        ),
        Menus(
            id = 3,
            title = "Collection",
            image = R.drawable.ic_star,
            route = "collection_screen"
        ),
        Menus(
            id = 4,
            title = "Dictionary",
            image = R.drawable.ic_book,
            route = "dictionary_screen"
        ),
    )
}