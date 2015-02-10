package org.clojurewerkz.ogre;

import com.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.runner.RunWith;

/**
 * Created by fitz on 2/8/15.
 */
@RunWith(OgreProcessStandardSuite.class)
@OgreProcessStandardSuite.GraphProviderClass(provider = TinkerGraphGraphProvider.class, graph = TinkerGraph.class)
public class OgreTinkerGraphProcessStandardTest {

}
