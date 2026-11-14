package com.example.tenantmanagementsystem2

import androidx.lifecycle.LiveData

class TenantRepository(private val tenantDao: TenantDao) {

    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}