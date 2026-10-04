package com.example.tenantmanagementsystemgroupa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystemgroupa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declare the binding variable[cite: 31]
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding and set the content view[cite: 32]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make the SAVE button work[cite: 32, 38]
        binding.saveButton.setOnClickListener {
            // Collect input[cite: 38]
            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            // Try it yourself #1: Validate that the name field is not empty[cite: 40, 41]
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            // Create the object and pass it to Data Binding[cite: 38]
            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant

            // Try it yourself #4: Clear the three input fields after saving[cite: 41]
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }
    }
}