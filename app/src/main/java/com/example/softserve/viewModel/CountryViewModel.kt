package com.example.softserve.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.example.softserve.CountryListQuery
import com.example.softserve.CountryQuery
import com.example.softserve.service.CountryService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val countryService = CountryService()

    private val _countryList: MutableLiveData<ResultState<List<CountryListQuery.Country>>> =
        MutableLiveData()
    val countryList: LiveData<ResultState<List<CountryListQuery.Country>>> = _countryList

    private val _country: MutableLiveData<ResultState<CountryQuery.Country>> =
        MutableLiveData()
    val country: LiveData<ResultState<CountryQuery.Country>> = _country

    fun fetchAndUpdateCountryList() {
        viewModelScope.launch {
            _countryList.postValue(ResultState.Loading)
            try {
                val deferred = fetchCountryListAsync()
                val response = deferred.await()
                _countryList.postValue(ResultState.Success(response))
            } catch (e: ApolloNetworkException) {
                _countryList.postValue(
                    ResultState.Failure(
                        "Please check your internet connection",
                        e
                    )
                )
            } catch (e: Exception) {
                _countryList.postValue(ResultState.Failure("Error occurred", e))
            }
        }
    }

    fun fetchAndUpdateCountry(countryCode: String) {
        viewModelScope.launch {
            _country.postValue(ResultState.Loading)
            try {
                val deferred = fetchCountryDetailsAsync(countryCode)
                val response = deferred.await()
                if (response != null) {
                    _country.postValue(ResultState.Success(response))
                } else {
                    _country.postValue(ResultState.Failure("Error while getting country", response))
                }
            } catch (e: ApolloNetworkException) {
                _country.postValue(ResultState.Failure("Please check your internet connection", e))
            } catch (e: Exception) {
                _country.postValue(ResultState.Failure("Error occurred", e))
            }
        }
    }

    private fun fetchCountryListAsync(): Deferred<List<CountryListQuery.Country>> {
        return viewModelScope.async {
            countryService.fetchCountryListAsync()
        }
    }

    private fun fetchCountryDetailsAsync(countryCode: String): Deferred<CountryQuery.Country?> {
        return viewModelScope.async {
            countryService.fetchCountryDetailsAsync(countryCode)
        }
    }
}