package com.lihan.leagueoflegends.feature.core.database.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomSkin(
    val num: Int,
    val image: ByteArray?=null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoomSkin

        if (num != other.num) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = num
        result = 31 * result + image.contentHashCode()
        return result
    }
}
