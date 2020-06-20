package todo.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todo.domain.port.*;
import todo.infrastructure.adapter.db.TodoListListInMemoryRepository;
import todo.infrastructure.adapter.events.SendTodoAddedEventGuavaAdapter;
import todo.infrastructure.adapter.events.SendTodoDoneEventGuavaAdapter;
import todo.infrastructure.adapter.events.SendTodoListCreatedEventGuavaAdapter;

@Configuration
public class InfrastructureConfig {

    private final EventBus eventBus;
    private final TodoListListInMemoryRepository repository;

    public InfrastructureConfig(){
        this.eventBus = new EventBus();
        this.repository = new TodoListListInMemoryRepository();
    }

    @Bean
    LoadTodoListPort loadTodoListPort(){
        return this.repository;
    }

    @Bean
    SaveTodoListPort saveTodoListPort(){
        return this.repository;
    }

    @Bean
    SendTodoAddedEventPort sendTodoAddedEventPort() {
        return new SendTodoAddedEventGuavaAdapter(this.eventBus);
    }

    @Bean
    SendTodoListCreatedEventPort sendTodoListCreatedEventPort() {
        return new SendTodoListCreatedEventGuavaAdapter(this.eventBus);
    }

    @Bean
    SendTodoDoneEventPort sendTodoDoneEventPort() {
        return new SendTodoDoneEventGuavaAdapter(this.eventBus);
    }
}