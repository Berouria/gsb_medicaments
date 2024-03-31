package fr.be2.gsb_medicaments;

import static javax.net.ssl.SSLEngineResult.Status.OK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.SecureRandom;

public class authentification extends AppCompatActivity {
    private EditText editTextCodeV,myKey;
    private Button buttonValiderCodeV,btnValiderCle,btnDeconnexion;
    LinearLayout layoutCle;
    private static final String PREF_NAME = "UserPrefs";
    private static final String SECURETOKEN = "BethElicheva5";
    private static final String KEY_USER_STATUS = "userStatus";
    String myRandomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        editTextCodeV=findViewById(R.id.editTextCodeV);
        myKey=findViewById(R.id.myKey);
        layoutCle =findViewById(R.id.layoutCle);
        layoutCle.setVisibility(View.INVISIBLE);
          }
    public void AfficheLayout(View v){
        myRandomKey=genererChaineAleatoire(5);
        //Log.d( "APPLI","myKey"+myRandomKey );
        String codeV = editTextCodeV.getText().toString();

        // Vous pouvez maintenant utiliser la méthode sendKeyByEmail
        // avec le codeV, secureKey, et token comme paramètres
        String secureKey = myRandomKey;
        String token = SECURETOKEN;
        SendKeyTask sendKeyTask = new SendKeyTask(getApplicationContext());
        sendKeyTask.execute(codeV, secureKey, token);
        layoutCle.setVisibility(View.VISIBLE);

    }
    private String genererChaineAleatoire(int longueur) {
        String caracteresPermis = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder chaineAleatoire = new StringBuilder();

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < longueur; i++) {
            int index = random.nextInt(caracteresPermis.length());
            char caractereAleatoire = caracteresPermis.charAt(index);
            chaineAleatoire.append(caractereAleatoire);
        }

        return chaineAleatoire.toString();
    }
    public void Affiche2Layout(View v){
        String key = myKey.getText().toString().trim();
        if (key.equals(myRandomKey)) {
            showToast("Les chaînes A et B sont égales.");
            setUserStatus("authentification=OK");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            showToast("Les chaînes A et B ne sont pas égales.");
            setUserStatus("authentification=KO");
        }
    }
    private void showToast(String montexte){
        Toast.makeText(this, montexte, Toast.LENGTH_LONG).show();
    }
    private void setUserStatus(String status) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_STATUS, status);

        editor.apply();
    }

}