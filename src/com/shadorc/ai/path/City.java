package com.shadorc.ai.path;

public record City(int x, int y) {

    public float distance(final City city) {
        final float deltaX = Math.abs(this.x() - city.x());
        final float deltaY = Math.abs(this.y() - city.y());
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}
