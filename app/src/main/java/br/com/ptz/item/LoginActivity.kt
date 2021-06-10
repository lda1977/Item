@file:Suppress("UNUSED_VARIABLE")

package br.com.ptz.item

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import br.com.ptz.item.sistema.FornecedorActivity
import java.sql.*
import java.util.Properties
import android.os.AsyncTask
import android.provider.ContactsContract
import java.lang.Exception
import java.sql.DriverManager

const val  EXTRA_MESSAGE = "br.com.ptz.itens.MESSAGE"

class LoginActivity : AppCompatActivity() {
    internal var conn: Connection? = null
    internal var username = "username" // provide the username
    internal var password = "password" // provide the corresponding password

    var text: TextView? = null
    var errorText: TextView? = null
    var show: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Criando Button
        val EntranoSistema = findViewById<Button>(R.id.btnLogar) as Button

        // Chamando onclicklistenner
        EntranoSistema.setOnClickListener {

            // Pegando o conteudo dos campos txtInpEmail e txtInpPassword
            val tLogin = findViewById<View>(R.id.txtInpEmail) as TextView  // Que é o testo Login
            val tSenha = findViewById<View>(R.id.txtInpPassword) as TextView  // Que é o testo Senha

            // Salvando em variaveis String Separadas
            val login = tLogin.text.toString()
            val senha = tSenha.text.toString()

            // Aqui vou chamara o DBA

            // Variáveis de Resultado do DBA
            val ResultadoLoginDBA = login
            val ResultadoSenhaDBA = senha

            // Fazendo uma comparação de variáveis
            if(ResultadoLoginDBA == tLogin.text.toString() && ResultadoSenhaDBA == tSenha.text.toString()){
                ALERTA("Login com Sucesso!")

                val message = tLogin.text.toString()
                //val messageP = tSenha.text.toString()

                val intent = Intent(this, FornecedorActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)

            }else{
                ALERTA("Erro!!! Login ou Senha incorretos, tente novamente.")
            }
        }

        // Mostrando os textView e Button
        text = findViewById<View>(R.id.textView) as TextView
        errorText = findViewById<View>(R.id.textView2) as TextView
        show = findViewById<Button>(R.id.button) as Button

        // Chamando a função setOnClickListener
        show!!.setOnClickListener { Task().execute() }
    }

    // Criando a função de ALERTA
    private fun ALERTA ( ResultadoLogin: String){
        // Aqui vou verificar se foi logado com sucesso ou não
        Toast.makeText(this, ResultadoLogin, Toast.LENGTH_LONG).show()
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
                    if(records == resultSet.getString(username)){

                    }else{
                        return  null
                    }
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