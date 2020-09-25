package com.example.notlar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var notlarListe:ArrayList<Notlar>
    private lateinit var adapter: NotlarAdapter

    private lateinit var vt: VeriTabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Notlar Uygulaması"

        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        vt = VeriTabaniYardimcisi(this)

        notlarListe = notlardao().tumnotlar(vt)

        adapter = NotlarAdapter(this,notlarListe)

        rv.adapter = adapter

        var toplam = 0

        for(n in notlarListe){
            toplam = toplam+(n.not1+n.not2)/2
        }
        if(toplam != 0){
            toolbar.subtitle = "Ortalama : ${toplam/notlarListe.size}"
        }

        fab.setOnClickListener{
            startActivity(Intent(this@MainActivity,NotKayitActivity::class.java))
        }
    }

    override fun onBackPressed() {
       val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}