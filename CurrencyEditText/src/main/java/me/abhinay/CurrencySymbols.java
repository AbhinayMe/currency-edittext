package me.abhinay;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AbhinayMe on 01/01/2019.
 */

public class CurrencySymbols {

    Context mContext;

    public static String NONE = "";
    public static String MALAYSIA = "RM";
    public static String INDONESIA = "Rp";
    public static String SRILANKA = "Rs";
    public static String USA = "$";
    public static String UK = "£";
    public static String INDIA = "₹";
    public static String PHILIPPINES = "₱";
    public static String PAKISTAN = "₨";

    public CurrencySymbols(Context mContext) {
        this.mContext = mContext;
    }
}
