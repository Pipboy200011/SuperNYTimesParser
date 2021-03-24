package com.n.supernytimesparser.base.domain

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
class BaseDomainBean<T> {

    /**
     * data
     */
    var successObject: T? = null

    /**
     * error
     */
    var errorObject: ErrorMessage? = null
}