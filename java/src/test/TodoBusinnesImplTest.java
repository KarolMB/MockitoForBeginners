package test;

import main.TodoBusinessImpl;
import main.TodoService;
import main.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
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


    @Test
    public void testListMockGet() {
        List listMock = mock(List.class);

        when(listMock.get(0)).thenReturn("test number");

        assertEquals("test number", listMock.get(0));

        when(listMock.get(anyInt())).thenReturn("my number");
        assertEquals("my number", listMock.get(0));
        assertEquals("my number", listMock.get(5));
        assertEquals("my number", listMock.get(10));
    }

    @Test
    public void testListMock_throwAnException() {
        List listMock = mock(List.class);

        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something went wrong"));

        //TODO: Why this test is not passed?
        //assertThrows(RuntimeException.class, (Executable) listMock.get(0));

        assertThrows(RuntimeException.class, () -> listMock.get(0));

    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {
        // Given
        TodoService mockTodoService = mock(TodoService.class);

        when(mockTodoService.retriveTodos("Dummy"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance"));

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);

        // When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD2() {
        // Given
        TodoService mockTodoService = mock(TodoService.class);

        given(mockTodoService.retriveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance"));

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);

        // When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertEquals(2, filteredTodos.size());
    }
}
