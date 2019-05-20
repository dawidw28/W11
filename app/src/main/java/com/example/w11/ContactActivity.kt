package com.example.w11

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.contact_layout.*

class ContactActivity : AppCompatActivity() {

    var mainText = "Katedra Informatyki\n" +
            "Wydział Podstawowych Problemów Techniki\n" +
            "Politechnika Wrocławska\n" +
            " \n" +
            "pl. Grunwaldzki 13\n" +
            "50-377 Wrocław\n" +
            " \n" +
            "tel.: +48 71 320 21 05\n" +
            "email: w11.k2@pwr.edu.pl"
    var contactText = "Katedra Informatyki (W11/K2)\n" +
            "Wydział Podstawowych Problemów Techniki\n" +
            "Politechnika Wrocławska\n" +
            " \n" +
            "ul. Wybrzeże Wyspiańskiego 27\n" +
            "50-370 Wrocław"
    var addInfText = "Wrocław University of Science and Technology\n" +
            "Wybrzeze Wyspianskiego 27\n" +
            "50-370 Wroclaw\n" +
            "Poland\n" +
            "NIP/VAT ID number: PL 896-000-58-51\n" +
            "REGON: 00000161433000"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_layout)

        mainPlaceText.text=mainText
        contactAdressText.text=contactText
        addInfoText.text=addInfText

    }
}