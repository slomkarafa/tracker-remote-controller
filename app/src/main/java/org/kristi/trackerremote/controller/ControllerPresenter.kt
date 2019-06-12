package org.kristi.trackerremote.controller

import android.graphics.BitmapFactory
import org.json.JSONObject
import org.kristi.trackerremote.network.NetworkService
import org.kristi.trackerremote.steering.Steering
import android.graphics.Bitmap
import android.provider.Contacts
import android.util.Base64
import android.util.Log
import kotlinx.coroutines.*
import okio.ByteString
import org.json.JSONArray
import kotlin.coroutines.CoroutineContext


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


    fun listener(it: ByteString) {

        val bytes = it.toByteArray()

        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        view.showMap(bitmap)
        Log.d("WSS", "showing map")

    }


    fun registerOnMessageListener() {
        network.onMessageListener = {
            listener(it)
        }
    }

    override fun takeView(view: ControllerContract.View) {
        this.view = view
        this.registerOnMessageListener()
    }

    override fun cleanup() {
    }
}