package com.example.activityresultcontractdemo

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts


class Contract: ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, input: String): Intent {    //This method will call when Input is sent from MainActivity
        Log.d("TAG", "!@# createIntent: $input")
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra(INTENT_REQ_KEY, intent)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {    //We'll get Output through this method
        return intent?.getStringExtra(INTENT_REQ_KEY)
    }

    companion object {
        const val INTENT_REQ_KEY = "intent_req_key"
        const val REQ_KEY = 100
        const val RESULT_KEY = "result_key"
    }
}