package com.project.rydrotin.ui.mood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.rydrotin.HabitsApp
import com.project.rydrotin.adapter.MoodAdapter
import com.project.rydrotin.databinding.ActivityMoodBinding
import com.project.rydrotin.local.entities.Mood
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoodBinding
    private lateinit var viewModel: MoodViewModel
    private lateinit var adapter: MoodAdapter
    private val calendar: Calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    val date = formatter.format(calendar.time).toString()
    private var selected = -1
    var userName: HabitsApp? = null
    private val map = mapOf(
        1 to "Very Bad",
        2 to "Bad",
        3 to "Neutral",
        4 to "Good",
        5 to "Very Good"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MoodViewModel::class.java]
        userName = (applicationContext as HabitsApp)

        updateUI()
        getSelected()

        binding.btn.setOnClickListener {
            if (selected != -1) {
                viewModel.checkTodayMood(date,userName!!.getData().toString())
                viewModel.state.observe(this@MoodActivity, object : Observer<Boolean> {
                    override fun onChanged(value: Boolean) {
                        if (value) {
                            var mood = viewModel.mood.value
                            if (mood != null) {
                                mood.value = selected
                                mood.message = binding.editMessage.text.toString()
                                viewModel.updateMood(mood)
                                adapter.editItem(mood)
                                Snackbar.make(
                                    binding.root,
                                    "Updated !",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else {
                                mood = Mood(
                                    userName!!.getData().toString(),selected, binding.editMessage.text.toString(), date
                                )
                                viewModel.insertMood(mood)
                                adapter.addItem(mood)
                                Snackbar.make(
                                    binding.root,
                                    "Inserted !",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            viewModel.state.removeObserver(this)
                            viewModel.clearTodayMood()
                        }
                    }
                })
            } else
                Snackbar.make(
                    binding.root,
                    "No Status Selected !",
                    Snackbar.LENGTH_SHORT
                ).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getSelected() {
        binding.veryBad.setOnClickListener {
            selected = 1
            binding.selected.text = "Selected : ${map[1]}"
        }

        binding.bad.setOnClickListener {
            selected = 2
            binding.selected.text = "Selected : ${map[2]}"
        }

        binding.neutral.setOnClickListener {
            selected = 3
            binding.selected.text = "Selected : ${map[3]}"
        }

        binding.good.setOnClickListener {
            selected = 4
            binding.selected.text = "Selected : ${map[4]}"
        }

        binding.veryGood.setOnClickListener {
            selected = 5
            binding.selected.text = "Selected : ${map[5]}"
        }
    }

    private fun updateUI() {
        viewModel.getMoodHistory(userName!!.getData().toString())
        viewModel.moodHistory.observe(this) {
            it?.let {
                adapter = MoodAdapter(it as MutableList<Mood>, this@MoodActivity)
                binding.recyclerMood.adapter = adapter
                val layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
                layoutManager.stackFromEnd = true
                layoutManager.reverseLayout = true
                binding.recyclerMood.layoutManager = layoutManager
            }
        }
    }
}