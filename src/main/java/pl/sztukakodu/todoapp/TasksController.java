package pl.sztukakodu.todoapp;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
@Timed
class TasksController {

    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, String> storage = new HashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public long addTask(@RequestBody String task) {
        long id = counter.getAndIncrement();
        storage.put(id, task);
        return id;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String addTask(@PathVariable Long id) {
        return storage.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> listTasks() {
        return storage;
    }

}
