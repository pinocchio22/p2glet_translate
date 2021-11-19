package com.example.p2glet_translate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.example.p2glet_translate.databinding.ActivityMainBinding
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var option : FirebaseTranslatorOptions.Builder? = null
    var translater : FirebaseTranslator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        option = FirebaseTranslatorOptions.Builder()
            .setSourceLanguage(FirebaseTranslateLanguage.EN)
            .setTargetLanguage(FirebaseTranslateLanguage.KO)
        translater = FirebaseNaturalLanguage.getInstance().getTranslator(option!!.build())
        translater?.downloadModelIfNeeded()

        binding.inputText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                translater?.translate(p0.toString())?.addOnSuccessListener {
                    binding.outTextView.text = it
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }
        })
    }
}