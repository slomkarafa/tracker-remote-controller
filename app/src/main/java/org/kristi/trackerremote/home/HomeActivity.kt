package org.kristi.trackerremote.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_home.*
import org.kristi.trackerremote.R
import org.kristi.trackerremote.controller.ControllerActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        serverAddress.setText(getString(R.string.default_server_address))
        goButton.setOnClickListener {
            if (serverAddress.text.isNotEmpty()) {
                val address = serverAddress.text.toString()
                Log.d("ELOL", serverAddress.text.toString())
                goToController(address)
            }
        }

    }

    fun goToController(address: String) {
        val intent = Intent(this, ControllerActivity::class.java)
        intent.putExtra("serverAddress", address)
        startActivity(intent)
    }
}
