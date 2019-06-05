package org.kristi.trackerremote.controller

import org.json.JSONObject
import org.kristi.trackerremote.network.NetworkService
import org.kristi.trackerremote.steering.Steering

class ControllerPresenter(
    private val steering: Steering,
    private val network: NetworkService
) : ControllerContract.Presenter {
    override fun handleJoystick(angle: Int, strength: Int) {
        if (angle==0 && strength==0){
            steering.stop()
        } else {
            steering.ride(angle,strength)
        }
    }

    lateinit var view: ControllerContract.View



    fun registerOnMessageListener() {
        network.onMessageListener {
            text -> {
            val data = JSONObject(text)
            view.showChart()
        }
        }
    }

    override fun takeView(view: ControllerContract.View) {
        this.view = view
    }

}