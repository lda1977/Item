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

const val  EXTRA_MESSAGE = "br.com.ptz.itens.MESSAGE"

class LoginActivity : AppCompatActivity() {
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
            if(ResultadoLoginDBA == "teste" && ResultadoSenhaDBA == "123"){
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
    }

    // Criando a função de ALERTA
    private fun ALERTA ( ResultadoLogin: String){
        // Aqui vou verificar se foi logado  com sucesso ou não
        Toast.makeText(this, ResultadoLogin, Toast.LENGTH_LONG).show()
    }


}