package com.myapp.moneytracker

object CategoryOptions {

    fun expenseCategory(): ArrayList<String> {
        val listExpense = ArrayList<String>()
        listExpense.add("Food/Beverage")
        listExpense.add("Transportation")
        listExpense.add("Entertainment")
        listExpense.add("Education")
        listExpense.add("Bills")
        listExpense.add("Shopping")
        listExpense.add("Communication")
        listExpense.add("Investment")
        listExpense.add("Health")
        listExpense.add("Other Expense")

        return listExpense //here it returns the list 
    }

    fun incomeCategory(): ArrayList<String> {
        val listIncome = ArrayList<String>()
        listIncome.add("Salary")
        listIncome.add("Award")
        listIncome.add("Gift")
        listIncome.add("Investment Return")
        listIncome.add("Other Income")

        return listIncome //here it returns the list 
    }
}
