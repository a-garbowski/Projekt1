package artur.projekt1.model

import java.io.Serializable

enum class SubscriptionType {
    Free, Premium
}

data class FormModel(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val accType: SubscriptionType,
    val marketingConsent: Boolean,
    val newsletterConsent: Boolean,
) : Serializable {
    override fun toString(): String {
        val displayedPassword = "".padStart(password.length, '*')

        return StringBuilder("")
            .append("E-mail: ").append(email).append("\n")
            .append("Password: ").append(displayedPassword).append("\n")
            .append("Subscription type: ").append(accType).append("\n")
            .append("Newsletter enabled: ").append(newsletterConsent)
            .toString()
    }
}
