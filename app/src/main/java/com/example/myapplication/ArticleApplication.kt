package com.example.myapplication

import android.app.Application

class ArticleApplication : Application() {
    val db by lazy {AppDatabase.getInstance(this)}
}