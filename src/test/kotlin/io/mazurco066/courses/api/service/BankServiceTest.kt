package io.mazurco066.courses.api.service

import io.mazurco066.courses.api.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BankServiceTest {
    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService: BankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // given
        every { dataSource.getBanks() } returns emptyList()

        // when
        bankService.getBanks()

        // then
        verify(exactly = 1) { dataSource.getBanks() }
    }

    @Test
    fun `should call its data source to retrieve a single bank`() {
        // given
        val accountNumber = "1234"
        every { dataSource.getBanks() } returns emptyList()

        // when
        bankService.getBank(accountNumber)

        // then
        verify(exactly = 1) { dataSource.getBank(accountNumber) }
    }
}