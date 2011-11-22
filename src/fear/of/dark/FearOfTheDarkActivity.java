package fear.of.dark;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class FearOfTheDarkActivity extends Activity {
    /** Called when the activity is first created. */
    private Button mOnBtn;
    private Button mOffBtn;
    
    private Camera mCamera;  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mOnBtn = (Button) findViewById(R.id.on_btn);
        mOnBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	
            	
                processOnClick();
                mOnBtn.setBackgroundDrawable(getResources().getDrawable( R.drawable.button_off));
                processOffClick();
               
            }
        });
     
        mOffBtn = (Button) findViewById(R.id.off_btn);
        mOffBtn.setEnabled(false);
        mOffBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                processOffClick();   
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        try{
            mCamera = Camera.open();
        } catch( Exception e ){
            Log.e("ERRO", "Impossivel ouvir a camera");
        }
    }
    @Override
    protected void onPause() {
        if( mCamera != null ){
            mCamera.release();
            mCamera = null;
        }
        super.onPause();
    }

    private void processOffClick(){
    	//mOnBtn.setEnabled(false);
        if( mCamera != null ){
            Parameters params = mCamera.getParameters();
            params.setFlashMode( Parameters.FLASH_MODE_OFF );
            mCamera.setParameters( params );
        }
    }

    private void processOnClick(){
        if( mCamera != null ){
            Parameters params = mCamera.getParameters();
            params.setFlashMode( Parameters.FLASH_MODE_TORCH );
            mCamera.setParameters( params );
            
        }
    }
}