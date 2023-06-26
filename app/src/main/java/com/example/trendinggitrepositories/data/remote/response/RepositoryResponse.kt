package com.example.trendinggitrepositories.data.remote.response

data class RepositoryResponse(
    val items: List<RemoteRepository>? = null,
)

data class RemoteRepository(
    val name: String? = null,
    val owner: Owner? = null,
    val score: Double? = null,
    val url: String? = null,
    val description: String? = null,
)

data class Owner(
    val avatarUrl: String? = null,
)