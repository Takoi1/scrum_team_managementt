package projet.springboot.scrumtaskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.springboot.scrumtaskmanagement.model.Task;
import projet.springboot.scrumtaskmanagement.model.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwnerOrderByDateDesc(User user);
}