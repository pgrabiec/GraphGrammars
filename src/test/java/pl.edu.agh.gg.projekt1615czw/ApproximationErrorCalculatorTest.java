package pl.edu.agh.gg.projekt1615czw;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.junit.Test;
import pl.edu.agh.gg.projekt1615czw.application.bitmap.BitmapProvider;
import pl.edu.agh.gg.projekt1615czw.application.drawing.ApproximationErrorCalculator;
import pl.edu.agh.gg.projekt1615czw.domain.HyperNode;
import pl.edu.agh.gg.projekt1615czw.domain.HyperNodeLabel;

import java.awt.*;
import java.io.IOException;

public class ApproximationErrorCalculatorTest {
    private final int bitmapMaxInd = 9;
    @Test
    public void shouldReturnZero() throws IOException {
        Graph<HyperNode, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        HyperNode node1 = new HyperNode(Color.RED, new Point(0,0));
        HyperNode node2 = new HyperNode(Color.RED, new Point(bitmapMaxInd,0));
        HyperNode node3 = new HyperNode(Color.RED, new Point(bitmapMaxInd,bitmapMaxInd));
        HyperNode node4 = new HyperNode(Color.RED, new Point(0,bitmapMaxInd));

        HyperNode edge = new HyperNode(HyperNodeLabel.I);

        graph.addVertex(node1);
        graph.addVertex(node2);
        graph.addVertex(node3);
        graph.addVertex(node4);
        graph.addVertex(edge);

        graph.addEdge(edge, node1);
        graph.addEdge(edge, node2);
        graph.addEdge(edge, node3);
        graph.addEdge(edge, node4);

        BitmapProvider p = new BitmapProvider("/red.bmp");
        ApproximationErrorCalculator calculator = new ApproximationErrorCalculator(p);

        assert 0 == calculator.calculateApproximationError(graph, edge);
    }


    @Test
    public void shouldReturnNonZero() throws IOException {
        Graph<HyperNode, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        HyperNode node1 = new HyperNode(Color.GREEN, new Point(0,0));
        HyperNode node2 = new HyperNode(Color.GREEN, new Point(bitmapMaxInd,0));
        HyperNode node3 = new HyperNode(Color.GREEN, new Point(bitmapMaxInd,bitmapMaxInd));
        HyperNode node4 = new HyperNode(Color.RED, new Point(0,bitmapMaxInd));

        HyperNode edge = new HyperNode(HyperNodeLabel.I);

        graph.addVertex(node1);
        graph.addVertex(node2);
        graph.addVertex(node3);
        graph.addVertex(node4);
        graph.addVertex(edge);

        graph.addEdge(edge, node1);
        graph.addEdge(edge, node2);
        graph.addEdge(edge, node3);
        graph.addEdge(edge, node4);

        BitmapProvider p = new BitmapProvider("/red.bmp");
        ApproximationErrorCalculator calculator = new ApproximationErrorCalculator(p);
        assert 1000 < calculator.calculateApproximationError(graph, edge);
    }
}
