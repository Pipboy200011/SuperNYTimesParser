package com.n.supernytimesparser.application.utils.resource

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.annotation.AnyRes
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.annotation.XmlRes
import androidx.core.content.ContextCompat
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.util.Locale

/**
 * manager for working with resources
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class ResourceManager(private val context: Context) {

    fun getText(@StringRes resId: Int): CharSequence {
        return context.getText(resId)
    }

    fun getString(@StringRes resId: Int): String {
        return contextWithLocaleConfiguration.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return contextWithLocaleConfiguration.resources.getString(resId, *formatArgs)
    }

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any?): String {
        return contextWithLocaleConfiguration.resources.getQuantityString(resId, quantity, *formatArgs)
    }

    fun getLocaleQuantityString(
        locale: Locale?,
        @PluralsRes resId: Int,
        quantity: Int,
        vararg formatArgs: Any?
    ): String {
        val result: String
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        result = context.createConfigurationContext(config).resources.getQuantityString(resId, quantity, *formatArgs)
        return result
    }

    fun getPluralString(@PluralsRes resId: Int, quantity: Int): String {
        return contextWithLocaleConfiguration.resources.getQuantityString(resId, quantity)
    }

    fun getResourceId(resourceName: String?, resourceType: String?): Int {
        return context.resources.getIdentifier(
            resourceName, resourceType,
            context.packageName
        )
    }

    fun getRawResource(@RawRes resId: Int): InputStream {
        return context.resources.openRawResource(resId)
    }

    fun getColorResource(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    fun getIntArray(@ArrayRes resId: Int): IntArray {
        return context.resources.getIntArray(resId)
    }

    fun getResourceEntryName(@AnyRes resId: Int): String {
        return context.resources.getResourceEntryName(resId)
    }

    fun getXml(@XmlRes resId: Int): XmlPullParser {
        return context.resources.getXml(resId)
    }

    fun getDimension(@DimenRes resId: Int): Float {
        return context.resources.getDimension(resId)
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return context.getDrawable(resId)
    }

    private val contextWithLocaleConfiguration: Context
        get() {
            val config = Configuration(context.resources.configuration)
            config.setLocale(Locale.getDefault())
            return context.createConfigurationContext(config)
        }
}