package artur.projekt1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import artur.projekt1.model.FormModel
import timber.log.Timber

class ProfileDetailsActivity : AppCompatActivity() {

    private var userEmail: TextView? = null
    private var userAccType: TextView? = null
    private var userPhoneNumber: TextView? = null
    private var receiveFormModel: FormModel? = null

    companion object {
        const val PROFILE_KEY = "artur.projekt1.ProfileDetailsActivity.PROFILE_KEY"
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate")
        receiveFormModel = intent.getSerializableExtra(PROFILE_KEY) as FormModel
        setContentView(R.layout.activity_profile_details)
        setUpViews()
    }

    private fun setUpViews() {
        userEmail = findViewById(R.id.profile_email)
        userAccType = findViewById(R.id.profile_acc_type)
        userPhoneNumber = findViewById(R.id.profile_phone_number)

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

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart")
    }
}