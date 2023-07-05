package com.example.mg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mg.data.MyTask
import com.example.mg.databinding.FragmentSecondBinding
import java.util.UUID

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkIsExistingNote()

        initUI()

    }

    private fun initUI() {
        binding.saveBtn.setOnClickListener {

            val text = binding.edittext.text.toString()
            val status = binding.checkboxToggleButton.isChecked

            val id = viewModel.currentTask?.id
            val myTask: MyTask = if (id == null) {
                MyTask(text, status)
            } else {
                MyTask(text, status, id = id)
            }
            viewModel.currentTask = myTask
            viewModel.addItemToList(viewModel.currentTask!!)

            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun checkIsExistingNote() {
        val currentTask = viewModel.currentTask

        if (currentTask != null) { // EXISTING task to edit
            binding.edittext.setText(currentTask.description)
            binding.checkboxToggleButton.isChecked = currentTask.status
        }
    }


}


