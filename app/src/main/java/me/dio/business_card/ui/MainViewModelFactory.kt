package me.dio.business_card.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.dio.business_card.data.BusinessCard
import me.dio.business_card.data.BusinessCardRepository

class MainViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {

    fun insertCard(businessCard: BusinessCard) {
        businessCardRepository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll()
    }

    fun delete(businessCard: BusinessCard) {
        businessCardRepository.delete(businessCard)
    }

}
@Suppress("UNCHECKED_CAST")

class MainViewModelFactory(private val  repository: BusinessCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}