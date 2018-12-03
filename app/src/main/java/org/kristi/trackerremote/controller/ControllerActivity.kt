package org.kristi.trackerremote.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.kristi.trackerremote.R

class ControllerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var controllerFragment: ControllerFragment? = supportFragmentManager.findFragmentById(R.id.fragmentContaier) as ControllerFragment?

        if (controllerFragment == null) {
            controllerFragment = controllerFragmentProvider.get()   
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, controllerFragment)
                .commit()
        }
    }
}
