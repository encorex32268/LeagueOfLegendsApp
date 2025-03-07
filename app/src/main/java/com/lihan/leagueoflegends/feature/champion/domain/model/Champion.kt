package com.lihan.leagueoflegends.feature.champion.domain.model

data class Champion(
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val image: String ="",
    val imageByteArray: ByteArray? =null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Champion

        if (id != other.id) return false
        if (name != other.name) return false
        if (title != other.title) return false
        if (blurb != other.blurb) return false
        if (image != other.image) return false
        if (imageByteArray != null) {
            if (other.imageByteArray == null) return false
            if (!imageByteArray.contentEquals(other.imageByteArray)) return false
        } else if (other.imageByteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + blurb.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + (imageByteArray?.contentHashCode() ?: 0)
        return result
    }

}
