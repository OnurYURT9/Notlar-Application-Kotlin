package com.example.notlar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_not_kayit.*

class NotKayitActivity : AppCompatActivity() {
    private lateinit var vt: VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_kayit)

        vt = VeriTabaniYardimcisi(this)

        toolbarnotkayit.title = "Not Kayıt"
        setSupportActionBar(toolbarnotkayit)
        buttonkaydet.setOnClickListener{

            val ders_adi = edittextders.text.toString().trim()
            val not1 = edittextnot1.text.toString().trim()
            val not2 = edittextnot2.text.toString().trim()

            if(TextUtils.isEmpty(ders_adi)){
                Snackbar.make(toolbarnotkayit,"ders adı giriniz..",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(not1)){
                Snackbar.make(toolbarnotkayit,"1.notu giriniz..",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(not2)){
                Snackbar.make(toolbarnotkayit,"2.notu giriniz..",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            notlardao().notekle(vt,ders_adi,not1.toInt(),not2.toInt())
            startActivity(Intent(this@NotKayitActivity,MainActivity::class.java))
            finish()
        }
    }
}