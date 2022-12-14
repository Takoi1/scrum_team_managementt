package projet.springboot.scrumtaskmanagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import projet.springboot.scrumtaskmanagement.model.Task;
import projet.springboot.scrumtaskmanagement.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskServiceMock;

    @Test
    public void findAll_shouldReturnTasksList() {
                 List<Task> expectedTasks = Arrays.asList(
                        new Task(),
                        new Task()
                );
        when(taskRepository.findAll()).thenReturn(expectedTasks);

        assertThat(taskServiceMock.findAll()).isEqualTo(expectedTasks);
    }

}