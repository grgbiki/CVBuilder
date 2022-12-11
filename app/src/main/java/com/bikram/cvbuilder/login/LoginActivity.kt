package com.bikram.cvbuilder.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bikram.cvbuilder.MainActivity
import com.bikram.cvbuilder.R
import com.bikram.cvbuilder.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var forgotPassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.checkLoginStatus(this)
        loginViewModel.isLoggedIn?.observe(this) {
            if (it) startMainActivity()
        }
        binding.forgotPassword.setOnClickListener {
            binding.errorTextView.visibility = View.GONE
            forgotPassword = !forgotPassword
            if (forgotPassword) {
                binding.loginButton.text = getString(R.string.create_button_text)
                binding.confirmPasswordEditTextLayout.visibility = View.VISIBLE
                binding.forgotPassword.text = getString(R.string.login_button_text)
            } else {
                binding.loginButton.text = getString(R.string.login_button_text)
                binding.confirmPasswordEditTextLayout.visibility = View.GONE
                binding.forgotPassword.text = getString(R.string.forgot_password)
            }
        }

        binding.loginButton.setOnClickListener {
            binding.loginButton.isEnabled = false
            binding.errorTextView.visibility = View.GONE
            if (validateEmail() && validatePassword()) {
                if (forgotPassword) {
                    loginViewModel.register(
                        binding.emailEditText.text.toString().trim(),
                        binding.passwordEditText.text.toString().trim()
                    ).observe(this) {
                        if (it) {
                            startMainActivity()
                        } else {
                            binding.loginButton.isEnabled = true
                        }
                    }
                } else {
                    loginViewModel.login(
                        binding.emailEditText.text.toString().trim(),
                        binding.passwordEditText.text.toString().trim()
                    ).observe(this) {
                        if (it) {
                            startMainActivity()
                        } else {
                            binding.loginButton.isEnabled = true
                            binding.errorTextView.visibility = View.VISIBLE
                        }
                    }
                }
            } else {
                binding.loginButton.isEnabled = true
            }
        }
    }

    private fun validateEmail(): Boolean {
        if (binding.emailEditText.text.toString().trim().isEmpty()) {
            binding.emailEditTextLayout.isErrorEnabled = true
            binding.emailEditTextLayout.error = "Required Field!"
            binding.emailEditText.requestFocus()
            return false
        } else {
            binding.emailEditTextLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {

        if (binding.passwordEditText.text.toString().trim().isEmpty()) {
            binding.passwordEditTextLayout.isErrorEnabled = true
            binding.passwordEditTextLayout.error = "Required Field!"
            binding.passwordEditText.requestFocus()
            return false
        } else {
            binding.passwordEditTextLayout.isErrorEnabled = false
        }
        if (forgotPassword) {
            if (binding.passwordEditText.text.toString().trim().length < 8) {
                binding.passwordEditTextLayout.isErrorEnabled = true
                binding.passwordEditTextLayout.error = "Password must be at least 8 characters long"
                binding.passwordEditText.requestFocus()
                return false
            } else if (binding.confirmPasswordEditText.text.toString()
                    .trim() != binding.passwordEditText.text.toString().trim()
            ) {
                binding.confirmPasswordEditTextLayout.isErrorEnabled = true
                binding.confirmPasswordEditTextLayout.error = "Password does not match"
                binding.confirmPasswordEditText.requestFocus()
                return false
            }
        } else {
            binding.confirmPasswordEditTextLayout.isErrorEnabled = false
        }
        return true
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}