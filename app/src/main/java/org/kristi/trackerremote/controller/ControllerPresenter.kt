package org.kristi.trackerremote.controller

import org.kristi.trackerremote.steering.Steering

class ControllerPresenter(
    private val steering: Steering
) : ControllerContract.Presenter {
    override fun handleJoystick(angle: Int, strength: Int) {
        if (angle==0 && strength==0){
            steering.stop()
        } else {
            steering.ride(angle,strength)
        }
    }

    lateinit var view: ControllerContract.View

    override fun takeView(view: ControllerContract.View) {
        this.view = view
    }

}