package io.mazurco066.courses.api.service

import io.mazurco066.courses.api.datasource.BankDataSource
import io.mazurco066.courses.api.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(
    private val dataSource: BankDataSource
) {
    fun getBanks(): Collection<Bank> {
        return dataSource.getBanks()
    }

    fun getBank(accountNumber: String): Bank {
        return dataSource.getBank(accountNumber)
    }

    fun addBank(bank: Bank): Bank {
        return dataSource.addBank(bank)
    }

    fun updateBank(accountNumber: String, bank: Bank): Bank {
        return dataSource.updateBank(accountNumber, bank)
    }

    fun deleteBank(accountNumber: String) {
        return dataSource.deleteBank(accountNumber)
    }
}