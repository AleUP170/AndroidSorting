package com.clasemovil.sorting

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.clasemovil.sorting.databinding.ActivityMainBinding
import com.clasemovil.sorting.databinding.ListelementBinding

class Adapter(private val context : Context, private val data: List<Float>): BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(R.layout.listelement, null)

        val ord = view.findViewById<TextView>(R.id.ordinal)
        val num = view.findViewById<TextView>(R.id.numFromList)

        ord.text = (p0+1).toString()
        num.text = data[p0].toString()

        return view
    }

}