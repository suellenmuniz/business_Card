package me.dio.business_card.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CardsRepository(private val businessCardDao: BusinessCardDao){

    fun insert(card: Card) = runBlocking {
        launch(Dispatchers.IO) {
            businessCardDao.insert(card)
        }
    }

    fun getAll() = businessCardDao.getAll()

    fun delete(card: Card) = runBlocking {
        launch(Dispatchers.IO) {
            businessCardDao.delete(card)
        }
    }

}