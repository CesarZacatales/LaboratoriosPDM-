package com.example.laboratorio11

import com.example.laboratorio11.network.dto.login.LoginRequest
import com.example.laboratorio11.network.service.AuthService
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var authService: AuthService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()

        authService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

    @Test
    fun loginTest() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("""{"msg": "Login successful", "token": "testToken"}""")
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val response = authService.login(LoginRequest("QA", "QA"))
        mockWebServer.takeRequest()

        Assert.assertEquals("Login successful",response.message)
        Assert.assertEquals("testToken", response.token)
    }

    @Test
    fun unsuccessfulLogin() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("""{"msg": "Check credentials"}""")
        mockWebServer.enqueue(mockResponse)

        val response = authService.login(LoginRequest("QA", "QA"))
        mockWebServer.takeRequest()

        Assert.assertEquals("Check credentials", response.message)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}