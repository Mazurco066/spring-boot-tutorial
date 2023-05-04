package io.mazurco066.courses.api.datasource.mock

import io.mazurco066.courses.api.model.Bank
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MockBankDataSourceTest {
    private val mockDataSource: MockBankDataSource = MockBankDataSource()
    @Test
    fun `should provide a collection of banks`() {
        // when
        val banks = mockDataSource.getBanks()

        // then
        assertThat(banks).isNotEmpty
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data`() {
        // when
        val banks = mockDataSource.getBanks()

        // then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }

    @Test
    fun `should provide a requested bank`() {
        // given
        val accountNumber = "1234"

        // when
        val bank = mockDataSource.getBank(accountNumber)

        // then
        assertThat(bank).isNotNull
        assertThat(bank.accountNumber).isEqualTo("1234")
        assertThat(bank.trust).isEqualTo(3.14)
        assertThat(bank.transactionFee).isEqualTo(17)
    }

    @Test
    fun `should delete a requested bank`() {
        // given
        val accountNumber = "1234"

        // when
        mockDataSource.deleteBank(accountNumber)
        val banks = mockDataSource.getBanks()

        // then
        assertThat(banks).allMatch { it.accountNumber !== accountNumber }
    }

    @Test
    fun `should create a given bank`() {
        // given
        val newBank = Bank("4321", 31.45, 2)

        // when
        val bank = mockDataSource.addBank(newBank)

        // then
        assertThat(bank).isNotNull
        assertThat(bank.accountNumber).isEqualTo("4321")
        assertThat(bank.trust).isEqualTo(31.45)
        assertThat(bank.transactionFee).isEqualTo(2)
    }

    @Test
    fun `should update a given bank`() {
        // given
        val accountNumber = "1234"
        val updatedBank = Bank(accountNumber, 1.1, 1)

        // when
        val bank = mockDataSource.updateBank(accountNumber, updatedBank)

        // then
        assertThat(bank).isNotNull
        assertThat(bank.accountNumber).isEqualTo("1234")
        assertThat(bank.trust).isEqualTo(1.1)
        assertThat(bank.transactionFee).isEqualTo(1)
    }
}