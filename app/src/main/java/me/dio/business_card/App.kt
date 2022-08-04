package me.dio.business_card

import android.app.Application
import me.dio.business_card.data.AppDataBase
import me.dio.business_card.data.BusinessCardRepository

class App : Application() {
    val dataBase by lazy { AppDataBase.getDataBase(this) }
    val repository by lazy { BusinessCardRepository(dataBase.businessDao()) }
}