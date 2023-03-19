package com.example.wordapp

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * This is the adapter class for the recycleview in main activity.
 * LetterAdapater of LetterViewHOlder type
 * Holds the letters in the view
 */
class LetterAdapter:RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private val letterList = ('A').rangeTo('Z').toList()

    /**
     * Reference for the views(button) needed to display item in the list
     */
    class LetterViewHolder(val view: View):RecyclerView.ViewHolder(view)
    {
        val button = view.findViewById<Button>(R.id.button)
    }

    /**
     * creates new view. Gets the view from layout file(item_view.xml)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = Accessibility
        return LetterViewHolder(layout)
    }

    /**
     * counts number of items to be displayed in the view
     */
    override fun getItemCount(): Int {
        return letterList.size
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = letterList.get(position)
        holder.button.text = item.toString()
        holder.button.setOnClickListener {
            val context = holder.itemView.context
            //intent is a activity launcher and we pass the name of the activity we want to launch
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.LETTER, holder.button.text.toString())
            context.startActivity(intent) //to start the detail page

        }
    }
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }


}