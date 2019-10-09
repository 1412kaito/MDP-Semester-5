//package edu.stts.p06;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
//import android.text.Editable;
//import android.text.InputType;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.appcompat.widget.AppCompatEditText;
//import androidx.core.content.res.ResourcesCompat;
//
//
//public class MyEditText extends AppCompatEditText {
//    Drawable drawableClose;
//    public MyEditText(Context context) {
//        super(context);
//        init();
//    }
//
//    public MyEditText(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        setHint("Enter NRP: ");
//        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
//        setRawInputType(InputType.TYPE_CLASS_NUMBER);
//    }
//
//    private void init(){
//        drawableClose = ResourcesCompat.getDrawable(getResources(),
//                R.drawable.ic_close_red, null);
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (null != getCompoundDrawablesRelative()[2]){
//                    float clearButtonStart, clearButtonEnd;
//                    boolean isClearButtonClicked = false;
//                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL){
//                        clearButtonEnd = drawableClose.getIntrinsicWidth()+getPaddingStart();
//                        if(event.getX()<clearButtonEnd) isClearButtonClicked = true;
//                    } else {
//                        clearButtonStart =
//                                getWidth() - (drawableClose.getIntrinsicWidth()+getPaddingEnd());
//                        if(event.getX()>clearButtonStart) isClearButtonClicked = true;
//                    }
//                    if(isClearButtonClicked){
//                        if(event.getAction()==MotionEvent.ACTION_DOWN){
//                            showClearButton();
//                            return true;
//                        }
//                        else if(event.getAction()==MotionEvent.ACTION_UP){
//                            getText().clear();
//                            hideClearButton();
//                            return true;
//                        }
//                        return false;
//                    } else {
//                        return false;
//                    }
//                }
//                return false;
//            }
//        });
//
//        addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString().isEmpty()) hideClearButton();
//                else showClearButton();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//    }
//
//    private void showClearButton() {
//        setCompoundDrawablesRelative(null, null, drawableClose, null);
//    }
//    private void hideClearButton() {
//        setCompoundDrawablesRelative(null, null, null, null);
//    }
//}

package edu.stts.p06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class MyEditText extends AppCompatEditText {
    Drawable clearButtonImage;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Enter NRP");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
        setRawInputType(InputType.TYPE_CLASS_NUMBER);
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    @Override
    public boolean hasOnClickListeners() {
        return super.hasOnClickListeners();
    }

    private void init() {
        clearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_red, null);
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(getCompoundDrawablesRelative()[2] != null) {
//                    float clearButtonPos = getWidth() - clearButtonImage.getIntrinsicWidth() - getPaddingEnd();
//
//                    if(motionEvent.getX() > clearButtonPos) {
//                        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                            showClearbutton();
//                            return true;
//                        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                            if(getText() != null) {
//                                getText().clear();
//                            }
//                            hideClearbutton();
//                            return true;
//                        } else return false;
//                    }
//                }
//                return false;
//            }
//        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()) {
                    showClearbutton();
                } else hideClearbutton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void showClearbutton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clearButtonImage, null);
    }

    private void hideClearbutton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
