package com.n.supernytimesparser.base.presentation.viewmodel

import androidx.core.util.Consumer
import androidx.core.util.Supplier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
class ViewModelProviderFactory<VM : BaseViewModel?>(
    viewModelCreator: Supplier<VM>,
    onCreateHook: Consumer<VM>?
) : Factory {

    private val mViewModelCreator: Supplier<VM> = viewModelCreator
    private val mOnCreateHook: Consumer<VM>? = onCreateHook

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: VM = mViewModelCreator.get()
        mOnCreateHook?.accept(viewModel)
        return viewModel as T
    }
}
