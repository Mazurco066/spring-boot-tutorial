package io.mazurco066.courses.api.datasource.mock

import io.mazurco066.courses.api.datasource.BankDataSource
import io.mazurco066.courses.api.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {
    val banks = listOf(
        Bank("1234", 3.14, 17),
        Bank("1010", 17.0, 0),
        Bank("1029", 0.0, 100)
    )

    override fun getBanks(): Collection<Bank> {
        return banks
    }
}