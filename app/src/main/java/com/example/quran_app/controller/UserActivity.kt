package com.example.quran_app.controller

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.quran_app.R
import kotlinx.android.synthetic.main.activity_user.*
import android.graphics.Typeface
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import free.translate.languagetranslator.cameratranslation.voicetranslator.TinyDB
import android.annotation.SuppressLint
import android.app.Dialog

class UserActivity : AppCompatActivity() {

lateinit var userFeedBack:LinearLayout
lateinit var userAds:LinearLayout
lateinit var userLanguageChange:LinearLayout
    lateinit var userRateUS:LinearLayout
    lateinit var userPrivacyPolicy:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val uToolBar: Toolbar = findViewById(R.id.toolbar_user)
        setSupportActionBar(uToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        uToolBar.setNavigationOnClickListener { view -> onBackPressed() }
        supportActionBar?.setTitle("Settings")
        supportActionBar?.themedContext
        val shareApp: LinearLayout = findViewById(R.id.lin_lay_share)
        shareApp.setOnClickListener {
            val shareIntent=Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.app_title))
            var shareMessage=getString(R.string.email_text_settings)
            shareMessage=shareMessage+getString(R.string.app_install_link)
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareMessage)
            startActivity(Intent.createChooser(shareIntent,getString(R.string.app_share_text)))
        }
        userAds=findViewById(R.id.lin_lay_ads)
        userAds.setOnClickListener {
            Toast.makeText(this, "This Feature is comming soon", Toast.LENGTH_SHORT).show()
        }
        userLanguageChange=findViewById(R.id.language)
        userLanguageChange.setOnClickListener {
            Toast.makeText(this, "This Feature is comming soon", Toast.LENGTH_SHORT).show()
        }
userFeedBack=findViewById(R.id.feedback)
        feedback.setOnClickListener {
            val testIntent = Intent(Intent.ACTION_VIEW)
            val data =
                Uri.parse("mailto:?subject=" + "Feedback About Quran App" + "&body=" + "Write Your Valuable Comments....." + "&to=" + "teams@itglobe.pk")
            testIntent.data = data
            startActivity(testIntent)

        }
        userRateUS=findViewById(R.id.rateus_user)
        userRateUS.setOnClickListener{v:View->
            showBottomSheetDialog(
                this@UserActivity
            )
//          browse(getString(R.string.app_install_link))

        }
        userPrivacyPolicy=findViewById(R.id.privacy)
        userPrivacyPolicy.setOnClickListener {
            browse(getString(R.string.app_install_link))
        }
    }


    fun showBottomSheetDialog(ctx: Context) {
//        val tinyDB = TinyDB(ctx)
//        tinyDB.putInt(ctx.getString(R.string.RATING_DIALOGUE_SHOW_COUNT), 0)
        val bottomSheetDialog = Dialog(ctx)
        bottomSheetDialog.setContentView(R.layout.rating_dialog_layout)
        val emoji: ImageView? = bottomSheetDialog.findViewById(R.id.emoji_ID)
        val text1 = bottomSheetDialog.findViewById<TextView>(R.id.text1ID)
        val text2 = bottomSheetDialog.findViewById<TextView>(R.id.text2ID)
        val ratingbar = bottomSheetDialog.findViewById<RatingBar>(R.id.ratingBar)
        val rateBtn: Button? = bottomSheetDialog.findViewById(R.id.rateBtnID)
        rateBtn?.setOnClickListener { v ->
    //                tinyDB.putInt(ctx.getString(R.string.RATING_DIALOGUE_SHOW_COUNT), -1)
            if (rateBtn.text.equals(ctx.getString(R.string.RATE))) {
                assert(ratingbar != null)

                email(
                    ctx.getString(R.string.feedback_email),
                    """${ctx.getString(R.string.email_subject)}
             Rating=""" + ratingbar!!.rating
                        .toInt()
                )
            } else if (rateBtn.text.equals(ctx.getString(R.string.RATE_US_ON_GOOGLE_PLAY))) {
                browse(
                    ctx.getString(R.string.app_install_link)
                )
            }
            bottomSheetDialog.cancel()
        }
        assert(ratingbar != null)
        if (ratingbar!!.rating < 1) {
            disableButton(ctx, rateBtn!!)
        }
        ratingbar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                when (rating.toInt()) {
                    1 -> {
                        enableButton(ctx, rateBtn!!)
                        emoji?.setImageDrawable(ctx.resources.getDrawable(R.drawable.ic_rate_emoji_1))
                        text1!!.setTypeface(null, Typeface.BOLD)
                        text1.setText(R.string.OH_NO)
                        if (text2 != null) {
                            text2.setText(R.string.PLEASE_LEAVE_US_FEEDBACK)
                        }
                        rateBtn.setText(R.string.RATE)
                    }
                    2 -> {
                        enableButton(ctx, rateBtn!!)
                        emoji?.setImageDrawable(ctx.resources.getDrawable(R.drawable.ic_rate_emoji_2))
                        text1!!.setTypeface(null, Typeface.BOLD)
                        text1.setText(R.string.OH_NO)
                        text2?.setText(R.string.PLEASE_LEAVE_US_FEEDBACK)
                        rateBtn.setText(R.string.RATE)

                    }
                    3 -> {
                        enableButton(ctx, rateBtn!!)
                        emoji?.setImageDrawable(ctx.resources.getDrawable(R.drawable.ic_rate_emoji_3))
                        text1!!.setTypeface(null, Typeface.BOLD)
                        text1.setText(R.string.OH_NO)
                        text2?.setText(R.string.PLEASE_LEAVE_US_FEEDBACK)
                        rateBtn.setText(R.string.RATE)
//                        Log.e(TAG, "onRatingChanged: $rating")
                    }
                    4 -> {
                        enableButton(ctx, rateBtn!!)
                        emoji?.setImageDrawable(ctx.resources.getDrawable(R.drawable.ic_rate_emoji_4))
                        text1!!.setTypeface(null, Typeface.BOLD)
                        text1.setText(R.string.WE_LIKE_YOU_TOO)
                        text2?.setText(R.string.THANKS_FOR_YOUR_FEEDBACK)
                        rateBtn.setText(R.string.RATE)
//                        Log.e(TAG, "onRatingChanged: $rating")
                    }
                    5 -> {
                        // rateBtn.setEnabled(true);
                        enableButton(ctx, rateBtn!!)
                        emoji?.setImageDrawable(ctx.resources.getDrawable(R.drawable.ic_rate_emoji_5))
                        text1!!.setTypeface(null, Typeface.BOLD)
                        text1.setText(R.string.WE_LIKE_YOU_TOO)
                        text2?.setText(R.string.THANKS_FOR_YOUR_FEEDBACK)
                        rateBtn.setText(R.string.RATE_US_ON_GOOGLE_PLAY)
//                        Log.e(TAG, "onRatingChanged: $rating")
                    }
                    else -> {
                        disableButton(ctx, rateBtn!!)
                        emoji?.setImageDrawable(ctx.resources.getDrawable(R.drawable.ic_rate_emoji_0))
                        text1?.setText(R.string.RATE_US_MSG_0)
                        text2?.setText(R.string.RATE_US_REQUEST)
                        rateBtn.setText(R.string.RATE)
//                        Log.e(TAG, "onRatingChanged: $rating")
                        ratingbar.rating = 0f
                    }
                }
            }
        bottomSheetDialog.show()
    }
    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun enableButton(ctx: Context, btn: Button) {
        btn.isEnabled = true
        btn.setTextColor(ctx.resources.getColor(R.color.white))
        btn.backgroundTintList = ctx.resources.getColorStateList(R.color.green)
    }

    @SuppressLint("UseCompatLoadingForColorStateLists", "ResourceType")
    private fun disableButton(ctx: Context, btn: Button) {
        btn.isEnabled = false
        btn.setTextColor(ctx.resources.getColor(R.color.grey))
        btn.backgroundTintList = ctx.resources.getColorStateList(R.color.green)
    }


    fun browse(url: String, newTask: Boolean = false): Boolean {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            if (newTask) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            return true
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            return false
        }
    }

    @JvmOverloads
    fun share(text: String, subject: String = ""): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(intent, getString(R.string.share_via)))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


    @JvmOverloads
    fun email(email: String, subject: String = "", text: String = ""): Boolean {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.setPackage("com.google.android.gm") //to open gmail app
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        if (subject.isNotEmpty())
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        if (text.isNotEmpty())
            intent.putExtra(Intent.EXTRA_TEXT, text)

            startActivity(intent)
            return true


    }
//private fun email(string: String, s1: String) {
//    val testIntent = Intent(Intent.ACTION_VIEW)
//    val data =
//        Uri.parse("mailto:?subject=" + "Feedback" + "&body=" + "Write Feedback here....." + "&to=" + "teams@itglobe.pk")
//    testIntent.data = data
//    startActivity(testIntent)
//    }

}