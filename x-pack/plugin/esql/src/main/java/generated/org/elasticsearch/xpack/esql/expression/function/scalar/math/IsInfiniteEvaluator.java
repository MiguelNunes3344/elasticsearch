// Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
// or more contributor license agreements. Licensed under the Elastic License
// 2.0; you may not use this file except in compliance with the Elastic License
// 2.0.
package org.elasticsearch.xpack.esql.expression.function.scalar.math;

import java.lang.Override;
import java.lang.String;
import org.elasticsearch.compute.data.Block;
import org.elasticsearch.compute.data.BooleanBlock;
import org.elasticsearch.compute.data.BooleanVector;
import org.elasticsearch.compute.data.DoubleBlock;
import org.elasticsearch.compute.data.DoubleVector;
import org.elasticsearch.compute.data.Page;
import org.elasticsearch.compute.operator.DriverContext;
import org.elasticsearch.compute.operator.EvalOperator;
import org.elasticsearch.core.Releasables;

/**
 * {@link EvalOperator.ExpressionEvaluator} implementation for {@link IsInfinite}.
 * This class is generated. Do not edit it.
 */
public final class IsInfiniteEvaluator implements EvalOperator.ExpressionEvaluator {
  private final EvalOperator.ExpressionEvaluator val;

  private final DriverContext driverContext;

  public IsInfiniteEvaluator(EvalOperator.ExpressionEvaluator val, DriverContext driverContext) {
    this.val = val;
    this.driverContext = driverContext;
  }

  @Override
  public Block eval(Page page) {
    try (DoubleBlock valBlock = (DoubleBlock) val.eval(page)) {
      DoubleVector valVector = valBlock.asVector();
      if (valVector == null) {
        return eval(page.getPositionCount(), valBlock);
      }
      return eval(page.getPositionCount(), valVector).asBlock();
    }
  }

  public BooleanBlock eval(int positionCount, DoubleBlock valBlock) {
    try(BooleanBlock.Builder result = driverContext.blockFactory().newBooleanBlockBuilder(positionCount)) {
      position: for (int p = 0; p < positionCount; p++) {
        if (valBlock.isNull(p) || valBlock.getValueCount(p) != 1) {
          result.appendNull();
          continue position;
        }
        result.appendBoolean(IsInfinite.process(valBlock.getDouble(valBlock.getFirstValueIndex(p))));
      }
      return result.build();
    }
  }

  public BooleanVector eval(int positionCount, DoubleVector valVector) {
    try(BooleanVector.Builder result = driverContext.blockFactory().newBooleanVectorBuilder(positionCount)) {
      position: for (int p = 0; p < positionCount; p++) {
        result.appendBoolean(IsInfinite.process(valVector.getDouble(p)));
      }
      return result.build();
    }
  }

  @Override
  public String toString() {
    return "IsInfiniteEvaluator[" + "val=" + val + "]";
  }

  @Override
  public void close() {
    Releasables.closeExpectNoException(val);
  }

  static class Factory implements EvalOperator.ExpressionEvaluator.Factory {
    private final EvalOperator.ExpressionEvaluator.Factory val;

    public Factory(EvalOperator.ExpressionEvaluator.Factory val) {
      this.val = val;
    }

    @Override
    public IsInfiniteEvaluator get(DriverContext context) {
      return new IsInfiniteEvaluator(val.get(context), context);
    }

    @Override
    public String toString() {
      return "IsInfiniteEvaluator[" + "val=" + val + "]";
    }
  }
}
