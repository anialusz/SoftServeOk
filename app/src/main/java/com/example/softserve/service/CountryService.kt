package com.example.softserve.service

import com.example.softserve.CountryListQuery
import com.example.softserve.CountryQuery

class CountryService {
    private val apollo = ApolloService

    suspend fun fetchCountryListAsync(): List<CountryListQuery.Country> {
        return apollo.apolloClient.query(CountryListQuery()).execute().data?.countries ?: listOf()
    }

    suspend fun fetchCountryDetailsAsync(countryCode: String): CountryQuery.Country? {
        return apollo.apolloClient.query(CountryQuery(countryCode)).execute().data?.country
    }
}