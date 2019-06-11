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
import org.json.JSONArray
import kotlin.coroutines.CoroutineContext


class ControllerPresenter(
    private val steering: Steering,
    private val network: NetworkService
) : ControllerContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun handleJoystick(angle: Int, strength: Int) {
        if (angle == 0 && strength == 0) {
            steering.stop()
        } else {
            steering.ride(angle, strength)
        }
    }

    lateinit var view: ControllerContract.View


    fun listener(it: String) {

        val data = JSONObject(it)
        val arr = data.getJSONArray("data")


        val bytes = ByteArray(arr.length())
        for (i in 0 until arr.length()) {
            bytes[i] = (arr.get(i) as Int and 0xFF).toByte()
        }

        val dim = data.getInt("dim")
        val options = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        view.showMap(bitmap, dim)
        Log.d("WSS", "showing map")

    }

    fun listener2(it: String): Bitmap {

        val data = JSONObject(it)
        val arr = data.getJSONArray("data")


        val bytes = ByteArray(arr.length())
        for (i in 0 until arr.length()) {
            bytes[i] = (arr.get(i) as Int and 0xFF).toByte()
        }

        val dim = data.getInt("dim")
        val options = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//        view.showMap(bitmap, dim)
        Log.d("WSS", "showing map")
        return bitmap

    }

    fun registerOnMessageListener() {
        network.onMessageListener = {
            listener(it)
//            launch { listener(it) }
//            GlobalScope.launch {
//                val bitmap = listener2(it)
//                withContext(Dispatchers.Main) {
////                    view.showMap(bitmap, 200)
//                    Log.d("WSS", "im async")
//                }
//            }
//            launch{
//                val bitmap = listener2(it)
//                withContext(Dispatchers.Main){
////                    view.showMap(bitmap, 200)
//                    Log.d("WSS", "im async")
//                }
//            }
        }
    }

    override fun takeView(view: ControllerContract.View) {
        this.view = view
        this.registerOnMessageListener()
    }

    override fun cleanup() {
        job.cancel()
    }
}