package francois.fr.applipromob;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class PlaySound {

    private Context context;

    private int soundId;
    private SoundPool soundPool;
    private AudioManager audioManager;
    private float volume;

    private int idSon;

    private static final int MAX_STREAMS = 5;
    private static final int streamType = AudioManager.STREAM_MUSIC;

    public PlaySound(int idSon, Context context) {
        this.idSon = idSon;
        this.context = context;

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        float currentVolume = audioManager.getStreamVolume(streamType);
        float maxVolume = audioManager.getStreamMaxVolume(streamType);
        this.volume = currentVolume / maxVolume;

        if (Build.VERSION.SDK_INT >= 21) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttributes).setMaxStreams(MAX_STREAMS);
            this.soundPool = builder.build();
        } else {
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                soundPool.play(soundId, volume, volume, 1, 0, 1);
            }
        });

        this.soundId = this.soundPool.load(context, R.raw.victory, 1);
    }

}
