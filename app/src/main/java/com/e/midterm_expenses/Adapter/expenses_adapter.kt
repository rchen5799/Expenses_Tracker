package com.e.midterm_expenses.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.e.midterm_expenses.MainActivity
import com.e.midterm_expenses.R
import com.e.midterm_expenses.data.Expenses
import com.e.midterm_expenses.touch.ExpensesTouchHelperCallback
import kotlinx.android.synthetic.main.expenses_row.view.*
import java.util.*

class expenses_adapter(val context:MainActivity) : RecyclerView.Adapter<expenses_adapter.ViewHolder>()
    //ExpensesTouchHelperCallback
    {

    var expensesItems = mutableListOf(Expenses("Example", true, 0))


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.expenses_row, parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expensesItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentExpenses = expensesItems[position]

        holder.tvDes.text = currentExpenses.expensesText
        holder.tvAmt.text = currentExpenses.expensesAmt.toString()

        if(currentExpenses.surplus) {
            holder.tvIcon.setImageResource(R.drawable.receive)
        } else {
            holder.tvIcon.setImageResource(R.drawable.spend)
        }

        holder.btnDelete.setOnClickListener {
            deleteExpenses(holder.adapterPosition)
        }
    }

    //Getting Income
    public fun getIncome() : Int {
        var income = 0
        var counter = 0
        while(counter<itemCount) {
            if(expensesItems[counter].surplus == true) {
                income = income + expensesItems[counter].expensesAmt
                counter = counter + 1
            } else {
                counter = counter + 1
            }
        }
        return income
    }

    //Getting Expenses
    fun getExpenses() : Int {
        var expenses = 0
        var counter = 0
        while(counter<itemCount) {
            if(expensesItems[counter].surplus == false) {
                expenses = expenses + expensesItems[counter].expensesAmt
                counter = counter + 1
            } else {
                counter = counter + 1
            }
        }
        return expenses
    }

    //Getting Balance
    fun getBalance(): Int {
        return getIncome() - getExpenses()
    }

    private fun deleteExpenses(position: Int) {
        expensesItems.removeAt(position)
        context.updateBal()
        notifyItemRemoved(position)
    }

    fun deleteAll() {
        expensesItems.clear()
        notifyDataSetChanged()
    }

    fun addExpenses(expenses: Expenses) {
        expensesItems.add(expenses)

        //notifyDataSetChanged() // this refreshes the whole list
        notifyItemInserted(expensesItems.lastIndex)
    }

    fun onDismissed(position: Int) {
        deleteExpenses(position)
    }

    fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(expensesItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDes = itemView.tvDes
        val tvAmt = itemView.tvAmt
        val tvIcon = itemView.ExpenseIcon
        val btnDelete = itemView.btnDelete

    }

}