package test;

import org.junit.jupiter.api.Test;

import main.TodoBusinessImpl;
import main.TodoService;
import main.TodoServiceStub;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TodoBusinnesImplTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingStub() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingMock() {
        TodoService mockTodoService = mock(TodoService.class);

        when(mockTodoService.retriveTodos("Dummy"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance"));

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testListMock() {
        List listMock = mock(List.class);

        when(listMock.size()).thenReturn(2).thenReturn(3);

        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }
}
