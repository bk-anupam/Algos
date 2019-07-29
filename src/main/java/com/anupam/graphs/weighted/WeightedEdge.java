package com.anupam.graphs.weighted;

import java.util.Objects;

public class WeightedEdge implements Comparable<WeightedEdge>{
    private int either;
    private int other;
    private double weight;

    public WeightedEdge(int either, int other, double weight) {
        this.either = either;
        this.other = other;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedEdge that = (WeightedEdge) o;
        return either == that.either &&
                other == that.other &&
                Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(either, other, weight);
    }

    public int either(){
        return either;
    }

    public int other(int vertex) throws Exception{
        if(vertex == either)
            return other;
        else if(vertex == other)
            return either;
        else
            throw new Exception(String.format("No such vertex %s exists in the edge: %s", vertex, this.toString()));
    }

    public double weight(){
        return weight;
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "either=" + either +
                ", other=" + other +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return Double.compare(weight, o.weight);
    }
}
