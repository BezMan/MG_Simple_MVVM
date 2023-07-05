package com.example.mg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mg.MainActivity.Companion.addFragment
import com.example.mg.databinding.FragmentFirstBinding

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

        viewModel.fetchData()

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
        viewModel.viewState.observe(viewLifecycleOwner) { response ->
            displayData(response)
        }
    }

    private fun displayData(list: List<MyTask>) {
        mainListAdapter.submitList(list)
    }


    override fun onItemClick(note: MyTask) {
        editTask(note)
    }

}


