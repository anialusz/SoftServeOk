package com.example.softserve.service

import com.apollographql.apollo3.ApolloClient

object ApolloService {

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/")
        .build()
}