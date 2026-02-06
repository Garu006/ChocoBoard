package com.chocoboard.service;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import androidx.core.content.res.ResourcesCompat;
import com.chocoboard.R;
import java.util.List;

public class CustomKeyboardView extends KeyboardView{
    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //Obtenemos la fuente personalizada "Chewy"
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.chewy);

        // se configura el pincel para dibujar las letras
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(typeface);
        paint.setTextSize(55); // este numero se puede ajustar si las letras se quieren mas grandes o pequeñas
        paint.setColor(getContext().getColor(R.color.choco_text));

        // dibujar el fondo del teclado original
        super.onDraw(canvas);

        // Dibuja las etiquetas de las teclas con la nueva fuente
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            if (key.label != null) {
                canvas.drawText(key.label.toString(), key.x + (key.width/2), key.y + (key.height/2) + 18, paint);
            }
        }
    }
}
