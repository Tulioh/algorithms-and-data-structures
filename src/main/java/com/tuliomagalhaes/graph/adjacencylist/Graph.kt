package com.tuliomagalhaes.graph.adjacencylist

import java.util.*

class Graph<T> {
    private val adjacencyList = mutableMapOf<T, MutableSet<T>>()

    fun addVertex(vertex: T) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList[vertex] = mutableSetOf()
        }
    }

    fun addEdge(vertex1: T, vertex2: T) {
        addVertex(vertex1)
        addVertex(vertex2)

        adjacencyList[vertex1]?.add(vertex2)
        adjacencyList[vertex2]?.add(vertex1)
    }

    fun containsVertex(vertex: T): Boolean {
        return adjacencyList[vertex] != null
    }

    fun isConnected(vertex1: T, vertex2: T): Boolean {
        return adjacencyList[vertex1]?.contains(vertex2) == true &&
               adjacencyList[vertex2]?.contains(vertex1) == true
    }

    fun depthFirstSearchRecursive(vertex: T): List<T> {
        val visitedVertices = mutableSetOf<T>()
        val result = mutableListOf<T>()

        fun dfs(vertex: T) {
            adjacencyList[vertex]?.let { neighbors ->
                visitedVertices.add(vertex)
                result.add(vertex)
                neighbors.forEach { neighbor ->
                    if (!visitedVertices.contains(neighbor)) {
                        dfs(neighbor)
                    }
                }
            }
        }
        dfs(vertex)

        return result
    }

    fun breadthFirstSearchRecursive(vertex: T): List<T> {
        val visitedVertices = mutableSetOf(vertex)
        val result = mutableListOf<T>()
        val nextVertices = LinkedList<T>()
        nextVertices.push(vertex)

        fun bfs(vertex: T) {
            if (nextVertices.isEmpty()) return
            val nextVertex = nextVertices.pop()
            result.add(nextVertex)
            adjacencyList[nextVertex]?.let { neighbors ->
                neighbors.forEach { neighbor ->
                    if (!visitedVertices.contains(neighbor)) {
                        visitedVertices.add(neighbor)
                        nextVertices.add(neighbor)
                    }
                }

                bfs(vertex)
            }
        }
        bfs(vertex)

        return result
    }

    fun depthFirstSearchIterative(vertex: T): List<T> {
        return traverse(vertex, true)
    }

    fun breadthFirstSearchIterative(vertex: T): List<T> {
        return traverse(vertex, false)
    }

    private fun traverse(vertex: T, dfs: Boolean): List<T> {
        if (!adjacencyList.containsKey(vertex)) return emptyList()

        val visitedVertices = mutableSetOf(vertex)
        val result = mutableListOf<T>()
        val nextVertices = LinkedList<T>()
        nextVertices.push(vertex)

        while (nextVertices.isNotEmpty()) {
            val nextVertex = nextVertices.pop()
            result.add(nextVertex)

            adjacencyList[nextVertex]?.forEach { neighbor ->
                if (!visitedVertices.contains(neighbor)) {
                    visitedVertices.add(neighbor)
                    if (dfs) {
                        nextVertices.push(neighbor)
                    } else {
                        nextVertices.add(neighbor)
                    }
                }
            }
        }

        return result
    }

    fun removeEdge(vertex1: T, vertex2: T) {
        adjacencyList[vertex1]?.remove(vertex2)
        adjacencyList[vertex2]?.remove(vertex1)
    }

    fun removeVertex(vertex: T) {
        adjacencyList[vertex]?.forEach {
            adjacencyList[it]?.remove(vertex)
        }
        adjacencyList.remove(vertex)
    }
}