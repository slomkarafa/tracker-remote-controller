package org.kristi.trackerremote.controller

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.kristi.trackerremote.R
import org.kristi.trackerremote.steering.ControllerService
import org.kristi.trackerremote.steering.SteeringMock

class ControllerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val steering = SteeringMock()
        val steering = ControllerService()

        val controllerPresenter = ControllerPresenter(steering)

        var controllerFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as ControllerFragment?

        if (controllerFragment == null) {
            controllerFragment = ControllerFragment()
            controllerFragment.takePresenter(controllerPresenter)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, controllerFragment)
                .commit()
        }

        controllerPresenter.takeView(controllerFragment)
    }
}
