package com.zistus.atracker.ui.main.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.auth.FirebaseUser
import com.zistus.atracker.mock
import com.zistus.atracker.ui.main.firebase.entry.Entry
import com.zistus.atracker.ui.main.utils.RxImmediateSchedulerRule
import com.zistus.atracker.whenever
import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import com.zistus.domain.usecases.BaseUseCase
import com.zistus.domain.usecases.user.UserUseCase
import io.reactivex.Single
import org.junit.*
import org.mockito.ArgumentMatchers.anyString
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

    @Test
    fun `login with email and password and update loginResponse with success user entity`() {
        val testUser = Entity.User("tester@test.com", "water test")
        // How mock behaves
        whenever(userUseCase.loginUser("tester@test.com", "password"))
            .thenReturn(Single.just(DataState.Success(testUser)))
        // Test method
        viewModel.loginUser("tester@test.com", "password")

        // Confirm
        Assert.assertEquals("tester@test.com", (viewModel.loginResponse.value as DataState.Success).data.userId)
    }

    @Test
    fun `login with email and password and update loginResponse with failed user entity`() {
        val errorMsg  = "Failed to fetch"
        // How mock behaves
        whenever(userUseCase.loginUser("tester@test.com", "password"))
            .thenReturn(Single.just(DataState.Failed(Throwable(errorMsg), null)))
        // Test method
        viewModel.loginUser("tester@test.com", "password")
        // Confirm
        Assert.assertEquals(errorMsg, (viewModel.loginResponse.value as DataState.Failed).error.message)
    }

    @Test
    fun `login with email and password failed for different error msgs`() {
        val errorMsg  = "Failed to fetch"
        // How mock behaves
        whenever(userUseCase.loginUser("tester@test.com", "password"))
            .thenReturn(Single.just(DataState.Failed(Throwable(errorMsg), null)))
        // Test method
        viewModel.loginUser("tester@test.com", "password")
        // Confirm
        Assert.assertTrue("Different Error" != (viewModel.loginResponse.value as DataState.Failed).error.message)
    }

    @Test
    fun `login with phone number update loginResponse with success user entity`() {
        val testUser = Entity.User("tester@test.com", "water test")
        // How mock behaves
//        whenever(userUseCase.loginUserPhone("+2348031162141"))
//            .thenReturn(Single.just(DataState.Success(testUser)))
        // Test method
//        viewModel.loginUserPhone("08031162141")

        // Confirm
        Assert.assertEquals("tester@test.com", (viewModel.loginResponse.value as DataState.Success).data.userId)
    }
}