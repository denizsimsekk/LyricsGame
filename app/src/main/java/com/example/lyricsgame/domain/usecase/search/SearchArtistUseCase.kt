package com.example.lyricsgame.domain.usecase.search

import com.example.lyricsgame.data.model.Resource
import com.example.lyricsgame.domain.repository.ISearchRepository
import com.example.lyricsgame.domain.viewentity.ArtistViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val searchRepository: ISearchRepository) {

    operator fun invoke(query: String): Flow<Resource<List<ArtistViewEntity>?>> {
        return searchRepository.searchArtist(query = query)
    }

}