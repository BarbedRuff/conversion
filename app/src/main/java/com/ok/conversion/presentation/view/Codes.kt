package com.ok.conversion.presentation.view

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ok.conversion.App
import com.ok.conversion.databinding.FragmentCodesListDialogItemBinding
import com.ok.conversion.databinding.FragmentCodesListDialogBinding

const val SupportedCodes = "SupportedCodes"

class Codes : BottomSheetDialogFragment() {

    private var _binding: FragmentCodesListDialogBinding? = null
    private val binding get() = _binding!!
    private var fromOrTo: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCodesListDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fromOrTo = arguments?.getBoolean("fromOrTo")!!
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = arguments?.getStringArray(SupportedCodes)?.let {
            ItemAdapter(it)
        }
    }

    private inner class ViewHolder internal constructor(binding: FragmentCodesListDialogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        internal val text: TextView = binding.text
        init{
            binding.root.setOnClickListener{
                if(fromOrTo){
                    App.userPreferences.basedCode = text.text.toString().split(" - ")[0]
                }
                else{
                    App.userPreferences.targetCode = text.text.toString().split(" - ")[0]
                }
                (getActivity() as MainActivity).updateButtons()
                onDestroyView()
            }
        }
    }

    private inner class ItemAdapter internal constructor(private val supportedCodes: Array<String>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                FragmentCodesListDialogItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = supportedCodes[position]
        }

        override fun getItemCount(): Int {
            return supportedCodes.size
        }
    }

    companion object {
        fun newInstance(
            supportedCodes: List<List<String>>,
            fromOrTo: Boolean
        ): Codes =
            Codes().apply {
                arguments = Bundle().apply {
                    putStringArray(
                        "SupportedCodes",
                        supportedCodes
                            .map { "${it[0]} - ${it[1]}" }
                            .toTypedArray()
                    )
                    putBoolean("fromOrTo", fromOrTo)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}