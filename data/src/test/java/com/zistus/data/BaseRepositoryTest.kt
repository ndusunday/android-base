//package com.zistus.data
//
//import com.fernandocejas.sample.InjectMocksRule
//import com.zistus.data.datasources.api.ApiService
//import com.zistus.data.datasources.api.ApiSource
//import com.zistus.data.datasources.api.ApiSourceImp
//import com.zistus.data.datasources.db.room.DatabaseSource
//import com.zistus.data.datasources.db.room.DatabaseSourceImpl
//import com.zistus.data.datasources.db.room.dao.BaseDao
//import com.zistus.data.repository.BaseRepositoryImpl
//import com.zistus.domain.repository.BaseRepository
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class BaseRepositoryTest {
//    @Rule
//    @JvmField val injectMocks = InjectMocksRule.create(this@BaseRepositoryTest)
//    @Mock private lateinit var service: ApiService
//
//    @Mock private lateinit var repository: BaseRepository
//    @Mock private lateinit var baseDao: BaseDao
//    @Mock private lateinit var apiSource: ApiSource
//    @Mock private lateinit var dbSource: DatabaseSource
//
//    @Before fun setUp() {
//        apiSource = ApiSourceImp(apiService = service)
//        dbSource = DatabaseSourceImpl(baseDao = baseDao)
//        repository = BaseRepositoryImpl(apiSource, dbSource)
//
//    }
//
//    /*
//    given { networkHandler.isConnected }.willReturn(true)
//        given { moviesResponse.body() }.willReturn(listOf(MovieEntity(1, "poster")))
//        given { moviesResponse.isSuccessful }.willReturn(true)
//        given { moviesCall.execute() }.willReturn(moviesResponse)
//        given { service.movies() }.willReturn(moviesCall)
//
//        val movies = networkRepository.movies()
//
//        movies shouldEqual Right(listOf(Movie(1, "poster")))
//        verify(service).movies()
//     */
//
//    @Test
//    fun `should return the value`() {
//    }
//
//
//}