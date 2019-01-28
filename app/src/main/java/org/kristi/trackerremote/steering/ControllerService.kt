package org.kristi.trackerremote.steering

import android.util.Log
import okhttp3.WebSocket
import org.json.JSONStringer
import org.kristi.trackerremote.network.NetworkService


private val URL = "http://192.168.43.154:8080/go"

class ControllerService : Steering, NetworkService() {

    lateinit var webSocket: WebSocket

    init {
        ws = create(3, URL)
    }

    override fun ride(angle: Int, power: Int) {
        Log.d("WSS", "sending msg")
        val request = """{
            "angle":$angle,
            "power":$power
        }"""
        ws.send(request)
    }

    override fun stop() {
        ws.send("stop")
    }

}