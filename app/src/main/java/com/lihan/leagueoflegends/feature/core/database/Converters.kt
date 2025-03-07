package com.lihan.leagueoflegends.feature.core.database

import android.util.Base64
import androidx.room.TypeConverter
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.core.database.model.RoomSkin
import com.lihan.leagueoflegends.feature.core.database.model.RoomSpell
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromRoomSpell(roomSpells: List<RoomSpell>): String{
        return Json.encodeToString(roomSpells)
    }

    @TypeConverter
    fun toRoomSpell(data: String): List<RoomSpell> {
        return Json.decodeFromString<List<RoomSpell>>(data)
    }

    @TypeConverter
    fun fromRoomSkin(roomSkins: List<RoomSkin>): String{
        return Json.encodeToString(roomSkins)
    }

    @TypeConverter
    fun toRoomSkin(data: String): List<RoomSkin> {
        return Json.decodeFromString<List<RoomSkin>>(data)
    }

    @TypeConverter
    fun fromPassive(passive: Passive): String {
        return Json.encodeToString(passive)
    }

    @TypeConverter
    fun toPassive(data: String): Passive {
        return Json.decodeFromString<Passive>(data)
    }

    @TypeConverter
    fun fromByteArray(byteArray: ByteArray): String {
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    @TypeConverter
    fun toByteArray(data: String): ByteArray {
        return Base64.decode(data, Base64.DEFAULT)
    }

    @TypeConverter
    fun fromSpells(spells: List<ByteArray>): String {
        return Json.encodeToString(spells)
    }
    @TypeConverter
    fun toSpells(data: String): List<ByteArray>{
        return Json.decodeFromString(data)
    }



}