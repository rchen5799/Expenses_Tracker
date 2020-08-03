package com.e.midterm_expenses

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.midterm_expenses.data.Expenses
import com.e.midterm_expenses.Adapter.expenses_adapter
import kotlinx.android.synthetic.main.activity_summary.*

class Summary : AppCompatActivity() {

    lateinit var expenses_adapter: expenses_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        expenses_adapter = expenses_adapter(MainActivity())


        val expenses = intent.getStringExtra("expenses")
        val income = intent.getStringExtra("income")
        val balance = intent.getStringExtra("balance")

        incAmt.text = income
        expAmt.text = expenses
        balAmt.text = balance


        btnExpense.setOnClickListener {
            var intentResult = Intent()
            intentResult.putExtra(
                MainActivity.KEY_RES, "accept")

            setResult(Activity.RESULT_OK, intentResult)

            finish()
        }
    }


}
