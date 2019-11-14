package fr.jgully.manalajumper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        boolean isHardMode = getIntent().getBooleanExtra(MainActivity.HARD_MODE, false);

        MyCustomView myCustomView = findViewById(R.id.game_view);
        myCustomView.setHardMode(isHardMode);
    }
}
