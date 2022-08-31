package com.rainfool.json

import com.alibaba.fastjson2.JSON
import com.google.gson.Gson

/**
 *
 * @author krystian
 */
object JsonUtil {
    private val gson = Gson()
    fun <T> fromJson(json: String, classOfT: Class<T>): T {
        return JSON.parseObject(json, classOfT)
//        return gson.fromJson(json,classOfT)
    }

    fun toJson(src: Any): String {
        return JSON.toJSONString(src)
//        return gson.toJson(src)
    }
}