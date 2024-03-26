package me.abhinay.input.sample.kotlin

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.abhinay.CurrencySymbols
import me.abhinay.input.sample.kotlin.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mActivity: Activity
    private lateinit var mContext: Context
    private lateinit var viewBinding: ActivityMainBinding

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        mActivity = this
        mContext = this

        viewBinding.etInput.setCurrency(CurrencySymbols.USA)
        viewBinding.etInput.setDelimiter(false)
        viewBinding.etInput.setSpacing(false)
        viewBinding.etInput.setDecimals(true)
        viewBinding.etInput.setSeparator(".")

        viewBinding.btnProcess.setOnClickListener {
            if (viewBinding.etInput.length() != 0) {
                val cleanDoubleOutput = viewBinding.etInput.cleanDoubleValue
                val cleanIntOutput = viewBinding.etInput.cleanIntValue

                writeLog("Clean Double  : " + cleanDoubleOutput)
                writeLog("Clean Integer : " + cleanIntOutput)
            } else {
                Toast.makeText(mContext, "Input cannot be empty.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writeLog(msg: String) {
        runOnUiThread {
            val now = Date()
            viewBinding.tvLogArea.append(
                SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SS",
                    Locale("en", "MY")
                ).format(now) + "\n" + msg + "\n\n"
            )
            Log.d(
                TAG,
                "writeLog: " + SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SS",
                    Locale("en", "MY")
                ).format(now) + "\n" + msg + "\n\n"
            )
        }
    }
}