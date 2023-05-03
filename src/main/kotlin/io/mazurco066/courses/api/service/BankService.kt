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
}