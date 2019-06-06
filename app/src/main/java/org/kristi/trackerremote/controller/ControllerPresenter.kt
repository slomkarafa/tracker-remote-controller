package org.kristi.trackerremote.controller

import android.graphics.BitmapFactory
import org.json.JSONObject
import org.kristi.trackerremote.network.NetworkService
import org.kristi.trackerremote.steering.Steering
import android.graphics.Bitmap
import android.util.Base64
import org.json.JSONArray


class ControllerPresenter(
    private val steering: Steering,
    private val network: NetworkService
) : ControllerContract.Presenter {
    override fun handleJoystick(angle: Int, strength: Int) {
        if (angle == 0 && strength == 0) {
            steering.stop()
        } else {
            steering.ride(angle, strength)
        }
    }

    lateinit var view: ControllerContract.View


    fun registerOnMessageListener() {
        network.onMessageListener = {

            val data = JSONObject(it)
            val arr = data.getJSONArray("data")


            val bytes = ByteArray(arr.length())
            for (i in 0 until arr.length()) {
                bytes[i] = (arr.get(i) as Int and 0xFF).toByte()
            }
//            val bytes = bytesString.toByteArray(Charsets.UTF_8)
            val dim = data.getInt("dim")
            val options = BitmapFactory.Options()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            view.showMap(bitmap, dim)


        }
    }

    override fun takeView(view: ControllerContract.View) {
        this.view = view
        this.registerOnMessageListener()
    }

}