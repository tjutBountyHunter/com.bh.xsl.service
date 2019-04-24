package service;

public interface searchTaskMQ {
    void addTaskJson(String json);

    void deleteTaskJson(String json);

    void alterTaskJson(String json);

    void numberTaskJson(String json);
}
