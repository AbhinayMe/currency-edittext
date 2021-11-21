package me.abhinay;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by AbhinayMe on 01/01/2019.
 */

public class CurrencyEditText extends AppCompatEditText {

    private String current = "";
    private CurrencyEditText editText = CurrencyEditText.this;

    private Context mContext;

    //properties
    private String currency = "";
    private String separator = ",";
    private Boolean spacing = false;
    private Boolean delimiter = false;
    private Boolean decimals = true;

    public CurrencyEditText(Context context) {
        super(context);
        init(context, null);
    }

    public CurrencyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context, attrs, null, null, null, null, null);
    }

    private void init(Context context, AttributeSet attrs, String currency, String separator, Boolean spacing, Boolean delimiter, Boolean decimals) {
        this.mContext = context;
        this.currency = currency;
        this.separator = separator;
        this.spacing = spacing;
        this.delimiter = delimiter;
        this.decimals = decimals;

        initByAttributes(context, attrs);
    }

    private void initByAttributes(Context context, AttributeSet attrs) {
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().equals(current)) {
                    editText.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "").replaceAll(currency, "").replaceAll("\\s+", "");

                    if (cleanString.length() != 0) {
                        try {

                            String currencyFormat = "";
                            if (spacing) {
                                if (delimiter) {
                                    currencyFormat = currency + ". ";
                                } else {
                                    currencyFormat = currency + " ";
                                }
                            } else {
                                if (delimiter) {
                                    currencyFormat = currency + ".";
                                } else {
                                    currencyFormat = currency;
                                }
                            }

                            double parsed;
                            int parsedInt;
                            String formatted;

                            if (decimals) {
                                parsed = Double.parseDouble(cleanString);
                                formatted = NumberFormat.getCurrencyInstance().format((parsed / 100)).replace(NumberFormat.getCurrencyInstance().getCurrency().getSymbol(), currencyFormat);
                            } else {
                                parsedInt = Integer.parseInt(cleanString);
                                formatted = currencyFormat + NumberFormat.getNumberInstance(Locale.getDefault()).format(parsedInt);
                            }

                            current = formatted;

                            //if decimals are turned off and separator is set as anything other than commas..
                            if (!separator.equals(",") && !decimals) {
                                //..replace the commas with the new separator
                                editText.setText(formatted.replaceAll(",", separator));
                            } else {
                                //since no custom separators were set, proceed with comma separation
                                editText.setText(formatted);
                            }
                            editText.setSelection(formatted.length());
                        } catch (NumberFormatException e) {

                        }
                    }
                    editText.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /*
     *
     */
    public double getCleanDoubleValue() {
        double value = 0.0;
        if (decimals) {
            value = Double.parseDouble(editText.getText().toString().trim().replaceAll("[$,]", "").replaceAll(currency, ""));
        } else {
            String cleanString = editText.getText().toString().trim().replaceAll("[$,.]", "").replaceAll(currency, "").replaceAll("\\s+", "");
            try {
                value = Double.parseDouble(cleanString);
            } catch (NumberFormatException e) {

            }
        }
        return value;
    }

    public int getCleanIntValue() {
        int value = 0;
        if (decimals) {
            double doubleValue = Double.parseDouble(editText.getText().toString().trim().replaceAll("[$,]", "").replaceAll(currency, ""));
            value = (int) Math.round(doubleValue);
        } else {
            String cleanString = editText.getText().toString().trim().replaceAll("[$,.]", "").replaceAll(currency, "").replaceAll("\\s+", "");
            try {
                value = Integer.parseInt(cleanString);
            } catch (NumberFormatException e) {
            }
        }
        return value;
    }

    public void setDecimals(boolean value) {
        this.decimals = value;
    }

    public void setCurrency(String currencySymbol) {
        this.currency = getCurrencySymbol(currencySymbol);
    }

    public void setSpacing(boolean value) {
        this.spacing = value;
    }

    public void setDelimiter(boolean value) {
        this.delimiter = value;
    }

    /**
     * separator allows a custom symbol to be used as the thousand separator. Default is set as comma (e.g: 20,000)
     * <p>
     * Custom separator cannot be set when decimals is set as `true`. Set decimals as `false` to continue setting up custom separator
     *
     * @value is the custom symbol sent in place of the default comma
     */
    public void setSeparator(String value) {
        this.separator = value;
    }

    public String getCurrencySymbol(String currency) {
        try {
            String sJSON = CurrencyUtils.getJsonFromAssets(mContext);
            JSONObject jsonObject = new JSONObject(sJSON);
            JSONObject jsonObjectCountry = jsonObject.getJSONObject(currency);
            String currencySymbol = jsonObjectCountry.getString("symbolNative");
            return currencySymbol;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
