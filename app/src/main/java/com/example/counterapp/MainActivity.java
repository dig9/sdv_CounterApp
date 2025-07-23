package com.example.counterapp;

import android.content.Intent;
import android.os.Bundle;
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
}
