package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.model

data class SpellUi(
    val id: String = "",
    val name: String,
    val description: String,
    val cooldownBurn: String ="",
    val image: String?="",
    val imageByteArray: ByteArray?=null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpellUi

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (cooldownBurn != other.cooldownBurn) return false
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
        result = 31 * result + description.hashCode()
        result = 31 * result + cooldownBurn.hashCode()
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (imageByteArray?.contentHashCode() ?: 0)
        return result
    }
}
