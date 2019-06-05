package org.kristi.trackerremote.controller

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.kristi.trackerremote.R
import org.kristi.trackerremote.network.NetworkService
import org.kristi.trackerremote.steering.ControllerService
import org.kristi.trackerremote.steering.SteeringMock

class ControllerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serverAdress = intent.getStringExtra("serverAddress")

//        val steering = SteeringMock()
        val network = NetworkService()
        val steering = ControllerService(network.create(3, serverAdress))

        val controllerPresenter = ControllerPresenter(steering, network)

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
