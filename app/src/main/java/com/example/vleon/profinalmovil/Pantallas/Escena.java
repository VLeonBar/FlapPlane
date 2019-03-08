package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.Manejadores.Sonidos;
import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.R;

import static android.content.Context.SENSOR_SERVICE;

/**
 * The type Escena.
 */
public class Escena {

    /**
     * The Contexto.
     */
    Context contexto;
    /**
     * The Id escena.
     */
    public int idEscena;
    /**
     * The Alto pantalla.
     */
    int altoPantalla, /**
     * The Ancho pantalla.
     */
    anchoPantalla;
    /**
     * The Img fondo.
     */
    Bitmap imgFondo, /**
     * The Vuelta atras.
     */
    vueltaAtras;
    /**
     * The Btn atras.
     */
    Boton btnAtras;
    /**
     * The Pincel.
     */
    Paint pincel;
    /**
     * The Fh.
     */
    FrameHandler fh;
    /**
     * The Sonidos.
     */
    static Sonidos sonidos;
    /**
     * The Audio manager.
     */
    AudioManager audioManager;
    /**
     * The Vibrator.
     */
    Vibrator vibrator;
    /**
     * The Typeface 1.
     */
    Typeface typeface1, /**
     * The Typeface 2.
     */
    typeface2;
    /**
     * The Sm.
     */
    static SensorManager sm;
    /**
     * The Prox sensor.
     */
    static Sensor proxSensor;
    /**
     * The Preferencias.
     */
    SharedPreferences preferencias;
    /**
     * The Editor preferencias.
     */
    SharedPreferences.Editor editorPreferencias;
    /**
     * The Is sensor on.
     */
    static boolean isSensorOn = false, /**
     * The Is sound on.
     */
    isSoundOn, /**
     * The Is vibration on.
     */
    isVibrationOn = true;


    /**
     * Instancia la clase Escena.
     *
     * @param contexto      el contexto
     * @param idEscena      el id  de la escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public Escena(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        preferencias = contexto.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        editorPreferencias = preferencias.edit();
        String font1 = "fonts/font1.TTF", font2 = "fonts/font2.ttf";
        typeface1 = Typeface.createFromAsset(contexto.getAssets(), font1);
        typeface2 = Typeface.createFromAsset(contexto.getAssets(), font2);
        isSoundOn = preferencias.getBoolean("btnActivaSonido", true);
        isVibrationOn = preferencias.getBoolean("btnActivaVibra", true);
        this.contexto = contexto;
        this.idEscena = idEscena;
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        sm = (SensorManager) contexto.getSystemService(SENSOR_SERVICE);
        proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        fh = new FrameHandler(contexto);
        vibrator = (Vibrator) contexto.getSystemService(Context.VIBRATOR_SERVICE);
        if (sonidos == null) {
            sonidos = new Sonidos(contexto, 10);
        }
        pincel = new Paint();
        pincel.setTypeface(typeface1);
        pincel.setColor(Color.rgb(59, 36, 16));
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth((float) fh.getDpH(20, altoPantalla));
        btnAtras = new Boton(0, 0, anchoPantalla / 15, anchoPantalla / 15, Color.TRANSPARENT, typeface1);
        vueltaAtras = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.flecha_atras);
        vueltaAtras = Bitmap.createScaledBitmap(vueltaAtras, btnAtras.getRect().width(), btnAtras.getRect().height(), false);
        btnAtras.setImg(vueltaAtras);
    }

    /**
     * Listener del sensor de proximidad.
     */
    SensorEventListener proximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.i("VALUE", proxSensor.getMaximumRange() + "");
            if (sensorEvent.values[0] < proxSensor.getMaximumRange()) {
                isSensorOn = true;
            } else {
                isSensorOn = false;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /**
     * Actualizar físicas.
     *
     * @return the int
     */
    public int actualizarFisica() {
        return idEscena;
    }

    /**
     * Dibujar.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {
        try {
            if (idEscena != 0) {
                c.drawBitmap(vueltaAtras, btnAtras.getRect().left, btnAtras.getRect().top, null);
            }
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    /**
     * Vibrar.
     *
     * @param duraVibra la duración de la vibración
     */
    public void vibrar(int duraVibra) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(duraVibra);
        }
    }

    /**
     * On touch event int.
     *
     * @param event the event
     * @return the int
     */
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (isVibrationOn)
                    vibrar(100);
                if (isSoundOn)
                    sonidos.getEfectos().play(sonidos.sonidoToque, 1, 1, 1, 0, 1);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(btnAtras.getRect(), event) && idEscena != 0) return 0;
                break;
        }

        return idEscena;
    }

    /**
     * Pulsa boolean.
     *
     * @param boton the boton
     * @param event the event
     * @return the boolean
     */
    public boolean pulsa(Rect boton, MotionEvent event) {
        if (boton.contains((int) event.getX(), (int) event.getY())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve contexto.
     *
     * @return the contexto
     */
    public Context getContexto() {
        return contexto;
    }

    /**
     * Devuelve typeface 1.
     *
     * @return the typeface 1
     */
    public Typeface getTypeface1() {
        return typeface1;
    }

    /**
     * Da un valor a contexto.
     *
     * @param contexto the contexto
     */
    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    /**
     * Devuelve id escena.
     *
     * @return the id escena
     */
    public int getIdEscena() {
        return idEscena;
    }

    /**
     * Da un valor a id escena.
     *
     * @param idEscena the id escena
     */
    public void setIdEscena(int idEscena) {
        this.idEscena = idEscena;
    }

    /**
     * Devuelve alto pantalla.
     *
     * @return the alto pantalla
     */
    public int getAltoPantalla() {
        return altoPantalla;
    }

    /**
     * Da un valor a alto pantalla.
     *
     * @param altoPantalla the alto pantalla
     */
    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    /**
     * Devuelve ancho pantalla.
     *
     * @return the ancho pantalla
     */
    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    /**
     * Da un valor a ancho pantalla.
     *
     * @param anchoPantalla the ancho pantalla
     */
    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    /**
     * Devuelve img fondo.
     *
     * @return the img fondo
     */
    public Bitmap getImgFondo() {
        return imgFondo;
    }

    /**
     * Da un valor a img fondo.
     *
     * @param imgFondo the img fondo
     */
    public void setImgFondo(Bitmap imgFondo) {
        this.imgFondo = imgFondo;
    }

    /**
     * Devuelve pincel.
     *
     * @return the pincel
     */
    public Paint getPincel() {
        return pincel;
    }

    /**
     * Da un valor a pincel.
     *
     * @param pincel the pincel
     */
    public void setPincel(Paint pincel) {
        this.pincel = pincel;
    }
}
