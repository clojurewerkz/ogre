package org.clojurewerkz.ogre;

import com.tinkerpop.gremlin.AbstractGremlinSuite;
import com.tinkerpop.gremlin.process.graph.step.branch.BranchTest;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by fitz on 2/8/15.
 */
public class OgreProcessStandardSuite extends AbstractGremlinSuite {
    /**
     * This list of tests in the suite that will be executed.  Gremlin developers should add to this list
     * as needed to enforce tests upon implementations.
     */
    private static final Class<?>[] allTests = new Class<?>[]{
            // branch
            ThingTest.StandardTest.class,

    };

    /**
     * Tests that will be enforced by the suite where instances of them should be in the list of testsToExecute.
     */
    protected static Class<?>[] testsToEnforce = new Class<?>[]{
            // branch
            ThingTest.class,

    };

    /**
     * This list of tests in the suite that will be executed.  Gremlin developers should add to this list
     * as needed to enforce tests upon implementations.
     */
    private static final Class<?>[] testsToExecute;

    static {
        final String override = System.getenv().getOrDefault("gremlin.tests", "");
        if (override.equals(""))
            testsToExecute = allTests;
        else {
            final List<String> filters = Arrays.asList(override.split(","));
            final List<Class<?>> allowed = Stream.of(allTests)
                    .filter(c -> filters.contains(c.getName()))
                    .collect(Collectors.toList());
            testsToExecute = allowed.toArray(new Class<?>[allowed.size()]);
            testsToEnforce = testsToExecute;
        }
    }

    public OgreProcessStandardSuite(final Class<?> klass, final RunnerBuilder builder) throws InitializationError {
        super(klass, builder, testsToExecute, testsToEnforce);
    }

    public OgreProcessStandardSuite(final Class<?> klass, final RunnerBuilder builder, final Class<?>[] testsToExecute, final Class<?>[] testsToEnforce) throws InitializationError {
        super(klass, builder, testsToExecute, testsToEnforce);
    }

    public OgreProcessStandardSuite(final Class<?> klass, final RunnerBuilder builder, final Class<?>[] testsToExecute, final Class<?>[] testsToEnforce, final boolean gremlinFlavorSuite) throws InitializationError {
        super(klass, builder, testsToExecute, testsToEnforce, gremlinFlavorSuite);
    }
}
