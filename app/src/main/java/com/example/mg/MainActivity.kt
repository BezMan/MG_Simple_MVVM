package com.example.mg

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(R.id.fragment_container, FirstFragment())
    }


    companion object {

        private inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
            beginTransaction().func().commit()
        }

        internal fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
            supportFragmentManager.doTransaction { replace(frameId, fragment, "First") }
        }

        internal fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment, fragName: String) {
            supportFragmentManager.doTransaction { add(frameId, fragment, fragName).addToBackStack(fragName) }
        }

        const val FRAGMENT_DATA = "FRAGMENT_DATA"

        const val EXTRA_NOTE = "EXTRA_NOTE"
        const val ID_NOTE = "ID_NOTE"
        const val EXTRA_IS_ARCHIVED = "EXTRA_IS_ARCHIVED"

        fun View.toggleShowView(show: Boolean) {
            visibility = if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}

