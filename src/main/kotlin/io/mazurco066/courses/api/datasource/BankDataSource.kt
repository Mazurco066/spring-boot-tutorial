package io.mazurco066.courses.api.datasource

import io.mazurco066.courses.api.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
}