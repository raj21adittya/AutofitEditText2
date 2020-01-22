package com.example.autofitedittext2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    private AutoResizeEditText mAutoResizeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutoResizeEditText = (AutoResizeEditText) findViewById(R.id.rET);


        mAutoResizeEditText = (AutoResizeEditText) findViewById(R.id.rET);
        mAutoResizeEditText.setEnabled(true);
        mAutoResizeEditText.setFocusableInTouchMode(true);
        mAutoResizeEditText.setFocusable(true);
        mAutoResizeEditText.setEnableSizeCache(false);
        mAutoResizeEditText.setMovementMethod(null);
        mAutoResizeEditText.setMaxHeight(330);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUI(findViewById(R.id.rlRoot), mAutoResizeEditText);
    }
    public void setupUI(View view, final AutoResizeEditText aText) {

        if (!(view instanceof AutoResizeEditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();

                    Log.d("TXTS",
                            "Text Size = "
                                    + aText.getTextSize());
                    if (aText.getTextSize() < 16f/*50f*/) {
                        aText.setText(aText.getText().toString().replaceAll("(?m)^[ \t]*\r?\n", ""));
                    }

                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, aText);
            }
        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null
                && this.getCurrentFocus().getWindowToken() != null)
            inputMethodManager.hideSoftInputFromWindow(this
                    .getCurrentFocus().getWindowToken(), 0);
    }
}
