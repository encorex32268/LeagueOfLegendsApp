package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model

data class ChampionInfoUi(
    val id: String,
    val name: String,
    val title: String,
    val icon: String,
    val skins: List<SkinUi> = emptyList(),
    val spells: List<SpellUi> = emptyList(),
    val iconByteArray: ByteArray?=null,
    val passiveSpellUi: SpellUi? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChampionInfoUi

        if (id != other.id) return false
        if (name != other.name) return false
        if (title != other.title) return false
        if (icon != other.icon) return false
        if (skins != other.skins) return false
        if (spells != other.spells) return false
        if (iconByteArray != null) {
            if (other.iconByteArray == null) return false
            if (!iconByteArray.contentEquals(other.iconByteArray)) return false
        } else if (other.iconByteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + skins.hashCode()
        result = 31 * result + spells.hashCode()
        result = 31 * result + (iconByteArray?.contentHashCode() ?: 0)
        return result
    }


}
