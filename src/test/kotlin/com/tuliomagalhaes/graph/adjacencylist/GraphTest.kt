package com.tuliomagalhaes.graph.adjacencylist

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

internal class GraphTest {

    private lateinit var graph: Graph<String>

    @Before
    fun setUp() {
        graph = Graph()
    }

    @Test
    fun addVertex() {
        // Given When
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")

        // Then
        assertTrue(graph.containsVertex("A"))
        assertTrue(graph.containsVertex("B"))
        assertTrue(graph.containsVertex("C"))
    }

    @Test
    fun addEdge() {
        // Given When
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")

        // Then
        assertTrue(graph.isConnected("A", "B"))
        assertTrue(graph.isConnected("A", "C"))
        assertTrue(graph.isConnected("B", "A"))
        assertTrue(graph.isConnected("C", "A"))
    }

    @Test
    fun removeEdge() {
        // Given
        graph.addEdge("A", "B")

        // When
        graph.removeEdge("A", "B")

        // Then
        assertFalse(graph.isConnected("A", "B"))
        assertFalse(graph.isConnected("B", "A"))
    }

    @Test
    fun removeVertex() {
        // Given
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")

        // When
        graph.removeVertex("A")

        // Then
        assertFalse(graph.containsVertex("A"))
        assertFalse(graph.isConnected("B", "A"))
        assertFalse(graph.isConnected("C", "A"))
    }

//          A
//        /   \
//       B     C
//       \       \
//        D ----- E
//         \     /
//            F
// DFS Result: A, B, D, E, C, F

    @Test
    fun depthFirstSearchRecursive_should_return_all_visited_vertices() {
        // Given
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        // When
        val visitedVertices = graph.depthFirstSearchRecursive("A")

        // Then
        val expected = listOf("A", "B", "D", "E", "C", "F")
        assertEquals(expected, visitedVertices)
    }

//          A
//        /   \
//       B     C
//       \       \
//        D ----- E
//         \     /
//            F
// DFS Result: A, C, E, F, D, B

    @Test
    fun depthFirstSearchIterative_should_return_all_visited_vertices() {
        // Given
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        // When
        val visitedVertices = graph.depthFirstSearchIterative("A")

        // Then
        val expected = listOf("A", "C", "E", "F", "D", "B")
        assertEquals(expected, visitedVertices)
    }

//          A
//        /   \
//       B     C
//       \       \
//        D ----- E
//         \     /
//            F
// BFS Result: A, B, C, D, E, F

    @Test
    fun breadthFirstSearchIterative_should_return_all_visited_vertices() {
        // Given
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        // When
        val visitedVertices = graph.breadthFirstSearchIterative("A")

        // Then
        val expected = listOf("A", "B", "C", "D", "E", "F")
        assertEquals(expected, visitedVertices)
    }

    @Test
    fun breadthFirstSearchRecursive_should_return_all_visited_vertices() {
        // Given
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        // When
        val visitedVertices = graph.breadthFirstSearchRecursive("A")

        // Then
        val expected = listOf("A", "B", "C", "D", "E", "F")
        assertEquals(expected, visitedVertices)
    }
}