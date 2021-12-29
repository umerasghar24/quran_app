package com.example.quran_app.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.quran_app.R
import com.example.quran_app.controller.BookMarkFragment
import com.example.quran_app.controller.PdfViewActivity
import com.example.quran_app.models.BookmarksParah
import com.example.quran_app.services.DataServices
import com.example.quran_app.viewModel.ParahViewModel
import kotlinx.android.synthetic.main.bookmark_list.view.*

private lateinit var mparahViewModel: ParahViewModel

class BookmarksAdapter(bookMarkFragment: BookMarkFragment) :RecyclerView.Adapter<BookmarksAdapter.Holder>(),Filterable

 {
     var bFilterList: List<BookmarksParah?> = emptyList()
    constructor(bookMarkFragment: BookMarkFragment, vmp: ViewModelProvider,context:Context) : this(bookMarkFragment) {
       vm =vmp
        this.context=context
    }
    private lateinit var vm:ViewModelProvider
     private lateinit var context: Context
    private var parahList = emptyList<BookmarksParah>()
init {
    bFilterList=parahList
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.bookmark_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = parahList[position].let { holder.bindParahs(it,context) }
//        holder.itemView.parah_num.text = currentItem.id.toString()

//holder.bindparah(parahList[position],context)
    }


    override fun getItemCount(): Int {
        return parahList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val parahPict: ImageView = itemView.findViewById(R.id.surrah_image)
        fun bindParahs(currentItem: BookmarksParah, context: Context) {
            itemView.page_bookmarks.text = currentItem.page.toString()
            itemView.bookmark_title.text=currentItem.image
            itemView.bookmarks_name.text=DataServices.getparahFromPage(currentItem.page).title
        val resourceId = context.resources.getIdentifier(
//            "ic_1"
            DataServices.getparahFromPage(currentItem.page).image, "drawable", context.packageName
        )
        itemView.surrah_image.setImageResource(resourceId)
//        (holder.itemView.surrah_image as ImageView).setImageResource(resourceId)

            Log.e(TAG, "onBindViewHolder: " )
            itemView.delete_icon.setOnClickListener {
                Log.e(TAG, "onBindViewHolder: delete" )
                deleteSurah(currentItem)
            }
            itemView.setOnClickListener {
                val intent:Intent=Intent(context,PdfViewActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(context.getString(R.string.bookmark_key), currentItem.page)
                context.startActivity(intent)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<BookmarksParah>) {
        this.parahList = user
        notifyDataSetChanged()
    }

    private fun deleteSurah(a: BookmarksParah) {
        mparahViewModel = vm.get(ParahViewModel::class.java)
//        mparahViewModel.deleteSurah(a.id)
        val dialogueBuilder=AlertDialog.Builder(context)
        dialogueBuilder.setPositiveButton("Yes"){_,_->
            mparahViewModel.deleteSurah(a.id)
            Toast.makeText(context, "You have successfully deleted this bookmark", Toast.LENGTH_SHORT).show()
        }
        dialogueBuilder.setNegativeButton("No"){_,_->

        }
        dialogueBuilder.setTitle("Delete?")

        dialogueBuilder.setMessage("Sure you want to delete this bookmark?")
        dialogueBuilder.create().show()




    }

     override fun getFilter(): Filter {
return costumFilter
     }
     val costumFilter=object:Filter(){
         override fun performFiltering(constraint: CharSequence?): FilterResults {
             val charSearch=constraint.toString()
             if (charSearch.isEmpty()){
                 bFilterList=parahList
             }else{
                 val resultList= mutableListOf<BookmarksParah>()
                 for (row in parahList){
                     if (row.id.toString().lowercase().contains(charSearch.lowercase())){
                         resultList.add(row)
                     }
                 }
                 bFilterList=resultList
             }
             val filterList=FilterResults()
             filterList.values=bFilterList
             return filterList
         }

         @Suppress("UNCHECKED_CAST")
         override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
bFilterList= (results?.values as? List<BookmarksParah?>)!!
             notifyDataSetChanged()
         }

     }


 }