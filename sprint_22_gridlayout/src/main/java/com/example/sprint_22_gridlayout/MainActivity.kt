package com.example.sprint_22_gridlayout

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val news = listOf(
            News("В Хомяковиле запущена новая станция метро", "Сегодня мэр Хомяковилла торжественно открыл новую станцию метро, соединяющую завод ХомяАгроИндастри и жилой сектор ХомиТаун."),
            News("Хомяки вышли на ежегодный марш в поддержку трудящихся","Ежегодно 1 мая в Хомякостане празднует день труда и вкусняшек. В этом году празднование прошло на площади независимости от колес"),
            News("В пригородном в Хомебино ночью была драка с участием местных банд", "В ночь с 21 по 22 февраля в день ВДФ в пригородном районе Хомебино прошла драка между уличными бандами домашних и диких хомяков. Пострадавших нет! Все подружились!"),
            News("Ученые лаборатории по изучению людей сделали открытие.","В среду директор лаборатории Хомишвейна по изучению поведения людей объявил о новом открытии в процессах мышления людей. Ученым пока неизвестно как это применить.")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = GridLayoutManager(this, /*Количество столбцов*/ 2)
        recyclerView.adapter = NewsAdapter(news+news+news)
    }
}