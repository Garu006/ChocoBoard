package com.chocoboard.service;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import com.chocoboard.R;

public class ChocoInputService extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        // Esto busca el archivo keyboard_view.xml que creamos en layout
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);

        // Esto carga las letras de qwerty.xml
        keyboard = new Keyboard(this, R.xml.qwerty);

        // Aquí es donde sucede la magia: unimos las letras con la vista
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);

        return keyboardView;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        keyboardView.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
        InputConnection ic = getCurrentInputConnection();
        if (primaryCode == -2) {
            return;
        }
        if (ic != null) {
            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE:
                    ic.deleteSurroundingText(1, 0);
                    break;
                case Keyboard.KEYCODE_SHIFT:
                    // por ahora solo notificamos, la logica de mayusculas es mas avanzada
                    break;
                case Keyboard.KEYCODE_DONE:
                case 10: // Codigo para Enter
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    break;
                default:
                    char code = (char) primaryCode;
                    ic.commitText(String.valueOf(code), 1);
            }
        }
    }

    // Estos métodos son obligatorios por el "implements".
    @Override public void onPress(int primaryCode) {}
    @Override public void onRelease(int primaryCode) {}
    @Override public void onText(CharSequence text) {}
    @Override public void swipeLeft() {}
    @Override public void swipeRight() {}
    @Override public void swipeDown() {}
    @Override public void swipeUp() {}
}