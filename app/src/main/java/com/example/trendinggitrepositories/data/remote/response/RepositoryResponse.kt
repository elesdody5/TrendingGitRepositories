package com.example.trendinggitrepositories.data.remote.response

import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails

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

fun List<RemoteRepository>.toGitRepositoryDetails(): List<GitRepositoryDetails> {
    return map {
        GitRepositoryDetails(
            name = it.name ?: "",
            avatar = it.owner?.avatarUrl ?: "",
            score = it.score ?: 0.0,
            url = it.url ?: "",
            description = it.description ?: ""
        )
    }
}