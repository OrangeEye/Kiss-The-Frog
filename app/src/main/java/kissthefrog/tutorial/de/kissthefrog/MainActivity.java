package kissthefrog.tutorial.de.kissthefrog;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private int points;
    private int round;
    private int countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView legt fest, welches Layout in der Activity dargestellt werden soll. R (Ressourcen) ist
        eine Klasse in der jede Ressourcen einer Konstanten zugeordnet sind. Dadurch ist sichergestellt,
        dass die richtigen Ressourcen geladen werden. Die Funktion sollte immer mit als erstes aufgerufen werden.
         */
        setContentView(R.layout.activity_main);
        newGame();
        showStartFragment();
    }

    private void showStartFragment() {
        ViewGroup container = findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_start, null));
        container.findViewById(R.id.start).setOnClickListener(this);
    }

    private void showGame() {
        ViewGroup container = findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.activity_main, null));

        WimmelView wv = new WimmelView(this);
        //Damit höhe und breite ausgefuellt werden, 2 zusaetzliche parameter
        container.addView(wv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        wv.setImageCount(1 * (10 + round));
        update();
    }

    private void showGameOverFragment() {

        ViewGroup container = findViewById(R.id.container);
        container.addView(getLayoutInflater().inflate(R.layout.fragment_game_over, null));
        container.findViewById(R.id.play_again).setOnClickListener(this);
    }

    private void newGame() {
        points = 0;
        round = 1;
        initRound();
    }

    private void initRound() {
        countdown = 10;
        showGame();
    }

    private void fillTextView(int id, String txt) {
        TextView tv = findViewById(id);
        tv.setText(txt);
    }

    private void update() {
        fillTextView(R.id.points, Integer.toString(this.points));
        fillTextView(R.id.round, Integer.toString(this.round));
        fillTextView(R.id.countdown, Integer.toString(this.countdown));
    }

    private void startGame() {
        newGame();
    }

    @Override
    public void onClick(View v) {
        System.out.println(v.getId());
        if (v.getId() == R.id.start)
            startGame();
        else if (v.getId() == R.id.play_again) showStartFragment();
    }
}
