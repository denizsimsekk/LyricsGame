package com.example.lyricsgame.domain.usecase.artist

import com.example.lyricsgame.domain.util.Resource
import com.example.lyricsgame.domain.repository.IArtistRepository
import com.example.lyricsgame.domain.viewentity.TrackViewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistTrackListUseCase @Inject constructor(private val artistRepository: IArtistRepository) {

    operator fun invoke(artistId: Int): Flow<Resource<List<TrackViewEntity>?>> {
        return artistRepository.getArtistTrackList(artistId = artistId)
    }

}