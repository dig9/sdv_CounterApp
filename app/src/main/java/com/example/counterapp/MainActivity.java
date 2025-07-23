package com.example.counterapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvCount;
    private Button btnStart;
    // 추후 Stop 버튼 등 추가 예정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Layout에서 뷰 찾아오기
        tvCount = findViewById(R.id.tvCount);
        btnStart = findViewById(R.id.btnStart);

        // 버튼 클릭 이벤트 처리
        btnStart.setOnClickListener(v -> {
            // 버튼 클릭 시 수행할 동작 정의
            // 현재 텍스트 가져오기 ("Count: 0")
            String text = tvCount.getText().toString();
            // 숫자 부분 추출 및 증가
            int countValue = 0;
            try {
                // "Count: X" 형식에서 ":" 뒤의 숫자 부분만 추출
                countValue = Integer.parseInt(text.split(": ")[1]);
            } catch (Exception e) {
                // 파싱 에러 대비 (첫 클릭 전 텍스트 형식이 다를 경우 등)
            }
            countValue++;  // 1 증가
            tvCount.setText("Count: " + countValue);
        });
    }
}
