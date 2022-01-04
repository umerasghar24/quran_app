package com.example.quran_app.util.widgets

import android.content.Context
import android.view.View
import com.example.quran_app.controller.PAGES_COUNT
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import java.util.zip.Inflater

open class QuranScrollHandle : DefaultScrollHandle {

    constructor(context: Context) : super(context)
    constructor(context: Context, inverted: Boolean) : super(context, inverted)
    override fun setPageNum(pageNum: Int) {
//        super.setPageNum(pageNum)
        val text = (PAGES_COUNT + 2 - pageNum).toString()
        if (textView.text != text) {
            textView.text = text
        }
    }
    }

