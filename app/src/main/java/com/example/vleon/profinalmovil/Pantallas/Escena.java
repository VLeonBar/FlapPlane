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
 * La clase Escena, de la que heredan todas las demás escenas/pantallas.
 * @author Victor Leon Barciela
 */
public class Escena {

    /**
     * El Contexto.
     */
    Context contexto;
    /**
     * La Id escena.
     */
    public int idEscena;
    /**
     * El Alto pantalla.
     */
    int altoPantalla, /**
     * El Ancho pantalla.
     */
    anchoPantalla;
    /**
     * La Imagen de fondo.
     */
    Bitmap imgFondo, /**
     * La imagen del botón de vuelta atras.
     */
    vueltaAtras;
    /**
     * El boton de vuelta atras.
     */
    Boton btnAtras;
    /**
     * El Pincel.
     */
    Paint pincel;
    /**
     * El objeto manejador FrameHandler.
     */
    FrameHandler fh;
    /**
     * El objeto manejador de Sonidos.
     */
    static Sonidos sonidos;
    /**
     * El Vibrador.
     */
    Vibrator vibrator;
    /**
     * Tipología 1.
     */
    Typeface typeface1, /**
     * Tipología 2.
     */
    typeface2;
    /**
     * El manejador de sensores.
     */
    static SensorManager sm;
    /**
     * El sensor de proximidad.
     */
    static Sensor proxSensor;
    /**
     * Las SharedPreferences.
     */
    SharedPreferences preferencias;
    /**
     * Editor de SharedPreferences.
     */
    SharedPreferences.Editor editorPreferencias;
    /**
     * Booleana que indica el estado del sensor de proximidad.
     */
    static boolean isSensorOn = false, /**
     * Booleana que indica el estado del sonido.
     */
    isSoundOn, /**
     * Booleana que indica el estado del vibrador.
     */
    isVibrationOn = true;


    /**
     * Instancia un nuevo objeto de la clase Escena.
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
     * Actualiza las físicas.
     *
     * @return int el id de la escena
     */
    public int actualizarFisica() {
        return idEscena;
    }

    /**
     * Se encarga de dibujar todos los elementos que se indiquen en el lienzo.
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
     * Evento OnTouch, se lanza cuando se toca la pantalla.
     *
     * @param event el evento
     * @return int el id de la escena.
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
     * Indica si se pulsa el boton indicado.
     *
     * @param boton el boton
     * @param event el evento
     * @return boolean
     */
    public boolean pulsa(Rect boton, MotionEvent event) {
        if (boton.contains((int) event.getX(), (int) event.getY())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve el contexto.
     *
     * @return contexto
     */
    public Context getContexto() {
        return contexto;
    }


    /**
     * Da un valor a contexto.
     *
     * @param contexto el contexto
     */
    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    /**
     * Devuelve alto pantalla.
     *
     * @return alto pantalla
     */
    public int getAltoPantalla() {
        return altoPantalla;
    }

    /**
     * Da un valor a alto pantalla.
     *
     * @param altoPantalla alto pantalla
     */
    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    /**
     * Devuelve ancho pantalla.
     *
     * @return ancho pantalla
     */
    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    /**
     * Da un valor a ancho pantalla.
     *
     * @param anchoPantalla ancho pantalla
     */
    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

}
