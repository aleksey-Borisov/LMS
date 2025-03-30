package com.example.services;

import com.example.models.Course;
import com.example.models.Problem;
import com.example.models.Topic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    private final List<Topic> topics = new ArrayList<>();
    private int nextId = 1;

    public TopicService() {}

    public Topic findTopicById(int id) {
        for (Topic topic : topics) {
            if (topic.getId() == id) {
                return topic;
            }
        }
        return null;
    }

    public boolean deleteTopic(int id) {
        Topic topicToRemove = findTopicById(id);
        if (topicToRemove != null) {
            return topics.remove(topicToRemove);
        }
        return false;
    }

    public Topic addProblem(int courseId, Problem newProblem) {
        int problemId = newProblem.getId();
        Topic topic = findTopicById(courseId);
        if (!topic.getProblems().contains(problemId)) {
            topic.getProblems().add(problemId);
        }
        return topic;
    }

    public void reset() {
        topics.clear();
        nextId = 1;
    }

    public List<Topic> getTopics() {
        return topics;
    }
}


