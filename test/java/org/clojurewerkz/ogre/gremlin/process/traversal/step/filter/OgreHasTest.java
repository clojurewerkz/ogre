package org.clojurewerkz.ogre.gremlin.process.traversal.step.filter;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.step.filter.HasTest;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public abstract class OgreHasTest {
    public static IFn require = Clojure.var("clojure.core", "require");

    public static class Traversals extends HasTest {
        public static final String HAS_NS = "clojurewerkz.ogre.suite.has-test";
        static {
            require.invoke(Clojure.read(HAS_NS));
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, String> get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_hasXkeyX(Object v1Id, String key) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_hasXkeyX").invoke(g, v1Id, key);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_hasXname_markoX(Object v1Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_hasXname_markoX").invoke(g, v1Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasXname_markoX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXname_markoX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasXname_blahX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXname_blahX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasXblahX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXblahX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_hasXage_gt_30X(Object v1Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_hasXage_gt_30X").invoke(g, v1Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VXv1X_hasXage_gt_30X(Object v1Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VXv1X_hasXage_gt_30X").invoke(g, v1Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_out_hasXid_lt_3X(Object v1Id, Object v3Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_out_hasXid_lt_3X").invoke(g, v1Id, v3Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_out_hasIdX2X(Object v1Id, Object v2Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_out_hasIdX2X").invoke(g, v1Id, v2Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_out_hasIdX2_3X(Object v1Id, Object v2Id, Object v3Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_out_hasIdX2_3X").invoke(g, v1Id, v2Id, v3Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasXage_gt_30X() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXage_gt_30X").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasXage_isXgt_30XX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXage_isXgt_30XX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Edge, Edge> get_g_EX7X_hasLabelXknowsX(Object e7Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_EX7X_hasLabelXknowsX").invoke(g, e7Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Edge, Edge> get_g_E_hasLabelXknowsX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_E_hasLabelXknowsX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Edge, Edge> get_g_EX11X_outV_outE_hasXid_10X(Object e11Id, Object e8Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_EX11X_outV_outE_hasXid_10X").invoke(g, e11Id, e8Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Edge, Edge> get_g_E_hasLabelXuses_traversesX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_E_hasLabelXuses_traversesX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasLabelXperson_software_blahX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasLabelXperson_software_blahX").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Integer> get_g_V_hasXperson_name_markoX_age() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXperson_name_markoX_age").invoke(g);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_VX1X_outE_hasXweight_inside_0_06X_inV(Object v1Id) {
            return (Traversal) Clojure.var(HAS_NS, "get_g_VX1X_outE_hasXweight_inside_0_06X_inV").invoke(g, v1Id);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Traversal<Vertex, Vertex> get_g_V_hasXlocationX() {
            return (Traversal) Clojure.var(HAS_NS, "get_g_V_hasXlocationX").invoke(g);
        }
    }
}
