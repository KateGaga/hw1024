package com.example.hw1024

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ed_name = findViewById<EditText>(R.id.ed_name)
        val tv_text = findViewById<TextView>(R.id.tv_text)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val button = findViewById<Button>(R.id.button)
        val tv_name = findViewById<TextView>(R.id.tv_name)
        val tv_winner = findViewById<TextView>(R.id.tv_winner)
        val tv_mmora = findViewById<TextView>(R.id.tv_mmora)
        val tv_cmora = findViewById<TextView>(R.id.tv_cmora)

        button.setOnClickListener {
            if (ed_name.text.isEmpty()) {
                tv_text.text = "請輸入玩家姓名"
                return@setOnClickListener
            }
            val playerName = ed_name.text.toString()
            val targetMora = (0..2).random()
            val myMora = when (radioGroup.checkedRadioButtonId) {
                R.id.scissor -> 0
                R.id.stone -> 1
                else -> 2
            }
            tv_name.text = "名字\n$playerName"
            tv_mmora.text = "我方出拳\n${getMoraString(myMora)}"
            tv_cmora.text = "電腦出拳\n${getMoraString(targetMora)}"

            when {
                myMora == targetMora -> {
                    tv_winner.text = "勝利者\n平手"
                    tv_text.text = "平局，請再試一次！"
                }

                (myMora == 0 && targetMora == 2) ||
                        (myMora == 1 && targetMora == 0) ||
                        (myMora == 2 && targetMora == 1) -> {
                    tv_winner.text = "勝利者\n$playerName"
                    tv_text.text = "恭喜你獲勝了！！！"
                }

                else -> {
                    tv_winner.text = "勝利者\n電腦"
                    tv_text.text = "可惜，電腦獲勝了！"
                }
            }
        }
    }

    private fun getMoraString(mora: Int): String {
        return when (mora) {
            0 -> "剪刀"
            1 -> "石頭"
            else -> "布"
        }
    }
}
