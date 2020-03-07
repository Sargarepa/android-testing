package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf<Task>(
                Task("title", "description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(100f, result.activeTasksPercent)
        assertEquals(0f, result.completedTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_someCompleted_returnsSixtyForty() {
        val tasks = listOf<Task>(
                Task("title", "description", isCompleted = true),
                Task("title", "description", isCompleted = true),
                Task("title", "description", isCompleted = false),
                Task("title", "description", isCompleted = false),
                Task("title", "description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(60f))
        assertEquals(result.completedTasksPercent, `is`(40f))
    }

    @Test
    fun getActiveAndCompletedStats_listEmpty_returnZeroZero() {
        val tasks = listOf<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.activeTasksPercent)
        assertEquals(0f, result.completedTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_listNull_returnZeroZero() {
        val tasks = null

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.activeTasksPercent)
        assertEquals(0f, result.completedTasksPercent)
    }
}