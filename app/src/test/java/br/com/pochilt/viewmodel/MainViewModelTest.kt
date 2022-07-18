package br.com.pochilt.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.pochilt.domain.GetUserNameUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("Thread")

    private val getUserNameUseCase = Mockito.mock(GetUserNameUseCase::class.java)
    private val viewModel = MainViewModel(getUserNameUseCase)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `GIVEN validateEmail is called with a wrong email THEN the screen InvalidEmail must be called`() {
        Mockito
            .`when`(
                getUserNameUseCase.getUserName()
            ).thenReturn(
                "Adriano"
            )

        val screenFlow = viewModel.screen.shareIn(
            CoroutineScope(Dispatchers.Unconfined),
            started = SharingStarted.Eagerly,
            replay = 1
        )

        runBlocking { viewModel.init().join() }
        val screen = screenFlow.replayCache.last()
        Truth.assertThat(screen).isEqualTo(MainViewModel.Screen.UpdateTitle("Adriano"))
    }
}