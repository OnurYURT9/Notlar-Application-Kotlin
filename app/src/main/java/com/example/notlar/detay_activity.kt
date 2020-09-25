package com.example.notlar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detay_activity.*
import kotlinx.android.synthetic.main.activity_detay_activity.edittextders
import kotlinx.android.synthetic.main.activity_detay_activity.edittextnot1
import kotlinx.android.synthetic.main.activity_detay_activity.edittextnot2
import kotlinx.android.synthetic.main.activity_not_kayit.*
import kotlinx.android.synthetic.main.card_tasarim.*

class detay_activity : AppCompatActivity() {

    private lateinit var not:Notlar
    private lateinit var vt: VeriTabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay_activity)

        toolbarnotdetay.title = "Not Detay"
        setSupportActionBar(toolbarnotdetay)
        vt = VeriTabaniYardimcisi(this)

        not = intent.getSerializableExtra("nesne") as Notlar
        edittextders.setText(not.ders_adi)
        edittextnot1.setText((not.not1).toString())
        edittextnot2.setText((not.not2).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_sil->{
                Snackbar.make(toolbarnotdetay,"Silinsin mi?",Snackbar.LENGTH_SHORT)
                    .setAction("Evet"){

                        notlardao().notsil(vt,not.not_id)

                        startActivity(Intent(this@detay_activity,MainActivity::class.java))
                        finish()
                    }.show()
                return true
            }
            R.id.action_duzenle->{
                val ders_adi = edittextders.text.toString().trim()
                val not1 = edittextnot1.text.toString().trim()
                val not2 = edittextnot2.text.toString().trim()

                if(TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(toolbarnotdetay,"ders adı giriniz..",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if(TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbarnotdetay,"1.notu giriniz..",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if(TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbarnotdetay,"2.notu giriniz..",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                notlardao().notguncelle(vt,not.not_id,ders_adi,not1.toInt(),not2.toInt())
                startActivity(Intent(this@detay_activity,MainActivity::class.java))
                finish()
                return true
            }
            else->return false
        }
        return super.onOptionsItemSelected(item)
    }
}