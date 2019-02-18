package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.vleon.profinalmovil.Manejadores.Sonidos;

public class SurfaceVw extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Context contexto;
    private Hilo hilo;
    public Sonidos sonidos;
    private boolean funcionando;
    private int altoPantalla, anchoPantalla;
    private Escena escenaActual;
    // control de tiempo de la aplicación
    long last, now;
    int timeXFrame;
    int maxFrames;

    public SurfaceVw(Context context) {
        super(context);
        now = System.currentTimeMillis();
        last = System.currentTimeMillis();
        maxFrames = 45;                 // Número máximo de frames por segundo
        timeXFrame = 1000 / maxFrames;    // Tasa de tiempo para dibujar un frame
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.contexto = context;
        sonidos = new Sonidos(contexto, 10);
        hilo = new Hilo();
        setFocusable(true);// control de tiempo de la aplicación


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
                        escenaActual = new Controles(contexto, 5, anchoPantalla, altoPantalla);
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

    class Hilo extends Thread {
        public Hilo() {

        }

        @Override
        public void run() {
            Looper.prepare();
            while (funcionando) {
                now = System.currentTimeMillis();
                if (now - last >= timeXFrame) { // si ya paso el tiempo necesario, dibujo. Control de FramesxSegundo en funcion del tiempo
                    last = now;
                    Canvas c = null; //Necesario repintar _todo el lienzo
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
                                        escenaActual = new Controles(contexto, 5, anchoPantalla, altoPantalla);
                                        break;
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
                        Thread.sleep((long) timeXFrame - (now - last)); // duermo el tiempo necesario para el siguiente frame
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.exit(0);
        }

        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

        public void setSurfaceSize(int width, int height) { // Función llamada si cambia el tamaño del view

        }
    }
}
