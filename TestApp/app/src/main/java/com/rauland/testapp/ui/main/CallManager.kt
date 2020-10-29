package com.rauland.testapp.ui.main

import android.content.Context
import android.media.AudioManager
import androidx.core.content.ContextCompat.getSystemService
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetViewListener
import java.net.URL

class CallManager(private val context: Context) : JitsiMeetViewListener {

    companion object{
        private const val serverUrl: String = "https://meet.jit.si"
    }

    private var callView: AudioOnlyView? = null

    public fun startAudioSession(roomId: String){
        val options: JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder()
            .setAudioMuted(false)
            .setAudioOnly(true)
            .setRoom(roomId)
            .setVideoMuted(true)
            .setWelcomePageEnabled(false)
            .setServerURL(URL(serverUrl))
            .build()
        callView = AudioOnlyView(context)
        callView!!.listener = this
        callView!!.join(options)
        callView!!.isSoundEffectsEnabled = false
    }

    public fun stopAudioSession(){
        val audioManager =  context.getSystemService(Context.AUDIO_SERVICE) as AudioManager;
        audioManager.mode = AudioManager.MODE_IN_CALL;
        audioManager.isSpeakerphoneOn = false;
        callView!!.leave()
    }

    override fun onConferenceJoined(p0: MutableMap<String, Any>?) {
        val audioManager =  context.getSystemService(Context.AUDIO_SERVICE) as AudioManager;
        audioManager.isSpeakerphoneOn = false;
        audioManager.mode = AudioManager.MODE_RINGTONE
    }

    override fun onConferenceTerminated(p0: MutableMap<String, Any>?) {

    }

    override fun onConferenceWillJoin(p0: MutableMap<String, Any>?) {
    }
}