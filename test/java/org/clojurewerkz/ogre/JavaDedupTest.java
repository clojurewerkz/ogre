package org.clojurewerkz.ogre;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.graph.step.filter.DedupTest;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.process.T;
import clojure.lang.IFn;
import clojure.java.api.Clojure;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by fitz on 2/6/15.
 */
@Ignore
@RunWith(Enclosed.class)
public abstract class JavaDedupTest {

    public static class StandardTest extends DedupTest{

        static IFn require = Clojure.var("clojure.core", "require");
        static {
            //require.invoke(Clojure.read("clojurewerkz.ogre.tp3suite.thing-clojure"));
        }

        @Override
        public Traversal<Vertex, String> get_g_V_both_dedup_name() {
            System.out.println("g = " + g);
         return g.V().both().dedup().values("name");
        }

        @Override
        public Traversal<Vertex, String> get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name() {
            System.out.println("g = " + g);
            return g.V().both().has(T.label, "software").dedup().by("lang").values("name");
        }

        @Override
        public Traversal<Vertex, String> get_g_V_both_propertiesXnameX_orderXa_bX_dedup_value() {
            System.out.println("g = " + g);
            return g.V().both().<String>properties("name").order().by((a, b) -> a.value().compareTo(b.value())).dedup().value();
        }

    }

}
