package com.example.vleon.profinalmovil;

import android.graphics.Point;
import android.graphics.PointF;

public class Circulo {

    private float posX, posY, radio;

    public Circulo(float posX, float posY, float radio) {
        this.posX = posX;
        this.posY = posY;
        this.radio = radio;
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

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }
}
