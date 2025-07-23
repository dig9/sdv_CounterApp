package com.example.counterapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {
    private boolean isRunning = false;
    private Thread counterThread;
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // return super.onStartCommand(intent, flags, startId);
        // 서비스의 주 작업 수행 (새로운 스레드 시작 등)
        if (!isRunning) {
            // 서비스가 시작되지 않은 상태에서만 동작
            isRunning = true;
            // 백그라운드 작업을 담당할 스레드 정의
            counterThread = new Thread(() -> {
                try {
                    while (isRunning) {
                        count++;
                        // Logcat에 현재 count 값 출력
                        Log.d("CounterService", "Count: " + count);
                        Thread.sleep(1000); // 1초 대기
                    }
                } catch (InterruptedException e) {
                    // 스레드가 interrupt될 경우 예외 발생하여 여기로 옴
                    Thread.currentThread().interrupt();
                }
                // 루프를 벗어나면 스레드 끝
            });
            counterThread.start();
        }
        // 서비스가 강제 종료되었을 때 시스템이 다시 실행하도록 설정
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스 종료 처리 (스레드 중지 등)
        isRunning = false;
        if (counterThread != null) {
            counterThread.interrupt(); // 스레드 종료 유도
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        // 바인드용 서비스가 아니므로 null 반환
        return null;
    }
}