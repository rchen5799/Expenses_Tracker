package com.e.midterm_expenses

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import com.e.midterm_expenses.Adapter.expenses_adapter
import com.e.midterm_expenses.data.Expenses
import com.e.midterm_expenses.touch.ExpensesRecyclerTouchCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_summary.*
import kotlinx.android.synthetic.main.expenses_row.*

class MainActivity : AppCompatActivity() {

    lateinit var expenses_adapter: expenses_adapter

    companion object {
        const val KEY_DATA = "KEY_DATA"
        const val REQ_ANSWER = 1001
        const val KEY_RES = "KEY_RES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LaunchLogin()

        //Takes you to different Activity
        btnSummary.setOnClickListener {
            var intentDetails = Intent()

            intentDetails.setClass(this@MainActivity, Summary::class.java)

            var expenses = expenses_adapter.getExpenses().toString()
            var income = expenses_adapter.getIncome().toString()
            var balance = expenses_adapter.getBalance().toString()
            intentDetails.putExtra("expenses", expenses)
            intentDetails.putExtra("income",income)
            intentDetails.putExtra("balance", balance)

            startActivityForResult(intentDetails, REQ_ANSWER)
            //startActivity(Intent(this, ResultActivity::class.java))
        }

        //Recycler Views
        expenses_adapter = expenses_adapter(this)
        recyclerExpenses.adapter = expenses_adapter

        val touchCallbackList = ExpensesRecyclerTouchCallback(expenses_adapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbackList)
        itemTouchHelper.attachToRecyclerView(recyclerExpenses)

        //Save Features
        btnSave.setOnClickListener {

            //throws error
            try {
                if(!TextUtils.isEmpty(edNum.text.toString()) && !TextUtils.isEmpty(edDes.text.toString())) {
                    //converts to int
                    var delta_amt = edNum.text.toString().toInt()
                    var info = edDes.text.toString()
                    var add = true

                        /*
                    //Changing Images
                    val img: ImageView = findViewById(R.id.ExpenseIcon)
                    if(btnExp.isChecked) {
                        add = false
                        img.setImageResource(R.drawable.spend)
                    } else {
                        img.setImageResource(R.drawable.receive)
                    } */

                    if(btnExp.isChecked) {
                        add = false
                    }

                    expenses_adapter.addExpenses(
                        Expenses(info, add, delta_amt)
                    )

                    //balNum.text = expenses_adapter.getBalance().toString()
                    updateBal()

                } else {
                    edNum.error = "This field can not be empty!"
                    edDes.error = "This field can not be empty!"
                }
            } catch(e: Exception) {
                edNum.error = e.message
                edDes.error = e.message
            }
        }


        //Delete Everything
        delAll.setOnClickListener{
            expenses_adapter.deleteAll()
            updateBal()
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    fun updateBal() {
        balNum.text = expenses_adapter.getBalance().toString()
    }

    fun LaunchLogin() {
        var intentDetails = Intent()

        intentDetails.setClass(this@MainActivity, Password::class.java)

        startActivityForResult(intentDetails, REQ_ANSWER)
    }
}
