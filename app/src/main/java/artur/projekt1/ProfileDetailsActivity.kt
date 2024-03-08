package artur.projekt1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val logTag = "Arturo - ProfileDetailsActivity"

class ProfileDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(logTag, "onCreate")
        setContentView(R.layout.activity_profile_details)
    }

    override fun onStart() {
        super.onStart()
        Log.i(logTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(logTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(logTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(logTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(logTag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(logTag, "onRestart")
    }
}