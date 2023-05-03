package io.mazurco066.courses.api.controller


import com.fasterxml.jackson.databind.ObjectMapper
import io.mazurco066.courses.api.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {
    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            // when / then
            mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {
                        value("1234")
                    }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 1234

            // when / then
            mockMvc.get("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {
                        value("1234")
                    }
                }
        }

        @Test
        fun `should return Not Found if the account number does not exists`() {
            // given
            val accountNumber = "does_not_exists"

            // when / then
            mockMvc.get("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddBank {
        @Test
        fun `should return the added bank with given account number`() {
            // given
            val newBank = Bank("4321", 31.45, 2)

            // when / then
            mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {
                        value("4321")
                    }
                    jsonPath("$.trust") {
                        value(31.45)
                    }
                    jsonPath("$.transactionFee") {
                        value(2)
                    }
                }
        }

        @Test
        fun `should return Bad Request if bank with given account number already exists`() {
            // given
            val invalidBank = Bank("1234", 31.45, 2)

            // when / then
            mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class UpdateBank {
        @Test
        fun `should return the updated bank with given account number`() {
            // given
            val accountNumber = "1234"
            val updatedBank = Bank(accountNumber, 1.1, 1)

            // when / then
            mockMvc.patch("/api/banks/$accountNumber") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {
                        value("1234")
                    }
                    jsonPath("$.trust") {
                        value(1.1)
                    }
                    jsonPath("$.transactionFee") {
                        value(1)
                    }
                }

            // when / then (confirm updated data)
            mockMvc.get("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") {
                        value(1.1)
                    }
                    jsonPath("$.transactionFee") {
                        value(1)
                    }
                }
        }

        @Test
        fun `should return NOT FOUND if the requested bank does not exists`() {
            // given
            val accountNumber = "9999"
            val updatedBank = Bank(accountNumber, 1.1, 1)

            // when / then
            mockMvc.patch("/api/banks/$accountNumber") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return BAD REQUEST if the given bank has invalid account number`() {
            // given
            val accountNumber = "1234"
            val updatedBank = Bank("9999", 1.1, 1)

            // when / then
            mockMvc.patch("/api/banks/$accountNumber") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteBank {
        @Test
        fun `should return NO CONTENT when removing a bank`() {
            // given
            val accountNumber = 1234

            // when / then
            mockMvc.delete("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            // when / then (confirm deleted data)
            mockMvc.get("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return NOT FOUND if the given account number does not exists`() {
            // given
            val accountNumber = "does_not_exists"

            // when / then
            mockMvc.delete("/api/banks/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}