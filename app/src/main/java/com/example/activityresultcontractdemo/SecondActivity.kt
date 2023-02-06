package com.example.activityresultcontractdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //        binding = ActivitySecondBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val tvContentView = findViewById<TextView>(R.id.tvContent)

        val dataContent =
            intent.getStringExtra(MainActivity.INTENT_REQ_KEY)  //Getting data from the intent which we pass from Main Activity
        if (dataContent?.isNotEmpty() == true) {
            tvContentView.text = dataContent
        } else {
            Toast.makeText(this, "Unable to retrieve data", Toast.LENGTH_SHORT).show()
        }
        tvContentView.setOnClickListener {
            Log.d("TAG", "!@# tvContentView clicked ")
            val intent = Intent()
            intent.putExtra(MainActivity.RESULT_KEY, "I'm the data came from Second Activity")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}