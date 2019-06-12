package org.kristi.trackerremote.controller

import android.graphics.BitmapFactory
import org.kristi.trackerremote.network.NetworkService
import org.kristi.trackerremote.steering.Steering
import android.util.Log
import okio.ByteString


class ControllerPresenter(
    private val steering: Steering,
    private val network: NetworkService
) : ControllerContract.Presenter{


    override fun handleJoystick(angle: Int, strength: Int) {
        if (angle == 0 && strength == 0) {
            steering.stop()
        } else {
            steering.ride(angle, strength)
        }
    }

    lateinit var view: ControllerContract.View


    private fun handleMessage(it: ByteString) {

        val bytes = it.toByteArray()

        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        view.showMap(bitmap)
        Log.d("WSS", "showing map")

    }
    private fun handleDisconnect(msg: String) {

    }


    private fun registerListeners() {
        network.onMessageListener = {
            handleMessage(it)
        }
    }

    override fun takeView(view: ControllerContract.View) {
        this.view = view
        this.registerListeners()
    }

    override fun cleanup() {
    }
}