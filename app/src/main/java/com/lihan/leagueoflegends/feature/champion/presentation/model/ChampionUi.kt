package com.lihan.leagueoflegends.feature.champion.presentation.model

data class ChampionUi(
    val id: String? = "",
    val blurb: String? = "",
    val image: String? = "",
    val name: String?= "",
    val title: String?= "",
    val imageByteArray: ByteArray?= null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChampionUi

        if (id != other.id) return false
        if (blurb != other.blurb) return false
        if (image != other.image) return false
        if (name != other.name) return false
        if (title != other.title) return false
        if (imageByteArray != null) {
            if (other.imageByteArray == null) return false
            if (!imageByteArray.contentEquals(other.imageByteArray)) return false
        } else if (other.imageByteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (blurb?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (imageByteArray?.contentHashCode() ?: 0)
        return result
    }

}
