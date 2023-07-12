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
import com.example.mg.views.MainActivity.Companion.toggleShowView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment(), ListFragmentAdapter.OnItemClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MutualViewModel by activityViewModels()

    private lateinit var listFragmentAdapter: ListFragmentAdapter

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
        listFragmentAdapter = ListFragmentAdapter(this)
        binding.recView.adapter = listFragmentAdapter

//        //CLICKS
        binding.fabAddNote.setOnClickListener {
            addTask()
        }
    }

    fun addTask() {
        parentActivity.addFragment(R.id.fragment_container, DetailFragment(), "Second")
    }

    fun editTask(task: MyTask) {
        viewModel.currentTask = task
        parentActivity.addFragment(R.id.fragment_container, DetailFragment(), "Second")
    }


    private fun observeResponse() {
        viewModel.taskListState.observe(viewLifecycleOwner) {
            displayData(it)
        }
    }

    private fun displayData(list: List<MyTask>) {
        listFragmentAdapter.submitList(list)
        //no notes layout
        binding.noNotesView.toggleShowView(list.isEmpty())

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


