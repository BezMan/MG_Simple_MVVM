package com.example.mg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mg.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by activityViewModels()

    private var isExistingNote: Boolean = false
    private lateinit var currentNote: MyTask
    private var taskId: Long = -1

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
            viewModel.addItemToList(MyTask(taskId, binding.edittext.text.toString()))
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun checkIsExistingNote() {
//        isExistingNote = intent.hasExtra(MainActivity.EXTRA_NOTE)
//        if (isExistingNote) {
//
//            currentNote = intent.getParcelableExtra(MainActivity.EXTRA_NOTE)!!
//            taskId = currentNote.id
//            binding.edittext.setText(currentNote.description)
//
//        } else { //NEW NOTE
////            noteId = random long
//            currentNote = MyTask(taskId)
//        }
    }

    fun addTask() {
//            val intent = Intent(this, DetailActivity::class.java)
//            startActivity(intent)
    }

}


