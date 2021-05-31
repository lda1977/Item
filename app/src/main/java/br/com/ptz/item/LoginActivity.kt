package br.com.ptz.item

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Criando Button
        val EntranoSistema = findViewById<Button>(R.id.btnLogar) as Button

        // Chamando onclicklistenner
        EntranoSistema.setOnClickListener {

            val tLogin = findViewById<View>(R.id.txtInpEmail) as TextView  // Que é o testo Login
            val tSenha = findViewById<View>(R.id.txtInpPassword) as TextView  // Que é o testo Login

            // Salvando em variaveis String Separadas
            val login = tLogin.text.toString()
            val senha = tSenha.text.toString()

            // Fazendo uma comparação de variáveis
            if(login == "teste" && senha == "123"){
                ALERTA("Login com Sucesso")
            }else{
                ALERTA("Erro! Login ou Senha incorretos, tente novamente.")
            }
        }
    }

    // Criando a função de ALERTA
    private fun ALERTA ( ResultadoLogin: String){
        // Aqui vou verificar se foi logado  com sucesso ou não
        Toast.makeText(this, ResultadoLogin, Toast.LENGTH_LONG).show()
    }
}