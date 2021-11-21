package me.abhinay.input.models;

import androidx.annotation.NonNull;

public class CurrencyModel {
    public static final String TAG = CurrencyModel.class.getSimpleName();

    String symbol;
    String name;
    String symbol_native;
    int decimal_digits;
    int rounding;
    String code;
    String name_plural;

    public static String getTAG() {
        return TAG;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol_native() {
        return symbol_native;
    }

    public void setSymbol_native(String symbol_native) {
        this.symbol_native = symbol_native;
    }

    public int getDecimal_digits() {
        return decimal_digits;
    }

    public void setDecimal_digits(int decimal_digits) {
        this.decimal_digits = decimal_digits;
    }

    public int getRounding() {
        return rounding;
    }

    public void setRounding(int rounding) {
        this.rounding = rounding;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName_plural() {
        return name_plural;
    }

    public void setName_plural(String name_plural) {
        this.name_plural = name_plural;
    }

    @NonNull
    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", symbol=" + symbol +
                ", symbol_native=" + symbol_native +
                ", decimal_digits=" + decimal_digits +
                ", rounding=" + rounding +
                ", code=" + code +
                ", name_plural=" + name_plural +
                '}';
    }
}
