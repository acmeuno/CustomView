package com.example.formador.customviewgroup;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by formador on 14/3/17.
 */

public class CounterView extends LinearLayout implements View.OnClickListener{


    /**
     * Clase para guardar la instancia de la view
     */
    private static class SavedState extends BaseSavedState {

        int counter; //this will store the current value from ValueBar

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            counter = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(counter);
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.counter = counter;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        counter = ss.counter;
        refreshCounter();
    }



    private static final int MIN = 0;
    private static final int MAX = 10;

    private Button buttonIncrease, buttonDecrease;
    private TextView textViewCounter;

    private int counter = MIN;

    public CounterView(Context context) {
        super(context);
        init();
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){

        //Las vistas (por defecto) no guardan el estado
        setSaveEnabled(true);

        setOrientation(LinearLayout.VERTICAL);

        inflate(getContext(), R.layout.view_counter, this);

        doBindings();

        refreshCounter();

        buttonIncrease.setOnClickListener(this);
        buttonDecrease.setOnClickListener(this);
    }

    private void doBindings() {

        textViewCounter = (TextView) findViewById(R.id.text_view_counter);
        buttonIncrease = (Button) findViewById(R.id.button_increase);
        buttonDecrease = (Button) findViewById(R.id.button_decrease);

    }

    private void refreshCounter(){
        textViewCounter.setText(String.valueOf(counter));

    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_increase:
                if (counter<MAX) {
                    counter++;
                    refreshCounter();
                }
                break;
            case R.id.button_decrease:
                if (counter>MIN) {
                    counter--;
                    refreshCounter();
                }
                break;
        }
    }

}
