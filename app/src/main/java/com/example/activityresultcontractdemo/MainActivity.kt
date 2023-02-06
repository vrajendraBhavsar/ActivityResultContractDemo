package com.example.activityresultcontractdemo

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.activityresultcontractdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val INTENT_REQ_KEY = "intent_req_key"
        const val REQ_KEY = 100
        const val RESULT_KEY = "result_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        /*val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        val etContentText = binding.etContent.text
        binding.fab.setOnClickListener { view ->
            if (!etContentText.isNullOrEmpty()) {

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(INTENT_REQ_KEY, etContentText.toString())
                startActivityForResult(intent, REQ_KEY)    //We use when we expect to receive some data from another Activity

            } else {
                Snackbar.make(view, "ET is empty", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {   //When we come back from the another activity, this fun will get called
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_KEY && resultCode == RESULT_OK) {
            binding.etContent.setText(data?.getStringExtra(RESULT_KEY))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}