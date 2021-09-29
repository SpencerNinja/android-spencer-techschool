package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    // If there are no completed tasks and one active task,
    // then there are 100% active tasks and 0% completed tasks.
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsZeroHundred() {

        // GIVEN a list of tasks with a single, active task
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = false)
        )

        // WHEN you call getActiveAndCompletedStats
        val result = getActiveAndCompletedStats(tasks)

        // THEN there are 0% completed tasks and 100% active tasks
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    // No active tasks, 100% of tasks completed
    @Test
    fun getActiveAndCompletedStats_noActive_returnsHundredZero() {
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = false)
        )
        // When the list of tasks is computed with a completed task
        val result = getActiveAndCompletedStats(null)
        // Then the percentages are 0 and 100
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    // If there are 2 completed tasks and 3 active tasks,
    // then there are 40% of tasks that are complete and 60% of active tasks.
    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Given 3 completed tasks and 2 active tasks
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false)
        )
        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)
        // Then the result is 40-60
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

    //
    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        val tasks = emptyList<Task>()
        // When there are no tasks
        val result = getActiveAndCompletedStats(tasks)
        // Both active and completed tasks are 0
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    //
    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        val tasks = null
        // When there's an error loading stats
        val result = getActiveAndCompletedStats(tasks)
        // Both active and completed tasks are 0
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

}