package com.example.niftynotes.resources

import android.content.Context
import com.example.niftynotes.utils.StringProvider

class AndroidStringProvider(private val context: Context) : StringProvider {
    override fun getString(id: String): String {
        val resId = context.resources.getIdentifier(id, "string", context.packageName)
        return if (resId != 0) context.getString(resId) else id
    }
}