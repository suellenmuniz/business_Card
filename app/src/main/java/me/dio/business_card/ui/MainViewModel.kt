package me.dio.business_card.ui

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.dio.business_card.data.BusinessCard
import me.dio.business_card.data.BusinessCardRepository

class MainViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {

    fun insert(businessCard: BusinessCard) {
        businessCardRepository.insert(businessCard)
    }

    fun getAll() : LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll()
    }
}
class MainViewModelFactory(private val repository: BusinessCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}