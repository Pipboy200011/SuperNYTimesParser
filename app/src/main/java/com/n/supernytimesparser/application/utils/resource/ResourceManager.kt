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
    /**
     * Получает строку по идентификатору ресурса
     *
     * @param resId идентификатор строкового ресурса
     * @return строка, которой соответствует индентификатор
     */
    fun getText(@StringRes resId: Int): CharSequence {
        return context.getText(resId)
    }

    /**
     * Получает строку по идентификатору ресурса
     *
     * @param resId идентификатор строкового ресурса
     * @return строка, которой соответствует индентификатор
     */
    fun getString(@StringRes resId: Int): String {
        return contextWithLocaleConfiguration.getString(resId)
    }

    /**
     * Получает строку по идентификатору ресурса
     *
     * @param resId      идентификатор строкового ресурса
     * @param formatArgs Аргументы, которые будут использоваться для подстановки строки
     * @return строка, которой соответствует индентификатор
     */
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return contextWithLocaleConfiguration.resources.getString(resId, *formatArgs)
    }

    /**
     * Получает строку по идентификатору и по квантификатору количества и в зависимости от аргументов
     *
     * @param resId      идентификатор строкового ресурса
     * @param quantity   количество, по которому нужно получить форматированную строку
     * @param formatArgs Аргументы, которые будут использоваться для подстановки строки
     * @return строка, которой соответствует индентификатор
     */
    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any?): String {
        return contextWithLocaleConfiguration.resources.getQuantityString(resId, quantity, *formatArgs)
    }

    /**
     * Применив заданную локаль получает строку по идентификатору и по квантификатору количества и в зависимости от аргументов.
     *
     * @param locale     локаль применительно к которой берут строку
     * @param resId      идентификатор строкового ресурса
     * @param quantity   количество, по которому нужно получить форматированную строку
     * @param formatArgs Аргументы, которые будут использоваться для подстановки строки
     * @return строка, которой соответствует индентификатор
     */
    fun getLocaleQuantityString(locale: Locale?, @PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any?): String {
        val result: String
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        result = context.createConfigurationContext(config).resources.getQuantityString(resId, quantity, *formatArgs)
        return result
    }

    /**
     * Получает строку по идентификатору и по квантификатору количества
     *
     * @param resId    идентификатор строкового ресурса типа [PluralsRes]
     * @param quantity количество, по которому нужно получить форматированную строку
     * @return строка по идентификатору и квантификатору количества
     */
    fun getPluralString(@PluralsRes resId: Int, quantity: Int): String {
        return contextWithLocaleConfiguration.resources.getQuantityString(resId, quantity)
    }

    /**
     * Получает идентификатор ресурса по его имени
     *
     * @param resourceName имя ресурса
     * @param resourceType тип ресурса. Например `raw, string, drawable` и т.д. Может быть
     * null, если тип ресурса указан в имени ресурса.
     * Например `string/some_string`
     * @return идентификатор ресурса
     * @see android.content.res.Resources.getIdentifier
     */
    fun getResourceId(resourceName: String?, resourceType: String?): Int {
        return context.resources.getIdentifier(
            resourceName, resourceType,
            context.packageName
        )
    }

    /**
     * Получает ресурс типа raw в виде потока
     *
     * @param resId идентификатор ресурса, который нужно получить
     * @return поток для чтения ресурса
     * @see android.content.res.Resources.openRawResource
     */
    fun getRawResource(@RawRes resId: Int): InputStream {
        return context.resources.openRawResource(resId)
    }

    /**
     * Получает цвет из ресурсов по id
     *
     * @param resId идентификатор ресурса, который нужно получить
     * @return значение цвета в форме 0xAARRGGBB
     */
    fun getColorResource(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    /**
     * Получает массив строк по идентификатору ресурса
     *
     * @param resId идентификатор массива строкового ресурса
     * @return массив строк, которой соответствует индентификатор
     */
    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    /**
     * Получает массив int по идентификатору ресурса
     *
     * @param resId идентификатор массива int ресурса
     * @return массив int, которой соответствует индентификатор
     */
    fun getIntArray(@ArrayRes resId: Int): IntArray {
        return context.resources.getIntArray(resId)
    }

    /**
     * Получает имя для искомого идентификатора ресурса.
     *
     * @param resId идентификатор ресурса, который нужно получить
     * @return строку, содержащую имя ресурса.
     */
    fun getResourceEntryName(@AnyRes resId: Int): String {
        return context.resources.getResourceEntryName(resId)
    }

    /**
     * Получает [XmlPullParser] для искомого идентификатора XML ресурса.
     *
     * @param resId идентификатор ресурса XML, который нужно получить
     * @return [XmlPullParser] искомого ресурса.
     */
    fun getXml(@XmlRes resId: Int): XmlPullParser {
        return context.resources.getXml(resId)
    }

    /**
     * Получает размер из ресурсов по id.
     *
     * @param resId идентификатор ресурса, который нужно получить.
     * @return значение размера полученного по id.
     */
    fun getDimension(@DimenRes resId: Int): Float {
        return context.resources.getDimension(resId)
    }

    /**
     * Получает Drawable
     */
    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return context.getDrawable(resId)
    }

    private val contextWithLocaleConfiguration: Context
        private get() {
            val config = Configuration(context.resources.configuration)
            config.setLocale(Locale.getDefault())
            return context.createConfigurationContext(config)
        }
}