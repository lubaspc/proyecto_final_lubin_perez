package com.example.proyectofinal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.database.AppDatabase
import com.example.proyectofinal.database.Breed
import com.example.proyectofinal.domain.DogRepository
import com.example.proyectofinal.model.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import okhttp3.internal.concurrent.Task

class MainViewModel : ViewModel() {
    private val api by lazy { DogRepository }
    private val dao by lazy { AppDatabase.db.dogDao() }
    private val dogsBackup = mutableListOf<Dog>()
    val dogs = mutableStateListOf<Dog>()
    val dogsDb = mutableStateListOf<Dog>()
    var isLoading = mutableStateOf(false)

    val errorState = MutableLiveData<String?>(null)
    var breedSaved = MutableLiveData<String>()

    fun getDogsService() {
        isLoading.value = true
        dogs.clear()
        dogsBackup.clear()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val breedsResponse = api.getBreeds()
                if (breedsResponse.status != "success") throw Exception()
                breedsResponse.message.map {
                    viewModelScope.async(Dispatchers.IO) {
                        val imagesResponse = api.getImages(it)
                        if (imagesResponse.status != "success") throw Exception()
                        dogs.add(Dog(it, imagesResponse.message).also(dogsBackup::add))
                    }
                }.awaitAll()
            } catch (e: Exception) {
                errorState.postValue(e.message)
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }

        }
    }


    fun filterList(sts: String) {
        dogs.clear()
        dogs.addAll(
            dogsBackup.filter { it.name.contains(sts.lowercase()) }
        )
    }

    fun getDBBreeds() {
        dogsDb.clear()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.getBreeds().groupBy { it.BreedId }.forEach { (breed, images) ->
                    dogsDb.add(Dog(breed, images.map { it.file }))
                }
            } catch (e: Exception) {
                errorState.postValue(e.message)
                e.printStackTrace()
            }
        }
    }

    fun saveBreed(breed: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.upsetBreed(Breed(url, breed))
                breedSaved.postValue("Imagen Guarda")
            } catch (e: Exception) {
                e.printStackTrace()
                errorState.postValue(e.message)
            }
        }
    }

    fun removeBreed(breed: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.deleteBreed(breed, url)
                getDBBreeds()
                breedSaved.postValue("Imagen eliminada")
            } catch (e: Exception) {
                e.printStackTrace()
                errorState.postValue(e.message)
            }
        }
    }


}