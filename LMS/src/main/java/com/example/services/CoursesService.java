package com.example.services;

import com.example.models.Course;
import com.example.models.RawCourse;
import com.example.models.Topic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CoursesService {
    private final List<Course> courses = new ArrayList<>();
    private int lastId = 0;

    public CoursesService() {}

    public void clearAll() {
        courses.clear();
        lastId = 0;
    }

    public Course getCourseById(int id) throws NoSuchElementException {
        Course result = null;
        for (Course course: courses) {
            if (course.getId() == id) {
                result = course;
                break;
            }
        }
        if (result == null) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
        return result;
    }

    public void deleteCourseById(int id) throws NoSuchElementException {
        boolean deleted = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.remove(i);
                deleted = true;
                break;
            }
        }
        if (!deleted) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
    public Course createCourse(RawCourse rawCourse) {
        int id = lastId + 1;
        lastId += 1;

        Course course = new Course(id, rawCourse.getTitle(), rawCourse.getDescription(), rawCourse.getTopics(), rawCourse.getStudents());
        courses.add(course);

        // Возвращает созданный курс
        return course;
    }
    public Course enrollStudent(int courseId, int studentId) {
        Course course = getCourseById(courseId);
        if (!course.getStudents().contains(studentId)) {
            course.getStudents().add(studentId);
        }
        return course;
    }
    public Course unenrollStudent(int courseId, int studentId) {
        Course course = getCourseById(courseId);
        boolean removed = course.getStudents().remove(Integer.valueOf(studentId));
        if (!removed) {
            throw new NoSuchElementException(
                    "Студент с ID " + studentId + " не записан на курс с ID " + courseId
            );
        }
        return course;
    }
    public Course addTopic(int courseId, Topic newTopic) {
        int topicId = newTopic.getId();
        Course course = getCourseById(courseId);
        if (!course.getTopics().contains(topicId)) {
            course.getTopics().add(topicId);
        }
        return course;
    }
}
