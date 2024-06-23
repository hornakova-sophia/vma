package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import android.os.Handler
import android.os.Looper
class PexesoActivity: AppCompatActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pexeso)
        var otocenie:Int=0
        val imageButton1 = findViewById<ImageButton>(R.id.imageButton1)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)
        val imageButton3 = findViewById<ImageButton>(R.id.imageButton3)
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4)
        val imageButton5 = findViewById<ImageButton>(R.id.imageButton5)
        val imageButton6 = findViewById<ImageButton>(R.id.imageButton6)
        val imageButton7 = findViewById<ImageButton>(R.id.imageButton7)
        val imageButton8 = findViewById<ImageButton>(R.id.imageButton8)
        val imageButton9 = findViewById<ImageButton>(R.id.imageButton9)
        val imageButton10 = findViewById<ImageButton>(R.id.imageButton10)
        val imageButton11 = findViewById<ImageButton>(R.id.imageButton11)
        val imageButton12 = findViewById<ImageButton>(R.id.imageButton12)
        val nacitanieButton = findViewById<ImageButton>(R.id.nacitanie_Button)
        val list: List<String> = listOf("#B60000","#B60000","#2196F3","#2196F3","#4CAF50","#4CAF50","#FFEB3B","#FFEB3B","#673AB7","#673AB7","#E61CD3","#E61CD3")
        val listBUttons: MutableList<ImageButton?> = mutableListOf(null, null)
        val listVybraneFarby: MutableList<String> = mutableListOf("","")
        val setik: MutableSet<ImageButton?> = mutableSetOf()
        var shuffledList = list.shuffled()
        fun otoceniefun() {
            if (listVybraneFarby[0] == listVybraneFarby[1]) {
                setik.add(listBUttons[0])
                setik.add(listBUttons[1])
            } else {
                listBUttons[0]?.setColorFilter(Color.TRANSPARENT)
                listBUttons[1]?.setColorFilter(Color.TRANSPARENT)
            }
            listBUttons[0] = null
            listBUttons[1] = null
            listVybraneFarby[0] = ""
            listVybraneFarby[1] = ""
            otocenie = 0
        }
        imageButton1.setOnClickListener {
            if(!setik.contains(imageButton1)){
            imageButton1.setColorFilter(Color.parseColor(shuffledList[0]))
           if (otocenie==0){
               listBUttons[0] = imageButton1
               listVybraneFarby[0]=shuffledList[0]
               otocenie=1
           }
           else if  (otocenie==1 &&  listBUttons[0]!=imageButton1){
                listBUttons[1] = imageButton1
                listVybraneFarby[1]=shuffledList[0]
                otoceniefun()
            }
            }
        }
        imageButton2.setOnClickListener {
            if(!setik.contains(imageButton2)){
            imageButton2.setColorFilter(Color.parseColor(shuffledList[1]))
                if (otocenie==0){
                    listBUttons[0] = imageButton2
                    listVybraneFarby[0]=shuffledList[1]
                    otocenie=1
                }
                else if  (otocenie==1 &&  listBUttons[0]!=imageButton2){
                    listBUttons[1] = imageButton2
                    listVybraneFarby[1]=shuffledList[1]
                    otoceniefun()
                }
            }

        }
        imageButton3.setOnClickListener {
            if(!setik.contains(imageButton3)) {
                imageButton3.setColorFilter(Color.parseColor(shuffledList[2]))
                if (otocenie == 0) {
                    listBUttons[0] = imageButton3
                    listVybraneFarby[0] = shuffledList[2]
                    otocenie = 1
                }
                else if (otocenie == 1 &&  listBUttons[0]!=imageButton3) {
                    listBUttons[1] = imageButton3
                    listVybraneFarby[1] = shuffledList[2]
                    otoceniefun()
                }
            }
        }
        imageButton4.setOnClickListener {
            if(!setik.contains(imageButton4)) {
                imageButton4.setColorFilter(Color.parseColor(shuffledList[3]))
                if (otocenie == 0) {
                    listBUttons[0] = imageButton4
                    listVybraneFarby[0] = shuffledList[3]
                    otocenie = 1
                }
                else if  (otocenie == 1 &&  listBUttons[0]!=imageButton4) {
                    listBUttons[1] = imageButton4
                    listVybraneFarby[1] = shuffledList[3]
                    otoceniefun()
                }
            }
        }
        imageButton5.setOnClickListener {
            if(!setik.contains(imageButton5)){
                imageButton5.setColorFilter(Color.parseColor(shuffledList[4]))
                if (otocenie==0){
                    listBUttons[0] = imageButton5
                    listVybraneFarby[0]=shuffledList[4]
                    otocenie=1
                }
                else if  (otocenie==1 &&  listBUttons[0]!=imageButton5){
                    listBUttons[1] = imageButton5
                    listVybraneFarby[1]=shuffledList[4]
                    otoceniefun()
                }
            }
        }
        imageButton6.setOnClickListener {
            if(!setik.contains(imageButton6)){
                imageButton6.setColorFilter(Color.parseColor(shuffledList[5]))
                if (otocenie==0){
                    listBUttons[0] = imageButton6
                    listVybraneFarby[0]=shuffledList[5]
                    otocenie=1
                }
                else if  (otocenie==1 &&  listBUttons[0]!=imageButton6){
                    listBUttons[1] = imageButton6
                    listVybraneFarby[1]=shuffledList[5]
                    otoceniefun()
                }
            }

        }
        imageButton7.setOnClickListener {
            if(!setik.contains(imageButton7)) {
                imageButton7.setColorFilter(Color.parseColor(shuffledList[6]))
                if (otocenie == 0) {
                    listBUttons[0] = imageButton7
                    listVybraneFarby[0] = shuffledList[6]
                    otocenie = 1
                }
                else if (otocenie == 1 &&  listBUttons[0]!=imageButton7) {
                    listBUttons[1] = imageButton7
                    listVybraneFarby[1] = shuffledList[6]
                    otoceniefun()
                }
            }
        }
        imageButton8.setOnClickListener {
            if(!setik.contains(imageButton8)) {
                imageButton8.setColorFilter(Color.parseColor(shuffledList[7]))
                if (otocenie == 0) {
                    listBUttons[0] = imageButton8
                    listVybraneFarby[0] = shuffledList[7]
                    otocenie = 1
                }
                else if  (otocenie == 1 &&  listBUttons[0]!=imageButton8) {
                    listBUttons[1] = imageButton8
                    listVybraneFarby[1] = shuffledList[7]
                    otoceniefun()
                }
            }
        }
        imageButton9.setOnClickListener {
            if(!setik.contains(imageButton9)){
                imageButton9.setColorFilter(Color.parseColor(shuffledList[8]))
                if (otocenie==0){
                    listBUttons[0] = imageButton9
                    listVybraneFarby[0]=shuffledList[8]
                    otocenie=1
                }
                else if  (otocenie==1 &&  listBUttons[0]!=imageButton9){
                    listBUttons[1] = imageButton9
                    listVybraneFarby[1]=shuffledList[8]
                    otoceniefun()
                }
            }
        }
        imageButton10.setOnClickListener {
            if(!setik.contains(imageButton10)){
                imageButton10.setColorFilter(Color.parseColor(shuffledList[9]))
                if (otocenie==0){
                    listBUttons[0] = imageButton10
                    listVybraneFarby[0]=shuffledList[9]
                    otocenie=1
                }
                else if  (otocenie==1 &&  listBUttons[0]!=imageButton10){
                    listBUttons[1] = imageButton10
                    listVybraneFarby[1]=shuffledList[9]
                    otoceniefun()
                }
            }

        }
        imageButton11.setOnClickListener {
            if(!setik.contains(imageButton11)) {
                imageButton11.setColorFilter(Color.parseColor(shuffledList[10]))
                if (otocenie == 0) {
                    listBUttons[0] = imageButton11
                    listVybraneFarby[0] = shuffledList[10]
                    otocenie = 1
                }
                else if (otocenie == 1 &&  listBUttons[0]!=imageButton11) {
                    listBUttons[1] = imageButton11
                    listVybraneFarby[1] = shuffledList[10]
                    otoceniefun()
                }
            }
        }
        imageButton12.setOnClickListener {
            if(!setik.contains(imageButton12)) {
                imageButton12.setColorFilter(Color.parseColor(shuffledList[11]))
                if (otocenie == 0) {
                    listBUttons[0] = imageButton12
                    listVybraneFarby[0] = shuffledList[11]
                    otocenie = 1
                }
                else if  (otocenie == 1 &&  listBUttons[0]!=imageButton12) {
                    listBUttons[1] = imageButton12
                    listVybraneFarby[1] = shuffledList[11]
                    otoceniefun()
                }
            }
        }
        nacitanieButton.setOnClickListener {
            imageButton1.setColorFilter(Color.TRANSPARENT)
            imageButton2.setColorFilter(Color.TRANSPARENT)
            imageButton3.setColorFilter(Color.TRANSPARENT)
            imageButton4.setColorFilter(Color.TRANSPARENT)
            imageButton5.setColorFilter(Color.TRANSPARENT)
            imageButton6.setColorFilter(Color.TRANSPARENT)
            imageButton7.setColorFilter(Color.TRANSPARENT)
            imageButton8.setColorFilter(Color.TRANSPARENT)
            imageButton9.setColorFilter(Color.TRANSPARENT)
            imageButton10.setColorFilter(Color.TRANSPARENT)
            imageButton11.setColorFilter(Color.TRANSPARENT)
            imageButton12.setColorFilter(Color.TRANSPARENT)
            setik.clear()
            shuffledList = list.shuffled()
            listBUttons[0] = null
            listBUttons[1] = null
            listVybraneFarby[0] = ""
            listVybraneFarby[1] = ""
            otocenie = 0
        }

    }


}