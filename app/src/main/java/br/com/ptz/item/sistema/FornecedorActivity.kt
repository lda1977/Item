package br.com.ptz.item.sistema

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.ptz.item.EXTRA_MESSAGE
import br.com.ptz.item.R

class FornecedorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedor)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        findViewById<TextView>(R.id.txtResulEmail).apply {
            text = message
        }

    }
}