package com.example.mg

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mg.MainActivity.Companion.EXTRA_NOTE
import com.example.mg.databinding.FragmentSecondBinding

class DetailActivity  : AppCompatActivity() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel by viewModels<MyViewModel>()
    private var isExistingNote: Boolean = false
    private lateinit var currentNote: MyTask
    private var taskId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIsExistingNote()

        initUI()

    }

    private fun initUI() {
        binding.saveBtn.setOnClickListener {
            viewModel.addItemToList(MyTask(taskId, binding.edittext.text.toString()))
            finish()
        }
    }

    private fun checkIsExistingNote() {
        isExistingNote = intent.hasExtra(EXTRA_NOTE)
        if (isExistingNote) {

            currentNote = intent.getParcelableExtra(EXTRA_NOTE)!!
            taskId = currentNote.id
            binding.edittext.setText(currentNote.description)

        } else { //NEW NOTE
//            noteId = random long
            currentNote = MyTask(taskId)
        }
    }

}
