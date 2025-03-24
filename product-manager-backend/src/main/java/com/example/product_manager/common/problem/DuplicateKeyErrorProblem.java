package com.example.product_manager.common.problem;

import java.util.Map;

public class DuplicateKeyErrorProblem extends ConflictProblem {

  public DuplicateKeyErrorProblem() {
    super("Duplicate Key Error", Map.of("translationKey", "DUPLICATE_KEY_ERROR"));
  }
}
