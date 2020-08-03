package com.e.midterm_expenses.touch

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.e.midterm_expenses.Adapter.expenses_adapter

class ExpensesRecyclerTouchCallback(
    private val expensesTouchHelperAdapter: expenses_adapter
) :
    ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        expensesTouchHelperAdapter.onDismissed(viewHolder.adapterPosition)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        expensesTouchHelperAdapter.onItemMoved(
            viewHolder.adapterPosition,
            target.adapterPosition
        )

        return true
    }

}