package com.example.vleon.profinalmovil;

import android.graphics.Bitmap;

public class Ficha {
    Bitmap imgFicha;
    float posX, posY;
    //Debe tener una hitbox determinada por un circulo.

    public Bitmap getImgFicha() {
        return imgFicha;
    }

    public void setImgFicha(Bitmap imgFicha) {
        this.imgFicha = imgFicha;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Ficha(int posX, int posY, Bitmap imgFicha) {
        this.imgFicha = imgFicha;
        this.posX = posX;
        this.posY = posY;

    }
}
