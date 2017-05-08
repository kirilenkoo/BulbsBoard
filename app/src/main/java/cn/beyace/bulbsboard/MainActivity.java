package cn.beyace.bulbsboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BulbsBoardView bulbsBoardView;
    byte[][] byteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulbsBoardView = (BulbsBoardView) findViewById(R.id.bulbsBoardView);
        byteData = new byte[][]{
                new byte[]{
                        0,1,1,1,0
                },
                new byte[]{
                        0,0,1,0,0
                },
                new byte[]{
                        0,1,1,1,0
                }
        };
        bulbsBoardView.setByteArrayData(byteData);
    }
}
