package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.vleon.profinalmovil.R;

/**
 * The type Sonidos.
 */
public class Sonidos {
    /**
     * The Efectos.
     */
    SoundPool efectos;
    /**
     * The Media player.
     */
    public MediaPlayer mediaPlayer;
    /**
     * The V.
     */
    int v;
    /**
     * The Audio manager.
     */
    AudioManager audioManager;
    /**
     * The Max sonidos simultaneos.
     */
    int maxSonidosSimultaneos = 10;
    /**
     * The Sound on.
     */
    boolean soundOn;
    /**
     * The Sonido toque.
     */
    public int sonidoToque, /**
     * The Sonido insert coin.
     */
    sonidoInsertCoin, /**
     * The Sonido explosion.
     */
    sonidoExplosion, /**
     * The Sonido motor.
     */
    sonidoMotor;

    /**
     * Gets efectos.
     *
     * @return the efectos
     */
    public SoundPool getEfectos() {
        return efectos;
    }

    /**
     * Instantiates a new Sonidos.
     *
     * @param contexto              the contexto
     * @param maxSonidosSimultaneos the max sonidos simultaneos
     */
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
