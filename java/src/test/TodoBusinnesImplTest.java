package test;

import org.junit.jupiter.api.Test;

import main.TodoBusinessImpl;
import main.TodoService;
import main.TodoServiceStub;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoBusinnesImplTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingStub() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());
    }
}
