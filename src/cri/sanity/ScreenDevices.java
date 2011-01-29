package cri.sanity;

import android.os.Bundle;


public class ScreenDevices extends ActivityScreen
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setEnabled(K.SKIP_MOBDATA, A.is(K.AUTO_MOBDATA) && !A.is(K.AUTO_GPS));
		setEnabled(K.SKIP_HOTSPOT, A.is(K.AUTO_MOBDATA) && Dev.isHotspotSupported());
		setEnabled(K.SKIP_TETHER , A.is(K.AUTO_MOBDATA) && Dev.isTetheringSupported());

		on(K.AUTO_MOBDATA, new Change(){ boolean on(){
			final boolean auto = (Boolean)value;
			setEnabled(K.SKIP_MOBDATA, auto && !A.is(K.AUTO_GPS));
			setEnabled(K.SKIP_HOTSPOT, auto && Dev.isHotspotSupported());
			setEnabled(K.SKIP_TETHER , auto && Dev.isTetheringSupported());
			return true;
		}});

		on(K.AUTO_GPS, new Change(){ boolean on(){
			final boolean auto = (Boolean)value;
			setEnabled(K.SKIP_MOBDATA, !auto && A.is(K.AUTO_MOBDATA));
			return true;
		}});
	}

}
