package io.mazurco066.courses.api.datasource.mock

import io.mazurco066.courses.api.datasource.BankDataSource
import io.mazurco066.courses.api.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {
    val banks = mutableListOf(
        Bank("1234", 3.14, 17),
        Bank("1010", 17.0, 0),
        Bank("1029", 0.0, 100)
    )

    override fun getBanks(): Collection<Bank> {
        return banks
    }

    override fun getBank(accountNumber: String): Bank {
        return  banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number: $accountNumber")
    }

    override fun addBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(accountNumber: String, bank: Bank): Bank {
        val currentBank = banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number: $accountNumber")
        if (accountNumber != bank.accountNumber) {
            throw IllegalArgumentException("Account number field cannot be updated!")
        }

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number: $accountNumber")

        banks.remove(currentBank)
    }
}