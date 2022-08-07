package me.dio.business_card

import android.app.Application
import me.dio.business_card.data.AppDataBase
import me.dio.business_card.data.CardsRepository

class App : Application() {
    val dataBase by lazy { AppDataBase.getDataBase(this) }
    val repository by lazy { CardsRepository(dataBase.businessDao()) }
}