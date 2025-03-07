package com.lihan.leagueoflegends.feature.champion.domain.model


/**
 *  name: Zac
 *  title: God Zac
 *  icon: Zac.png
 *  https://ddragon.leagueoflegends.com/cdn/15.3.1/img/champion/Zac.png
 *  iconByteArray by coilImageLoader
 *
 */

data class ChampionInfo(
    val id: String = "",
    val name: String = "",
    val title: String = "",
    val icon: String = "",
    val iconByteArray: ByteArray?=null,
    val skins: List<Skin> = emptyList(),
    val spells: List<Spell> = emptyList(),
    val language: String,
    val passive: Passive? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChampionInfo

        if (id != other.id) return false
        if (name != other.name) return false
        if (title != other.title) return false
        if (icon != other.icon) return false
        if (iconByteArray != null) {
            if (other.iconByteArray == null) return false
            if (!iconByteArray.contentEquals(other.iconByteArray)) return false
        } else if (other.iconByteArray != null) return false
        if (skins != other.skins) return false
        if (spells != other.spells) return false
        if (language != other.language) return false
        if (passive != other.passive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + (iconByteArray?.contentHashCode() ?: 0)
        result = 31 * result + skins.hashCode()
        result = 31 * result + spells.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + (passive?.hashCode() ?: 0)
        return result
    }


}
