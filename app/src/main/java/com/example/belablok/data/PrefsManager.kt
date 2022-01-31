package com.example.belablok.data


import android.content.Context
import com.example.belablok.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefsManager {
    private val sharedPreferences =
        App.application.getSharedPreferences("BelotGameSettings", Context.MODE_PRIVATE)

    private val userEditor = sharedPreferences.edit()

    private val playersEditor = sharedPreferences.edit()



    fun saveUser(user: String) {
        userEditor.putString("user", user)
        userEditor.apply()
    }

    fun getUser() = sharedPreferences.getString("user", "")


    fun setLists(list: List<String>) {
        val gson = Gson()
        val json = gson.toJson(list)//converting list to Json
        playersEditor.putString("list", json)
        playersEditor.commit()
    }

    //getting the list from shared preference
    fun getList(): List<String> {
        val gson = Gson()
        val json = sharedPreferences.getString("list", null)
        val type = object : TypeToken<ArrayList<String>>() {}.type//converting the json to list
        return gson.fromJson(json, type)//returning the list
    }

    fun deletePlayers() {
        sharedPreferences.edit().remove("list").commit()
    }


}