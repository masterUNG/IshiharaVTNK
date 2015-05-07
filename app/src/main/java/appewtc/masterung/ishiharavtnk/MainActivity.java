package appewtc.masterung.ishiharavtnk;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView txtShowQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private int intRadio, intIndex;
    private MyModel objMyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //About MyModel
        aboutMyModel();

    }   //onCreate

    private void aboutMyModel() {

        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //Change View
                changeView(myModel.getIntButton());

            }
        });

    }

    private void changeView(int intButton) {



    }   // changeView

    private void radioController() {

        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //Sound Effect
                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                objMediaPlayer.start();

                //Setup intRadio
                switch (i) {
                    case R.id.radioButton:
                        intRadio = 1;
                        break;
                    case R.id.radioButton2:
                        intRadio = 2;
                        break;
                    case R.id.radioButton3:
                        intRadio = 3;
                        break;
                    case R.id.radioButton4:
                        intRadio = 4;
                        break;
                    default:
                        intRadio = 0;
                        break;
                }


            }
        });

    }   // radioController

    private void buttonController() {

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Sound Effect
                MediaPlayer btnSound = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                btnSound.start();

                //Check Zero
                checkZero();


            }
        });

    }   //buttonController

    private void checkZero() {

        if (intRadio == 0) {

            Toast.makeText(MainActivity.this, "Please Choose Answer", Toast.LENGTH_SHORT).show();

        } else {

            //Check Times
            checkTimes();

        }

    }

    private void checkTimes() {

        if (intIndex == 9) {

            //Intent to ShowScore
            intentToShowScore();

        } else {

            //Controller Call View
            txtShowQuestion.setText(Integer.toString(intIndex + 2) + ". What is this ?");

            //Increase
            intIndex += 1;

            //Controller Call Model
            objMyModel.setIntButton(intIndex);


        }

    }

    private void intentToShowScore() {

        Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
        startActivity(objIntent);
        finish();

    }

    private void bindWidget() {

        txtShowQuestion = (TextView) findViewById(R.id.textView2);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);

    }   //bindWidget

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class นี่คือคลาสหลัก เว้ยเห้ย
