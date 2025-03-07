package com.lihan.leagueoflegends.feature.core.database.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomSpell(
    val id: String,
    val name: String,
    val description: String,
    val cooldownBurn: String,
    val image: ByteArray?=null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoomSpell

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (cooldownBurn != other.cooldownBurn) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + cooldownBurn.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}
