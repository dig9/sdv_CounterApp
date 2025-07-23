package com.example.counterapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvCount;
    private Button btnStart;
    private Button btnStop;  // Stop 버튼 추가
    private final String TAG = "MainActivity";
    private BroadcastReceiver countReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 브로드캐스트를 받았을 때 실행되는 코드
            if (intent != null && intent.getAction() != null) {
                if (intent.getAction().equals("com.example.counterapp.COUNT_UPDATE")) {
                    int value = intent.getIntExtra("count", 0);
                    tvCount.setText("Count: " + value);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Layout에서 뷰 찾아오기
        tvCount = findViewById(R.id.tvCount);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);  // find stop button

        // 버튼 클릭 이벤트 처리
        btnStart.setOnClickListener(v -> {
            // CounterService 시작
            Intent serviceIntent = new Intent(MainActivity.this, CounterService.class);
            startService(serviceIntent);
        });
        btnStop.setOnClickListener(v -> {
            // CounterService 중지
            Intent serviceIntent = new Intent(MainActivity.this, CounterService.class);
            stopService(serviceIntent);
            // 서비스 중지를 사용자에게 알리기 위해 토스트 표시 (선택 사항)
            Toast.makeText(MainActivity.this, "서비스 중지", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        // 브로드캐스트 리시버 등록
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.counterapp.COUNT_UPDATE");
        registerReceiver(countReceiver, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        // 브로드캐스트 리시버 해제
        unregisterReceiver(countReceiver);

    }
}
