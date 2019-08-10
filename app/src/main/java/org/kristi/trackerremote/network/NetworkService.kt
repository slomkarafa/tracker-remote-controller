package org.kristi.trackerremote.network

import android.util.Log
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit


private val NORMAL_CLOSURE_STATUS = 1000

class NetworkService {


    var timeout: Long = 3
    lateinit var url: String
    lateinit var ws: WebSocket
    var onMapChangeListener: ((ByteString) -> Unit)? = null
    var onMessageListener: ((String) -> Unit)? = null
    var onDisconnectListener: ((String) -> Unit)? = null

    fun create(timeout: Long, url: String): WebSocket {
        this.timeout = timeout
        this.url = "ws://$url/mobile"

        val client = OkHttpClient.Builder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(this.url)
            .build()
        ws = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                webSocket.send("""{"action":"register","data":"app"}""")
                output("WS openned")
            }

            override fun onMessage(webSocket: WebSocket?, text: String?) {
//                text?.let { onMapChangeListener?.invoke(it) }
                output("Receiving : " + text!!)
                text.let { onMessageListener?.invoke(it) }
            }

            override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
//                output("Receiving bytes : " + bytes!!.hex())
                output("Receiving bytes : " + bytes!!.toString())

                bytes.let { onMapChangeListener?.invoke(it) }

            }

            override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
                webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
                output("Closing : $code / $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.e("WSS", response?.message() ?: "null", t)
            }

            private fun output(txt: String) {
                Log.v("WSS", txt)
            }
        })
        return ws
    }

}