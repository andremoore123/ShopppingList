package com.example.myrecyclerview.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class SwipeGesture: ItemTouchHelper.Callback(){
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlag = ItemTouchHelper.LEFT
        return makeMovementFlags(0, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
       return false
    }
}