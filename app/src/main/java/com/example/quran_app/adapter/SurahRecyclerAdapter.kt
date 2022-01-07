package com.example.quran_app.adapter

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
import com.example.quran_app.models.SurahNames

class SurahRecyclerAdapter(
    val context:Context, val surahName:List<SurahNames>, val itemClick:(Int)->Unit):
    RecyclerView.Adapter<SurahRecyclerAdapter.ViewHolder>(),Filterable {
    var surahFilterList:List<SurahNames?>?= listOf()
    init {
        surahFilterList=surahName
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.surah_list_item,parent,false)
        return ViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       holder.BindSurah(surahName[position],context)
        surahFilterList?.get(position)?.let { holder.BindSurah(it,context) }
    }

    override fun getItemCount(): Int {
//       return surahName.count()
        return surahFilterList?.count()?:0
    }
    inner class ViewHolder(itemView:View,itemClick: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {
private val suratName:TextView=itemView.findViewById(R.id.surah_name)
        private val suratNumber:TextView=itemView.findViewById(R.id.surah_num)
        private val suratImage:ImageView=itemView.findViewById(R.id.surah_image)
        private val suratVerse:TextView=itemView.findViewById(R.id.verse_number_surah)
        fun BindSurah(suraahNames:SurahNames,context: Context){
            val resourceid=context.resources.getIdentifier(suraahNames.image,"drawable"
                ,context.packageName)
            suratImage.setImageResource(resourceid)
            suratName.text=suraahNames.title
            suratNumber.text=suraahNames.surahNumber.toString()
            suratVerse.text=suraahNames.verse.toString()
            itemView.setOnClickListener{itemClick(suraahNames.surahNumber-1)}
        }
    }

    override fun getFilter(): Filter {
        return costumFilter
    }
    private val costumFilter=object :Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch=constraint.toString()
            if (charSearch.isEmpty()){
                surahFilterList=surahName
            }else{
                val resultList= mutableListOf<SurahNames?>()
                for (row in surahName){
                    if (row.title.lowercase().contains(charSearch.lowercase())){
                        resultList.add(row)
                    }else if (row.surahNumber.toString().contains(charSearch)){
                        resultList.add(row)
                    }
                }
                surahFilterList=resultList
            }
            val filterList=FilterResults()
            filterList.values=surahFilterList
            return filterList
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
           surahFilterList=results?.values as? List<SurahNames?>
            notifyDataSetChanged()
        }

    }
}