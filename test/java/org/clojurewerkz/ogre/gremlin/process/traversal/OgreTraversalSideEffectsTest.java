package org.clojurewerkz.ogre.gremlin.process.traversal;

import org.apache.tinkerpop.gremlin.process.traversal.TraversalSideEffects;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalSideEffectsTest;
import org.junit.Ignore;

@Ignore
public abstract class OgreTraversalSideEffectsTest {
    public static class Traversals extends TraversalSideEffectsTest {
        @Override
        public TraversalSideEffects get_g_V_asAdmin_getSideEffects() {
            return null;
        }
    }
}
