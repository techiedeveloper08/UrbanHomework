package com.urban.androidhomework.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.urban.androidhomework.R
import com.urban.androidhomework.model.Person
import com.urban.androidhomework.model.PersonData

class PersonsAdapter(private val context: Context,
                     private val onPersonClickListener: OnPersonClickListener) :
        RecyclerView.Adapter<PersonsAdapter.ViewHolder>() {

    private var personsList: List<PersonData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.persons_deatil_row, parent, false))
    }

    override fun getItemCount(): Int {
        return personsList.size
    }

    fun setPersonsData(personsList: List<PersonData>) {
        this.personsList = personsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.text = personsList[position].name
        holder.personGender.text = personsList[position].gender

        Glide.with(context)
                .load(personsList[position].image)
                .override(1080, 1280)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.personPic)

        holder.itemView.setOnClickListener {
            onPersonClickListener.onPersonClicked(personsList[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personName: AppCompatTextView = view.findViewById(R.id.personName)
        val personGender: AppCompatTextView = view.findViewById(R.id.personGender)
        val personPic: RoundedImageView = view.findViewById(R.id.personImg)
    }
}

interface OnPersonClickListener {
    fun onPersonClicked(person: PersonData)
}