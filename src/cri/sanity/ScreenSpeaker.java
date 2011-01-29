package cri.sanity;

import android.os.Bundle;


public class ScreenSpeaker extends ActivityScreen
{
	@Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setupProximity();
  }

	private void setupProximity()
	{
  	// setup preferences when proximity sensor exists or not
  	if(Dev.sensorProxim() != null) {
  		// disable "loud_speaker" when both "auto_speaker" and "speaker_call" are unchecked
    	on(K.SPEAKER_AUTO, new Click(){ boolean on(){
    		findPref(K.SPEAKER_LOUD).setEnabled(is(pref) || is(K.SPEAKER_CALL));
    		return false;
    	}});
    	on(K.SPEAKER_CALL, new Click(){ boolean on(){
    		findPref(K.SPEAKER_LOUD).setEnabled(is(pref) || is(K.SPEAKER_AUTO));
    		return false;
    	}});
  	}
  	else {
  		// if no proximity sensor found: disable all proximity options
  		setEnabled(K.SPEAKER_AUTO, false);
  		setChecked(K.SPEAKER_CALL, false);
  		findPref(K.SPEAKER_LOUD).setDependency(K.SPEAKER_CALL);
  	}
	}

}
