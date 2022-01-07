package com.example.quran_app.controller

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
import android.app.Activity
import android.R.string.no
import android.view.*
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE


class PdfViewActivity : BaseActivity() {
    private lateinit var  constraintLayout: ConstraintLayout
    private lateinit var mtoolBar: Toolbar
    lateinit var scrollChanger: TextView
    lateinit var onPageScrollListner: OnPageScrollListener
    private var currentSurah by Delegates.notNull<Int>()
    private var fabPage by Delegates.notNull<Int>()
    private val TAG: String = "pdf viewr"
    lateinit var pdfView: PDFView
    lateinit var nex: ImageView
    lateinit var previ: ImageView
    lateinit var mHorizVerticleSplit:ImageView
    private lateinit var mUserViewModel: ParahViewModel
    private var bookmarkpage by Delegates.notNull<Int>()
    var surah by Delegates.notNull<Int>()
    var parah by Delegates.notNull<Int>()
//    lateinit var editText: EditText
//    lateinit var mBookmarkText:TextView
    private var nightMode: Boolean = false
    private var mBookmarkEditText:EditText?=null
    private var swipeVertical: Boolean = true
    private lateinit var mCostumTitle: TextView
    private var isFullScreen=false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        //casting verticle Horizontol Split ImageView
        mHorizVerticleSplit=findViewById(R.id.horizontol_verticle_split)
        //for changingg status bar color for different activities
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)//use default color for status bar
        //for hiding the bottom bar in this activity
        constraintLayout=findViewById(R.id.constraint_parent_layout)
        scrollChanger = findViewById(R.id.horiz_vertical_scroll)
        bookmarkpage = intent.getIntExtra(getString(R.string.bookmark_key), -1)
        //saving last page to display usinng  fab button
        fabPage = TinyDB.getInstance(this).getInt(getString(R.string.recent_page), 0)
        currentSurah =
            DataServices.getsurahFromPage(fabPage).surahNumber //for getting surah from page number
//        toolbar1.title=DataServices.getparahFromPage(fabPage).title
        mtoolBar = findViewById(R.id.toolbar1)//casting  toolbar
        setSupportActionBar(mtoolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //back arrow pressed
        mtoolBar.setNavigationOnClickListener { view -> onBackPressed() }
        surah = intent.getIntExtra(getString(R.string.surah_key), -1)
        Log.e("PdfViewActivity", "you selected$surah")
        parah = intent.getIntExtra(getString(R.string.parah_key), -1)
        Log.e("PdfViewActivity", "you selected$parah")
        pdfView = findViewById(R.id.pdf_viewer)
        nex = findViewById(R.id.next)
        previ = findViewById(R.id.previous)
        Log.e(TAG, "onCreate: $fabPage $surah $parah ")
        //casting textview in toolbar
        mCostumTitle=mtoolBar.findViewById(R.id.custom_title)

        onPageScrollListner = OnPageScrollListener { page, positionOffset ->
            Log.e(TAG, "Page on scroll: $page  $positionOffset")
            mCostumTitle.text = DataServices.getsurahFromPage(PAGES_COUNT-page).title


//to show text in toolbar title
//            getSupportActionBar()?.title = DataServices.getsurahFromPage(PAGES_COUNT - page).title
        }

        nex.setOnClickListener {
            if (surah >= 0) {
                //load surrah
                if(surah!=113)
                    surah++
                    LoadSurah(surah)

            } else if (parah >= 0) {

                //load parah
//                val mPageGo=DataServices.surah[surah].page
//                pdfView.jumpTo(mPageGo)
                if(parah!=29)
                parah++
                LoadParah(parah)
            } else { //if comming from fab page
                if(currentSurah!=113)
                currentSurah++
                LoadSurah(currentSurah)
            }

//            var mPageGo=pdfView.currentPage

        }
        previ.setOnClickListener {
            if (surah >= 0) {
                //load surrah
                if(surah!=0)
                    surah--

                LoadSurah(surah)


            } else if (parah >= 0) {
                //load parah
//                val mPageGo=DataServices.surah[surah].page
//                pdfView.jumpTo(mPageGo)
    if(parah!=0)parah--
                LoadParah(parah)
            } else {
                //if comming from fab page
                if(currentSurah!=0)
                currentSurah--
                LoadSurah(currentSurah)
            }
        }
        pdfView.setOnClickListener {
            if (isFullScreen){
                isFullScreen=false
                supportActionBar?.hide()
                constraintLayout.visibility=View.GONE
            }else{
                isFullScreen=true
                supportActionBar?.show()
                constraintLayout.visibility=View.VISIBLE
            }
        }
//         surah --
//             val mPageG=DataServices.surah[surah].page
//            pdfView.jumpTo(mPageG)
////            pdfView.jumpTo(DataServices.surah[surah --].page)

        mHorizVerticleSplit.setOnClickListener {

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
                scrollChanger.text = getString(R.string.vertical)
                mHorizVerticleSplit.setImageResource(R.drawable.ic_verticle_split)

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
                scrollChanger.text = getString(R.string.horizontol)
             mHorizVerticleSplit.setImageResource(R.drawable.ic_horizontol_split)

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
//            openSoftKeyboard()
        }
    }

    private fun gotoPage(goto: Int) {
        val editText = EditText(this)
        editText.setHint("Enter Page Number")
        editText.keyListener = DigitsKeyListener.getInstance("0123456789")
//        editText.inputType=


        val dialougeBuilder = AlertDialog.Builder(this)
        dialougeBuilder.setMessage("Go To Page")
        DialogInterface.OnShowListener {   editText.requestFocus()
            val inputManager =applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.restartInput(editText) }

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
        dialougeBuilder.setNegativeButton("Cancel") { _, _ ->
//            hideKeyboard(this)
        }
//        dialougeBuilder.show()

//        dialougeBuilder = builder.create();
//        dialougeBuilder.create()
//        dialougeBuilder.getWindow()?.setSoftInputMode(ViewGroup.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialougeBuilder.show();
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
        if (i < 0 || i > 29){
//            return
            Toast.makeText(this, "No Juzz Found", Toast.LENGTH_SHORT).show()

//        show()
        }else{
            loadDocument(DataServices.parahs[i].page)
            return
        }

    }

    private fun LoadSurah(i: Int) {
        if (i < 0 || i > 113){
//            return
            Toast.makeText(this, "No Surah Found", Toast.LENGTH_SHORT).show()

        }else{
            loadDocument(DataServices.surah[i].page)
            return
        }
//

//        show()
    }

    //load doucument from assest folder

    private fun loadDocument(i: Int) {
//  pdfView.fromAsset(ASSET_NAME).defaultPage(PAGES_COUNT-i)
//        pdfView.loadPages()
//        pdfView.startAnimation(pdfView.animation)

        val pagesArray = (PAGES_COUNT downTo 0).toList()
        Log.e(TAG, "loadDocument: $pagesArray")

        pdfView.fromAsset(ASSET_NAME)
            .enableSwipe(true)
            .pages(*pagesArray.toIntArray())
            .defaultPage(PAGES_COUNT - i)
            /*.pageSnap(true)
            .pageFling(true)*/
            .nightMode(nightMode)
            .fitEachPage(true)
            .onPageScroll(onPageScrollListner)
            .swipeHorizontal(swipeVertical)
            .pageFitPolicy(FitPolicy.HEIGHT)
            .spacing(10)
            .enableAnnotationRendering(true)
            .scrollHandle(QuranScrollHandle(this, false))
            .pageFitPolicy(FitPolicy.WIDTH)
            .load()
    }
    //calling method for menu item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_viewer, menu)
        return super.onCreateOptionsMenu(menu)
    }
//for casting of menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("onOptionsItemSelected", "ïtem = ${item.itemId}")
        if (item.itemId == R.id.bookmarks) {//when bookmarks is click we''ll inflate a custom layout for
            //dialoge
           showBookmarkDialoge(
               this@PdfViewActivity
           )
          /*  mBookmarkText= TextView(this)
            mBookmarkText.text= (PAGES_COUNT-pdfView.currentPage+1).toString()
            editText = EditText(this)
//            editText.setHint("Enter title")
            editText.setText(DataServices.getsurahFromPage(PAGES_COUNT - pdfView.currentPage).title)
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setView(editText)
//            dialogBuilder.setView(mBookmarkText)
            dialogBuilder.setMessage("Enter Title")
            dialogBuilder.setPositiveButton("Done") { _, _ ->
                insertDataToDatabase()

            }
            dialogBuilder.setNegativeButton("Cancel") { _, _ -> }
            dialogBuilder.show()*/

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

    private fun showBookmarkDialoge(ctx: Context) {//setting surah name ,page number in bookmark dialog
        val bookmarkDialog=Dialog(ctx)
        bookmarkDialog.setContentView(R.layout.bookmaks_dialogue)
val resourceId=ctx.resources.getIdentifier(DataServices.getparahFromPage(PAGES_COUNT-pdfView.currentPage).image,
    "drawable",ctx.packageName)
        val mBookmarkTitle:TextView?=bookmarkDialog.findViewById(R.id.enter_title_text)
        mBookmarkTitle?.text = DataServices.getparahFromPage(PAGES_COUNT-pdfView.currentPage).title
val mDialogImage:ImageView?=bookmarkDialog.findViewById(R.id.parah_image_dialouge)
        mDialogImage?.setImageResource(resourceId)
        val mBookmarkPage=bookmarkDialog.findViewById<TextView>(R.id.page_number)
        mBookmarkEditText= bookmarkDialog.findViewById(R.id.edit_text_surah_name)!!
        val mDoneBtn=bookmarkDialog.findViewById<TextView>(R.id.save_text)
        val mCancelBtn=bookmarkDialog.findViewById<TextView>(R.id.cancel_text)
        mBookmarkPage?.text=(PAGES_COUNT-pdfView.currentPage+1).toString()
        mBookmarkEditText?.setText(DataServices.getsurahFromPage(PAGES_COUNT - pdfView.currentPage).title)
        mDoneBtn?.setOnClickListener {
            insertDataToDatabase()
            bookmarkDialog.hide()
        }
        mCancelBtn?.setOnClickListener {
            bookmarkDialog.hide()
        }
        bookmarkDialog.show()
    }

    private fun insertDataToDatabase() {
//        val getText = editText.text.toString()
        val getText=mBookmarkEditText?.text.toString()
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




