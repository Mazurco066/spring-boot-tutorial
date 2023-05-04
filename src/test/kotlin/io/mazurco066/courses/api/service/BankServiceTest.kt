package io.mazurco066.courses.api.service

import io.mazurco066.courses.api.datasource.BankDataSource
import io.mazurco066.courses.api.model.Bank
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

    @Test
    fun `should call its data source to create a single bank`() {
        // given
        val newBank = Bank("4321", 31.45, 2)
        every { dataSource.getBanks() } returns emptyList()

        // when
        bankService.addBank(newBank)

        // then
        verify(exactly = 1) { dataSource.addBank(newBank) }
    }

    @Test
    fun `should call its data source to update a single bank`() {
        // given
        val accountNumber = "1234"
        val updatedBank = Bank(accountNumber, 1.1, 1)
        every { dataSource.getBanks() } returns emptyList()

        // when
        bankService.updateBank(accountNumber, updatedBank)

        // then
        verify(exactly = 1) { dataSource.updateBank(accountNumber, updatedBank) }
    }

    @Test
    fun `should call its data source to delete a single bank`() {
        // given
        val accountNumber = "1234"
        every { dataSource.getBanks() } returns emptyList()

        // when
        bankService.deleteBank(accountNumber)

        // then
        verify(exactly = 1) { dataSource.deleteBank(accountNumber) }
    }
}