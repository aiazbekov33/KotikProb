package com.example.kotikprob.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(fragmentCharacter: Int) :
    Fragment() {

    private lateinit var binding: Binding
    private lateinit var viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setupRequest()
        setupObservers()
        swipeRefresh()
    }

    open fun initialize() {}

    open fun setupListener() {}

    open fun setupRequest() {}

    open fun setupObservers() {}

    open fun swipeRefresh() {}
}