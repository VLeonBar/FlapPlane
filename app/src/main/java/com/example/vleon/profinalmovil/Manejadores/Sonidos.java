package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.vleon.profinalmovil.R;

public class Sonidos {
    SoundPool efectos;
    public MediaPlayer mediaPlayer;
    int v;
    AudioManager audioManager;
    int maxSonidosSimultaneos = 10;
    boolean soundOn;
    public int sonidoToque, sonidoInsertCoin, sonidoExplosion, sonidoMotor;

    public SoundPool getEfectos() {
        return efectos;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setMaxSonidosSimultaneos(int maxSonidosSimultaneos) {
        this.maxSonidosSimultaneos = maxSonidosSimultaneos;
    }

    public int getMaxSonidosSimultaneos() {
        return maxSonidosSimultaneos;
    }

    public Sonidos(Context contexto, int maxSonidosSimultaneos) {
        this.maxSonidosSimultaneos = maxSonidosSimultaneos;
        audioManager = (AudioManager) contexto.getSystemService(Context.AUDIO_SERVICE);
        //cancion
        mediaPlayer = MediaPlayer.create(contexto, R.raw.dreamingaltitude);
        int v = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(v / 2, v / 2);
        mediaPlayer.setLooping(true);

        if ((android.os.Build.VERSION.SDK_INT) >= 21) {
            SoundPool.Builder spb = new SoundPool.Builder();
            spb.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build());
            spb.setMaxStreams(maxSonidosSimultaneos);
            this.efectos = spb.build();
        } else {
            this.efectos = new SoundPool(maxSonidosSimultaneos, AudioManager.STREAM_MUSIC, 0);
        }
        sonidoToque = efectos.load(contexto, R.raw.toquepantalla, 1);
        sonidoInsertCoin = efectos.load(contexto, R.raw.insertcoin, 1);
        sonidoExplosion = efectos.load(contexto, R.raw.ohnoyoudied, 1);
        sonidoMotor = efectos.load(contexto, R.raw.motoravion, 1);
    }
}
