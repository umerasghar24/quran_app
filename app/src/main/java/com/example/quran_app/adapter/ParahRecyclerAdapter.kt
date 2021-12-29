package com.example.quran_app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quran_app.R
import com.example.quran_app.models.ParahNames
import java.util.*

class ParahRecyclerAdapter(
    val context: Context,
    val parahNames: List<ParahNames>,
    val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<ParahRecyclerAdapter.Holder>(), Filterable {
    var countryFilterList: List<ParahNames?>? = listOf()

    init {
        countryFilterList = parahNames
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.parah_list_item, parent, false)
        return Holder(view, itemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bindParahs(parahNames[position], context)
        countryFilterList?.get(position)?.let { holder.bindParahs(it, context) }
    }

    override fun getItemCount(): Int {
//        return parahNames.count()
        return countryFilterList?.count()?:0
    }

    inner class Holder(itemView: View, val itemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val parahNam: TextView = itemView.findViewById(R.id.parah_name)
        private val parahPict: ImageView = itemView.findViewById(R.id.parah_image)
        private val parahNum: TextView = itemView.findViewById(R.id.parah_num)
        private val parahVerse: TextView = itemView.findViewById(R.id.verse_number_parah)
        fun bindParahs(parahNames: ParahNames, context: Context) {
            val resourceId = context.resources.getIdentifier(
                parahNames.image, "drawable", context.packageName
            )
            parahPict.setImageResource(resourceId)
            parahNam.text = parahNames.title
            parahNum.text = parahNames.parahNumber.toString()
            parahVerse.text = parahNames.verse.toString()
            itemView.setOnClickListener { itemClick(parahNames.parahNumber-1) }

        }

    }

    override fun getFilter(): Filter {
        return costumFilter
    }

    private val costumFilter = object : Filter() {
        @SuppressLint("NotifyDataSetChanged")
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val charSearch = constraint.toString()
            if (charSearch.isEmpty()) {
                countryFilterList = parahNames
            } else {
                val resultList = mutableListOf<ParahNames?>()
                for (row in parahNames) {
                    if (row.title.lowercase().contains(charSearch.lowercase())
                    ) {
                        resultList.add(row)
                    }
                }
                countryFilterList = resultList
            }
            val filterResults = FilterResults()
            filterResults.values = countryFilterList
            return filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            countryFilterList = results?.values as? List<ParahNames?>
            notifyDataSetChanged()
        }
    }


}

