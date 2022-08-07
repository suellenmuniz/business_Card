package me.dio.business_card.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val businessCardDao: BusinessCardDao) {

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            businessCardDao.insert(businessCard)

        }

    }

    fun getAll() = businessCardDao.getAll()

    fun delete(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            businessCardDao.delete(businessCard)
        }
    }
}