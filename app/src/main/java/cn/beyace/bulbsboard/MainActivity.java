package cn.beyace.bulbsboard;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    BulbsBoardView bulbsBoardView;
    //complex byte array data
    //no limit
    byte[][] byteData;
    //simple byte array data
    //column count max 7
    byte[] simpleByteData;
    //int hex array data
    //column count max 8
    int l = 0x10101110;
    int[] hexData = new int[]{
            0x10000001,
            0x00000001,
            0x11111110
    };
    //int octal array data
    //column count max 11
    int i = 010001000101;
    int[] octalData = new int[]{
            010000001001,
            000000000001,
            000001000010
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulbsBoardView = (BulbsBoardView) findViewById(R.id.bulbsBoardView);
//        bulbsBoardView.setByteArrayData(BulbsBoardView.parseInt2ByteArray(5));
//        bulbsBoardView.setSimpleByteArrayData(BulbsBoardView.parseInt2SimpleByteArray(3));

    }

    @Override
    protected void onResume() {
        super.onResume();
        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bulbsBoardView.setSimpleByteArrayData(BulbsBoardView.parseInt2SimpleByteArray((int) (millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                bulbsBoardView.setSimpleByteArrayData(BulbsBoardView.parseInt2SimpleByteArray(0));
            }
        };
        countDownTimer.start();

    }
}
