package org.clojurewerkz.ogre.gremlin.process;


import org.apache.tinkerpop.gremlin.process.ProcessStandardSuite;

import org.apache.tinkerpop.gremlin.process.traversal.CoreTraversalTest;
import org.clojurewerkz.ogre.gremlin.process.traversal.OgreTraversalSideEffectsTest;
import org.clojurewerkz.ogre.gremlin.process.traversal.step.branch.*;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class OgreProcessStandardSuite extends ProcessStandardSuite{

    /**
     * This list of tests in the suite that will be executed.  Gremlin developers should add to this list
     * as needed to enforce tests upon implementations.
     */
    private static final Class<?>[] allTests = new Class<?>[]{
            org.clojurewerkz.Branch$Traversals.class
//            // branch
//            OgreBranchTest.Traversals.class,
//            OgreChooseTest.Traversals.class,
//            OgreLocalTest.Traversals.class,
//            OgreRepeatTest.Traversals.class,
//            OgreUnionTest.Traversals.class,
//            // filter
//            OgreAndTest.Traversals.class,
//            OgreCoinTest.Traversals.class,
//            OgreCyclicPathTest.Traversals.class,
//            OgreDedupTest.Traversals.class,
//            OgreDropTest.Traversals.class,
//            OgreFilterTest.Traversals.class,
//            OgreHasTest.Traversals.class,
//            OgreIsTest.Traversals.class,
//            OgreOrTest.Traversals.class,
//            OgreRangeTest.Traversals.class,
//            OgreSampleTest.Traversals.class,
//            OgreSimplePathTest.Traversals.class,
//            OgreTailTest.Traversals.class,
//            OgreWhereTest.Traversals.class,
//            // map
//            OgreAddEdgeTest.Traversals.class,
//            OgreAddVertexTest.Traversals.class,
//            OgreCoalesceTest.Traversals.class,
//            OgreConstantTest.Traversals.class,
//            OgreCountTest.Traversals.class,
//            OgreFoldTest.Traversals.class,
//            OgreMapTest.Traversals.class,
//            OgreMapKeysTest.Traversals.class,
//            OgreMapValuesTest.Traversals.class,
//            OgreMatchTest.CountMatchTraversals.class,
//            OgreMatchTest.GreedyMatchTraversals.class,
//            OgreMaxTest.Traversals.class,
//            OgreMeanTest.Traversals.class,
//            OgreMinTest.Traversals.class,
//            OgreOrderTest.Traversals.class,
//            OgrePathTest.Traversals.class,
//            OgrePropertiesTest.Traversals.class,
//            OgreSelectTest.Traversals.class,
//            OgreSumTest.Traversals.class,
//            OgreUnfoldTest.Traversals.class,
//            OgreValueMapTest.Traversals.class,
//            OgreVertexTest.Traversals.class,
//            // sideEffect
//            OgreAggregateTest.Traversals.class,
//            OgreGroupTest.Traversals.class,
//            OgreGroupCountTest.Traversals.class,
//            OgreInjectTest.Traversals.class,
//            OgreProfileTest.Traversals.class,
//            OgreSackTest.Traversals.class,
//            OgreSideEffectCapTest.Traversals.class,
//            OgreSideEffectTest.Traversals.class,
//            OgreStoreTest.Traversals.class,
//            OgreSubgraphTest.Traversals.class,
//            OgreTreeTest.Traversals.class,

            // util
//            OgreTraversalSideEffectsTest.Traversals.class,

            // compliance
//            CoreTraversalTest.class,
    };

    public OgreProcessStandardSuite(final Class<?> klass, final RunnerBuilder builder) throws InitializationError {
        super(klass, builder, allTests);
    }
}
