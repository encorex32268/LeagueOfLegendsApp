package com.lihan.leagueoflegends.feature.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChampionEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val language: String,
    val image: ByteArray?=null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChampionEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (title != other.title) return false
        if (blurb != other.blurb) return false
        if (language != other.language) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + blurb.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + (image?.contentHashCode() ?: 0)
        return result
    }


}


