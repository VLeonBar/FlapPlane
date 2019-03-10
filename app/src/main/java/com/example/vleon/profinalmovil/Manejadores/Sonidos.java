package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.vleon.profinalmovil.R;

/**
 * La clase Sonidos es la cual se encarga de manejar la musica y los efectos de sonido.
 */
public class Sonidos {
    /**
     * Los Efectos.
     */
    SoundPool efectos;
    /**
     * El Media player.
     */
    public MediaPlayer mediaPlayer;
    /**
     * El Audio manager.
     */
    AudioManager audioManager;
    /**
     * El maximo de sonidos simultaneos.
     */
    int maxSonidosSimultaneos;
    /**
     * El Sonido de toque en la pantalla.
     */
    public int sonidoToque, /**
     * El Sonido de insertar una moneda.
     */
    sonidoInsertCoin, /**
     * El Sonido de toque de la explosión.
     */
    sonidoExplosion, /**
     * El Sonido del motor.
     */
    sonidoMotor;

    /**
     * Devuelve los efectos.
     *
     * @return los efectos
     */
    public SoundPool getEfectos() {
        return efectos;
    }

    /**
     * Instancia un nuevo objeto de la clase Sonidos.
     *
     * @param contexto              el contexto
     * @param maxSonidosSimultaneos el maximo de sonidos simultaneos
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
