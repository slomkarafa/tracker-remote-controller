package org.kristi.trackerremote.network

import android.os.Bundle
import android.util.Log
import okhttp3.*
import okio.ByteString
import org.kristi.trackerremote.R
import java.util.concurrent.TimeUnit


private val NORMAL_CLOSURE_STATUS = 1000

open class NetworkService{


    var timeout:Long = 3
    lateinit var url:String
    lateinit var ws:WebSocket
    
    fun create(timeout:Long,url:String) : WebSocket{
        this.timeout = timeout
        this.url = url

        val client = OkHttpClient.Builder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(url)
            .build()
        ws = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                webSocket.send("a tutaj?")
                output("WS openned")
            }

            override fun onMessage(webSocket: WebSocket?, text: String?) {
                output("Receiving : " + text!!)
            }

            override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
                output("Receiving bytes : " + bytes!!.hex())
            }

            override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
                webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
                output("Closing : $code / $reason")
            }

//            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response) {
//                output("Error : " + t.message)
//            }

            private fun output(txt: String) {
                Log.v("WSS", txt)
            }
        })
        return ws
    }
}