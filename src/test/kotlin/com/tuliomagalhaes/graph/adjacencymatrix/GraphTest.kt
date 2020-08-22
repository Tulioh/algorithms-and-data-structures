package com.tuliomagalhaes.graph.adjacencymatrix

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GraphTest {

    private lateinit var graph: Graph<String>

    @Before
    fun setUp() {
        graph = Graph(10)
    }

    @Test
    fun addVertex() {
        // Given When
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")

        // Then
        assertEquals("A", graph.getVertexAt(0))
        assertEquals("B", graph.getVertexAt(1))
        assertEquals("C", graph.getVertexAt(2))
    }

    @Test
    fun addEdge() {
        // Given
        graph.addVertex("A") // 0
        graph.addVertex("B") // 1
        graph.addVertex("C") // 2

        // When
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)

        // Then
        assertTrue(graph.isConnected(0, 1))
        assertTrue(graph.isConnected(1, 0))
        assertTrue(graph.isConnected(0, 2))
        assertTrue(graph.isConnected(2, 0))
    }
}