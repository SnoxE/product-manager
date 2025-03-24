package com.example.product_manager.common.problem;

import java.util.Map;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

public class AlreadyExistsProblem extends AbstractThrowableProblem {
  public AlreadyExistsProblem(String detail) {
    super(Problem.DEFAULT_TYPE, Status.CONFLICT.getReasonPhrase(), Status.CONFLICT, detail);
  }

  public AlreadyExistsProblem(String detail, Map<String, Object> parameters) {
    super(
        Problem.DEFAULT_TYPE,
        Status.CONFLICT.getReasonPhrase(),
        Status.CONFLICT,
        detail,
        null,
        null,
        parameters);
  }
}
