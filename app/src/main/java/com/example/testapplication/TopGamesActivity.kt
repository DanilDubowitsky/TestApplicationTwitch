package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.db.DbManager
import retrofit2.Call
import retrofit2.Response

class TopGamesActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var gameList: ArrayList<Game>
    lateinit var pg:ProgressBar
    var dbManager: DbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_games)
        recyclerView = findViewById(R.id.gamesRecycle)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        pg = findViewById(R.id.progressBar)
        pg.visibility=ProgressBar.VISIBLE
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }
        })
        getGames()

    }
    fun setDbValues(list:ArrayList<Game>){
        dbManager.openDb()

        for (i in 0 until list.size){
            dbManager.insertToDb(list[i].game.name,list[i].viewers,list[i].channels,list[i].game.box.large)
        }
        dbManager.closeDb()


    }
    fun getFromDb():ArrayList<Game>{
        dbManager.openDb()
        var list:ArrayList<Game>
        list = dbManager.readDbData()
        dbManager.closeDb()
        return list
    }
    fun getGames(){
        NetworkService.getInstance().jsonApi.getTopGames(100,Constants.accept,Constants.clientId).
                enqueue(object:retrofit2.Callback<GamesModel>{
                    override fun onFailure(call: Call<GamesModel>, t: Throwable) {
                        Toast.makeText(applicationContext,"Приложение работает в офлайн режиме, данные загружены из локальной БД",Toast.LENGTH_LONG).show()
                        pg.visibility = ProgressBar.INVISIBLE
                        var list:ArrayList<Game> = getFromDb()
                        recyclerView.adapter = RecycleAdapter(list)
                        (recyclerView.adapter as RecycleAdapter).notifyDataSetChanged()
                    }

                    override fun onResponse(call: Call<GamesModel>, response: Response<GamesModel>) {
                            gameList = response.body()?.top ?: ArrayList()

                        recyclerView.adapter = RecycleAdapter(gameList)
                        (recyclerView.adapter as RecycleAdapter).notifyDataSetChanged()
                        pg.visibility = ProgressBar.INVISIBLE
                        dbManager.openDb()
                        dbManager.destroyDb()
                        dbManager.closeDb()
                        setDbValues(gameList)
                    }

                })
    }
}