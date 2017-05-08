package cn.beyace.bulbsboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangzilong on 2017/5/8.
 */

public class BulbsBoardView extends View {
    byte[][] byteArrayData;
    byte[] simpleByteArrayData;
    int bulbColor;

    public BulbsBoardView(Context context) {
        super(context);
    }

    public BulbsBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBulbColor(Color.RED);
    }

    public BulbsBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setByteArrayData(byte[][] bytes){
        this.byteArrayData = bytes;
        invalidate();
    }

    public void setSimpleByteArrayData(byte[] bytes){
        this.simpleByteArrayData = bytes;
        invalidate();
    }

    public void setBulbColor(int color){
        this.bulbColor = color;
    }

    //TODO: add 1,2,3,4,5,6,7,8,9,0,a,b....
    //TODO: make style fancy
    //TODO: add shadow

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint lightBulbPaint = new Paint();
        Paint unLightBulbPaint = new Paint();
        unLightBulbPaint.setStyle(Paint.Style.STROKE);
        unLightBulbPaint.setColor(bulbColor);
        lightBulbPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        lightBulbPaint.setColor(bulbColor);
//        Log.d("canvas wide",canvas.getWidth()+"");
//        Log.d("canvas height", canvas.getHeight()+"");
//        Log.d("view wide", getWidth()+"");
//        Log.d("view height", getHeight()+"");
//        Log.d("measure wide", getMeasuredWidth()+"");
//        Log.d("measure height", getMeasuredHeight()+"");
        if(byteArrayData != null) {
            int bulbsColumnCount = byteArrayData[0].length;
            int bulbGap = 10;
            int bulbRadius = (canvas.getWidth()-bulbGap*(bulbsColumnCount+1))/bulbsColumnCount/2;
            for(int i = 0; i<byteArrayData.length; i++){

                if(i == 0) {
                    canvas.translate(0, bulbGap + bulbRadius);
                }else{
                    canvas.translate(0, bulbGap + bulbRadius*2);
                }
                canvas.save();
                for(int j = 0; j<byteArrayData[i].length; j++){
                    if(j == 0) {
                        canvas.translate(bulbGap + bulbRadius, 0);
                    }else{
                        canvas.translate(bulbGap + bulbRadius*2, 0);
                    }
                    if(byteArrayData[i][j] != 0 ) {
                        canvas.drawCircle(0, 0, bulbRadius, lightBulbPaint);
                    }else{
                        canvas.drawCircle(0, 0, bulbRadius, unLightBulbPaint);
                    }
                }
                canvas.restore();
            }
        }else if(simpleByteArrayData != null){
            int bulbsColumnCount = 7;
            int bulbGap = 10;
            int bulbRadius = (canvas.getWidth()-bulbGap*(bulbsColumnCount+1))/bulbsColumnCount/2;
            for(int i = 0; i<simpleByteArrayData.length; i++){
                if(i == 0) {
                    canvas.translate(0, bulbGap + bulbRadius);
                }else{
                    canvas.translate(0, bulbGap + bulbRadius*2);
                }
                canvas.save();
                byte b = simpleByteArrayData[i];
//                int mask = 1;
                for(int j = 0; j<bulbsColumnCount; j++){
                    if(j == 0) {
                        canvas.translate(bulbGap + bulbRadius, 0);
                    }else{
                        canvas.translate(bulbGap + bulbRadius*2, 0);
                    }
                    int mask = 1;
                    mask = mask << bulbsColumnCount-1-j;
                    int tmp = b & mask;
                    if(tmp>0){
                        canvas.drawCircle(0, 0, bulbRadius, lightBulbPaint);
                    }else{
                        canvas.drawCircle(0, 0, bulbRadius, unLightBulbPaint);
                    }
                    Log.d("tmp", Integer.toBinaryString(tmp));
                }
                canvas.restore();
            }
        }
    }

    public static byte[] parseInt2SimpleByteArray(int i){
        byte[] sByte;
        switch (i){
            case 0:
                sByte = new byte[]{
                        0b1111111,
                        0b1000001,
                        0b1000001,
                        0b1000001,
                        0b1000001,
                        0b1000001,
                        0b1111111,
                };
                break;
            case 1:
                sByte = new byte[]{
                        0b0001000,
                        0b0001000,
                        0b0001000,
                        0b0001000,
                        0b0001000,
                        0b0001000,
                        0b0001000,
                };
                break;
            case 2:
                sByte = new byte[]{
                        0b1111111,
                        0b0000001,
                        0b0000001,
                        0b1111111,
                        0b1000000,
                        0b1000000,
                        0b1111111,
                };
                break;
            case 3:
                sByte = new byte[]{
                        0b1111111,
                        0b0000001,
                        0b0000001,
                        0b1111111,
                        0b0000001,
                        0b0000001,
                        0b1111111,
                };
                break;
            case 4:
                sByte = new byte[]{
                        0b1001000,
                        0b1001000,
                        0b1001000,
                        0b1111111,
                        0b0001000,
                        0b0001000,
                        0b0001000,
                };
                break;
            case 5:
                sByte = new byte[]{
                        0b1111111,
                        0b1000000,
                        0b1000000,
                        0b1111111,
                        0b0000001,
                        0b0000001,
                        0b1111111,
                };
                break;
            default:
                sByte = new byte[]{
                        0b0000000,
                        0b0000000,
                        0b0000000,
                        0b0000000,
                        0b0000000,
                        0b0000000,
                        0b0000000,
                };
                break;
        }
        return sByte;
    }

    public static byte[][] parseInt2ByteArray(int i){
        byte[][] bytes;
        switch (i){
            case 0:
                bytes = new byte[][]{
                        {1,1,1,1,1},
                        {1,0,0,0,1},
                        {1,0,0,0,1},
                        {1,0,0,0,1},
                        {1,1,1,1,1},
                };
                break;
            case 1:
                bytes = new byte[][]{
                        {0,0,1,0,0},
                        {0,0,1,0,0},
                        {0,0,1,0,0},
                        {0,0,1,0,0},
                        {0,0,1,0,0},
                };
                break;
            case 2:
                bytes = new byte[][]{
                        {1,1,1,1,1},
                        {0,0,0,0,1},
                        {1,1,1,1,1},
                        {1,0,0,0,0},
                        {1,1,1,1,1},
                };
                break;
            case 3:
                bytes = new byte[][]{
                        {1,1,1,1,1},
                        {0,0,0,0,1},
                        {1,1,1,1,1},
                        {0,0,0,0,1},
                        {1,1,1,1,1},
                };
                break;
            case 4:
                bytes = new byte[][]{
                        {1,0,0,1,0},
                        {1,0,0,1,0},
                        {1,1,1,1,1},
                        {0,0,0,1,0},
                        {0,0,0,1,0},
                };
                break;
            case 5:
                bytes = new byte[][]{
                        {1,1,1,1,1},
                        {1,0,0,0,0},
                        {1,1,1,1,1},
                        {0,0,0,0,1},
                        {1,1,1,1,1},
                };
                break;
            default:
                bytes = new byte[][]{};
        }
        return bytes;
    }
}
