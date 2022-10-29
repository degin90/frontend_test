package com.dapkod.test.test62.services

import android.content.Context
import android.content.SharedPreferences
import com.dapkod.test.test62.R


class SharedPref(context: Context) {
	
	private val sp: SharedPreferences =
		context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
	private val spe: SharedPreferences.Editor

	init {
		spe = sp.edit()
	}

	fun putData(key: String, value: Any) {
		when(value){
			is String -> spe.putString(key, value)
			is Boolean -> spe.putBoolean(key, value)
		}
		spe.commit()
	}

	fun getString(key: String) = sp.getString(key, null)

	fun removeAllData(){
		spe.clear()
		spe.commit()
	}
}