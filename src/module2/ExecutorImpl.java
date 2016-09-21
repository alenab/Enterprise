package module2;

import java.util.*;

public class ExecutorImpl<T> implements Executor<T> {
    private Map<Task<? extends T>, Validator<? super T>> tasMap = new HashMap<>();
    private List<T> validResults = new ArrayList<>();
    private List<T> invalidResults = new ArrayList<>();
    private boolean isExecuted = false;

    @Override
    public void addTask(Task<? extends T> task) {
        if (!isExecuted) {
            if (task != null) {
                tasMap.put(task, null);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void addTask(Task<? extends T> task, Validator<? super T> validator) {
        if (!isExecuted) {
            if (task != null) {
                tasMap.put(task, validator);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void execute() {
        Set<Task<? extends T>> set = tasMap.keySet();
        for (Task<? extends T> t : set) {
            Validator<? super T> validator = tasMap.get(t);
            t.execute();
            if (validator != null) {
                if (validator.isValid(t.getResult())) {
                    validResults.add(t.getResult());
                } else {
                    invalidResults.add(t.getResult());
                }
            } else {
                validResults.add(t.getResult());
            }
        }
        isExecuted = true;
    }

    @Override
    public List<T> getValidResults() {
        if (isExecuted) {
            return validResults;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<T> getInvalidResults() {
        if (isExecuted) {
            return invalidResults;
        } else {
            throw new IllegalStateException();
        }
    }

}