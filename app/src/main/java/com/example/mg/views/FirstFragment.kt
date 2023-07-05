package com.example.mg.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mg.R
import com.example.mg.data.MyTask
import com.example.mg.databinding.FragmentFirstBinding
import com.example.mg.views.MainActivity.Companion.addFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstFragment : Fragment(), MainListAdapter.OnItemClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var mainListAdapter: MainListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeResponse()

        initUI()
    }

    private val parentActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }

    private fun initUI() {

        //RECYCLER
        binding.recView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recView.setHasFixedSize(true)
        mainListAdapter = MainListAdapter(this)
        binding.recView.adapter = mainListAdapter

//        //CLICKS
        binding.fabAddNote.setOnClickListener {
            addTask()
        }
    }

    fun addTask() {
        parentActivity.addFragment(R.id.fragment_container, SecondFragment(), "Second")
    }

    fun editTask(task: MyTask) {
        viewModel.currentTask = task
        parentActivity.addFragment(R.id.fragment_container, SecondFragment(), "Second")
    }


    private fun observeResponse() {
        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            displayData(it)
        }
    }

    private fun displayData(list: List<MyTask>) {
        mainListAdapter.submitList(list)
    }


    override fun onItemClick(task: MyTask) {
        editTask(task)
    }

    override fun onItemLongClick(task: MyTask): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.delete(task)
            Snackbar.make(binding.notesLayout, "deleted - ${task.description}", Snackbar.LENGTH_LONG).show()
        }
        return true
    }

}


