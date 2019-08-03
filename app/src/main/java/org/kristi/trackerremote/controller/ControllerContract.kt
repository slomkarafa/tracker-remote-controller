package org.kristi.trackerremote.controller

import android.graphics.Bitmap

interface ControllerContract {

    interface View {
        fun isActive(): Boolean
        fun takePresenter(presenter: Presenter)
        fun showJoystick()
        fun showMap(data: Bitmap)
        fun showMessage()
        fun showError(msg: String)
        fun showSavingStatus(isSaving: Boolean)
    }

    interface Presenter {
        fun takeView(view: View)
        fun handleJoystick(angle: Int, strength: Int)
        fun cleanup()
        fun handleSavingStatus(isSaving: Boolean)
    }
}