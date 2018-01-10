package org.clojurewerkz.ogre.gremlin.process;


import org.apache.tinkerpop.gremlin.process.ProcessStandardSuite;

import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class OgreProcessStandardSuite extends ProcessStandardSuite {

    /**
     * This list of tests in the suite that will be executed.  Gremlin developers should add to this list
     * as needed to enforce tests upon implementations.
     */
    private static final Class<?>[] allTests = new Class<?>[]{
//            // branch
              OgreTinkerPopCheck.BranchTestTraversals.class,
              OgreTinkerPopCheck.ChooseTestTraversals.class,
              OgreTinkerPopCheck.OptionalTestTraversals.class,
              OgreTinkerPopCheck.LocalTestTraversals.class,
              OgreTinkerPopCheck.RepeatTestTraversals.class,
              OgreTinkerPopCheck.UnionTestTraversals.class,
//            // filter
              OgreTinkerPopCheck.AndTestTraversals.class,
              OgreTinkerPopCheck.CoinTestTraversals.class,
              OgreTinkerPopCheck.CyclicPathTestTraversals.class,
              OgreTinkerPopCheck.DedupTestTraversals.class,
              OgreTinkerPopCheck.DropTestTraversals.class,
              OgreTinkerPopCheck.FilterTestTraversals.class,
              OgreTinkerPopCheck.HasTestTraversals.class,
              OgreTinkerPopCheck.IsTestTraversals.class,
              OgreTinkerPopCheck.OrTestTraversals.class,
              OgreTinkerPopCheck.RangeTestTraversals.class,
              OgreTinkerPopCheck.SampleTestTraversals.class,
              OgreTinkerPopCheck.SimplePathTestTraversals.class,
              OgreTinkerPopCheck.TailTestTraversals.class,
              OgreTinkerPopCheck.WhereTestTraversals.class,
//            // map
              OgreTinkerPopCheck.AddEdgeTestTraversals.class,
              OgreTinkerPopCheck.AddVertexTestTraversals.class,
              OgreTinkerPopCheck.CoalesceTestTraversals.class,
              OgreTinkerPopCheck.ConstantTestTraversals.class,
              OgreTinkerPopCheck.CountTestTraversals.class,
              OgreTinkerPopCheck.FlatMapTestTraversals.class,
              OgreTinkerPopCheck.FoldTestTraversals.class,
              OgreTinkerPopCheck.GraphTestTraversals.class,
              OgreTinkerPopCheck.LoopsTestTraversals.class,
              OgreTinkerPopCheck.MapTestTraversals.class,
              OgreTinkerPopCheck.MathTestTraversals.class,
              OgreTinkerPopCheck.MaxTestTraversals.class,
              OgreTinkerPopCheck.MeanTestTraversals.class,
              OgreTinkerPopCheck.MinTestTraversals.class,
              OgreTinkerPopCheck.SumTestTraversals.class,
              OgreTinkerPopCheck.OrderTestTraversals.class,
              OgreTinkerPopCheck.PathTestTraversals.class,
              OgreTinkerPopCheck.ProjectTestTraversals.class,
              OgreTinkerPopCheck.PropertiesTestTraversals.class,
              OgreTinkerPopCheck.SelectTestTraversals.class,
              OgreTinkerPopCheck.UnfoldTestTraversals.class,
              OgreTinkerPopCheck.ValueMapTestTraversals.class,
              OgreTinkerPopCheck.VertexTestTraversals.class,
//            // sideEffect
              OgreTinkerPopCheck.AggregateTestTraversals.class,
              OgreTinkerPopCheck.ExplainTestTraversals.class,
              OgreTinkerPopCheck.GroupTestTraversals.class,
              OgreTinkerPopCheck.GroupCountTestTraversals.class,
              OgreTinkerPopCheck.InjectTestTraversals.class,
              OgreTinkerPopCheck.SackTestTraversals.class,
              OgreTinkerPopCheck.SideEffectCapTestTraversals.class,
              OgreTinkerPopCheck.SideEffectTestTraversals.class,
              OgreTinkerPopCheck.StoreTestTraversals.class,
              OgreTinkerPopCheck.SubgraphTestTraversals.class,
              OgreTinkerPopCheck.TreeTestTraversals.class
    };

    public OgreProcessStandardSuite(final Class<?> klass, final RunnerBuilder builder) throws InitializationError {
        super(klass, builder, allTests);
    }
}
