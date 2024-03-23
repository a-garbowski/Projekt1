package artur.projekt1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import artur.projekt1.model.FormModel
import artur.projekt1.screens.FormFragment
import artur.projekt1.screens.ProfileDetailsFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, FormFragment())
                .commit()
        }
    }

    fun notifyingForm(data: FormModel) {
        Timber.i("notifyingForm $data")

        val fragment = ProfileDetailsFragment()
        val arguments = Bundle()
        arguments.putSerializable(ProfileDetailsFragment.PROFILE_KEY, data)
        fragment.setArguments(arguments)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}