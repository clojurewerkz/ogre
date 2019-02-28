package org.clojurewerkz.ogre.gremlin.process;


import org.apache.tinkerpop.gremlin.AbstractGraphProvider;
import org.apache.tinkerpop.gremlin.LoadGraphWith;
import org.apache.tinkerpop.gremlin.TestHelper;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.GraphTest;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.structure.io.IoEdgeTest;
import org.apache.tinkerpop.gremlin.structure.io.IoVertexTest;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoResourceAccess;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedGraphTest;
import org.apache.tinkerpop.gremlin.structure.util.star.StarGraphTest;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerEdge;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerElement;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraphVariables;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerProperty;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerVertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerVertexProperty;
import org.apache.commons.configuration.Configuration;
import org.junit.Ignore;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
@Ignore
public class TinkerGraphProvider extends AbstractGraphProvider {


    private static final Set<Class> IMPLEMENTATION = new HashSet<Class>() {{
        add(TinkerEdge.class);
        add(TinkerElement.class);
        add(TinkerGraph.class);
        add(TinkerGraphVariables.class);
        add(TinkerProperty.class);
        add(TinkerVertex.class);
        add(TinkerVertexProperty.class);
    }};

    @Override
    public Map<String, Object> getBaseConfiguration(final String graphName, final Class<?> test, final String testMethodName,
                                                    final LoadGraphWith.GraphData loadGraphWith) {
        final TinkerGraph.DefaultIdManager idManager = selectIdMakerFromGraphData(loadGraphWith);
        final String idMaker = (idManager.equals(TinkerGraph.DefaultIdManager.ANY) ? selectIdMakerFromTest(test, testMethodName) : idManager).name();
        return new HashMap<String, Object>() {{
            put(Graph.GRAPH, TinkerGraph.class.getName());
            put(TinkerGraph.GREMLIN_TINKERGRAPH_VERTEX_ID_MANAGER, idMaker);
            put(TinkerGraph.GREMLIN_TINKERGRAPH_EDGE_ID_MANAGER, idMaker);
            put(TinkerGraph.GREMLIN_TINKERGRAPH_VERTEX_PROPERTY_ID_MANAGER, idMaker);
            if (requiresListCardinalityAsDefault(loadGraphWith, test, testMethodName))
                put(TinkerGraph.GREMLIN_TINKERGRAPH_DEFAULT_VERTEX_PROPERTY_CARDINALITY, VertexProperty.Cardinality.list.name());
        }};
    }

    @Override
    public void clear(final Graph graph, final Configuration configuration) throws Exception {
        if (graph != null)
            graph.close();
    }

    @Override
    public Set<Class> getImplementations() {
        return IMPLEMENTATION;
    }

    /**
     * Determines if a test requires a different cardinality as the default or not.
     */
    private static boolean requiresListCardinalityAsDefault(final LoadGraphWith.GraphData loadGraphWith,
                                                            final Class<?> test, final String testMethodName) {
        return loadGraphWith == LoadGraphWith.GraphData.CREW
                || (test == StarGraphTest.class && testMethodName.equals("shouldAttachWithCreateMethod"))
                || (test == DetachedGraphTest.class && testMethodName.equals("testAttachableCreateMethod"));
    }

    /**
     * Some tests require special configuration for TinkerGraph to properly configure the id manager.
     */
    private TinkerGraph.DefaultIdManager selectIdMakerFromTest(final Class<?> test, final String testMethodName) {
        if (test.equals(GraphTest.class)) {
            final Set<String> testsThatNeedLongIdManager = new HashSet<String>() {{
                add("shouldIterateVerticesWithNumericIdSupportUsingDoubleRepresentation");
                add("shouldIterateVerticesWithNumericIdSupportUsingDoubleRepresentations");
                add("shouldIterateVerticesWithNumericIdSupportUsingIntegerRepresentation");
                add("shouldIterateVerticesWithNumericIdSupportUsingIntegerRepresentations");
                add("shouldIterateVerticesWithNumericIdSupportUsingFloatRepresentation");
                add("shouldIterateVerticesWithNumericIdSupportUsingFloatRepresentations");
                add("shouldIterateVerticesWithNumericIdSupportUsingStringRepresentation");
                add("shouldIterateVerticesWithNumericIdSupportUsingStringRepresentations");
                add("shouldIterateEdgesWithNumericIdSupportUsingDoubleRepresentation");
                add("shouldIterateEdgesWithNumericIdSupportUsingDoubleRepresentations");
                add("shouldIterateEdgesWithNumericIdSupportUsingIntegerRepresentation");
                add("shouldIterateEdgesWithNumericIdSupportUsingIntegerRepresentations");
                add("shouldIterateEdgesWithNumericIdSupportUsingFloatRepresentation");
                add("shouldIterateEdgesWithNumericIdSupportUsingFloatRepresentations");
                add("shouldIterateEdgesWithNumericIdSupportUsingStringRepresentation");
                add("shouldIterateEdgesWithNumericIdSupportUsingStringRepresentations");
            }};

            final Set<String> testsThatNeedUuidIdManager = new HashSet<String>() {{
                add("shouldIterateVerticesWithUuidIdSupportUsingStringRepresentation");
                add("shouldIterateVerticesWithUuidIdSupportUsingStringRepresentations");
                add("shouldIterateEdgesWithUuidIdSupportUsingStringRepresentation");
                add("shouldIterateEdgesWithUuidIdSupportUsingStringRepresentations");
            }};

            if (testsThatNeedLongIdManager.contains(testMethodName))
                return TinkerGraph.DefaultIdManager.LONG;
            else if (testsThatNeedUuidIdManager.contains(testMethodName))
                return TinkerGraph.DefaultIdManager.UUID;
        } else if (test.equals(IoEdgeTest.class)) {
            final Set<String> testsThatNeedLongIdManager = new HashSet<String>() {{
                add("shouldReadWriteEdge[graphson]");
                add("shouldReadWriteDetachedEdgeAsReference[graphson]");
                add("shouldReadWriteDetachedEdge[graphson]");
            }};

            if (testsThatNeedLongIdManager.contains(testMethodName))
                return TinkerGraph.DefaultIdManager.LONG;
        } else if (test.equals(IoVertexTest.class)) {
            final Set<String> testsThatNeedLongIdManager = new HashSet<String>() {{
                add("shouldReadWriteVertexWithBOTHEdges[graphson]");
                add("shouldReadWriteVertexWithINEdges[graphson]");
                add("shouldReadWriteVertexWithOUTEdges[graphson]");
                add("shouldReadWriteVertexNoEdges[graphson]");
                add("shouldReadWriteDetachedVertexNoEdges[graphson]");
                add("shouldReadWriteDetachedVertexAsReferenceNoEdges[graphson]");
                add("shouldReadWriteVertexMultiPropsNoEdges[graphson]");
            }};

            if (testsThatNeedLongIdManager.contains(testMethodName))
                return TinkerGraph.DefaultIdManager.LONG;
        }

        return TinkerGraph.DefaultIdManager.ANY;
    }

    /**
     * Test that load with specific graph data can be configured with a specific id manager as the data type to
     * be used in the test for that graph is known.
     */
    private TinkerGraph.DefaultIdManager selectIdMakerFromGraphData(final LoadGraphWith.GraphData loadGraphWith) {
        if (null == loadGraphWith) return TinkerGraph.DefaultIdManager.ANY;
        if (loadGraphWith.equals(LoadGraphWith.GraphData.CLASSIC))
            return TinkerGraph.DefaultIdManager.INTEGER;
        else if (loadGraphWith.equals(LoadGraphWith.GraphData.MODERN))
            return TinkerGraph.DefaultIdManager.INTEGER;
        else if (loadGraphWith.equals(LoadGraphWith.GraphData.CREW))
            return TinkerGraph.DefaultIdManager.INTEGER;
        else if (loadGraphWith.equals(LoadGraphWith.GraphData.GRATEFUL))
            return TinkerGraph.DefaultIdManager.INTEGER;
        else if (loadGraphWith.equals(LoadGraphWith.GraphData.SINK))
            return TinkerGraph.DefaultIdManager.INTEGER;
        else
            throw new IllegalStateException(String.format("Need to define a new %s for %s", TinkerGraph.IdManager.class.getName(), loadGraphWith.name()));
    }

    @Override
    protected void readIntoGraph(final Graph graph, final String path) throws IOException {
        final String dataFile = TestHelper.generateTempFileFromResource(this.getClass(),
                GryoResourceAccess.class, path.substring(path.lastIndexOf(File.separator) + 1), "", false).getAbsolutePath();
        graph.traversal().io(dataFile).read().iterate();
    }
}