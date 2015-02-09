package org.clojurewerkz.ogre;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.graph.step.filter.DedupTest;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.process.T;
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
public abstract class ThingTest {

    public static class StandardTest extends DedupTest{

        @Override
        public Traversal<Vertex, String> get_g_V_both_dedup_name() {
            //let's make one fail
            return g.V().both().dedup().values("name!");//.values("name");
        }

        @Override
        public Traversal<Vertex, String> get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name() {
            return g.V().both().has(T.label, "software").dedup().by("lang").values("name");
        }

        @Override
        public Traversal<Vertex, String> get_g_V_both_propertiesXnameX_orderXa_bX_dedup_value() {
            return g.V().both().<String>properties("name").order().by((a, b) -> a.value().compareTo(b.value())).dedup().value();
        }

    }

}
