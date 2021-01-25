package jgeun.study.flo_a_jeffrey.src.selecttaste

import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTasteResponse
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.SelectTasteResponse

interface SelectTasteView {
    fun onGetArtistInfoSuccess(response: SelectTasteResponse)
    fun onGetArtistInfoFailure(message: String)

    fun onPutUserTasteSuccess(response: PutUserTasteResponse)
    fun onPutUserTasteFailure(message: String)
}