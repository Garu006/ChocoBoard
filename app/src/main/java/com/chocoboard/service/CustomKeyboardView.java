package com.chocoboard.service;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import androidx.core.content.res.ResourcesCompat;
import com.chocoboard.R;
import java.util.List;

public class CustomKeyboardView extends KeyboardView {

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // Primero dibujamos el fondo estándar del teclado
//        super.onDraw(canvas);

        // Cargamos la fuente con cuidado
        Typeface fuenteChoco = ResourcesCompat.getFont(getContext(), R.font.chewy);

        // Establece el color del fondo del teclado, el primer argumento es la opacidad y el color, el segundo es el modo
        canvas.drawColor(0x44000000, PorterDuff.Mode.SRC);

        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(fuenteChoco); // Usamos el nombre correcto
        paint.setColor(getContext().getColor(R.color.choco_text));
        paint.setTextSize(38f);

        // Dibujamos nuestras letras personalizadas
        if (getKeyboard() != null) {
            List<Keyboard.Key> keys = getKeyboard().getKeys();
            for (Keyboard.Key key : keys) {
                if (key.label != null) {
                    float centerX = key.x + (key.width / 2f);
                    float centerY = key.y + (key.height / 2f) + (paint.getTextSize() / 3f);
                    canvas.drawText(key.label.toString(), centerX, centerY, paint);
                }
            }
        }
    }
}