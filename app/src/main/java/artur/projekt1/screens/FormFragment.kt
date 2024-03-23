package artur.projekt1.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import artur.projekt1.MainActivity
import artur.projekt1.R
import artur.projekt1.model.FormModel
import artur.projekt1.model.SubscriptionType
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

class FormFragment : Fragment() {

    private var inputEmail: TextInputEditText? = null
    private var inputPassword: TextInputEditText? = null
    private var inputPhoneNumber: TextInputEditText? = null
    private var checkAccType: RadioGroup? = null
    private var checkMarketingConsent: CheckBox? = null
    private var checkNewsletterConsent: CheckBox? = null
    private var registerButton: Button? = null
    private var inputEmailLayout: TextInputLayout? = null
    private var inputPasswordLayout: TextInputLayout? = null
    private var inputPhoneNumberLayout: TextInputLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createdView: View = inflater.inflate(R.layout.fragment_form, container, false)
        Timber.i("Container ID: ${container?.id}")
        return createdView
    }

    override fun onViewCreated(createdView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(createdView, savedInstanceState)
        setupViews(createdView)
    }

    private fun setupViews(createdView: View) {
        inputEmail = createdView.findViewById(R.id.input_email)
        inputEmail?.doAfterTextChanged {
            inputEmailLayout?.error = ""
        }
        inputPassword = createdView.findViewById(R.id.input_password)
        inputPassword?.doAfterTextChanged {
            inputPasswordLayout?.error = ""
        }
        inputPhoneNumber = createdView.findViewById(R.id.input_phone_number)
        inputPhoneNumber?.doAfterTextChanged {
            inputPhoneNumberLayout?.error = ""
        }
        checkAccType = createdView.findViewById(R.id.check_acc_type)
        checkMarketingConsent = createdView.findViewById(R.id.check_marketing_consent)
        checkNewsletterConsent = createdView.findViewById(R.id.check_newsletter_consent)
        registerButton = createdView.findViewById(R.id.button)
        inputEmailLayout = createdView.findViewById(R.id.textInputLayoutEmail)
        inputPasswordLayout = createdView.findViewById(R.id.textInputLayoutPassword)
        inputPhoneNumberLayout = createdView.findViewById(R.id.textInputLayoutPhoneNumber)
        registerButton?.setOnClickListener {
            if (validate()) {
                sendForm()
            }
        }
    }

    private fun sendForm() {
        val email: String = inputEmail?.text.toString()
        val password: String = inputPassword?.text.toString()
        val phoneNumber: String = inputPhoneNumber?.text.toString()
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
            phoneNumber,
            subscriptionPlan,
            marketingConsent,
            newsletterConsent,
        )
        (activity as MainActivity).notifyingForm(dataToSend)
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

        val phoneNumber: String = inputPhoneNumber?.text.toString()
        if (phoneNumber.length < 9) {
            isValid = false
            inputPhoneNumberLayout?.error = getString(R.string.validation_phone_number_error)
        }

        val dataConsent: Boolean = checkMarketingConsent?.isChecked ?: false
        if (!dataConsent) {
            isValid = false
            AlertDialog.Builder(requireContext())
                .setMessage(R.string.validation_marketing_consent_error)
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false)
                .show()
        }

        return isValid
    }

}