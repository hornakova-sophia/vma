package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jazyk.Jazyk
import jazyk.JazykViewModel


class PrekladSKActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textove)
        val jazykViewModel : JazykViewModel by androidx.activity.viewModels {
            JazykViewModel.JazykViewModelFactory((androidx.activity.application as Aplikacia).jazykRepository)
        }

        val recyclerView  = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PrekladSKAdapter(
            object : OnJazykClickListener {
                override fun onJazykClick(jazyk: Jazyk) {
                    Toast.makeText(this@PrekladSKActivity, jazyk.toString(), Toast.LENGTH_SHORT).show()

                }

            }
        )
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val index = viewHolder.adapterPosition
                adapter.remove(index)
            }

        })
        itemTouchHelper.attachToRecyclerView(recyclerView)


        val return_Button = findViewById<View>(R.id.return_Button)
        return_Button.setOnClickListener {
            val intent = Intent(this, UcenieActivity::class.java)
            startActivity(intent)
        }
        val dalej_Button = findViewById<View>(R.id.dalej_Button)
        dalej_Button.setOnClickListener {
        }
        val vpisuj_pole = findViewById<EditText>(R.id.vpisuj_pole)
        vpisuj_pole.inputType = InputType.TYPE_CLASS_NUMBER

        val skontroluj_Button = findViewById<View>(R.id.skontroluj_Button)
        skontroluj_Button.setOnClickListener {
            val userInput = vpisuj_pole.text.toString()
            if (userInput.equals("")){
                showToast("Spravne")
            }else{
                showToast("Nespravne")
            }
        }
    }
    private fun random() {

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}