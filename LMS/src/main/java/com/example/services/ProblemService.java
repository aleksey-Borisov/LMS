package com.example.services;

import com.example.models.Problem;
import com.example.models.RawProblem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProblemService {
    private final List<Problem> problems = new ArrayList<>();
    private int lastId = 0;

    public ProblemService() {}

    public Problem getProblemById(int id) throws NoSuchElementException {
        Problem result = null;
        for (Problem problem: problems) {
            if (problem.getId() == id) {
                result = problem;
                break;
            }
        }
        if (result == null) {
            throw new NoSuchElementException("Problem not found with ID: " + id);
        }
        return result;
    }

    public void deleteProblemById(int id) throws NoSuchElementException {
        boolean deleted = false;
        for (int i = 0; i < problems.size(); i++) {
            if (problems.get(i).getId() == id) {
                problems.remove(i);
                deleted = true;
                break;
            }
        }
        if (!deleted) {
            throw new NoSuchElementException("Problem not found with ID: " + id);
        }
    }

    public Problem createProblem(RawProblem rawProblem) {
        int id = lastId + 1;
        lastId += 1;

        Problem problem = new Problem(id, rawProblem.getTitle(), rawProblem.getDescription());
        problems.add(problem);

        // Возвращает созданную задачу
        return problem;
    }

    public List<Problem> getAllProblems() {
        return new ArrayList<>(problems); // Чтобы нельзя было изменить статический список
    }

    public int getLastId() {
        return lastId;
    }

    public void reset() {
        problems.clear();
        lastId = 0;
    }
}
