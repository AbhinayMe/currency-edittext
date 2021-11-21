package me.abhinay.input.sample.java;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.abhinay.input.sample.java.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    Activity mActivity;
    Context mContext;

    ActivityMainBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);

        mActivity = this;
        mContext = this;

//        writeLog(CurrencyEditText.getCurrencySymbol(mContext, "INR"));
        viewBinding.etInput.setCurrency("USD");

//        viewBinding.etInput.setCurrency(CurrencySymbols.USA);
        viewBinding.etInput.setDelimiter(false);
        viewBinding.etInput.setSpacing(false);
        viewBinding.etInput.setDecimals(true);
        viewBinding.etInput.setSeparator(".");

        viewBinding.btnProcess.setOnClickListener(view1 -> {
            if (viewBinding.etInput.length() != 0) {
                double cleanDoubleOutput = viewBinding.etInput.getCleanDoubleValue();
                int cleanIntOutput = viewBinding.etInput.getCleanIntValue();

                writeLog("Clean Double: " + cleanDoubleOutput);
                writeLog("Clean Integer: " + cleanIntOutput);
            } else {
                Toast.makeText(mContext, "Input cannot be empty.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeLog(String msg) {
        this.runOnUiThread(() -> {
            Date now = new Date();
            viewBinding.tvLogArea.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", new Locale("en", "MY")).format(now) + "\n" + msg + "\n\n");
            Log.d(TAG, "writeLog: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", new Locale("en", "MY")).format(now) + "\n" + msg + "\n\n");
        });
    }
}