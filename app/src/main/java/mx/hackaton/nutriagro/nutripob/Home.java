package mx.hackaton.nutriagro.nutripob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private CardView cerdoCard, borregoCard, vacasCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cerdoCard =  findViewById(R.id.cerdoCard);
        borregoCard = findViewById(R.id.borregoCard);
        vacasCard = findViewById(R.id.vacaCard);

        cerdoCard.setOnClickListener(this);
        borregoCard.setOnClickListener(this);
        vacasCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i;
        switch(v.getId()){
            case R.id.cerdoCard:
                i = new Intent(this, Porcino.class);
                startActivity(i);
                break;
            case R.id.borregoCard:
                i = new Intent(this, Ovino.class);
                startActivity(i);
                break;
            case R.id.vacaCard:
                i = new Intent(this, Bovino.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
