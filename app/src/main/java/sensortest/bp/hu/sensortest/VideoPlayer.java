package sensortest.bp.hu.sensortest;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by peter on 11/9/15.
 * It can not play MJPEG videstream. Check this:
 * https://bitbucket.org/neuralassembly/simplemjpegview/src/6a5cf5bd8f64/app/src/main/java/com/camera/simplemjpeg/?at=master
 */
public class VideoPlayer {
    private static final String TAG = SensorTest.class.getSimpleName();

    private final Context mContext;
    private String username="";
    private String password="";

    private final VideoView videoView;

    public VideoPlayer(Activity mContext) {
        this.mContext = mContext;
        String vidAddress = "http://" + username + ":" + password +"@192.168.1.126:81/video.cgi";
        Uri vidUri = Uri.parse(vidAddress);

        videoView = (VideoView)mContext.findViewById(R.id.videoView1);
        videoView.setVideoURI(vidUri);
    }

    public void start() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    public void stop() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
}
