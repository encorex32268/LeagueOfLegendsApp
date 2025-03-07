package com.lihan.leagueoflegends.feature.core.database.mapper

import com.lihan.leagueoflegends.feature.champion.domain.model.Skin
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell
import com.lihan.leagueoflegends.feature.core.database.model.RoomSkin
import com.lihan.leagueoflegends.feature.core.database.model.RoomSpell

fun RoomSpell.toSpell(): Spell {
    return Spell(
        id = id,
        name = name,
        description = description,
        cooldownBurn = cooldownBurn,
        imageByteArray = image,
        image = ""
    )
}

fun RoomSkin.toSkin(): Skin {
    return Skin(
        num = num,
        image = image,
        name = ""
    )
}

fun Skin.toRoomSkin(): RoomSkin {
    return RoomSkin(
        num = num,
        image = image
    )
}

fun Spell.toRoomSpell(): RoomSpell {
    return RoomSpell(
        id = id,
        name = name,
        description = description,
        cooldownBurn = cooldownBurn,
        image = imageByteArray
    )
}