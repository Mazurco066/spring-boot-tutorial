package io.mazurco066.courses.api.datasource

import io.mazurco066.courses.api.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber: String): Bank
    fun addBank(bank: Bank): Bank
}