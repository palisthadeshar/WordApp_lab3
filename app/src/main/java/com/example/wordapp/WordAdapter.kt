package com.example.wordapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for recyclerview in detail activity
 */
class WordAdapter(private val letterId:String, context: Context): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    private val chosenWords: List<String>
    //initializer for the chosen words
    init {
        //gets the words from the array.xml file
        val words = context.resources.getStringArray(R.array.words).toList()
        //retuns the word in the array list if the case matches ignoring the uppercase or lowercase
        //returns the collection it has shuffuled in place
        //takes the first nth item
        //and finally returns the sorted list
        chosenWords = words.filter { it.startsWith(letterId, ignoreCase = true) }.shuffled().take(5).sorted()
    }

    /**
     * reference for the views needed to be displayed
     */
    class WordViewHolder(val view: View):RecyclerView.ViewHolder(view)
    {
        val button = view.findViewById<Button>(R.id.button)
    }

    /**
     * creates new view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = LetterAdapter.Accessibility

        return WordViewHolder(layout)
    }
    /**
     * Replaces the content of an existing view with new words
     */
    override fun onBindViewHolder(holder: WordAdapter.WordViewHolder, position: Int) {
        val item = chosenWords[position]
        // Needed to call startActivity
        val context = holder.view.context

        // Set the text of the WordViewHolder
        holder.button.text = item
        holder.button.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int=chosenWords.size

}