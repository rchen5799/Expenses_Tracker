package com.e.midterm_expenses.touch

interface ExpensesTouchHelperCallback {
    fun onDismissed(position: Int)
    fun onItemMoved(fromPosition: Int, toPosition: Int)
}