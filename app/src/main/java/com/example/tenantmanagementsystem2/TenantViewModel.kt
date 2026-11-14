package com.example.tenantmanagementsystem2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class TenantViewModel : ViewModel() {

    private val _tenants = MutableLiveData<List<Tenant>>(TenantRepository.tenants)
    val tenants: LiveData<List<Tenant>> get() = _tenants

    val totalRent: LiveData<Double> = _tenants.map { tenantList ->
        tenantList.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }

    val formattedTenantList: LiveData<String> = _tenants.map { tenantList ->
        if (tenantList.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            tenantList.joinToString("\n\n") { it.summary() }
        }
    }

    fun addTenant(name: String, phone: String, rent: String): Boolean {
        if (name.isBlank() || phone.isBlank() || rent.isBlank()) {
            return false
        }
        val tenant = Tenant(name.trim(), phone.trim(), rent.trim())
        TenantRepository.addTenant(tenant)
        _tenants.value = TenantRepository.tenants.toList()
        return true
    }
}