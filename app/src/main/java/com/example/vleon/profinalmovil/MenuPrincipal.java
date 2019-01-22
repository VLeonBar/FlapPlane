package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class MenuPrincipal extends Escena {
    Rect rectJugar, rectAjustes, rectRecord, rectCreditos, rectControles;
    Bitmap imgGradaIzq, imgGradaDch, imgLogo;
    int anchoDecimo, altoMedio, anchoTercio, altoSexto,anchoMedio;
    Ficha ficha;

    public MenuPrincipal(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, altoPantalla, anchoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondo_prov);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        //Dimensiones de corte de pantalla
        anchoDecimo = anchoPantalla / 10;
        anchoTercio = anchoPantalla / 3;
        anchoMedio = anchoPantalla / 2;
        altoMedio = altoPantalla / 2;
        altoSexto = altoMedio / 3;
        //Ficha
        ficha=new Ficha(0,0,new BitmapFactory().decodeResource(contexto.getResources(),R.drawable.bandera9));
        ficha.setImgFicha( Bitmap.createScaledBitmap(ficha.getImgFicha(),altoSexto/2,altoSexto/2,false));
        ficha.setPosX(anchoMedio-ficha.getImgFicha().getWidth()/2);
        ficha.setPosY(altoSexto*(float)5.5-ficha.getImgFicha().getHeight()/2);
        //  Gradas||Laterales && Logo central
        imgLogo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.icono);
        imgLogo = Bitmap.createScaledBitmap(imgLogo, anchoDecimo * 6, altoPantalla / 3, false);
        imgGradaDch = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.gradadch_main);
        imgGradaDch = Bitmap.createScaledBitmap(imgGradaDch, anchoDecimo, altoMedio, false);
        imgGradaIzq = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.gradaizq_main);
        imgGradaIzq = Bitmap.createScaledBitmap(imgGradaIzq, anchoDecimo, altoMedio, false);

        //Rectángulos - Botones

        rectCreditos = new Rect(0, 0, anchoTercio, altoSexto);
        rectAjustes = new Rect(anchoTercio * 2, 0, anchoTercio * 3, altoSexto);
        rectJugar = new Rect(0, altoSexto, anchoPantalla, altoSexto * 2);
        rectRecord = new Rect(0, altoSexto * 2, anchoTercio, altoSexto * 3);
        rectControles = new Rect(anchoTercio * 2, altoSexto * 2, anchoPantalla, altoSexto * 3);

    }

    //FÍSICAS Y DIBUJO DE LA CLASE MENÚ
    public void actualizarFisica() {

    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del menú
            c.drawBitmap(imgFondo, 0, 0, null);
            c.drawBitmap(imgLogo, anchoDecimo * 2, altoMedio, null);
            c.drawBitmap(ficha.getImgFicha(),ficha.getPosX(),ficha.getPosY(), null);
            c.drawBitmap(imgGradaDch, anchoDecimo * 9, altoMedio, null);
            c.drawBitmap(imgGradaIzq, 0, altoMedio, null);
            c.drawRect(rectCreditos, pincel);
            c.drawRect(rectAjustes, pincel);
            c.drawRect(rectJugar, pincel);
            c.drawRect(rectRecord, pincel);
            c.drawRect(rectControles, pincel);
            c.drawLine(0, 0, anchoPantalla, 0, pincel);
            c.drawLine(0, altoSexto * 3, anchoPantalla, altoSexto * 3, pincel);
            c.drawCircle(anchoMedio, altoSexto*(float)5.5, altoSexto/(float)2.5, pincel);


        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectCreditos, event)) return 1;
                else if (pulsa(rectAjustes, event)) return 2;
                else if (pulsa(rectJugar, event)) return 3;
                else if (pulsa(rectRecord, event)) return 4;
                else if (pulsa(rectControles, event)) return 5;

                break;
        }

        return idEscena;
    }

}
