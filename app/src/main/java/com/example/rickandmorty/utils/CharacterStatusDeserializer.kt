package com.example.rickandmorty.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CharacterStatusDeserializer : JsonDeserializer<StatusQ> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): StatusQ {
        val statusString = json?.asString
        for (enum in StatusQ.values()) {
            if (enum.provider == statusString) {
                return enum
            }
        }
        throw IllegalArgumentException("Unknown tsp $statusString!")
    }
}