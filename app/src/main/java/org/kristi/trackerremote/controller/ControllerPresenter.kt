package org.kristi.trackerremote.controller

import android.graphics.BitmapFactory
import org.kristi.trackerremote.network.NetworkService
import org.kristi.trackerremote.steering.Steering
import android.util.Log
import com.beust.klaxon.Klaxon
import okio.ByteString
import org.kristi.trackerremote.network.Action


class ControllerPresenter(
    private val steering: Steering,
    private val network: NetworkService
) : ControllerContract.Presenter{
    override fun handleSavingStatus(isSaving: Boolean) {
        Log.d("WSS","should send")
        steering.setSaving(isSaving)
    }


    override fun handleJoystick(angle: Int, strength: Int) {
        if (angle == 0 && strength == 0) {
            steering.stop()
        } else {
            steering.ride(angle, strength)
        }
    }

    lateinit var view: ControllerContract.View


    private fun handleMap(it: ByteString) {

        val bytes = it.toByteArray()

        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        view.showMap(bitmap)
        Log.d("WSS", "showing map")

    }

    private fun handleMessage(rawMsg: String) {
        val msg = Klaxon().parse<Action>(rawMsg)
        Log.d("WSS", rawMsg)
        if (msg?.action.equals("saving") && msg?.data is Boolean) {
            Log.d("WSS", msg.data.toString())
            view.showSavingStatus(msg.data as Boolean)
        }
    }
    private fun handleDisconnect(msg: String) {
        Log.d("WSS_disconnected", msg)
    }


    private fun registerListeners() {
        network.onMapChangeListener = {
            handleMap(it)
        }
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