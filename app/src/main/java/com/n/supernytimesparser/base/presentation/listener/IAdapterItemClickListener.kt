package com.n.supernytimesparser.base.presentation.listener

/**
 * Base listener for adapter elements
 * @author Pavel Apanovskii on 23/03/2021.
 */
interface IAdapterItemClickListener {

    /**
     * element click
     *
     * @param position position in list
     */
    fun onClick(position: Int)
}