import java.applet.Applet;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.36
// 

public class Som
{
    public static final AudioClip BALL;
    public static final AudioClip GAMEOVER;
    public static final AudioClip BACK;
    
    static {
        BALL = Applet.newAudioClip(Som.class.getResource("ball.wav"));
        GAMEOVER = Applet.newAudioClip(Som.class.getResource("gameover.wav"));
        BACK = Applet.newAudioClip(Som.class.getResource("back.wav"));
    }
}
