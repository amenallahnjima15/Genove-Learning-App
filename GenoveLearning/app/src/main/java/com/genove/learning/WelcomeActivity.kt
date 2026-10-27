package com.genove.learning

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.genove.learning.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityWelcomeBinding
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Connexion rapide Google
        b.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Connexion avec Google réussie !", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }

        // Connexion rapide Apple
        b.btnApple.setOnClickListener {
            Toast.makeText(this, "Connexion avec Apple réussie !", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }

        // Fast sign in with LinkedIn
        b.btnLinkedIn.setOnClickListener {
            Toast.makeText(this, "Signed in with LinkedIn successfully!", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }

        // Afficher / Masquer le mot de passe
        b.btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                b.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                b.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            b.etPassword.setSelection(b.etPassword.text.length)
        }

        // Mot de passe oublié
        b.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Lien de réinitialisation envoyé à votre adresse.", Toast.LENGTH_LONG).show()
        }

        // Lien "Sign Up" -> Ouvre l'interface RegisterActivity
        b.tvToggleMode.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Bouton Login principal
        b.btnSubmitEmail.setOnClickListener {
            val emailOrUsername = b.etEmail.text.toString().trim()
            val password = b.etPassword.text.toString().trim()

            if (emailOrUsername.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer votre Email ou Nom d'utilisateur", Toast.LENGTH_SHORT).show()
                b.etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Le mot de passe doit comporter au moins 6 caractères", Toast.LENGTH_SHORT).show()
                b.etPassword.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(this, "Connexion réussie ! Bienvenue sur Genove Learning.", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }
    }

    private fun goToCatalog() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
