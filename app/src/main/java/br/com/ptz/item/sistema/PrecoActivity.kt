package br.com.ptz.item.sistema

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.ptz.item.R
import android.os.AsyncTask
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import java.sql.DriverManager

class PrecoActivity : AppCompatActivity() {

    var text: TextView? = null
    var errorText: TextView? = null
    var show: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preco)

        text = findViewById<View>(R.id.textView) as TextView
        errorText = findViewById<View>(R.id.textView2) as TextView
        show = findViewById<Button>(R.id.button) as Button
        show!!.setOnClickListener { Task().execute() }

    }

    internal inner class Task : AsyncTask<Void?, Void?, Void?>() {
        var records = ""
        var error = ""
        protected override fun doInBackground(vararg params: Void?): Void? {
            try {
                Class.forName("com.mysql.jdbc.Driver")
                val connection = DriverManager.getConnection("jdbc:mysql://192.168.1.164:3306/fornecedor", "andro", "andro")
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT Email, Nome\n" +
                        "FROM `fornecedor`.`user` ORDER BY Nome")
                    while(resultSet.next()){
                    records += """${resultSet.getString(1)} ${resultSet.getString(2)} 
"""
                    }

                } catch (e: Exception) {
                error = e.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            text!!.text = records
            if (error !== "") errorText!!.text = error
            super.onPostExecute(aVoid)
        }
    }
}