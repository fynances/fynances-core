package fynances.core.util

import kotlin.reflect.memberProperties

/**
 * author Andrey_Yevseyenko
 * functions and annotations for converting objects to maps and vice versa
 */


/**
 * Annotated property will not be converted to/from map
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class ToMapSkip

/**
 * Annotated property will have other name in a map
 * @param name a name of a property in a map
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class ToMapName(val name: String)

/**
 * Converts Kotlin class to a map. All properties will be converted
 * except the ones which are annotated with <code>@ToMapSkip</code>
 * Property names will be used as a key unless <code>@ToMapName("other name")</code> is used.
 * @param obj object to convert
 */
fun objectToMap(obj: Any): Map<String, Any?> = obj.javaClass.kotlin.memberProperties.filter {
    !it.annotations.any { it is ToMapSkip }
}.map {
    val annotation = it.annotations.firstOrNull { it is ToMapName && it.name.isNotBlank() } as ToMapName?
    val name = annotation?.name ?: it.name
    name to it.get(obj)
}.toMap()

