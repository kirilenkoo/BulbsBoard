package cn.beyace.bulbsboard;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BulbsBoardView bulbsBoardView;
    byte[][] byteData;
    byte[] simpleByteData;
    long l = 0x84877578;
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
