package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class DefaultTasksRepositoryTest {

    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var fakeRemoteDataSource: FakeDataSource
    private lateinit var fakeLocalDataSource: FakeDataSource

    private lateinit var fakeTasksRepository: DefaultTasksRepository

    @Before
    fun createFakeRepository() {
        fakeRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        fakeLocalDataSource = FakeDataSource(localTasks.toMutableList())

        fakeTasksRepository = DefaultTasksRepository(fakeRemoteDataSource, fakeLocalDataSource, Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTasks_requestAllTasksFromRemoteDataSource() = runBlockingTest {

        val tasks = fakeTasksRepository.getTasks(true) as Result.Success

        assertThat(tasks.data, IsEqual(remoteTasks))
    }

}