package com.example.quran_app.controller

//import com.example.quran_app.controller.databinding.ActivityPdfViewFullSizeBinding
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quran_app.R
import com.example.quran_app.databinding.ActivityPdfViewFullSizeBinding
import com.example.quran_app.models.BookmarksParah
import com.example.quran_app.models.ParahNames
import com.example.quran_app.services.DataServices
import com.example.quran_app.viewModel.ParahViewModel
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener
import com.github.barteksc.pdfviewer.util.FitPolicy
import free.translate.languagetranslator.cameratranslation.voicetranslator.TinyDB
import kotlinx.android.synthetic.main.activity_pdf_view_full_size.*
import kotlin.properties.Delegates

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class PdfViewFullSizeActivity : AppCompatActivity() {
    lateinit var scrollChanger:TextView
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
    private var nightMode:Boolean=false
    private var swipeVertical:Boolean=true

    private lateinit var binding: ActivityPdfViewFullSizeBinding
    private lateinit var fullscreenContent: PDFView
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler()

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar
        if (Build.VERSION.SDK_INT >= 30) {
            fullscreenContent.windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            fullscreenContent.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreenContentControls.visibility = View.VISIBLE
    }
    private var isFullscreen: Boolean = false

    private val hideRunnable = Runnable { hide() }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val delayHideTouchListener = View.OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS)
            }
            MotionEvent.ACTION_UP -> view.performClick()
            else -> {
            }
        }
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPdfViewFullSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)




        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isFullscreen = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContent = binding.pdfViewer
        fullscreenContent.setOnClickListener { toggle() }

        fullscreenContentControls = binding.fullscreenContentControls

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        binding.dummyButton.setOnTouchListener(delayHideTouchListener)
        scrollChanger=findViewById(R.id.horiz_vertical_scroll)
        bookmarkpage=intent.getIntExtra(getString(R.string.bookmark_key), -1)
        fabPage=TinyDB.getInstance(this).getInt(getString(R.string.recent_page),0)
        currentSurah=DataServices.getsurahFromPage(fabPage).surahNumber //for getting surah from page number
//        toolbar1.title=DataServices.getparahFromPage(fabPage).title

        setSupportActionBar(toolbar1)
        surah = intent.getIntExtra(getString(R.string.surah_key), -1)
        Log.e("PdfViewActivity", "you selected$surah")
        parah = intent.getIntExtra(getString(R.string.parah_key), -1)
        Log.e("PdfViewActivity", "you selected$parah")
        pdfView = findViewById(R.id.pdf_viewer)
        nex = findViewById(R.id.next)
        previ = findViewById(R.id.previous)
        Log.e(TAG, "onCreate: $fabPage $surah $parah ", )

        onPageScrollListner=OnPageScrollListener({page, positionOffset ->
            Log.e(TAG, "Page on scroll: $page  $positionOffset" )
            getSupportActionBar()?.title = DataServices.getsurahFromPage(page).title
        })

        nex.setOnClickListener {
//var su=DataServices.surah[surah ++].page
////            surah=surah ++
//            su=pdfView.currentPage
//            pdfView.jumpTo(su)

            if(surah>=0){
                //load surrah
                surah++
                LoadSurah(surah)
            }else if(parah>=0)
            {
                //load parah
//                val mPageGo=DataServices.surah[surah].page
//                pdfView.jumpTo(mPageGo)
                parah++
                LoadParah(parah)
            }else{ //if comming from fab page
                currentSurah++
                LoadSurah(currentSurah)
            }

//            var mPageGo=pdfView.currentPage

        }
        previ.setOnClickListener {


            if (surah >= 0)
            {
                //load surrah
                surah--
                LoadSurah(surah)
            } else if(parah>=0) {
                //load parah
//                val mPageGo=DataServices.surah[surah].page
//                pdfView.jumpTo(mPageGo)
                parah--
                LoadParah(parah)
            }else{ //if comming from fab page
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
            if (pdfView.isSwipeVertical == true) {
                swipeVertical=true
//                pdfView.isSwipeEnabled=swipeVertical
//                pdfView.loadPages()

                pdfView.fromAsset("Quranpak.pdf")
                    .defaultPage(pdfView.currentPage)
//                    .pages(pdfView.currentPage)
                    .onPageScroll(onPageScrollListner)
                    .enableSwipe(swipeVertical)
                    .nightMode(nightMode)
                    .swipeHorizontal(true).pageFitPolicy(FitPolicy.HEIGHT)
                    .enableAnnotationRendering(true)
//                    .scrollHandle(DefaultScrollHandle(this))
                    .spacing(10).pageFitPolicy(FitPolicy.WIDTH).load()
                scrollChanger.setText(getString(R.string.vertical))
            } else {
                swipeVertical=false
//                pdfView.isSwipeEnabled=swipeVertical
//                pdfView.loadPages()
                pdfView.fromAsset("Quranpak.pdf")
                    .enableSwipe(true)
                    .defaultPage(pdfView.currentPage)
                    .onPageScroll(onPageScrollListner)
                    .nightMode(nightMode)
//                    .pages(pdfView.currentPage)
                    .swipeHorizontal(swipeVertical).pageFitPolicy(FitPolicy.HEIGHT)
                    .enableAnnotationRendering(true)
//                    .scrollHandle(DefaultScrollHandle(this))
                    .spacing(10).pageFitPolicy(FitPolicy.WIDTH)
                    .load()

                scrollChanger.setText(getString(R.string.horizontol))

            }


        }


        val para: ParahNames = DataServices.parahs[1]
        Log.e(TAG, "onCreate: Title " + para.title)
        if(bookmarkpage>=0)
        {
            LoadPage(bookmarkpage)
        }
        else if (surah>=0) {
            LoadSurah(surah)
        } else if(parah>=0) {
            LoadParah(parah)
        }else{
            LoadDocument(fabPage)
//            showw()
        }
        mUserViewModel = ViewModelProvider(this).get(ParahViewModel::class.java)
        val goTo:ImageView=findViewById(R.id.search)

        goTo.setOnClickListener {
            gotoPage(bookmarkpage)
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////

    private fun gotoPage(goto: Int) {
        val editText = EditText(this)
        editText.setHint("Page Number")
        editText.keyListener= DigitsKeyListener.getInstance("0123456789")
        val dialougeBuilder= AlertDialog.Builder(this)
        dialougeBuilder.setMessage("Enter The Page Number")
        dialougeBuilder.setView(editText)
        dialougeBuilder.setPositiveButton("Done") { _, _ ->
            //by getting text value and jump to that page

            if (!editText.getText().toString().equals("")) {
                val getValue: Int = editText.getText().toString().toInt()
                if (getValue < 550) {
                    pdfView.jumpTo(getValue - 1)
                }

            }else{
                Toast.makeText(this, "Please Enter a valid page number", Toast.LENGTH_LONG).show()
            }
        }
        dialougeBuilder.setNegativeButton("Cancel"){_,_->}
        dialougeBuilder.show()
        val editor=object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")){
                    val input:Int= Integer.parseInt(s.toString())
                    if(input<0 || input>550){
                        editText.setError("Enter Number Between 1-550")
                    }
                }
            }
        }

        editText.addTextChangedListener(editor)
    }

    private fun LoadPage(page:Int) {
        LoadDocument(page-1)
//        showw()
    }
    private fun LoadParah(i: Int) {
        if(i<0 || i>30)
            return
        LoadDocument(DataServices.parahs[i].page)
//        showw()
    }
    private fun LoadSurah(i: Int) {
        if (i<0||i>114)
            return
        LoadDocument(DataServices.surah[i].page)
//        showw()
    }
    private fun LoadDocument(i: Int): Int {
        pdfView.fromAsset("Quranpak.pdf")
            .enableSwipe(true)
            .defaultPage(i)
            .nightMode(nightMode)
            .onPageScroll(onPageScrollListner)
            .swipeHorizontal(swipeVertical)
            .pageFitPolicy(FitPolicy.HEIGHT)
            .spacing(10)
            .enableAnnotationRendering(true)
            .pageFitPolicy(FitPolicy.WIDTH)
            .load()
        return i
    }

//    private fun showw() {
//        pdfView.visibility = View.VISIBLE
//        nex.visibility = View.VISIBLE
//        previ.visibility = View.VISIBLE
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_viewer,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("onOptionsItemSelected", "ïtem = ${item.itemId}")
        if (item.itemId == R.id.bookmarks) {
            editText= EditText(this)
            editText.setText(DataServices.getsurahFromPage(pdfView.currentPage).title)
            val dialogBuilder= AlertDialog.Builder(this)
            dialogBuilder.setView(editText)
            dialogBuilder.setMessage("Enter Title")
            dialogBuilder.setPositiveButton("Done"){_,_->
                insertDataToDatabase()

            }
            dialogBuilder.setNegativeButton("No"){_,_->}
            dialogBuilder.show()



        }
        if (item.itemId== R.id.color_mode){
            if(!nightMode){
                nightMode=true
//                LoadDocument(pdfView.currentPage)

                pdfView.setNightMode(nightMode)
                pdfView.loadPages()


//                LoadDocument(pdfView.currentPage)


            }else{
                nightMode=false


                    pdfView.setNightMode(nightMode)
                    pdfView.loadPages()
//                  LoadDocument(pdfView.currentPage)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val getText=editText.text.toString()
        val bookmark= BookmarksParah(
            id = 0,
            page = pdfView.currentPage+1,
            title = getText

        )
        mUserViewModel.addParah(bookmark)
        Toast.makeText(this, "BookMark Saved", Toast.LENGTH_SHORT).show()

    }
    override fun onPause() {
        super.onPause()
        val tinyDB= TinyDB(this)
        tinyDB.putInt(getString(R.string.recent_page), pdfView.currentPage)

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(2000)
    }

    private fun toggle() {
        if (isFullscreen) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreenContentControls.visibility = View.GONE
        isFullscreen = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        if (Build.VERSION.SDK_INT >= 30) {
            fullscreenContent.windowInsetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            fullscreenContent.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
        isFullscreen = true

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}