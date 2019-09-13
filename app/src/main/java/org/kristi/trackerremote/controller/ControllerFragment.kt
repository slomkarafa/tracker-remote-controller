package org.kristi.trackerremote.controller

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.github.controlwear.virtual.joystick.android.JoystickView
import kotlinx.android.synthetic.main.fragment_controller.*
import org.kristi.trackerremote.R

class ControllerFragment : Fragment(), ControllerContract.View {
    private var strength : Int = 0
    private var angle : Int = 90
    override fun showSavingStatus(isSaving: Boolean) {
        requireActivity().runOnUiThread { saving_switch.isChecked = isSaving }
    }

    override fun showError(msg: String) {
        Toast
            .makeText(context, msg, Toast.LENGTH_LONG)
            .show()
    }

    lateinit var presenter: ControllerContract.Presenter

    override fun takePresenter(presenter: ControllerContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_controller, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        joystick_0.setOnMoveListener { _: Int, strength: Int ->
            this.strength = strength
            this.angle = if (this.angle == 0) 90 else this.angle
            presenter.handleJoystick(this.angle, this.strength)
            strength_text.text = this.strength.toString()
            angle_text.text = this.angle.toString()
        }
        joystick_1.setOnMoveListener { angle: Int, _: Int ->
            this.angle = angle
            presenter.handleJoystick(this.angle, this.strength)
            angle_text.text = this.angle.toString()
            strength_text.text = this.strength.toString()
        }
        saving_switch?.setOnCheckedChangeListener { _, isChecked -> presenter.handleSavingStatus(isChecked) }
    }

    override fun showMap(data: Bitmap) {

        requireActivity().runOnUiThread { map_image.setImageBitmap(data) }
    }

    override fun showMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isActive(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showJoystick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        super.onStop()
        presenter.cleanup()
    }

}