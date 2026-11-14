package com.example.tenantmanagementsystem2.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystem2.data.model.Tenant
import com.example.tenantmanagementsystem2.databinding.FragmentAddTenantBinding
import com.example.tenantmanagementsystem2.ui.viewmodel.TenantViewModel

class AddTenantFragment : Fragment() {
    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TenantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            // The ViewModel validates the input and saves the tenant
            val saved = viewModel.addTenant(name, phone, rent)

            if (saved) {
                // Tenant is only built here to pass to the "Just added" section via Safe Args
                val tenant = Tenant(name = name.trim(), phone = phone.trim(), rent = rent.trim())
                val action =
                    AddTenantFragmentDirections.actionAddTenantFragmentToViewTenantsFragment(tenant)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}