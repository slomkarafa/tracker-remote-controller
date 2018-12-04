package org.kristi.trackerremote.controller

class ControllerPresenter : ControllerContract.Presenter {
    lateinit var view: ControllerContract.View

    override fun takeView(view: ControllerContract.View) {
        this.view = view
    }

}