package com.genove.learning

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.genove.learning.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var b: ActivityRegisterBinding
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Retour à la connexion
        b.btnBackToLogin.setOnClickListener { finish() }
        b.tvGoToLogin.setOnClickListener { finish() }

        // Toggle visibilité mot de passe
        b.btnToggleRegPassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            b.etRegisterPassword.transformationMethod = if (isPasswordVisible) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            b.etRegisterPassword.setSelection(b.etRegisterPassword.text?.length ?: 0)
        }

        // Toggle visibilité confirmation mot de passe
        b.btnToggleRegConfirmPassword.setOnClickListener {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
            b.etRegisterConfirmPassword.transformationMethod = if (isConfirmPasswordVisible) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            b.etRegisterConfirmPassword.setSelection(b.etRegisterConfirmPassword.text?.length ?: 0)
        }

        // Inscription rapide avec Apple
        b.btnRegisterApple.setOnClickListener {
            Toast.makeText(this, "Compte créé avec Apple ! Bienvenue sur Genove Learning.", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }

        // Inscription rapide avec Google
        b.btnRegisterGoogle.setOnClickListener {
            Toast.makeText(this, "Compte créé avec Google ! Bienvenue sur Genove Learning.", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }

        // Fast sign up with LinkedIn
        b.btnRegisterLinkedIn.setOnClickListener {
            Toast.makeText(this, "Account created with LinkedIn! Welcome to Genove Learning.", Toast.LENGTH_SHORT).show()
            goToCatalog()
        }

        // Soumission du formulaire d'inscription
        b.btnSubmitRegister.setOnClickListener {
            val name = b.etRegisterName.text.toString().trim()
            val email = b.etRegisterEmail.text.toString().trim()
            val password = b.etRegisterPassword.text.toString().trim()
            val confirmPassword = b.etRegisterConfirmPassword.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer votre nom complet", Toast.LENGTH_SHORT).show()
                b.etRegisterName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
                b.etRegisterEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Le mot de passe doit comporter au moins 6 caractères", Toast.LENGTH_SHORT).show()
                b.etRegisterPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show()
                b.etRegisterConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(this, "Compte créé avec succès ! Bienvenue, $name.", Toast.LENGTH_LONG).show()
            goToCatalog()
        }
    }

    private fun goToCatalog() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
