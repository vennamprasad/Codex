package com.android.codex.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class RunDaoTest {


    lateinit var database: RunningDatabase
    lateinit var runDAO: RunDAO

    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RunningDatabase::class.java
        )
            .allowMainThreadQueries().build()
        runDAO = database.getRunDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

   /*
   //check https://www.youtube.com/watch?v=xGbr9LOSbC0&list=PLQkwcJG4YTCSYJ13G4kVIJ10X5zisB2Lq 24:22 min
   @ExperimentalCoroutinesApi
    @Test
    fun insertUpdateRun() = runBlockingTest {
        val run = Run(, 0L, 0F, 0, 0L, 0)
        runDAO.insertRun(run)
        val allRuns = runDAO.getAllData().getOrAwaitValue()
        assertThat(allRuns).contains(run)
    }*/
}
