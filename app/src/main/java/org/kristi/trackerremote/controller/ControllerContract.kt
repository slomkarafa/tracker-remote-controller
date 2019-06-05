package org.kristi.trackerremote.controller

interface ControllerContract {

    interface View {
        fun isActive(): Boolean
        fun takePresenter(presenter: Presenter)
        fun showJoystick()
        fun showChart(data: ByteArray, dim: Int)
        fun showMessage()
    }

    interface Presenter {
        fun takeView(view: View)
        fun handleJoystick(angle: Int, strength: Int)
    }
}