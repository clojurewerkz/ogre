package org.clojurewerkz.ogre;

import com.tinkerpop.gremlin.process.ProcessStandardSuite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * Created by fitz on 2/8/15.
 */
public class OgreProcessStandardSuite extends ProcessStandardSuite {
    /**
     * This list of tests in the suite that will be executed.  Gremlin developers should add to this list
     * as needed to enforce tests upon implementations.
     */
    private static final Class<?>[] allTests = new Class<?>[]{
            // branch
            getClass("clojurewerkz.ogre.suite.Dedup"),
            JavaDedupTest.StandardTest.class

    };

    private static Class getClass(String className){
        try{
            return Class.forName(className);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }



    public OgreProcessStandardSuite(final Class<?> klass, final RunnerBuilder builder) throws InitializationError {
        super(klass, builder, allTests, allTests, true);
    }


}
