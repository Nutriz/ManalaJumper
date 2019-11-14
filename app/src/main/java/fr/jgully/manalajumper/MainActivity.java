package fr.jgully.manalajumper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String HARD_MODE = "manalajumper.hardmode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlayActivity();
            }
        });

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void startPlayActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(HARD_MODE, isHardMode());
        startActivity(intent);
    }

    private boolean isHardMode() {
        AppCompatCheckBox hardModeCheckbox = findViewById(R.id.hard_mode_button);
        return hardModeCheckbox.isChecked();
    }
}
