package artur.projekt1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import artur.projekt1.model.FormModel
import artur.projekt1.model.SubscriptionType
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private var inputEmail: TextInputEditText? = null
    private var inputPassword: TextInputEditText? = null
    private var checkAccType: RadioGroup? = null
    private var checkMarketingConsent: CheckBox? = null
    private var checkNewsletterConsent: CheckBox? = null
    private var registerButton: Button? = null
    private var inputEmailLayout: TextInputLayout? = null
    private var inputPasswordLayout: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        inputEmail = findViewById(R.id.input_email)
        inputEmail?.doAfterTextChanged {
            inputEmailLayout?.error = ""
        }
        inputPassword = findViewById(R.id.input_password)
        inputPassword?.doAfterTextChanged {
            inputPasswordLayout?.error = ""
        }
        checkAccType = findViewById(R.id.check_acc_type)
        checkMarketingConsent = findViewById(R.id.check_marketing_consent)
        checkNewsletterConsent = findViewById(R.id.check_newsletter_consent)
        registerButton = findViewById(R.id.button)
        inputEmailLayout = findViewById(R.id.textInputLayoutEmail)
        inputPasswordLayout = findViewById(R.id.textInputLayoutPassword)
        registerButton?.setOnClickListener {
            if (validate()) {
                sendForm()
            }
        }
    }

    private fun sendForm() {
        val email: String = inputEmail?.text.toString()
        val password: String = inputPassword?.text.toString()
        val subscriptionPlan: SubscriptionType = when (checkAccType?.checkedRadioButtonId) {
            R.id.radio_free -> SubscriptionType.Free
            R.id.radio_premium -> SubscriptionType.Premium
            else -> SubscriptionType.Premium
        }
        val marketingConsent: Boolean = checkMarketingConsent?.isChecked ?: false
        val newsletterConsent: Boolean = checkNewsletterConsent?.isChecked ?: false

        val dataToSend = FormModel(
            email,
            password,
            subscriptionPlan,
            marketingConsent,
            newsletterConsent,
        )

        val intent = Intent(this, ProfileDetailsActivity::class.java)
        intent.putExtra(ProfileDetailsActivity.PROFILE_KEY, dataToSend)
        startActivity(intent)
    }

    private fun validate(): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        var isValid: Boolean = true
        val email: String = inputEmail?.text.toString()
        if (!email.matches(emailRegex.toRegex())) {
            isValid = false
            inputEmailLayout?.error = getString(R.string.validation_email_error)
        }

        val password: String = inputPassword?.text.toString()
        if (password.length < 6) {
            isValid = false
            inputPasswordLayout?.error = getString(R.string.validation_password_error)
        }

        val dataConsent: Boolean = checkMarketingConsent?.isChecked ?: false
        if (!dataConsent) {
            isValid = false
            AlertDialog.Builder(this)
                .setMessage(R.string.validation_marketing_consent_error)
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false)
                .show()
        }

        return isValid
    }
}