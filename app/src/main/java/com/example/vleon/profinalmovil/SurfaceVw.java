package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceVw extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Context contexto;
    private Hilo hilo;
    private boolean funcionando;
    private int altoPantalla, anchoPantalla;
    private Escena escenaActual;
    private GestureDetector detectorDeGestos = new GestureDetector(new DetectorDeGestos());

    public SurfaceVw(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.contexto = context;
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
                        Log.i("patata", "asfdasdfasf");
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
            while (funcionando) {
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
            }
        }

        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

        public void setSurfaceSize(int width, int height) { // Función llamada si cambia el tamaño del view

        }
    }
}
