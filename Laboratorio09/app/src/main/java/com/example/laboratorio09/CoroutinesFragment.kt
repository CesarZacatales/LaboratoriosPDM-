package com.example.laboratorio09

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.corutinesdemo.databinding.FragmentCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesFragment : Fragment() {

    private lateinit var binding: FragmentCoroutinesBinding
    private var count = 0

    // "Job" represents a unit of work in a coroutine.
    // It is declared globally so that it can later be canceled in another function if necessary.
    private var downloadJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoroutinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countButton.setOnClickListener {
            binding.counterTextView.text = count++.toString()
        }

        binding.downloadButton.setOnClickListener {
            val scope = CoroutineScope(Dispatchers.IO)
            downloadJob = scope.launch {
                downloadUserData()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            // This is NOT running in the main thread
            Log.i(MainActivity.APP_TAG, "Downloading user $i in ${Thread.currentThread().name}")

            // This is an ERROR
            // tvMessage.text = "Downloading user $i" // Can not be called in a background thread

            // Switch to main thread to update view
            withContext(Dispatchers.Main) {
                binding.messageTextView.text = "Downloading user $i"
            }
            delay(100)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // If downloadJob is running then cancel the coroutine
        // when fragment is closed
        downloadJob?.cancel()
    }
}