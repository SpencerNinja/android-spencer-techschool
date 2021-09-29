package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFilterType
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TaskDetailViewModelTest {

    private lateinit var tasksRepository: FakeTestRepository

    // Subject under test
    private lateinit var taskDetailViewModel: TaskDetailViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        taskDetailViewModel = TaskDetailViewModel(tasksRepository)

    }


    @Test
    fun addNewTask_setsNewTaskEvent() {

        // When adding a new task
        taskDetailViewModel.addNewTask()

        // Then the new task event is triggered
        val value = taskDetailViewModel.newTaskEvent.getOrAwaitValue()
        Assert.assertThat(
            value?.getContentIfNotHandled(), (CoreMatchers.not(CoreMatchers.nullValue()))
        )
    }

    @Test
    fun getTasksAddViewVisible() {

        // When the filter type is ALL_TASKS
        taskDetailViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        Assert.assertThat(
            taskDetailViewModel.tasksAddViewVisible.getOrAwaitValue(),
            CoreMatchers.`is`(true)
        )
    }

}