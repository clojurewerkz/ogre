package org.clojurewerkz.ogre.gremlin.process;


import org.apache.tinkerpop.gremlin.GraphProviderClass;
import org.apache.tinkerpop.gremlin.process.ProcessStandardSuite;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.runner.RunWith;

@RunWith(OgreProcessStandardSuite.class)
@GraphProviderClass(provider = TinkerGraphProvider.class, graph = TinkerGraph.class)
public class OgreTinkerGraphProcessStandardTest {
}
