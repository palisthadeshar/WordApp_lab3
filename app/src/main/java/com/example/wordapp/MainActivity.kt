package com.example.wordapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var isLinearLayoutManager = true
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate the layout
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        changeLayout()
    }

    /**
     * changes the layout from linear to grid and vice versa
     */
    private fun changeLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        }
        recyclerView.adapter = LetterAdapter()
    }

    /**
     * Set the drawable for the menu icon based on which LayoutManager is currently in use
     */
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.baseline_grid_view_24)
            else ContextCompat.getDrawable(this, R.drawable.baseline_view_headline_24)

    }
    /**
     * inflate the options menu and perform any additional setup
     */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)

        return true
    }

    /**
     * call the changeLayout() function here
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                changeLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }






}
