package com.example.w11.courses

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.w11.R
import kotlinx.android.synthetic.main.courses.*
import kotlinx.android.synthetic.main.courses.view.*

class CoursesActivity : AppCompatActivity() {
    lateinit var courseList : ArrayList<Course>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courses)

        courseList = ArrayList<Course>()
        courseList.add(Course(R.drawable.math, "Analiza matematyczna", 1, 8, "Paweł Krupski", "Fajny przedmiocik dla kogoś o stalowych nerwach."))
        courseList.add(Course(R.drawable.math, "Algebra liniowa", 1, 7, "Jacek Cichoń", "Dużo abstrakcji, dużo niezrozumiałych znaczków, ETrapez niezbędny."))
        courseList.add(Course(R.drawable.inf_icon, "Wstęp do informatyki i programowania", 1, 8, "Przemyslaw Kobylanski", "Nauczysz się trochę C, ale ogólnie to się nie ucz, bo Ada lepsza."))
        courseList.add(Course(R.drawable.math, "Logika i struktury formalne", 1, 7, "Szymon Żeberski", "Potrzebne rzeczy, solidny prowadzący, wymagające kolokwium."))
        courseList.add(Course(R.drawable.math, "Matematyka dyskretna", 2, 6, "Małgorzata Sulkowska", "Dużo matematyki na na kosmicznym poziomie. Ciężko zdać egzamin, ale zawsze progi mogą pójść w dół. Wtedy już łatwo."))
        courseList.add(Course(R.drawable.math, "Analiza matematyczna II",2, 6, "Paweł Krupski", "Jak zdasz analizę I, to II już niestraszna. Nawet staje się przyjemna."))
        courseList.add(Course(R.drawable.math, "Algebra abstrakcyjna i kodowanie",2, 6, "Krzysztof Majcher", "Czysta przyjemność. Antydepresant tych studiów."))
        courseList.add(Course(R.drawable.inf_icon, "Kurs programowania",2, 4, "Wojciech Macyna", "Podstawy Javy z elementami C++. Samodzielna nauka wskazana."))
        courseList.add(Course(R.drawable.math, "Fizyka",2, 3, "Katarzyna Weron", "Nikt tego nie chce, ale każdy musi. Łatwo zdać."))
        courseList.add(Course(R.drawable.inf_icon, "Problemy prawne informatyki",2, 3, "Mirosław Kutyłowski", "Bardzo ważny przedmiot. Dowiesz się za co pójdziesz do więzienia."))
        courseList.add(Course(R.drawable.math, "Metody probabilistyczne i statystyka",3, 6, "Michał Morayne", "Bajkowe przykłady, materiał przerabiany w tempie PKP, egzamin łatwy."))
        courseList.add(Course(R.drawable.inf_icon, "Technologia programowania",3, 6, "Wojciech Macyna", "Java podobnie jak w II semestrze. Podobnie też samodzielna nauka wskazana."))
        courseList.add(Course(R.drawable.inf_icon, "Bazy danych",3, 6, "Piotr Syga", "Dużo konkretnej wiedzy. Bazy przerobione wzdłuż i wszerz. Dużo wyniesiesz z tego kursu."))
        courseList.add(Course(R.drawable.inf_icon, "Architektura komputerów i systemy operacyjne",3, 9, "Marcin Zawada", "Dużo wiedzy, dużo pracy."))
        courseList.add(Course(R.drawable.inf_icon, "Algorytmy i struktury danych",4, 6, "Maciej Gębala", "Zaprzyjaźnij się z Cormenem."))
        courseList.add(Course(R.drawable.inf_icon, "Technologie sieciowe",4, 4, "Łukasz Krzywiecki", "Dużo nauki o protokołach, ale podłączać internetu się nie nauczysz."))
        courseList.add(Course(R.drawable.math, "Obliczenia naukowe",5, 4, "Paweł Zieliński", "Myślałeś, że 5 semestr to już koniec matematyki na studiach? Nie tym razem."))
        courseList.add(Course(R.drawable.inf_icon, "Języki formalne i techniki translacji",5, 6, "Maciej Gębala", "Napiszesz swój własny kompilator. Na pewno Ci się spodoba."))
        courseList.add(Course(R.drawable.inf_icon, "Programowanie zespołowe",5, 2, "-", "Masz szansę na stworzenie czegoś ciekawego."))
        courseList.add(Course(R.drawable.inf_icon, "Systemy wbudowane",6, 6, "Przemysław Błaśkiewicz", "Pomajsterkujesz i poprogramujesz, Dobra zabawa, przed ostatnim semestrem"))
        courseList.add(Course(R.drawable.inf_icon, "Projekt dyplomowy",7, 15, "-", "Pokaż czego się nauczyłeś przez te kilka lat."))











        val myAdapter = MyCourseAdapter(this, courseList)
        courses.adapter = myAdapter

        courses.setOnItemClickListener {_, _, index, _ ->
            var course = courseList[index]
            val intent = Intent(this, CourseDetails::class.java)
            intent.putExtra("picture", course.picture);
            intent.putExtra("name", course.name)
            intent.putExtra("semester", course.name)
            intent.putExtra("ects", course.ects)
            intent.putExtra("lecturer", course.teacher)
            intent.putExtra("description", course.description)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("taskList", courseList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        courseList = savedInstanceState?.getSerializable("taskList") as ArrayList<Course>
        val myAdapter = MyCourseAdapter(this, courseList)
        courses.adapter = myAdapter
    }

}