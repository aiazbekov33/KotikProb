package com.example.kotikprob.ui.fragments.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.kotikprob.databinding.FragmentMyDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDialogFragment : DialogFragment() {

    private var _binding: FragmentMyDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentMyDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        builder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupArgs()
        return builder
    }

    private fun setupArgs() {
        Glide.with(binding.dialogIv)
            .load(MyDialogFragmentArgs.fromBundle(requireArguments()).image)
            .into(binding.dialogIv)

    }
}

