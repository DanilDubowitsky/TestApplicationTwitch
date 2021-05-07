package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun topGames(view:View){
        val intent: Intent = Intent(this,TopGamesActivity::class.java)
        startActivity(intent)
    }
    fun setReview(view:View){

    }
}