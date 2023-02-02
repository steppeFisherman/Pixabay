package com.example.pixabay.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabay.data.repository.ToDispatch
import com.example.pixabay.domain.model.ErrorType
import com.example.pixabay.domain.model.MyResult
import com.example.pixabay.domain.usecases.FetchUseCase
import com.example.pixabay.ui.model.DataUi
import com.example.pixabay.ui.model.MapDomainToUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchUseCase: FetchUseCase,
    private val mapper: MapDomainToUi,
    private val dispatchers: ToDispatch,
) : ViewModel() {

    private var mFeelings = MutableLiveData<DataUi>()
    private var mFeelingsError = MutableLiveData<ErrorType>()
    val feelings: LiveData<DataUi> get() = mFeelings
    val errorFeelings: LiveData<ErrorType> get() = mFeelingsError

    private var mAnimals = MutableLiveData<DataUi>()
    private var mAnimalsError = MutableLiveData<ErrorType>()
    val animals: LiveData<DataUi> get() = mAnimals
    val errorAnimals: LiveData<ErrorType> get() = mAnimalsError

    private var mMusic = MutableLiveData<DataUi>()
    private var mMusicError = MutableLiveData<ErrorType>()
    val music: LiveData<DataUi> get() = mMusic
    val errorMusic: LiveData<ErrorType> get() = mMusicError

    private var mSports = MutableLiveData<DataUi>()
    private var mSportsError = MutableLiveData<ErrorType>()
    val sports: LiveData<DataUi> get() = mSports
    val errorSports: LiveData<ErrorType> get() = mSportsError

    private var mIndustry = MutableLiveData<DataUi>()
    private var mIndustryError = MutableLiveData<ErrorType>()
    val industry: LiveData<DataUi> get() = mIndustry
    val errorIndustry: LiveData<ErrorType> get() = mIndustryError

    private var mScience = MutableLiveData<DataUi>()
    private var mScienceError = MutableLiveData<ErrorType>()
    val science: LiveData<DataUi> get() = mScience
    val errorScience: LiveData<ErrorType> get() = mScienceError

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    init {
        viewModelScope.launch(exceptionHandler) {
            when (val result = fetchUseCase.fetchFeelings()) {
                is MyResult.Success -> mFeelings.value = mapper.transform(result.itemsDomain)
                is MyResult.Fail -> mFeelingsError.value = result.errorType
            }
        }
        viewModelScope.launch(exceptionHandler) {
            when (val result = fetchUseCase.fetchAnimals()) {
                is MyResult.Success -> mAnimals.value = mapper.transform(result.itemsDomain)
                is MyResult.Fail -> mAnimalsError.value = result.errorType
            }
        }
        viewModelScope.launch(exceptionHandler) {
            when (val result = fetchUseCase.fetchMusic()) {
                is MyResult.Success -> mMusic.value = mapper.transform(result.itemsDomain)
                is MyResult.Fail -> mMusicError.value = result.errorType
            }
        }
        viewModelScope.launch(exceptionHandler) {
            when (val result = fetchUseCase.fetchSports()) {
                is MyResult.Success -> mSports.value = mapper.transform(result.itemsDomain)
                is MyResult.Fail -> mSportsError.value = result.errorType
            }
        }
        viewModelScope.launch(exceptionHandler) {
            when (val result = fetchUseCase.fetchIndustry()) {
                is MyResult.Success -> mIndustry.value = mapper.transform(result.itemsDomain)
                is MyResult.Fail -> mIndustryError.value = result.errorType
            }
        }
        viewModelScope.launch(exceptionHandler) {
            when (val result = fetchUseCase.fetchScience()) {
                is MyResult.Success -> mScience.value = mapper.transform(result.itemsDomain)
                is MyResult.Fail -> mScienceError.value = result.errorType
            }
        }
    }
}
