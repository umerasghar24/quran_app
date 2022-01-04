package com.example.quran_app.controller

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.example.quran_app.R
import com.example.quran_app.models.BookmarksParah
import com.example.quran_app.models.ParahNames
import com.example.quran_app.services.DataServices
import com.example.quran_app.util.widgets.QuranScrollHandle
import com.example.quran_app.viewModel.ParahViewModel
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener
import com.github.barteksc.pdfviewer.util.FitPolicy
import free.translate.languagetranslator.cameratranslation.voicetranslator.TinyDB
import kotlinx.android.synthetic.main.activity_pdf_view.*
import kotlin.properties.Delegates


class PdfViewActivity : BaseActivity() {
    private lateinit var mtoolBar: Toolbar
    lateinit var scrollChanger: TextView
    lateinit var onPageScrollListner: OnPageScrollListener
    private var currentSurah by Delegates.notNull<Int>()
    private var fabPage by Delegates.notNull<Int>()
    private val TAG: String = "pdf viewr"
    lateinit var pdfView: PDFView
    lateinit var nex: ImageView
    lateinit var previ: ImageView
    private lateinit var mUserViewModel: ParahViewModel
    private var bookmarkpage by Delegates.notNull<Int>()
    var surah by Delegates.notNull<Int>()
    var parah by Delegates.notNull<Int>()
    lateinit var editText: EditText
    private var nightMode: Boolean = false
    private var swipeVertical: Boolean = true
    private lateinit var mCostumTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        scrollChanger = findViewById(R.id.horiz_vertical_scroll)
        bookmarkpage = intent.getIntExtra(getString(R.string.bookmark_key), -1)
        fabPage = TinyDB.getInstance(this).getInt(getString(R.string.recent_page), 0)
        currentSurah =
            DataServices.getsurahFromPage(fabPage).surahNumber //for getting surah from page number
//        toolbar1.title=DataServices.getparahFromPage(fabPage).title
        mtoolBar = findViewById(R.id.toolbar1)
        setSupportActionBar(mtoolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mtoolBar.setNavigationOnClickListener { view -> onBackPressed() }
        surah = intent.getIntExtra(getString(R.string.surah_key), -1)
        Log.e("PdfViewActivity", "you selected$surah")
        parah = intent.getIntExtra(getString(R.string.parah_key), -1)
        Log.e("PdfViewActivity", "you selected$parah")
        pdfView = findViewById(R.id.pdf_viewer)
        nex = findViewById(R.id.next)
        previ = findViewById(R.id.previous)
        Log.e(TAG, "onCreate: $fabPage $surah $parah ")

        onPageScrollListner = OnPageScrollListener { page, positionOffset ->
            Log.e(TAG, "Page on scroll: $page  $positionOffset")
            mCostumTitle=mtoolBar.findViewById(R.id.custom_title)

                mCostumTitle.text = DataServices.getsurahFromPage(PAGES_COUNT-page).title

//to show text in toolbar title
//            getSupportActionBar()?.title = DataServices.getsurahFromPage(PAGES_COUNT - page).title
        }

        nex.setOnClickListener {
/*
var su=DataServices.surah[surah ++].page
//            surah=surah ++
            su=pdfView.currentPage
            pdfView.jumpTo(su)
*/
            if (surah >= 0) {
                //load surrah
                if (surah > 113) {
                    Toast.makeText(this, "This is Last Surah", Toast.LENGTH_SHORT).show()
                } else {
                    surah++
                    LoadSurah(surah)
                }

            } else if (parah >= 0) {

                //load parah
//                val mPageGo=DataServices.surah[surah].page
//                pdfView.jumpTo(mPageGo)
                parah++
                LoadParah(parah)
            } else { //if comming from fab page
                currentSurah++
                LoadSurah(currentSurah)
            }

//            var mPageGo=pdfView.currentPage

        }
        previ.setOnClickListener {
            if (surah >= 0) {
                //load surrah
                surah--
                LoadSurah(surah)
            } else if (parah >= 0) {
                //load parah
//                val mPageGo=DataServices.surah[surah].page
//                pdfView.jumpTo(mPageGo)
                parah--
                LoadParah(parah)
            } else { //if comming from fab page
                currentSurah--
                LoadSurah(currentSurah)
            }
        }

//         surah --
//             val mPageG=DataServices.surah[surah].page
//            pdfView.jumpTo(mPageG)
////            pdfView.jumpTo(DataServices.surah[surah --].page)

        imageView3.setOnClickListener {

//            val pagehold=DataServices.parahs[parah].page
            if (pdfView.isSwipeVertical) {

                swipeVertical = true
                loadDocument(PAGES_COUNT-pdfView.currentPage)
              /*  pdfView.fromAsset(ASSET_NAME)
                    .defaultPage(PAGES_COUNT-pdfView.currentPage)
//                    .pages(pdfView.currentPage)
                    .onPageScroll(onPageScrollListner)
                    .enableSwipe(swipeVertical)
                    .nightMode(nightMode)
                    .swipeHorizontal(true).pageFitPolicy(FitPolicy.HEIGHT)
                    .enableAnnotationRendering(true)
                    .scrollHandle(QuranScrollHandle(this))
                    .spacing(10).pageFitPolicy(FitPolicy.WIDTH).load()*/
//                pdfView.isSwipeEnabled=swipeVertical
                scrollChanger.setText(getString(R.string.vertical))
            } else {
                swipeVertical = false
                loadDocument(PAGES_COUNT-pdfView.currentPage)
            /*    pdfView.fromAsset(ASSET_NAME)
                    .enableSwipe(true)
                    .defaultPage(PAGES_COUNT-pdfView.currentPage)
                    .onPageScroll(onPageScrollListner)
                    .nightMode(nightMode)
//                    .pages(pdfView.currentPage)
                    .swipeHorizontal(swipeVertical).pageFitPolicy(FitPolicy.HEIGHT)
                    .enableAnnotationRendering(true)
                    .scrollHandle(QuranScrollHandle(this,true))
                    .spacing(10).pageFitPolicy(FitPolicy.WIDTH)
                    .load()*/
//                pdfView.isSwipeEnabled=swipeVertical
                scrollChanger.setText(getString(R.string.horizontol))

            }
//pdfView.loadPages()
        }


        val para: ParahNames = DataServices.parahs[1]
        Log.e(TAG, "onCreate: Title " + para.title)
        if (bookmarkpage >= 0) {
            LoadPage(bookmarkpage)
        } else if (surah >= 0) {
            LoadSurah(surah)
        } else if (parah >= 0) {
            LoadParah(parah)
        } else {
            loadDocument(fabPage)
//            show()
        }
        mUserViewModel = ViewModelProvider(this).get(ParahViewModel::class.java)
        val goTo: ImageView = findViewById(R.id.search)

        goTo.setOnClickListener {
            gotoPage(bookmarkpage)
        }
    }

    private fun gotoPage(goto: Int) {
        val editText = EditText(this)
        editText.setHint("Enter Page Number")
        editText.keyListener = DigitsKeyListener.getInstance("0123456789")
        val dialougeBuilder = AlertDialog.Builder(this)
        dialougeBuilder.setMessage("Go To Page")
        dialougeBuilder.setView(editText)
        dialougeBuilder.setPositiveButton("Done") { _, _ ->
            //by getting text value and jump to that page
            if (!editText.getText().toString().equals("")) {
                val getValue: Int = editText.getText().toString().toInt()
                if (getValue < 550) {
                    pdfView.jumpTo(PAGES_COUNT-getValue+ PAGE_DIFFERENCE )
                }
//                 else {
////                dialougeBuilder.show()
//                    Toast.makeText(
//                        this, "Please Select Page Between 1-549",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }

            } else {
                Toast.makeText(this, "Please Enter a valid page number", Toast.LENGTH_LONG).show()
            }
        }
        dialougeBuilder.setNegativeButton("Cancel") { _, _ -> }
        dialougeBuilder.show()
        val editor = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")) {
                    val input: Int = Integer.parseInt(s.toString())
//                    s.toString()
                    if (input < 0 || input > 550) {
//                    editText.setError("Invalid Input")
//                    editText.error="Enter a valid Page Number"
                        editText.setError("Enter Number Between 1-550")
                    }
                }

//                else{
//                    pdfView.jumpTo(input)
//                }

            }

        }

        editText.addTextChangedListener(editor)


    }

    private fun LoadPage(page: Int) {
        loadDocument(page - 1)
//        show()
    }

    private fun LoadParah(i: Int) {
        if (i < 0 || i > 30)
            return

        loadDocument(DataServices.parahs[i].page)
//        show()
    }

    private fun LoadSurah(i: Int) {
        if (i < 0 || i > 114)
            return
        loadDocument(DataServices.surah[i].page)
//        show()
    }

    private fun loadDocument(i: Int) {
//  pdfView.fromAsset(ASSET_NAME).defaultPage(PAGES_COUNT-i)
//        pdfView.loadPages()

        val pagesArray = (PAGES_COUNT downTo 0).toList()
        Log.e(TAG, "loadDocument: $pagesArray")

        pdfView.fromAsset(ASSET_NAME)
            .enableSwipe(true)
            .pages(*pagesArray.toIntArray())
            .defaultPage(PAGES_COUNT - i)
            .pageSnap(true)
            .pageFling(true)
            .nightMode(nightMode)
            .onPageScroll(onPageScrollListner)
            .swipeHorizontal(swipeVertical)
            .pageFitPolicy(FitPolicy.HEIGHT)
            .spacing(10)
            .enableAnnotationRendering(true)
            .scrollHandle(QuranScrollHandle(this, true))
            .pageFitPolicy(FitPolicy.WIDTH)
            .load()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_viewer, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("onOptionsItemSelected", "ïtem = ${item.itemId}")
        if (item.itemId == R.id.bookmarks) {
            editText = EditText(this)
//            editText.setHint("Enter title")
            editText.setText(DataServices.getsurahFromPage(PAGES_COUNT - pdfView.currentPage).title)
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setView(editText)
            dialogBuilder.setMessage("Enter Title")
            dialogBuilder.setPositiveButton("Done") { _, _ ->
                insertDataToDatabase()

            }
            dialogBuilder.setNegativeButton("Cancel") { _, _ -> }
            dialogBuilder.show()


        }
        if (item.itemId == R.id.color_mode) {
            if (!nightMode) {
                nightMode = true
//                loadDocument(pdfView.currentPage)

                pdfView.setNightMode(nightMode)
                item.setIcon(R.drawable.ic_light)


//                loadDocument(pdfView.currentPage)


            } else {
                nightMode = false


                pdfView.setNightMode(nightMode)
                item.setIcon(R.drawable.ic_dark_mode)
//                  loadDocument(pdfView.currentPage)
            }
            pdfView.loadPages()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val getText = editText.text.toString()
        val bookmark = BookmarksParah(
            id = 0,
            page = PAGES_COUNT-pdfView.currentPage + PAGE_DIFFERENCE,
            title = getText

        )
        mUserViewModel.addParah(bookmark)



        Toast.makeText(this, "BookMark Saved", Toast.LENGTH_SHORT).show()

    }
//
//    override fun onBackPressed() {
//
//        super.onBackPressed()
//    }

    override fun onPause() {
        super.onPause()
        val tinyDB = TinyDB(this)
        tinyDB.putInt(getString(R.string.recent_page), PAGES_COUNT-pdfView.currentPage)

    }


//    fun storePrefrence() {
//       val  savedpage=pdfView.currentPage
//        val tinyDB = TinyDB(this)
//        val count = tinyDB.putInt(getString(R.string.shared_prefrence), 0)
////        val pref:SharedPreferences= applicationContext.getSharedPreferences("MY_PREF", MODE_PRIVATE)
////        val editor:SharedPreferences.Editor=pref.edit()
////        editor.putInt("key_name2",savedpage)
////        editor.apply()
//    }
//    private fun getPreferences() {
//        val pref: SharedPreferences = applicationContext.getSharedPreferences("MY_PREF", MODE_PRIVATE)
//        pref.getInt("key_name2",0)
//    }
/*    fun getParahTitle(page: Int){
       val mTitle= DataServices.parahs[page].page
        for (pages:Int in mTitle){

        }
    }*/
}

const val PAGES_COUNT = 549
const val PAGE_DIFFERENCE=1
const val ASSET_NAME="Quranpak.pdf"




