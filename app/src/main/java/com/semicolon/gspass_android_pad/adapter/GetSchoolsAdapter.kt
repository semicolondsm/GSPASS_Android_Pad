package com.semicolon.gspass_android_pad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.gspass_android_pad.databinding.ItemSearchSchoolBinding
import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import com.semicolon.gspass_android_pad.viewmodel.AddSchoolViewModel

class GetSchoolsAdapter(private val viewModel: AddSchoolViewModel) :
    RecyclerView.Adapter<GetSchoolsAdapter.SchoolViewHolder>() {
    private var schools = ArrayList<GetSchoolResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding =
            ItemSearchSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) =
        holder.bind(schools[position])

    override fun getItemCount(): Int =
        schools.size

    fun setItems(schoolsList: LiveData<ArrayList<GetSchoolResponse>>) {
        this.schools = schoolsList.value ?: ArrayList()
        this.notifyDataSetChanged()
    }

    inner class SchoolViewHolder(private val binding: ItemSearchSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(schoolModel: GetSchoolResponse) {
            binding.vm = viewModel
            binding.model = schoolModel
        }
    }
}