package com.example.dj.flyingbirds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Bird extends View {
    private Bitmap bird[] = new Bitmap[3];
    private Bitmap background;
    private Bitmap life[] = new Bitmap[3];
    private Bitmap greenins;
    private Bitmap yellowins;
    private Bitmap redins;
    private Paint score;
    private int x=10,y,canvasH,canvasW, speed;
    private int yellowx, yellowy, yellowSpeed=16;
    private Paint yellowPaint = new Paint();
    private int greenx, greeny, greenSpeed=20;
    private Paint greenPaint = new Paint();
    private int redx, redy, redSpeed=25;
    private Paint redPaint = new Paint();
    private int points;

    private boolean touch = false;
    private int redEncounter =0;
    private  int zero=0, one=0, two=0;
    public static String sendPoints ="";

    public  Bird(Context context){
        super(context);
        bird[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        bird[0] = Bitmap.createScaledBitmap(bird[0],180, 180, true);

        bird[1] = BitmapFactory.decodeResource(getResources(),R.drawable.bird2);
        bird[1] = Bitmap.createScaledBitmap(bird[1],180, 180, true);

        yellowins = BitmapFactory.decodeResource(getResources(),R.drawable.yelloins);
        yellowins = Bitmap.createScaledBitmap(yellowins,100, 100, true);
        greenins = BitmapFactory.decodeResource(getResources(),R.drawable.greenins);
        greenins = Bitmap.createScaledBitmap(greenins,100, 100, true);
        redins = BitmapFactory.decodeResource(getResources(),R.drawable.redins);
        redins = Bitmap.createScaledBitmap(redins,120, 120, true);

        score = new Paint();
        score.setColor(Color.WHITE);
        score.setTextSize(70);
        score.setAntiAlias(true);
        score.setStyle(Paint.Style.FILL);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        background = BitmapFactory.decodeResource(getResources(),R.drawable.sky);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.green);
        life[0] = Bitmap.createScaledBitmap(life[0],80, 80, true);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.red);
        life[1] = Bitmap.createScaledBitmap(life[1],80, 80, true);

        y=550;
        points=0;

    }

    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvasH = canvas.getHeight();
        canvasW = canvas.getWidth();

        canvas.drawBitmap(background,0,0,null);

        int minY = bird[0].getHeight();
        int maxY = canvasH - bird[0].getHeight()*3;
        y =y+speed;

        if(y<minY){
            y=minY;
        }

        if(y>maxY){
            y=maxY;
        }

        speed=speed+2;

        if(touch){
            canvas.drawBitmap(bird[1],x,y,null);
            touch=false;
        }
        else{
            canvas.drawBitmap(bird[0],x,y,null);
        }



        yellowx=yellowx-yellowSpeed;

        if(hit(yellowx,yellowy)){
            points=points+10;
            yellowx = yellowx-200;
        }

        if(yellowx<0){

            yellowx=canvasW+21;
            yellowy = (int) Math.floor(Math.random()*(maxY-minY))+minY;

        }
        canvas.drawBitmap(yellowins, yellowx -yellowins.getWidth()/2, yellowy-yellowins.getHeight()/2, null);
      //  canvas.drawCircle(yellowx,yellowy,25,yellowPaint);

        greenx=greenx-greenSpeed;

        if(hit(greenx,greeny)){
            points=points+25;
            greenx = greenx-200;
        }

        if(greenx<0){

            greenx=canvasW+21;
            greeny = (int) Math.floor(Math.random()*(maxY-minY))+minY;

        }
        canvas.drawBitmap(greenins, greenx -greenins.getWidth()/2, greeny-greenins.getHeight()/2, null);
        //canvas.drawCircle(greenx,greeny,25,greenPaint);


        redx=redx-redSpeed;

        if(hit(redx,redy)){

            if(redEncounter==0){
                canvas.drawBitmap(life[1],canvasW-300,10,null); zero=zero+1;
            }
            else if(redEncounter==1){
                canvas.drawBitmap(life[1],canvasW-200,10,null); one=one+1;
            }
            else if(redEncounter==2){
                canvas.drawBitmap(life[1],canvasW-100,10,null); two=two+1;
            }
            redx = redx-200;
            redEncounter = redEncounter+1;
        }

        if(redx<0){

            redx=canvasW+21;
            redy = (int) Math.floor(Math.random()*(maxY-minY))+minY;

        }
        canvas.drawBitmap(redins, redx -redins.getHeight()/2, redy-redins.getWidth()/2, null);
        //canvas.drawCircle(redx,redy,30,redPaint);

      //  canvas.drawBitmap(bird[0],0,30,null);

        canvas.drawText("Points : " +points,20,60,score );
        if(zero==0) {
            canvas.drawBitmap(life[0], canvasW - 300, 10, null);
        }
        else{
            canvas.drawBitmap(life[1], canvasW - 300, 10, null);
        }
        if(one==0) {
            canvas.drawBitmap(life[0], canvasW - 200, 10, null);
        }
        else{
            canvas.drawBitmap(life[1], canvasW - 200, 10, null);
        }
        if(two==0) {
            canvas.drawBitmap(life[0], canvasW - 100, 10, null);
        }
        else{
            canvas.drawBitmap(life[1], canvasW - 100, 10, null);
            Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();

            Intent gameOver = new Intent(getContext(),GameOverActivity.class);

            gameOver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            sendPoints = String.valueOf(points);
            gameOver.putExtra("points",sendPoints+"");
            getContext().startActivity(gameOver);
            two=0;
        }
    }

    public boolean hit(int cx, int cy){
        if(x<cx && cx<(x+bird[0].getWidth()) && y<cy && cy<(y+bird[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch=true;
            speed=-35;
        }
        return  true;
    }
}
