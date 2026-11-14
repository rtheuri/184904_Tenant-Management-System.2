package com.example.tenantmanagementsystem2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystem2.data.model.Tenant
import com.example.tenantmanagementsystem2.data.repository.TenantRepository
import com.example.tenantmanagementsystem2.TenantDatabase
import kotlinx.coroutines.launch

class TenantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TenantRepository
    val tenants: LiveData<List<Tenant>>

    init {
        val dao = TenantDatabase.getDatabase(application).tenantDao()
        repository = TenantRepository(dao)
        tenants = repository.tenants
    }

    val totalRent: LiveData<Double> = tenants.map { list ->
        list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }

    val formattedTenantList: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            list.joinToString("\n\n") { it.summary() }
        }
    }

    fun addTenant(name: String, phone: String, rent: String): Boolean {
        if (name.isBlank() || phone.isBlank() || rent.isBlank()) {
            return false
        }
        viewModelScope.launch {
            repository.addTenant(Tenant(name = name.trim(), phone = phone.trim(), rent = rent.trim()))
        }
        return true
    }
}