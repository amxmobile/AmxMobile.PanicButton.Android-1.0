package com.amxmobile.PanicButton;

import com.amxmobile.PanicButton.Runtime.MessageBox;
import com.amxmobile.PanicButton.Services.PanicService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PanicForm extends Activity implements OnClickListener 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setTitle("Panic");
        setContentView(R.layout.main);
        
        // subscribe...
        getArmButton().setOnClickListener(this);
        getPanicButton().setOnClickListener(this);
    }

	private Button getArmButton() 
	{
		return (Button)findViewById(R.id.buttonArm);
	}

	private Button getPanicButton() 
	{
		return (Button)findViewById(R.id.buttonPanic);
	}

	@Override
	public void onClick(View view) 
	{
		if(view.getId() == R.id.buttonArm)
			HandleArm();
		else if(view.getId() == R.id.buttonPanic)
			HandlePanic();
	}

	private void HandlePanic() 
	{
		try
		{
			PanicService service = new PanicService();
			service.StartPanicking();
			
			// ok...
			MessageBox.Show(this, "You are now panicking.");
		}
		catch(Exception ex)
		{
			MessageBox.Show(this, ex);
		}
	}

	private void HandleArm() 
	{
		Button panic = getPanicButton();
		panic.setEnabled(true);
	}
}