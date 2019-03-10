package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.vleon.profinalmovil.Manejadores.Sonidos;

/**
 * La clase Surface View.
 *
 * @author Victor Leon Barciela
 */
public class SurfaceVw extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Context contexto;
    private Hilo hilo;
    /**
     * Objeto de la clase Sonidos.
     */
    public Sonidos sonidos;
    private boolean funcionando;
    private int altoPantalla, anchoPantalla;
    private Escena escenaActual;
    /**
     * El tiempo pasado.
     */
    long last, /**
     * El tiempo actual.
     */
    now;
    /**
     * El tiempo por frame.
     */
    int timeXFrame;
    /**
     * El maximo de frames.
     */
    int maxFrames;

    /**
     * Instancia un nuevo Surface view.
     *
     * @param context el context
     */
    public SurfaceVw(Context context) {
        super(context);
        now = System.currentTimeMillis();
        last = System.currentTimeMillis();
        maxFrames = 45;
        timeXFrame = 1000 / maxFrames;
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.contexto = context;
        sonidos = new Sonidos(contexto, 10);
        hilo = new Hilo();
        setFocusable(true);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        Log.i("ANCHO PANTALLA", " " + anchoPantalla / 10 * 5);
        Log.i("ALTO PANTALLA", " " + altoPantalla / 2);
        escenaActual = new MenuPrincipal(contexto, 0, anchoPantalla, altoPantalla);

        hilo.setFuncionando(true);
        if (hilo.getState() == Thread.State.NEW)
            hilo.start();
        if (hilo.getState() == Thread.State.TERMINATED) {
            hilo = new Hilo();
            hilo.start();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder) {
            int nuevaEscena = escenaActual.onTouchEvent(event);
            if (nuevaEscena != escenaActual.idEscena) {
                switch (nuevaEscena) {
                    case 0:
                        escenaActual = new MenuPrincipal(contexto, 0, anchoPantalla, altoPantalla);
                        break;
                    case 1:
                        escenaActual = new Creditos(contexto, 1, anchoPantalla, altoPantalla);
                        break;
                    case 2:
                        escenaActual = new Ajustes(contexto, 2, anchoPantalla, altoPantalla);
                        break;
                    case 3:
                        escenaActual = new Juego(contexto, 3, anchoPantalla, altoPantalla);
                        break;
                    case 4:
                        escenaActual = new Records(contexto, 4, anchoPantalla, altoPantalla);
                        break;
                    case 5:
                        escenaActual = new Ayuda(contexto, 5, anchoPantalla, altoPantalla);
                        break;
                }
            } else if (escenaActual.getClass() == Juego.class) {
                escenaActual.onTouchEvent(event);
            }

        }
        return true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hilo.setFuncionando(false);
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * La clase Hilo.
     */
    class Hilo extends Thread {
        /**
         * Instancia un nuevo objeto de la clase Hilo que hereda de Thread.
         */
        public Hilo() {
        }

        @Override
        public void run() {
            Looper.prepare();
            while (funcionando) {
                now = System.currentTimeMillis();
                if (now - last >= timeXFrame) {
                    last = now;
                    Canvas c = null;
                    try {
                        if (!surfaceHolder.getSurface().isValid())
                            continue;
                        c = surfaceHolder.lockCanvas();
                        synchronized (surfaceHolder) {

                            int novaEscena = escenaActual.actualizarFisica();
                            if (novaEscena != escenaActual.idEscena) {
                                switch (novaEscena) {
                                    case 0:
                                        escenaActual = new MenuPrincipal(contexto, 0, anchoPantalla, altoPantalla);
                                        break;
                                    case 1:
                                        escenaActual = new Creditos(contexto, 1, anchoPantalla, altoPantalla);
                                        break;
                                    case 2:
                                        escenaActual = new Ajustes(contexto, 2, anchoPantalla, altoPantalla);
                                        break;

                                    case 3:
                                        escenaActual = new Juego(contexto, 3, anchoPantalla, altoPantalla);
                                        break;

                                    case 4:
                                        escenaActual = new Records(contexto, 4, anchoPantalla, altoPantalla);
                                        break;
                                    case 5:
                                        escenaActual = new Ayuda(contexto, 5, anchoPantalla, altoPantalla);
                                        break;
                                    case 6:
                                        escenaActual = new FinDeJuego(contexto, 6, anchoPantalla, altoPantalla);
                                }
                            }
                            escenaActual.dibujar(c);
                        }
                    } finally {
                        if (c != null) {
                            surfaceHolder.unlockCanvasAndPost(c);
                        }
                    }
                } else {
                    try {
                        Thread.sleep((long) timeXFrame - (now - last));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.exit(0);
        }

        /**
         * Da un valor a funcionando.
         *
         * @param flag el valor.
         */
        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

    }
}
