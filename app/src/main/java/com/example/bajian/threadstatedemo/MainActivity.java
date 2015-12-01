package com.example.bajian.threadstatedemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Thread mThread=new Thread(new MyRunnable());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("mThread.getState()"+mThread.getState());
            }
        });
    }
/*
    NEW
    在生成线程对象，并没有调用该对象的start方法，这是线程处于创建状态。
    RUNNABLE
    正在 Java 虚拟机中执行的线程处于这种状态。
    BLOCKED
    线程正在运行的时候，被暂停，通常是为了等待某个时间的发生(比如说某项资源就绪)之后再继续运行。sleep,suspend，wait等方法都可以导致线程阻塞。
    WAITING
    无限期地等待另一个线程来执行某一特定操作的线程处于这种状态。
    TIMED_WAITING
    等待另一个线程来执行取决于指定等待时间的操作的线程处于这种状态。
    TERMINATED
    如果一个线程的run方法执行结束或者调用stop方法后，该线程就会死亡。对于已经死亡的线程，无法再使用start方法令其进入就绪。

    */


//    线程重用的核心是，它把Thread.start()给屏蔽起来了（一定不要重复调用），
// 然后它自己有一个Runnable.run()，循环在跑，跑的过程中不断检查我们是否有新加入的子Runnable对象，
// 有就调一下我们的run()，其实就一个大run()把其它小run()#1,run()#2,...给串联起来了，基本原理就这么简单。
//
//    JDK代码节选
    /**
     * Main run loop
     */
/*    public void run() {
        try {
            Runnable task = firstTask;
            firstTask = null;
            while (task != null || (task = getTask()) != null) {
                runTask(task);//这里最终会调用task.run()
                task = null;
            }
        } finally {
            workerDone(this);
        }
    }
}*/

    public void run (View v){
        mThread.run();
        mThread.run();
        mThread.run();
    }

    public void start(View v){
        mThread.start();
//        mThread.run();
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException mE) {
                mE.printStackTrace();
            }
            System.out.println("MyRunnable...");
        }
    }

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
}
