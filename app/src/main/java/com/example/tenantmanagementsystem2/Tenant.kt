package com.example.tenantmanagementsystemgroupa

// Data class to hold tenant information for the UI[cite: 35]
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) {
    fun summary(): String {
        // Try it yourself #2: Modified summary to show "Rent paid:"
        return "Tenant: $name\nPhone: $phone\nRent paid: KSh $rent"
    }
}