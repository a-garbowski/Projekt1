package artur.projekt1.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import artur.projekt1.R
import artur.projekt1.model.FormModel
import timber.log.Timber

class ProfileDetailsFragment : Fragment() {

    private var userEmail: TextView? = null
    private var userAccType: TextView? = null
    private var userPhoneNumber: TextView? = null
    private var receiveFormModel: FormModel? = null

    companion object {
        const val PROFILE_KEY = "artur.projekt1.ProfileDetailsFragment.PROFILE_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createdView: View = inflater.inflate(R.layout.fragment_profile_details, container, false)
        return createdView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }

    private fun setUpViews(view: View) {
        userEmail = view.findViewById(R.id.profile_email)
        userAccType = view.findViewById(R.id.profile_acc_type)
        userPhoneNumber = view.findViewById(R.id.profile_phone_number)

        val emailData = String.format(getString(R.string.profile_email), receiveFormModel?.email)
        val subscriptionData = String.format(getString(R.string.profile_acc_type), receiveFormModel?.accType.toString())
        val phoneNumberData = String.format(getString(R.string.profile_phone_number), receiveFormModel?.phoneNumber.toString())

        userEmail?.text = emailData
        userAccType?.text = subscriptionData
        userPhoneNumber?.text = phoneNumberData
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
    }
}
