package com.tuliomagalhaes.graph.adjacencymatrix;

public class Graph<T> {
    private final int CONNECTED = 1;
    private final int DISCONNECTED = 0;

    private final int size;
    private final T[] vertexList;
    private final int[][] adjacencyMatrix;
    private int numberOfVertex;

    public Graph(int size) {
        this.size = size;
        numberOfVertex = 0;
        vertexList = (T[]) new Object[size];
        adjacencyMatrix = new int[size][size];

        initializeAdjacencyMatrix();
    }

    public void addVertex(T vertex) {
        vertexList[numberOfVertex++] = vertex;
    }

    public void addEdge(int vertex1, int vertex2) {
        adjacencyMatrix[vertex1][vertex2] = CONNECTED;
        adjacencyMatrix[vertex2][vertex1] = CONNECTED;
    }

    public T getVertexAt(int index) {
        return vertexList[index];
    }

    public boolean isConnected(int vertex1, int vertex2) {
        return adjacencyMatrix[vertex1][vertex2] == CONNECTED;
    }

    private void initializeAdjacencyMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = DISCONNECTED;
            }
        }
    }
}
