package com.n.supernytimesparser.base.domain

/**
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
data class ErrorMessage(var errors: String?) {

    /**
     * warnings list
     */
    private var warnings: String? = null
}