package com.zistus.atracker.ui.main.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zistus.atracker.mock
import com.zistus.atracker.ui.main.utils.RxImmediateSchedulerRule
import com.zistus.atracker.whenever
import com.zistus.domain.usecases.BaseUseCase
import com.zistus.domain.usecases.user.UserUseCase
import io.reactivex.Single
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AuthenticationViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var userUseCase: UserUseCase
    @Mock
    lateinit var baseUseCase: BaseUseCase
    lateinit var viewModel: AuthenticationViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        print("The usecase ${userUseCase.testString()} ${baseUseCase.testString()}")
        viewModel = AuthenticationViewModel(userUseCase)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testSetUp() {
        val testText = "This is a test"
        // How mock behaves
        whenever(userUseCase.testString())
            .thenReturn(Single.just(testText))
        // Test method
        viewModel.testSetup()
        // Confirm
        Assert.assertEquals("This is a test", viewModel.light.value)
    }
}