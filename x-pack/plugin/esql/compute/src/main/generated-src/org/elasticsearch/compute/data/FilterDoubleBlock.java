/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.compute.data;

/**
 * Filter block for DoubleBlocks.
 * This class is generated. Do not edit it.
 */
final class FilterDoubleBlock extends AbstractFilterBlock implements DoubleBlock {

    private final DoubleBlock block;

    FilterDoubleBlock(DoubleBlock block, int... positions) {
        super(block, positions);
        this.block = block;
    }

    @Override
    public DoubleVector asVector() {
        return null;
    }

    @Override
    public double getDouble(int valueIndex) {
        return block.getDouble(mapPosition(valueIndex));
    }

    @Override
    public ElementType elementType() {
        return ElementType.DOUBLE;
    }

    @Override
    public DoubleBlock getRow(int position) {
        return filter(position);
    }

    @Override
    public DoubleBlock filter(int... positions) {
        return new FilterDoubleBlock(this, positions);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoubleBlock that) {
            return DoubleBlock.equals(this, that);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return DoubleBlock.hash(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[block=" + block + "]";
    }
}
