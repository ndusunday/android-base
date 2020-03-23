package com.zistus.atracker.ui.main.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zistus.atracker.entity.ResponseLiveData
import com.zistus.atracker.mock
import com.zistus.atracker.ui.main.utils.RxImmediateSchedulerRule
import com.zistus.atracker.whenever
import com.zistus.domain.entity.Entity
import com.zistus.domain.repository.BaseRepository
import com.zistus.domain.state.DataState
import com.zistus.domain.usecases.BaseUseCase
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.*
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

//    @Mock
//    private var baseUseCase: BaseUseCase = mock()
    @Mock
    lateinit var baseUseCase: BaseUseCase

    @Mock
    lateinit var baseRepository: BaseRepository

    private val observerState = mock<Observer<DataState<Entity.TestObject>>>()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(baseUseCase)
        print("The usecase ${baseUseCase.testString()}")
//        viewModel.stateLiveData.observeForever(observerState)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testSetUp() {
        val testText = "This is a test"

        val test = viewModel.testReturnString()

        Assert.assertEquals(testText, test)
    }

    @Test
    fun getStuff() {

    }

    @Test
    fun `test test object return as an observable`() {
//        // How mock behaves
//        whenever(baseUseCase.getTestObject())
//            .thenReturn(Single.just(Entity.TestObject("01")))
//
//        // Test method
//        viewModel.getTester()
//
//        Assert.assertEquals("01", viewModel.signedInUser.value?.id)
    }

    @Test
    fun `request for test object should update response live data`() {
//        // How mock behaves
//        val testEntity = Entity.TestObject("01")
//        whenever(baseUseCase.fetchTestObjectDataState())
//            .thenReturn(Single.just(DataState.Success(testEntity)) )
//
//        // Test method
//        viewModel.testObjectDataState()
//
//        val dataState = (viewModel.responseLiveData.value as ResponseLiveData)
//        Assert.assertEquals("01", (dataState.data as Entity.TestObject).id)
    }


//    @Test
//    fun `test test object return as an error`() {
//        // How mock behaves
//        whenever(baseUseCase.getTestObject())
//            .thenReturn(Single.error(Throwable()))
//
//        // Test method
//        viewModel.getTester()
//
//        Assert.fail()
//    }
//
//    @Test
//    fun `simple test should return a string`(){
//        val expected = "test string"
//        val actual = viewModel.testReturnString()
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun fetchUser_ShouldReturnUser() {
//        val expected = 3
//        val actual = viewModel.fetchUser(3)
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun fetchUser_should_returnUser() {
//        val argumentCaptor = ArgumentCaptor.forClass(DataState::class.java)
//        val obj = Entity.TestObject("water")
//        val expectedSuccess = DataState.Success(obj)
//
//        argumentCaptor.run {}
//    }
//
//    @Test
//    fun fetchUserError_should_returnError() {
//        val expectedError = Throwable("water is empty")
//        whenever(baseUseCase.getTestObject())
//            .thenReturn(Single.error(expectedError))
//
//        baseUseCase.getTestObject()
//            .test()
//            .assertError(expectedError)
//    }
}