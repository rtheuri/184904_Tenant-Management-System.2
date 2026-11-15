package com.example.tenantmanagementsystem2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tenantmanagementsystem2.data.model.Tenant

@Dao
interface TenantDao {

    @Insert
    suspend fun insertTenant(tenant: Tenant)

    @Query("SELECT * FROM tenants")
    fun getAllTenants(): LiveData<List<Tenant>>
}