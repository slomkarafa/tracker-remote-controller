package org.kristi.trackerremote.controller

interface ControllerContract {

    interface View {
        fun isActive(): Boolean
        fun takePresnter(presenter: Presenter)
        fun showJoystick()
        fun showChart()
        fun showMessage()
    }

    interface Presenter {
        fun takeView(view: View)
    }
}