package org.kristi.trackerremote.controller

import android.graphics.Bitmap

interface ControllerContract {

    interface View {
        fun isActive(): Boolean
        fun takePresenter(presenter: Presenter)
        fun showJoystick()
        fun showMap(data: Bitmap, dim: Int)
        fun showMessage()
    }

    interface Presenter {
        fun takeView(view: View)
        fun handleJoystick(angle: Int, strength: Int)
    }
}