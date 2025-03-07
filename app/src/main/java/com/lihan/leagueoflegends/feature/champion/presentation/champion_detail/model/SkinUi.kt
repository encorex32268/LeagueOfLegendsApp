package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model

data class SkinUi(
    val num: Int,
    val name: String,
    val image: String = "",
    val imageByteArray: ByteArray?=null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SkinUi

        if (num != other.num) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (imageByteArray != null) {
            if (other.imageByteArray == null) return false
            if (!imageByteArray.contentEquals(other.imageByteArray)) return false
        } else if (other.imageByteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = num
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + (imageByteArray?.contentHashCode() ?: 0)
        return result
    }

}
