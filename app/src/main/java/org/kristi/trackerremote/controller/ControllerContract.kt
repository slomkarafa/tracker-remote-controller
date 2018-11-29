package org.kristi.trackerremote.controller

interface ControllerContract {

    interface View {
        fun isActive(): Boolean
        fun showJoystick()
    }

    interface Presenter {
        fun takeView(view: View)
    }
}