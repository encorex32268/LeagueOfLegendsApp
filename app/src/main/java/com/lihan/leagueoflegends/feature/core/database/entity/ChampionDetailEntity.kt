package com.lihan.leagueoflegends.feature.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.core.database.model.RoomSkin
import com.lihan.leagueoflegends.feature.core.database.model.RoomSpell

@Entity
data class ChampionDetailEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val title: String,
    val icon: ByteArray?,
    val spells: List<RoomSpell>,
    val skins: List<RoomSkin>,
    val language: String,
    val passive: Passive?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChampionDetailEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (title != other.title) return false
        if (icon != null) {
            if (other.icon == null) return false
            if (!icon.contentEquals(other.icon)) return false
        } else if (other.icon != null) return false
        if (spells != other.spells) return false
        if (skins != other.skins) return false
        if (language != other.language) return false
        if (passive != other.passive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (icon?.contentHashCode() ?: 0)
        result = 31 * result + spells.hashCode()
        result = 31 * result + skins.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + (passive?.hashCode() ?: 0)
        return result
    }

}