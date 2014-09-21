package com.prog101.magicbutton;


import java.util.Calendar;
import java.util.Timer;

import javax.xml.transform.Templates;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	final int NBRBUTTONS = 16;
	public int dernierBoutton = -1;
	public int compteur;
	final int NBRBUTTONAPESER = 10;
	final long LIMIT = 15000;
	public Calendar debut;
	public Button Go;
	public RelativeLayout background;
	public TextView textCompteur;
	
	Button liste [] = new Button[NBRBUTTONS]; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Go = (Button)findViewById(R.id.buttonGo);
		background = (RelativeLayout)findViewById(R.id.background);
		textCompteur = (TextView)findViewById(R.id.compteur);
		initialiserListe();
		initialiserbutton();
	}
	
	private void initialiserListe()
	{
		for (int i = 0 ; i < NBRBUTTONS ;++i )
		{
			String buttonID = "button" + i;
		    int resID = getResources().getIdentifier(buttonID, "id", "com.prog101.magicbutton");
			liste[i] = (Button)findViewById(resID);
		}
	}
	
	private void initialiserbutton()
	{
		for(Button b : liste){
			b.setVisibility(View.INVISIBLE);
		}
	}
	
	//set on click...  todo  qui appelle randomiser...
	
	//randomiser no boutton
	public int randomiser(){
		int nouveau = -1;
		do {
			nouveau = (int)(Math.random() * NBRBUTTONS);			
		}while(nouveau == dernierBoutton);
		return nouveau;
	}
	
	//sur click de go:
	public void demarrerPartie(View vue){
		//start timer
		debut =  Calendar.getInstance();
		compteur = NBRBUTTONAPESER;
		vue.setEnabled(false);
		afficherBoutton();
		
				
	}
	
	public void clickBoutton(View vue){
		// valider si timeout...
		vue.setVisibility(View.INVISIBLE);
		if(!tempsEcoule() && compteur > 0)
		{
			--compteur;
			textCompteur.setText(compteur + "");
			afficherBoutton();
		}
		else
		{
			Go.setEnabled(true);
			textCompteur.setText(NBRBUTTONAPESER + "");
			if ( tempsEcoule())
			{
				Toast.makeText(this, "GameOver", Toast.LENGTH_LONG).show();
				background.setBackgroundResource(R.drawable.marche);
			}
			else
			{
				Toast.makeText(this, "Vous avez gagner, vous passez au niveau 2", Toast.LENGTH_LONG).show();
				background.setBackgroundResource(R.drawable.marche2);
				
			}
		}
	}
	public boolean tempsEcoule(){
		Calendar actuel = Calendar.getInstance();			
		return (actuel.getTimeInMillis() - debut.getTimeInMillis()) > LIMIT;
	}
	
	private void afficherBoutton()
	{
		int nouveau = randomiser();
		dernierBoutton = nouveau;
		liste[nouveau].setVisibility(View.VISIBLE);
	}
	
	
	
}
