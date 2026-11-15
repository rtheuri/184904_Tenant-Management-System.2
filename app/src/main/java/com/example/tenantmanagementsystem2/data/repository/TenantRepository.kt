package com.example.tenantmanagementsystem2.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystem2.data.local.TenantDao
import com.example.tenantmanagementsystem2.data.model.Tenant

class TenantRepository(private val tenantDao: TenantDao) {

    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}
