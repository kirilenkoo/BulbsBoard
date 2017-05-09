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
    int bulbColor;

    DataType mDataType;
    Object mData;

    public enum DataType{
        SIMPLE_BYTE_ARRAY,
        COMPLEX_BYTE_ARRAY,
        HEX_INT_ARRAY,
        OCTAL_INT_ARRAY
    }

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


    public void setArrayData(Object data, DataType dataType){
        mDataType = dataType;
        mData = data;
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
        unLightBulbPaint.setAntiAlias(true);
        lightBulbPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        lightBulbPaint.setAntiAlias(true);
        lightBulbPaint.setColor(bulbColor);

        int bulbsColumnCount = 0;
        int bulbGap = 0;
        int bulbRadius = 0;
        if(mData!=null){
            switch (mDataType){
                case SIMPLE_BYTE_ARRAY:
                    byte[] simpleTmpData = (byte[]) mData;
                    bulbsColumnCount = 7;
                    bulbGap = 10;
                    bulbRadius = (canvas.getWidth()-bulbGap*(bulbsColumnCount+1))/bulbsColumnCount/2;
                    for(int i = 0; i<simpleTmpData.length; i++) {
                        if (i == 0) {
                            canvas.translate(0, bulbGap + bulbRadius);
                        } else {
                            canvas.translate(0, bulbGap + bulbRadius * 2);
                        }
                        canvas.save();
                        byte b = simpleTmpData[i];
                        for (int j = 0; j < bulbsColumnCount; j++) {
                            if (j == 0) {
                                canvas.translate(bulbGap + bulbRadius, 0);
                            } else {
                                canvas.translate(bulbGap + bulbRadius * 2, 0);
                            }
                            int mask = 1;
                            mask = mask << bulbsColumnCount - 1 - j;
                            int tmp = b & mask;
                            if (tmp > 0) {
                                canvas.drawCircle(0, 0, bulbRadius, lightBulbPaint);
                            } else {
                                canvas.drawCircle(0, 0, bulbRadius, unLightBulbPaint);
                            }
                            Log.d("tmp", Integer.toBinaryString(tmp));
                        }
                        canvas.restore();
                    }
                    break;
                case COMPLEX_BYTE_ARRAY:
                    byte[][] complexTmpData = (byte[][]) mData;
                    bulbsColumnCount = complexTmpData[0].length;
                    bulbGap = 10;
                    bulbRadius = (canvas.getWidth()-bulbGap*(bulbsColumnCount+1))/bulbsColumnCount/2;
                    for(int i = 0; i<complexTmpData.length; i++){

                        if(i == 0) {
                            canvas.translate(0, bulbGap + bulbRadius);
                        }else{
                            canvas.translate(0, bulbGap + bulbRadius*2);
                        }
                        canvas.save();
                        for(int j = 0; j<complexTmpData[i].length; j++){
                            if(j == 0) {
                                canvas.translate(bulbGap + bulbRadius, 0);
                            }else{
                                canvas.translate(bulbGap + bulbRadius*2, 0);
                            }
                            if(complexTmpData[i][j] != 0 ) {
                                canvas.drawCircle(0, 0, bulbRadius, lightBulbPaint);
                            }else{
                                canvas.drawCircle(0, 0, bulbRadius, unLightBulbPaint);
                            }
                        }
                        canvas.restore();
                    }
                    break;
                case HEX_INT_ARRAY:
                    int[] hexTmpData = (int[]) mData;
                    bulbsColumnCount = 8;
                    bulbGap = 10;
                    bulbRadius = (canvas.getWidth()-bulbGap*(bulbsColumnCount+1))/bulbsColumnCount/2;
                    for(int i = 0; i<hexTmpData.length; i++) {
                        if (i == 0) {
                            canvas.translate(0, bulbGap + bulbRadius);
                        } else {
                            canvas.translate(0, bulbGap + bulbRadius * 2);
                        }
                        canvas.save();
                        int b = hexTmpData[i];
                        for (int j = 0; j < bulbsColumnCount; j++) {
                            if (j == 0) {
                                canvas.translate(bulbGap + bulbRadius, 0);
                            } else {
                                canvas.translate(bulbGap + bulbRadius * 2, 0);
                            }
                            int mask = 0x00000001;
                            mask = mask << (bulbsColumnCount - 1- j)*4;
                            int tmp = b & mask;
                            if (tmp > 0) {
                                canvas.drawCircle(0, 0, bulbRadius, lightBulbPaint);
                            } else {
                                canvas.drawCircle(0, 0, bulbRadius, unLightBulbPaint);
                            }
                            Log.d("tmp", Integer.toBinaryString(tmp));
                        }
                        canvas.restore();
                    }
                    break;
                case OCTAL_INT_ARRAY:
                    int[] octalTmpData = (int[]) mData;
                    bulbsColumnCount = 11;
                    bulbGap = 10;
                    bulbRadius = (canvas.getWidth()-bulbGap*(bulbsColumnCount+1))/bulbsColumnCount/2;
                    for(int i = 0; i<octalTmpData.length; i++) {
                        if (i == 0) {
                            canvas.translate(0, bulbGap + bulbRadius);
                        } else {
                            canvas.translate(0, bulbGap + bulbRadius * 2);
                        }
                        canvas.save();
                        int b = octalTmpData[i];
                        for (int j = 0; j < bulbsColumnCount; j++) {
                            if (j == 0) {
                                canvas.translate(bulbGap + bulbRadius, 0);
                            } else {
                                canvas.translate(bulbGap + bulbRadius * 2, 0);
                            }
                            int mask = 000000000001;
                            mask = mask << (bulbsColumnCount - 1- j)*3;
                            int tmp = b & mask;
                            if (tmp > 0) {
                                canvas.drawCircle(0, 0, bulbRadius, lightBulbPaint);
                            } else {
                                canvas.drawCircle(0, 0, bulbRadius, unLightBulbPaint);
                            }
                            Log.d("tmp", Integer.toBinaryString(tmp));
                        }
                        canvas.restore();
                    }
                    break;
            }
        }
    }

    public static int[] parseInt2OctalIntArray(int i){
        int[] hInt;
        switch (i){
            case 0:
                hInt = new int[]{
                        011111111111,
                        011111111111,
                        011000000011,
                        011000000011,
                        011000000011,
                        011000000011,
                        011111111111,
                        011111111111
                };
                break;
            case 1:
                hInt = new int[]{
                        000001110000,
                        000001110000,
                        000001110000,
                        000001110000,
                        000001110000,
                        000001110000,
                        000001110000,
                        000001110000
                };
                break;
            case 2:
                hInt = new int[]{
                        011111111111,
                        011111111111,
                        000000000011,
                        011111111111,
                        011111111111,
                        011000000000,
                        011111111111,
                        011111111111
                };
                break;
            case 3:
                hInt = new int[]{
                        011111111111,
                        011111111111,
                        000000000011,
                        011111111111,
                        011111111111,
                        000000000011,
                        011111111111,
                        011111111111
                };
                break;
            case 4:
                hInt = new int[]{
                        011000011000,
                        011000011000,
                        011000011000,
                        011111111111,
                        011111111111,
                        000000011000,
                        000000011000,
                        000000011000
                };
                break;
            case 5:
                hInt = new int[]{
                        011111111111,
                        011111111111,
                        011000000000,
                        011111111111,
                        011111111111,
                        000000000011,
                        011111111111,
                        011111111111
                };
                break;
            default:
                hInt = new int[]{
                        011111111111,
                        011111111111,
                        011111111111,
                        011111111111,
                        011111111111,
                        011111111111,
                        011111111111,
                        011111111111

                };
        }
        return hInt;
    }

    public static int[] parseInt2HexIntArray(int i){
        int[] hInt;
        switch (i){
            case 0:
                hInt = new int[]{
                        0x11111111,
                        0x10000001,
                        0x10000001,
                        0x10000001,
                        0x10000001,
                        0x10000001,
                        0x11111111
                };
                break;
            case 1:
                hInt = new int[]{
                        0x00011000,
                        0x00011000,
                        0x00011000,
                        0x00011000,
                        0x00011000,
                        0x00011000,
                        0x00011000
                };
                break;
            case 2:
                hInt = new int[]{
                        0x11111111,
                        0x00000001,
                        0x00000001,
                        0x11111111,
                        0x10000000,
                        0x10000000,
                        0x11111111
                };
                break;
            case 3:
                hInt = new int[]{
                        0x11111111,
                        0x00000001,
                        0x00000001,
                        0x11111111,
                        0x00000001,
                        0x00000001,
                        0x11111111
                };
                break;
            case 4:
                hInt = new int[]{
                        0x10001000,
                        0x10001000,
                        0x10001000,
                        0x11111111,
                        0x00001000,
                        0x00001000,
                        0x00001000
                };
                break;
            case 5:
                hInt = new int[]{
                        0x11111111,
                        0x10000000,
                        0x10000000,
                        0x11111111,
                        0x00000001,
                        0x00000001,
                        0x11111111
                };
                break;
            default:
                hInt = new int[]{
                        0x00000000,
                        0x00000000,
                        0x00000000,
                        0x00000000,
                        0x00000000,
                        0x00000000,
                        0x00000000

                };
        }
        return hInt;
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
