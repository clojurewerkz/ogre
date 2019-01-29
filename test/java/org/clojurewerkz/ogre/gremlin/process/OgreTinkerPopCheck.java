package org.clojurewerkz.ogre.gremlin.process;
import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.process.traversal.step.branch.*;
import org.apache.tinkerpop.gremlin.process.traversal.step.filter.*;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.*;
import org.apache.tinkerpop.gremlin.process.traversal.step.sideEffect.*;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalExplanation;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OgreTinkerPopCheck {
    public static IFn require = Clojure.var("clojure.core", "require");
    
    public static class BranchTestTraversals extends BranchTest {
        private static final String NS = "clojurewerkz.ogre.suite.branch-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_branchXlabel_eq_person__a_bX_optionXa__ageX_optionXb__langX_optionXb__nameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_branchXlabel_eq_person__a_bX_optionXa__ageX_optionXb__langX_optionXb__nameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_branchXlabel_isXpersonX_countX_optionX1__ageX_optionX0__langX_optionX0__nameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_branchXlabel_isXpersonX_countX_optionX1__ageX_optionX0__langX_optionX0__nameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_branchXlabel_isXpersonX_countX_optionX1__ageX_optionX0__langX_optionX0__nameX_optionXany__labelX() {
            return (Traversal) Clojure.var(NS, "get_g_V_branchXlabel_isXpersonX_countX_optionX1__ageX_optionX0__langX_optionX0__nameX_optionXany__labelX").invoke(g);
        }

    }

    public static class ChooseTestTraversals extends ChooseTest {
        private static final String NS = "clojurewerkz.ogre.suite.choose-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_chooseXout_countX_optionX2L__nameX_optionX3L__valueMapX() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXout_countX_optionX2L__nameX_optionX3L__valueMapX").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXlabel_eqXpersonX__outXknowsX__inXcreatedXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXlabel_eqXpersonX__outXknowsX__inXcreatedXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXhasLabelXpersonX_and_outXcreatedX__outXknowsX__identityX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXhasLabelXpersonX_and_outXcreatedX__outXknowsX__identityX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXoutXknowsX_count_isXgtX0XX__outXknowsXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXoutXknowsX_count_isXgtX0XX__outXknowsXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXlabelX_optionXblah__outXknowsXX_optionXbleep__outXcreatedXX_optionXnone__identityX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXlabelX_optionXblah__outXknowsXX_optionXbleep__outXcreatedXX_optionXnone__identityX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_asXp1X_chooseXoutEXknowsX__outXknowsXX_asXp2X_selectXp1_p2X_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_asXp1X_chooseXoutEXknowsX__outXknowsXX_asXp2X_selectXp1_p2X_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_injectX2X_chooseXisX1X__constantX10Xfold__foldX() {
            // TODO: next release of TinkerPop will have a better way to assert this result - faulty test
            return g.inject(2).choose(__.is(1), __.constant(10).fold(), __.fold());
            //return (Traversal) Clojure.var(NS, "get_g_injectX2X_chooseXisX1X__constantX10Xfold__foldX").invoke(g);
        }

        @Override
        public Traversal get_g_injectX1X_chooseXisX1X__constantX10Xfold__foldX() {
            // TODO: next release of TinkerPop will have a better way to assert this result - faulty test
            return g.inject(1).choose(__.is(1), __.constant(10).fold(), __.fold());
            //return (Traversal) Clojure.var(NS, "get_g_injectX1X_chooseXisX1X__constantX10Xfold__foldX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_chooseXageX__optionX27L__constantXyoungXX_optionXnone__constantXoldXX_groupCount() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_chooseXageX__optionX27L__constantXyoungXX_optionXnone__constantXoldXX_groupCount").invoke(g);
        }

    }

    public static class OptionalTestTraversals extends OptionalTest {
        private static final String NS = "clojurewerkz.ogre.suite.optional-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX2X_optionalXoutXknowsXX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX2X_optionalXoutXknowsXX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX2X_optionalXinXknowsXX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX2X_optionalXinXknowsXX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_optionalXoutXknowsX_optionalXoutXcreatedXXX_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_optionalXoutXknowsX_optionalXoutXcreatedXXX_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_optionalXout_optionalXoutXX_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_optionalXout_optionalXoutXX_path").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_optionalXaddVXdogXX_label(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_optionalXaddVXdogXX_label").invoke(g, arg0);
        }

    }

    public static class LocalTestTraversals extends LocalTest {
        private static final String NS = "clojurewerkz.ogre.suite.local-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_localXoutE_countX() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXoutE_countX").invoke(g);
        }

        @Override
        public Traversal get_g_V_localXpropertiesXlocationX_order_byXvalueX_limitX2XX_value() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXpropertiesXlocationX_order_byXvalueX_limitX2XX_value").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXlabel_personX_asXaX_localXoutXcreatedX_asXbXX_selectXa_bX_byXnameX_byXidX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXlabel_personX_asXaX_localXoutXcreatedX_asXbXX_selectXa_bX_byXnameX_byXidX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_localXoutEXknowsX_limitX1XX_inV_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_localXoutEXknowsX_limitX1XX_inV_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_localXbothEXcreatedX_limitX1XX_otherV_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXbothEXcreatedX_limitX1XX_otherV_name").invoke(g);
        }

        @Override
        public Traversal get_g_VX4X_localXbothEX1_createdX_limitX1XX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_localXbothEX1_createdX_limitX1XX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_localXbothEXknows_createdX_limitX1XX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_localXbothEXknows_createdX_limitX1XX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_localXbothE_limitX1XX_otherV_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_localXbothE_limitX1XX_otherV_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_localXbothE_limitX2XX_otherV_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_localXbothE_limitX2XX_otherV_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_localXinEXknowsX_limitX2XX_outV_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXinEXknowsX_limitX2XX_outV_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_localXmatchXproject__created_person__person_name_nameX_selectXname_projectX_by_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXmatchXproject__created_person__person_name_nameX_selectXname_projectX_by_byXnameX").invoke(g);
        }

    }

    public static class RepeatTestTraversals extends RepeatTest {
        private static final String NS = "clojurewerkz.ogre.suite.repeat-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_repeatXoutX_timesX2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX2X_emit_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX2X_emit_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX2X_emit() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX2X_emit").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX2X_repeatXinX_timesX2X_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX2X_repeatXinX_timesX2X_name").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_timesX2X_repeatXoutX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_timesX2X_repeatXoutX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_emit_repeatXoutX_timesX2X_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_emit_repeatXoutX_timesX2X_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_emit_timesX2X_repeatXoutX_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_emit_timesX2X_repeatXoutX_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXgroupCountXmX_byXnameX_outX_timesX2X_capXmX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXgroupCountXmX_byXnameX_outX_timesX2X_capXmX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_timesX10X_asXaX_out_asXbX_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX10X_asXaX_out_asXbX_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_repeatXoutX_untilXoutE_count_isX0XX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXoutX_untilXoutE_count_isX0XX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_emitXhasXlabel_personXX_repeatXoutX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_emitXhasXlabel_personXX_repeatXoutX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_untilXname_eq_marko_or_loops_gt_1X_groupCount_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_untilXname_eq_marko_or_loops_gt_1X_groupCount_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_repeatXgroupCountXmX_byXloopsX_outX_timesX3X_capXmX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXgroupCountXmX_byXloopsX_outX_timesX3X_capXmX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasXname_markoX_repeatXoutE_inV_simplePathX_untilXhasXname_rippleXX_path_byXnameX_byXlabelX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_markoX_repeatXoutE_inV_simplePathX_untilXhasXname_rippleXX_path_byXnameX_byXlabelX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXloop_name_loopX_repeatXinX_timesX5X_path_by_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXloop_name_loopX_repeatXinX_timesX5X_path_by_name").invoke(g);
        }

    }

    public static class UnionTestTraversals extends UnionTest {
        private static final String NS = "clojurewerkz.ogre.suite.union-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_unionXout__inX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_unionXout__inX_name").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_unionXrepeatXoutX_timesX2X__outX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_unionXrepeatXoutX_timesX2X__outX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX_groupCount() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX_groupCount").invoke(g);
        }

        @Override
        public Traversal get_g_V_unionXrepeatXunionXoutXcreatedX__inXcreatedXX_timesX2X__repeatXunionXinXcreatedX__outXcreatedXX_timesX2XX_label_groupCount() {
            return (Traversal) Clojure.var(NS, "get_g_V_unionXrepeatXunionXoutXcreatedX__inXcreatedXX_timesX2X__repeatXunionXinXcreatedX__outXcreatedXX_timesX2XX_label_groupCount").invoke(g);
        }

        @Override
        public Traversal get_g_VX1_2X_unionXoutE_count__inE_count__outE_weight_sumX(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1_2X_unionXoutE_count__inE_count__outE_weight_sumX").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1_2X_localXunionXoutE_count__inE_count__outE_weight_sumXX(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1_2X_localXunionXoutE_count__inE_count__outE_weight_sumXX").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1_2X_localXunionXcountXX(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1_2X_localXunionXcountXX").invoke(g, arg0, arg1);
        }
    }

    public static class AndTestTraversals extends AndTest {
        private static final String NS = "clojurewerkz.ogre.suite.and-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_andXhasXage_gt_27X__outE_count_gte_2X_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_andXhasXage_gt_27X__outE_count_gte_2X_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_andXoutE__hasXlabel_personX_and_hasXage_gte_32XX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_andXoutE__hasXlabel_personX_and_hasXage_gte_32XX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXknowsX_and_outXcreatedX_inXcreatedX_asXaX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXknowsX_and_outXcreatedX_inXcreatedX_asXaX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_andXselectXaX_selectXaXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_andXselectXaX_selectXaXX").invoke(g);
        }

    }

    public static class CoinTestTraversals extends CoinTest {
        private static final String NS = "clojurewerkz.ogre.suite.coin-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_coinX1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_coinX1X").invoke(g);
        }

        @Override
        public Traversal get_g_V_coinX0X() {
            return (Traversal) Clojure.var(NS, "get_g_V_coinX0X").invoke(g);
        }

    }

    public static class CyclicPathTestTraversals extends CyclicPathTest {
        private static final String NS = "clojurewerkz.ogre.suite.cyclic-path-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath_path(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath_path").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_cyclicPath_fromXaX_toXbX_path(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_cyclicPath_fromXaX_toXbX_path").invoke(g, arg0);
        }

    }

    public static class DedupTestTraversals extends DedupTest {
        private static final String NS = "clojurewerkz.ogre.suite.dedup-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_both_both_dedup_byXoutE_countX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_both_dedup_byXoutE_countX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_dedup_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_dedup_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_dedupXa_bX_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_dedupXa_bX_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_name_order_byXa_bX_dedup_value() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_name_order_byXa_bX_dedup_value").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_both_name_dedup() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_both_name_dedup").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_both_dedup() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_both_dedup").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_both_dedup_byXlabelX() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_both_dedup_byXlabelX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXlabelX_byXbothE_weight_dedup_foldX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXlabelX_byXbothE_weight_dedup_foldX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_both_asXbX_dedupXa_bX_byXlabelX_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_both_asXbX_dedupXa_bX_byXlabelX_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_asXeX_inV_asXvX_selectXeX_order_byXweight_ascX_selectXvX_valuesXnameX_dedup() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_asXeX_inV_asXvX_selectXeX_order_byXweight_ascX_selectXvX_valuesXnameX_dedup").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupCount_selectXvaluesX_unfold_dedup() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupCount_selectXvaluesX_unfold_dedup").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_asXxX_in_asXyX_selectXx_yX_byXnameX_fold_dedupXlocal_x_yX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_asXxX_in_asXyX_selectXx_yX_byXnameX_fold_dedupXlocal_x_yX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXdedupX_timesX2X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXdedupX_timesX2X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_in_valuesXnameX_fold_dedupXlocalX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_in_valuesXnameX_fold_dedupXlocalX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_repeatXbothX_timesX3X_emit_name_asXbX_group_byXselectXaXX_byXselectXbX_dedup_order_foldX_selectXvaluesX_unfold_dedup() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_repeatXbothX_timesX3X_emit_name_asXbX_group_byXselectXaXX_byXselectXbX_dedup_order_foldX_selectXvaluesX_unfold_dedup").invoke(g);
        }
    }

    public static class DropTestTraversals extends DropTest {
        private static final String NS = "clojurewerkz.ogre.suite.drop-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_outE_drop() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_drop").invoke(g);
        }

        @Override
        public Traversal get_g_V_properties_drop() {
            return (Traversal) Clojure.var(NS, "get_g_V_properties_drop").invoke(g);
        }

        @Override
        public Traversal get_g_V_drop() {
            return (Traversal) Clojure.var(NS, "get_g_V_drop").invoke(g);
        }

    }

    public static class FilterTestTraversals extends FilterTest {
        private static final String NS = "clojurewerkz.ogre.suite.filter-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_filterXfalseX() {
            return (Traversal) Clojure.var(NS, "get_g_V_filterXfalseX").invoke(g);
        }

        @Override
        public Traversal get_g_E_filterXfalseX() {
            return (Traversal) Clojure.var(NS, "get_g_E_filterXfalseX").invoke(g);
        }

        @Override
        public Traversal get_g_V_filterXtrueX() {
            return (Traversal) Clojure.var(NS, "get_g_V_filterXtrueX").invoke(g);
        }

        @Override
        public Traversal get_g_V_filterXlang_eq_javaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_filterXlang_eq_javaX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_filterXage_gt_30X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_filterXage_gt_30X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_filterXage_gt_30X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_filterXage_gt_30X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_filterXname_startsWith_m_OR_name_startsWith_pX() {
            return (Traversal) Clojure.var(NS, "get_g_V_filterXname_startsWith_m_OR_name_startsWith_pX").invoke(g);
        }

        @Override
        public Traversal get_g_E_filterXtrueX() {
            return (Traversal) Clojure.var(NS, "get_g_E_filterXtrueX").invoke(g);
        }

    }

    public static class HasTestTraversals extends HasTest {
        private static final String NS = "clojurewerkz.ogre.suite.has-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_hasXkeyX(java.lang.Object arg0, java.lang.String arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_hasXkeyX").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1X_hasXname_markoX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_hasXname_markoX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasXname_markoX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_markoX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXname_blahX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_blahX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXblahX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXblahX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_hasXage_gt_30X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_hasXage_gt_30X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VXv1X_hasXage_gt_30X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VXv1X_hasXage_gt_30X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_hasXid_lt_3X(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_hasXid_lt_3X").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1X_out_hasIdX2X(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_hasIdX2X").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1X_out_hasIdX2_3X(java.lang.Object arg0, java.lang.Object arg1, java.lang.Object arg2) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_hasIdX2_3X").invoke(g, arg0, arg1, arg2);
        }

        @Override
        public Traversal get_g_V_hasXage_gt_30X() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXage_gt_30X").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXage_isXgt_30XX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXage_isXgt_30XX").invoke(g);
        }

        @Override
        public Traversal get_g_EX7X_hasLabelXknowsX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_EX7X_hasLabelXknowsX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_E_hasLabelXknowsX() {
            return (Traversal) Clojure.var(NS, "get_g_E_hasLabelXknowsX").invoke(g);
        }

        @Override
        public Traversal get_g_E_hasLabelXuses_traversesX() {
            return (Traversal) Clojure.var(NS, "get_g_E_hasLabelXuses_traversesX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXperson_software_blahX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXperson_software_blahX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXperson_name_markoX_age() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXperson_name_markoX_age").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_outE_hasXweight_inside_0_06X_inV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outE_hasXweight_inside_0_06X_inV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasXlocationX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXlocationX").invoke(g);
        }

        @Override
        public Traversal get_g_EX11X_outV_outE_hasXid_10X(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_EX11X_outV_outE_hasXid_10X").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_in_hasIdXneqX1XX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_V_in_hasIdXneqX1XX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_hasLabelXsoftwareX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_hasLabelXsoftwareX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasIdX1X_hasIdX2X(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_V_hasIdX1X_hasIdX2X").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_V_hasNotXageX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasNotXageX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_properties_dedup_hasKeyXageX_hasValueXgtX30XX_value() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_properties_dedup_hasKeyXageX_hasValueXgtX30XX_value").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_properties_dedup_hasKeyXageX_value() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_properties_dedup_hasKeyXageX_value").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasIdXwithoutXemptyXX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasIdXwithoutXemptyXX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasIdXemptyX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasIdXemptyX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasIdXwithinXemptyXX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasIdXwithinXemptyXX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_notXhasIdXwithinXemptyXXX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_notXhasIdXwithinXemptyXXX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXage_withoutX27X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXage_withoutX27X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXage_withoutX27_29X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXage_withoutX27_29X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXage_withinX27X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXage_withinX27X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXage_withinX27_29X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXage_withinX27_29X_count").invoke(g);
        }
    }

    public static class IsTestTraversals extends IsTest {
        private static final String NS = "clojurewerkz.ogre.suite.is-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_valuesXageX_isX32X() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXageX_isX32X").invoke(g);
        }

        @Override
        public Traversal get_g_V_valuesXageX_isXlte_30X() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXageX_isXlte_30X").invoke(g);
        }

        @Override
        public Traversal get_g_V_valuesXageX_isXgte_29X_isXlt_34X() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXageX_isXgte_29X_isXlt_34X").invoke(g);
        }

        @Override
        public Traversal get_g_V_whereXinXcreatedX_count_isX1XX_valuesXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXinXcreatedX_count_isX1XX_valuesXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_whereXinXcreatedX_count_isXgte_2XX_valuesXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXinXcreatedX_count_isXgte_2XX_valuesXnameX").invoke(g);
        }

    }

    public static class OrTestTraversals extends OrTest {
        private static final String NS = "clojurewerkz.ogre.suite.or-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_orXoutEXknowsX__hasXlabel_softwareX_or_hasXage_gte_35XX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_orXoutEXknowsX__hasXlabel_softwareX_or_hasXage_gte_35XX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_orXselectXaX_selectXaXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_orXselectXaX_selectXaXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_orXhasXage_gt_27X__outE_count_gte_2X_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_orXhasXage_gt_27X__outE_count_gte_2X_name").invoke(g);
        }

    }

    public static class RangeTestTraversals extends RangeTest {
        private static final String NS = "clojurewerkz.ogre.suite.range-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_out_limitX2X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_limitX2X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_localXoutE_limitX1X_inVX_limitX3X() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXoutE_limitX1X_inVX_limitX3X").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_outXknowsX_outEXcreatedX_rangeX0_1X_inV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXknowsX_outEXcreatedX_rangeX0_1X_inV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outXknowsX_outXcreatedX_rangeX0_1X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXknowsX_outXcreatedX_rangeX0_1X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outXcreatedX_inXcreatedX_rangeX1_3X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXcreatedX_inXcreatedX_rangeX1_3X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outXcreatedX_inEXcreatedX_rangeX1_3X_outV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXcreatedX_inEXcreatedX_rangeX1_3X_outV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_timesX3X_rangeX5_11X() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX3X_rangeX5_11X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_1X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_3X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_3X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_4_5X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_4_5X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_1X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_3X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_3X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_valuesXweightX_fold_orderXlocalX_skipXlocal_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_valuesXweightX_fold_orderXlocalX_skipXlocal_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_order_byXageX_skipX1X_valuesXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_order_byXageX_skipX1X_valuesXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_order_byXageX_valuesXnameX_skipX1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_order_byXageX_valuesXnameX_skipX1X").invoke(g);
        }



    }

    public static class SampleTestTraversals extends SampleTest {
        private static final String NS = "clojurewerkz.ogre.suite.sample-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_E_sampleX1X() {
            return (Traversal) Clojure.var(NS, "get_g_E_sampleX1X").invoke(g);
        }

        @Override
        public Traversal get_g_E_sampleX2X_byXweightX() {
            return (Traversal) Clojure.var(NS, "get_g_E_sampleX2X_byXweightX").invoke(g);
        }

        @Override
        public Traversal get_g_V_localXoutE_sampleX1X_byXweightXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXoutE_sampleX1X_byXweightXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXlabelX_byXbothE_weight_sampleX2X_foldX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXlabelX_byXbothE_weight_sampleX2X_foldX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXlabelX_byXbothE_weight_fold_sampleXlocal_5XX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXlabelX_byXbothE_weight_fold_sampleXlocal_5XX").invoke(g);
        }

    }

    public static class SimplePathTestTraversals extends SimplePathTest {
        private static final String NS = "clojurewerkz.ogre.suite.simple-path-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_outXcreatedX_inXcreatedX_simplePath(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXcreatedX_inXcreatedX_simplePath").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_repeatXboth_simplePathX_timesX3X_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXboth_simplePathX_timesX3X_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_out_asXcX_simplePath_byXlabelX_fromXbX_toXcX_path_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_out_asXcX_simplePath_byXlabelX_fromXbX_toXcX_path_byXnameX").invoke(g);
        }

    }

    public static class TailTestTraversals extends TailTest {
        private static final String NS = "clojurewerkz.ogre.suite.tail-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_valuesXnameX_order_tailX2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXnameX_order_tailX2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_valuesXnameX_order_tail() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXnameX_order_tail").invoke(g);
        }

        @Override
        public Traversal get_g_V_valuesXnameX_order_tailX7X() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXnameX_order_tailX7X").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_timesX3X_tailX7X() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX3X_tailX7X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_1X").invoke(g);
        }

        @Override
        public Traversal get_g_V_valuesXnameX_order_tailXglobal_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXnameX_order_tailXglobal_2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXin_outX_timesX3X_tailX7X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXin_outX_timesX3X_tailX7X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXlimitXlocal_0XX_tailXlocal_1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXlimitXlocal_0XX_tailXlocal_1X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocalX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocalX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_1X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_1X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_2X").invoke(g);
        }



    }

    public static class WhereTestTraversals extends WhereTest {
        private static final String NS = "clojurewerkz.ogre.suite.where-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_eqXbXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_eqXbXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_neqXbXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_neqXbXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXb_hasXname_markoXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXb_hasXname_markoXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_outXknowsX_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_outXknowsX_bX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_neqXbXX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_neqXbXX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXasXbX_outXcreatedX_hasXname_rippleXX_valuesXage_nameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXasXbX_outXcreatedX_hasXname_rippleXX_valuesXage_nameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXeqXaXX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXeqXaXX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_aggregateXxX_out_whereXnotXwithinXaXXX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_aggregateXxX_out_whereXnotXwithinXaXXX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_withSideEffectXa_graph_verticesX2XX_VX1X_out_whereXneqXaXX(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_graph_verticesX2XX_VX1X_out_whereXneqXaXX").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1X_repeatXbothEXcreatedX_whereXwithoutXeXX_aggregateXeX_otherVX_emit_path(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXbothEXcreatedX_whereXwithoutXeXX_aggregateXeX_otherVX_emit_path").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_whereXnotXoutXcreatedXXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXnotXoutXcreatedXXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_whereXandXasXaX_outXknowsX_asXbX__orXasXbX_outXcreatedX_hasXname_rippleX__asXbX_inXknowsX_count_isXnotXeqX0XXXXX_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_whereXandXasXaX_outXknowsX_asXbX__orXasXbX_outXcreatedX_hasXname_rippleX__asXbX_inXknowsX_count_isXnotXeqX0XXXXX_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_whereXoutXcreatedX_and_outXknowsX_or_inXknowsXX_valuesXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXoutXcreatedX_and_outXknowsX_or_inXknowsXX_valuesXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_asXbX_whereXandXasXbX_in__notXasXaX_outXcreatedX_hasXname_rippleXXX_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_asXbX_whereXandXasXbX_in__notXasXaX_outXcreatedX_hasXname_rippleXXX_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_bothXknowsX_bothXknowsX_asXdX_whereXc__notXeqXaX_orXeqXdXXXX_selectXa_b_c_dX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_bothXknowsX_bothXknowsX_asXdX_whereXc__notXeqXaX_orXeqXdXXXX_selectXa_b_c_dX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_whereXin_count_isXeqX3XX_or_whereXoutXcreatedX_and_hasXlabel_personXXX_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_whereXin_count_isXeqX3XX_or_whereXoutXcreatedX_and_hasXlabel_personXXX_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outEXcreatedX_asXbX_inV_asXcX_inXcreatedX_asXdX_whereXa_ltXbX_orXgtXcXX_andXneqXdXXX_byXageX_byXweightX_byXinXcreatedX_valuesXageX_minX_selectXa_c_dX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outEXcreatedX_asXbX_inV_asXcX_inXcreatedX_asXdX_whereXa_ltXbX_orXgtXcXX_andXneqXdXXX_byXageX_byXweightX_byXinXcreatedX_valuesXageX_minX_selectXa_c_dX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outEXcreatedX_asXbX_inV_asXcX_whereXa_gtXbX_orXeqXbXXX_byXageX_byXweightX_byXweightX_selectXa_cX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outEXcreatedX_asXbX_inV_asXcX_whereXa_gtXbX_orXeqXbXXX_byXageX_byXweightX_byXweightX_selectXa_cX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_gtXbXX_byXageX_selectXa_bX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_gtXbXX_byXageX_selectXa_bX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_out_hasXageX_whereXgtXaXX_byXageX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_out_hasXageX_whereXgtXaXX_byXageX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_withSideEffectXa_josh_peterX_VX1X_outXcreatedX_inXcreatedX_name_whereXwithinXaXX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_josh_peterX_VX1X_outXcreatedX_inXcreatedX_name_whereXwithinXaXX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_whereXasXaX_name_isXjoshXX_inXcreatedX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_whereXasXaX_name_isXjoshXX_inXcreatedX_name").invoke(g);
        }

    }

    public static class AddEdgeTestTraversals extends AddEdgeTest {
        private static final String NS = "clojurewerkz.ogre.suite.add-edge-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX() {
            return (Traversal) Clojure.var(NS, "get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX").invoke(g);
        }

        @Override
        public Traversal get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX() {
            return (Traversal) Clojure.var(NS, "get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X(Vertex a, Vertex b) {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X").invoke(g, a, b);
        }

        @Override
        public Traversal get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X(Vertex a, Vertex b) {
            return (Traversal) Clojure.var(NS, "get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X").invoke(g, a, b);
        }

        @Override
        public Traversal get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X(Vertex a, Vertex b) {
            return (Traversal) Clojure.var(NS, "get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X").invoke(g, a, b);
        }

        @Override
        public Traversal get_g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_descX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX() {
            return (Traversal) Clojure.var(NS, "get_g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_descX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX").invoke(g);
        }

    }

    public static class AddVertexTestTraversals extends AddVertexTest {
        private static final String NS = "clojurewerkz.ogre.suite.add-vertex-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_addVXanimalX_propertyXage_selectXaX_byXageXX_propertyXname_puppyX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_addVXanimalX_propertyXage_selectXaX_byXageXX_propertyXname_puppyX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_addVXpersonX_propertyXname_stephenX() {
            return (Traversal) Clojure.var(NS, "get_g_addVXpersonX_propertyXname_stephenX").invoke(g);
        }

        @Override
        public Traversal get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenmX() {
            return (Traversal) Clojure.var(NS, "get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenmX").invoke(g);
        }

        @Override
        public Traversal get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenm_since_2010X() {
            return (Traversal) Clojure.var(NS, "get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenm_since_2010X").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXname_markoX_propertyXfriendWeight_outEXknowsX_weight_sum__acl_privateX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_markoX_propertyXfriendWeight_outEXknowsX_weight_sum__acl_privateX").invoke(g);
        }

        @Override
        public Traversal get_g_addVXanimalX_propertyXname_mateoX_propertyXname_gateoX_propertyXname_cateoX_propertyXage_5X() {
            return (Traversal) Clojure.var(NS, "get_g_addVXanimalX_propertyXname_mateoX_propertyXname_gateoX_propertyXname_cateoX_propertyXage_5X").invoke(g);
        }

        @Override
        public Traversal get_g_V_addVXanimalX_propertyXname_valuesXnameXX_propertyXname_an_animalX_propertyXvaluesXnameX_labelX() {
            return (Traversal) Clojure.var(NS, "get_g_V_addVXanimalX_propertyXname_valuesXnameXX_propertyXname_an_animalX_propertyXvaluesXnameX_labelX").invoke(g);
        }

        @Override
        public Traversal get_g_V_addVXanimalX_propertyXage_0X() {
            return (Traversal) Clojure.var(NS, "get_g_V_addVXanimalX_propertyXage_0X").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXa_markoX_addV_propertyXname_selectXaXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_markoX_addV_propertyXname_selectXaXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXa_testX_V_hasLabelXsoftwareX_propertyXtemp_selectXaXX_valueMapXname_tempX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_testX_V_hasLabelXsoftwareX_propertyXtemp_selectXaXX_valueMapXname_tempX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_hasXname_markoX_outXcreatedX_asXbX_addVXselectXaX_labelX_propertyXtest_selectXbX_labelX_valueMapXtrueX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_hasXname_markoX_outXcreatedX_asXbX_addVXselectXaX_labelX_propertyXtest_selectXbX_labelX_valueMapXtrueX").invoke(g);
        }

        @Override
        public Traversal get_g_addVXV_hasXname_markoX_propertiesXnameX_keyX_label() {
            return (Traversal) Clojure.var(NS, "get_g_addVXV_hasXname_markoX_propertiesXnameX_keyX_label").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXa_nameX_addV_propertyXselectXaX_markoX_name() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_nameX_addV_propertyXselectXaX_markoX_name").invoke(g);
        }

    }

    public static class CoalesceTestTraversals extends CoalesceTest {
        private static final String NS = "clojurewerkz.ogre.suite.coalesce-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_coalesceXoutXfooX_outXbarXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_coalesceXoutXfooX_outXbarXX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_coalesceXoutXknowsX_outXcreatedXX_valuesXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_coalesceXoutXknowsX_outXcreatedXX_valuesXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_coalesceXoutXcreatedX_outXknowsXX_valuesXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_coalesceXoutXcreatedX_outXknowsXX_valuesXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_coalesceXoutXlikesX_outXknowsX_inXcreatedXX_groupCount_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_coalesceXoutXlikesX_outXknowsX_inXcreatedXX_groupCount_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_coalesceXoutEXknowsX_outEXcreatedXX_otherV_path_byXnameX_byXlabelX() {
            return (Traversal) Clojure.var(NS, "get_g_V_coalesceXoutEXknowsX_outEXcreatedXX_otherV_path_byXnameX_byXlabelX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_order_byXnameX_coalesceXname_constantXxXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_order_byXnameX_coalesceXname_constantXxXX").invoke(g);
        }

    }

    public static class ConstantTestTraversals extends ConstantTest {
        private static final String NS = "clojurewerkz.ogre.suite.constant-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_chooseXhasLabelXpersonX_valuesXnameX_constantXinhumanXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXhasLabelXpersonX_valuesXnameX_constantXinhumanXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_constantX123X() {
            return (Traversal) Clojure.var(NS, "get_g_V_constantX123X").invoke(g);
        }

    }

    public static class CountTestTraversals extends CountTest {
        private static final String NS = "clojurewerkz.ogre.suite.count-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_out_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_both_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_both_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX3X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX3X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX8X_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX8X_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX5X_asXaX_outXwrittenByX_asXbX_selectXa_bX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX5X_asXaX_outXwrittenByX_asXbX_selectXa_bX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXnoX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXnoX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_fold_countXlocalX() {
            return (Traversal) Clojure.var(NS, "get_g_V_fold_countXlocalX").invoke(g);
        }

        @Override
        public Traversal get_g_V_whereXinXknowsX_outXcreatedX_count_is_0XX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXinXknowsX_outXcreatedX_count_is_0XX_name").invoke(g);
        }

    }

    public static class FlatMapTestTraversals extends FlatMapTest {
        private static final String NS = "clojurewerkz.ogre.suite.flat-map-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_asXaX_flatMapXselectXaXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_flatMapXselectXaXX").invoke(g);
        }

    }

    public static class FoldTestTraversals extends FoldTest {
        private static final String NS = "clojurewerkz.ogre.suite.fold-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_fold() {
            return (Traversal) Clojure.var(NS, "get_g_V_fold").invoke(g);
        }

        @Override
        public Traversal get_g_V_fold_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_fold_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_age_foldX0_plusX() {
            return (Traversal) Clojure.var(NS, "get_g_V_age_foldX0_plusX").invoke(g);
        }

    }

    public static class GraphTestTraversals extends GraphTest {
        private static final String NS = "clojurewerkz.ogre.suite.graph-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasXname_GarciaX_inXsungByX_asXsongX_V_hasXname_Willie_DixonX_inXwrittenByX_whereXeqXsongXX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_GarciaX_inXsungByX_asXsongX_V_hasXname_Willie_DixonX_inXwrittenByX_whereXeqXsongXX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_asXpX_VXsoftwareX_addInEXuses_pX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_asXpX_VXsoftwareX_addInEXuses_pX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_V_valuesXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_V_valuesXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_outXknowsX_V_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXknowsX_V_name").invoke(g);
        }

    }

    public static class LoopsTestTraversals extends LoopsTest {
        private static final String NS = "clojurewerkz.ogre.suite.loops-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX3XX_hasXname_peterX_path_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX3XX_hasXname_peterX_path_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX2XX_hasXname_peterX_path_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX2XX_hasXname_peterX_path_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_and_loops_isX3XX_hasXname_peterX_path_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_and_loops_isX3XX_hasXname_peterX_path_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_emitXhasXname_markoX_or_loops_isX2XX_repeatXoutX_valuesXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_emitXhasXname_markoX_or_loops_isX2XX_repeatXoutX_valuesXnameX").invoke(g);
        }

    }

    public static class MapTestTraversals extends MapTest {
        private static final String NS = "clojurewerkz.ogre.suite.map-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_out_mapXnameX_mapXlengthX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_mapXnameX_mapXlengthX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_withPath_V_asXaX_out_out_mapXa_name_it_nameX() {
            return (Traversal) Clojure.var(NS, "get_g_withPath_V_asXaX_out_out_mapXa_name_it_nameX").invoke(g);
        }

        @Override
        public Traversal get_g_withPath_V_asXaX_out_mapXa_nameX() {
            return (Traversal) Clojure.var(NS, "get_g_withPath_V_asXaX_out_mapXa_nameX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_mapXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_mapXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_mapXselectXaXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_mapXselectXaXX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_outE_label_mapXlengthX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outE_label_mapXlengthX").invoke(g, arg0);
        }

    }

    public static class MathTestTraversals extends MathTest {
        private static final String NS = "clojurewerkz.ogre.suite.math-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_asXaX_outXknowsX_asXbX_mathXa_plus_bX_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXknowsX_asXbX_mathXa_plus_bX_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXx_100X_V_age_mathX__plus_xX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXx_100X_V_age_mathX__plus_xX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_asXbX_mathXb_plus_aX_byXinXcreatedX_countX_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_asXbX_mathXb_plus_aX_byXinXcreatedX_countX_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_withSackX1X_injectX1X_repeatXsackXsumX_byXconstantX1XXX_timesX5X_emit_mathXsin__X_byXsackX() {
            return (Traversal) Clojure.var(NS, "get_g_withSackX1X_injectX1X_repeatXsackXsumX_byXconstantX1XXX_timesX5X_emit_mathXsin__X_byXsackX").invoke(g);
        }

        @Override
        public Traversal get_g_V_projectXa_b_cX_byXbothE_weight_sumX_byXbothE_countX_byXnameX_order_byXmathXa_div_bX_descX_selectXcX() {
            return (Traversal) Clojure.var(NS, "get_g_V_projectXa_b_cX_byXbothE_weight_sumX_byXbothE_countX_byXnameX_order_byXmathXa_div_bX_descX_selectXcX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_mathX0_minus_itX_byXweightX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_mathX0_minus_itX_byXweightX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXageX_valueMap_mathXit_plus_itXbyXselectXageX_unfoldXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_valueMap_mathXit_plus_itXbyXselectXageX_unfoldXX").invoke(g);
        }

    }

    public static class MaxTestTraversals extends MaxTest {
        private static final String NS = "clojurewerkz.ogre.suite.max-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_maxX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_maxX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_timesX5X_age_max() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX5X_age_max").invoke(g);
        }

        @Override
        public Traversal get_g_V_age_max() {
            return (Traversal) Clojure.var(NS, "get_g_V_age_max").invoke(g);
        }

    }

    public static class MeanTestTraversals extends MeanTest {
        private static final String NS = "clojurewerkz.ogre.suite.mean-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_age_mean() {
            return (Traversal) Clojure.var(NS, "get_g_V_age_mean").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_meanX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_meanX").invoke(g);
        }

    }

    public static class MinTestTraversals extends MinTest {
        private static final String NS = "clojurewerkz.ogre.suite.min-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_repeatXbothX_timesX5X_age_min() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX5X_age_min").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_minX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_minX").invoke(g);
        }

        @Override
        public Traversal get_g_V_age_min() {
            return (Traversal) Clojure.var(NS, "get_g_V_age_min").invoke(g);
        }

        @Override
        public Traversal get_g_V_foo_injectX9999999999X_min() {
            return (Traversal) Clojure.var(NS, "get_g_V_foo_injectX9999999999X_min").invoke(g);
        }
    }

    public static class SumTestTraversals extends SumTest {
        private static final String NS = "clojurewerkz.ogre.suite.sum-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_valuesXageX_sum() {
            return (Traversal) Clojure.var(NS, "get_g_V_valuesXageX_sum").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_sumX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_sumX").invoke(g);
        }

    }

    public static class OrderTestTraversals extends OrderTest {
        private static final String NS = "clojurewerkz.ogre.suite.order-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_asXaX_outXcreatedX_asXbX_order_byXshuffleX_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXcreatedX_asXbX_order_byXshuffleX_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_hasXlabel_personX_mapXmapXint_ageXX_orderXlocalX_byXvalues_descX_byXkeys_ascX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_hasXlabel_personX_mapXmapXint_ageXX_orderXlocalX_byXvalues_descX_byXkeys_ascX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_order_byXoutE_count_descX() {
            return (Traversal) Clojure.var(NS, "get_g_V_order_byXoutE_count_descX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXlabelX_byXname_order_byXdescX_foldX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXlabelX_byXname_order_byXdescX_foldX").invoke(g);
        }

        @Override
        public Traversal get_g_V_localXbothE_weight_foldX_order_byXsumXlocalX_descX() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXbothE_weight_foldX_order_byXsumXlocalX_descX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXvX_mapXbothE_weight_foldX_sumXlocalX_asXsX_selectXv_sX_order_byXselectXsX_descX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXvX_mapXbothE_weight_foldX_sumXlocalX_asXsX_selectXv_sX_order_byXselectXsX_descX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_order_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_order_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_fold_orderXlocalX_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_fold_orderXlocalX_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_order_byXvalueXageX_descX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_order_byXvalueXageX_descX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_properties_order_byXkey_descX_key() {
            return (Traversal) Clojure.var(NS, "get_g_V_properties_order_byXkey_descX_key").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXsong_name_OHBOYX_outXfollowedByX_outXfollowedByX_order_byXperformancesX_byXsongType_descX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXsong_name_OHBOYX_outXfollowedByX_outXfollowedByX_order_byXperformancesX_byXsongType_descX").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_hasLabelXpersonX_order_byXage_descX_limitX5X_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_hasLabelXpersonX_order_byXage_descX_limitX5X_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_hasLabelXpersonX_order_byXage_descX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_hasLabelXpersonX_order_byXage_descX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsongX_order_byXperformances_descX_byXnameX_rangeX110_120X_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsongX_order_byXperformances_descX_byXnameX_rangeX110_120X_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_name_order_byXa1_b1X_byXb2_a2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_name_order_byXa1_b1X_byXb2_a2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_name_order() {
            return (Traversal) Clojure.var(NS, "get_g_V_name_order").invoke(g);
        }

        @Override
        public Traversal get_g_V_order_byXname_ascX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_order_byXname_ascX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_order_byXname_incrX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_order_byXname_incrX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_order_byXnameX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_order_byXnameX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_order_byXname_a1_b1X_byXname_b2_a2X_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_order_byXname_a1_b1X_byXname_b2_a2X_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_order_byXweight_decrX_weight() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_order_byXweight_decrX_weight").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_unfold_order_byXvalues_descX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_unfold_order_byXvalues_descX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_orderXlocalX_byXvaluesX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_orderXlocalX_byXvaluesX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_order_byXweight_descX_weight() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_order_byXweight_descX_weight").invoke(g);
        }
    }

    public static class PathTestTraversals extends PathTest {
        private static final String NS = "clojurewerkz.ogre.suite.path-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_name_path(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_name_path").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_repeatXoutX_timesX2X_path_by_byXnameX_byXlangX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXoutX_timesX2X_path_by_byXnameX_byXlangX").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_out_path_byXnameX_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_path_byXnameX_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_hasXname_markoX_asXbX_hasXage_29X_asXcX_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_hasXname_markoX_asXbX_hasXage_29X_asXcX_path").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_out_path_byXageX_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_path_byXageX_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outEXcreatedX_inV_inE_outV_path(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXcreatedX_inV_inE_outV_path").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_out_asXcX_path_fromXbX_toXcX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_out_asXcX_path_fromXbX_toXcX_byXnameX").invoke(g);
        }

    }

    public static class ProfileTestTraversals extends ProfileTest {
        private static final String NS = "clojurewerkz.ogre.suite.profile-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_sideEffectXThread_sleepX10XX_sideEffectXThread_sleepX5XX_profileXmetricsX() {
            return (Traversal) Clojure.var(NS, "get_g_V_sideEffectXThread_sleepX10XX_sideEffectXThread_sleepX5XX_profileXmetricsX").invoke(g);
        }

        @Override
        public Traversal get_g_V_whereXinXcreatedX_count_isX1XX_name_profileXmetricsX() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXinXcreatedX_count_isX1XX_name_profileXmetricsX").invoke(g);
        }

        @Override
        public Traversal get_g_V_matchXa_created_b__b_in_count_isXeqX1XXX_selectXa_bX_profileXmetricsX() {
            return (Traversal) Clojure.var(NS, "get_g_V_matchXa_created_b__b_in_count_isXeqX1XXX_selectXa_bX_profileXmetricsX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_pageRank_byXrankX_byXbothEX_rank_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_pageRank_byXrankX_byXbothEX_rank_profile").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_out_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_profile").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_timesX3X_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX3X_profile").invoke(g);
        }

        @Override
        public Traversal get_g_V_sideEffectXThread_sleepX10XX_sideEffectXThread_sleepX5XX_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_sideEffectXThread_sleepX10XX_sideEffectXThread_sleepX5XX_profile").invoke(g);
        }

        @Override
        public Traversal get_g_V_whereXinXcreatedX_count_isX1XX_name_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_whereXinXcreatedX_count_isX1XX_name_profile").invoke(g);
        }

        @Override
        public Traversal get_g_V_matchXa_created_b__b_in_count_isXeqX1XXX_selectXa_bX_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_matchXa_created_b__b_in_count_isXeqX1XXX_selectXa_bX_profile").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_out_profileXmetricsX() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_profileXmetricsX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXbothX_timesX3X_profileXmetricsX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothX_timesX3X_profileXmetricsX").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupXmX_profile() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupXmX_profile").invoke(g);
        }

    }

    public static class ProjectTestTraversals extends ProjectTest {
        private static final String NS = "clojurewerkz.ogre.suite.project-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasLabelXpersonX_projectXa_bX_byXoutE_countX_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_projectXa_bX_byXoutE_countX_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_projectXa_bX_byXnameX_byXinXcreatedX_countX_order_byXselectXbX__descX_selectXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_projectXa_bX_byXnameX_byXinXcreatedX_countX_order_byXselectXbX__descX_selectXaX").invoke(g);
        }

    }

    public static class PropertiesTestTraversals extends PropertiesTest {
        private static final String NS = "clojurewerkz.ogre.suite.properties-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasXageX_propertiesXname_ageX_value() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_propertiesXname_ageX_value").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXageX_propertiesXage_nameX_value() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_propertiesXage_nameX_value").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXageX_properties_hasXid_nameIdX_value(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_properties_hasXid_nameIdX_value").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasXageX_propertiesXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_propertiesXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_injectXg_VX1X_propertiesXnameX_nextX_value(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_injectXg_VX1X_propertiesXnameX_nextX_value").invoke(g, arg0);
        }

    }

    public static class SelectTestTraversals extends SelectTest {
        private static final String NS = "clojurewerkz.ogre.suite.select-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_asXaX_out_asXbX_selectXa_bX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_asXbX_selectXa_bX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_out_aggregateXxX_asXbX_selectXa_bX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_out_aggregateXxX_asXbX_selectXa_bX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_name_order_asXbX_selectXa_bX_byXnameX_by_XitX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_name_order_asXbX_selectXa_bX_byXnameX_by_XitX").invoke(g);
        }

        @Override
        public Traversal get_g_V_untilXout_outX_repeatXin_asXaXX_selectXaX_byXtailXlocalX_nameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_untilXout_outX_repeatXin_asXaXX_selectXaX_byXtailXlocalX_nameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_untilXout_outX_repeatXin_asXaX_in_asXbXX_selectXa_bX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_untilXout_outX_repeatXin_asXaX_in_asXbXX_selectXa_bX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_whereXoutXknowsXX_selectXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_whereXoutXknowsXX_selectXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_weight_groupCount_selectXvaluesX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_weight_groupCount_selectXvaluesX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_weight_groupCount_unfold_selectXvaluesX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_weight_groupCount_unfold_selectXvaluesX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_weight_groupCount_selectXvaluesX_unfold_groupCount_selectXvaluesX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_weight_groupCount_selectXvaluesX_unfold_groupCount_selectXvaluesX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_weight_groupCount_selectXkeysX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_weight_groupCount_selectXkeysX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_outE_weight_groupCount_unfold_selectXkeysX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_weight_groupCount_unfold_selectXkeysX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXa_bX_out_asXcX_path_selectXkeysX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXa_bX_out_asXcX_path_selectXkeysX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_outXknowsX_asXbX_localXselectXa_bX_byXnameXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXknowsX_asXbX_localXselectXa_bX_byXnameXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXname_gremlinX_inEXusesX_order_byXskill_ascX_asXaX_outV_asXbX_selectXa_bX_byXskillX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_gremlinX_inEXusesX_order_byXskill_ascX_asXaX_outV_asXbX_selectXa_bX_byXskillX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXname_isXmarkoXX_asXaX_selectXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXname_isXmarkoXX_asXaX_selectXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_label_groupCount_asXxX_selectXxX() {
            return (Traversal) Clojure.var(NS, "get_g_V_label_groupCount_asXxX_selectXxX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_asXpX_mapXbothE_label_groupCountX_asXrX_selectXp_rX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_asXpX_mapXbothE_label_groupCountX_asXrX_selectXp_rX").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXoutE_count_isX0X__asXaX__asXbXX_chooseXselectXaX__selectXaX__selectXbXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXoutE_count_isX0X__asXaX__asXbXX_chooseXselectXaX__selectXaX__selectXbXX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_asXhereX_out_selectXhereX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXhereX_out_selectXhereX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outE_asXhereX_inV_hasXname_vadasX_selectXhereX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outE_asXhereX_inV_hasXname_vadasX_selectXhereX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outEXknowsX_hasXweight_1X_asXhereX_inV_hasXname_joshX_selectXhereX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXknowsX_hasXweight_1X_asXhereX_inV_hasXname_joshX_selectXhereX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_inV_hasXname_joshX_selectXhereX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_inV_hasXname_joshX_selectXhereX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_asXfakeX_inV_hasXname_joshX_selectXhereX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_asXfakeX_inV_hasXname_joshX_selectXhereX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_asXhereXout_name_selectXhereX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXhereXout_name_selectXhereX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_unionXasXprojectX_inXcreatedX_hasXname_markoX_selectXprojectX__asXprojectX_inXcreatedX_inXknowsX_hasXname_markoX_selectXprojectXX_groupCount_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_unionXasXprojectX_inXcreatedX_hasXname_markoX_selectXprojectX__asXprojectX_inXcreatedX_inXknowsX_hasXname_markoX_selectXprojectXX_groupCount_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_hasXname_markoX_asXbX_asXcX_selectXa_b_cX_by_byXnameX_byXageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_hasXname_markoX_asXbX_asXcX_selectXa_b_cX_by_byXnameX_byXageX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsoftwareX_asXnameX_asXlanguageX_asXcreatorsX_selectXname_language_creatorsX_byXnameX_byXlangX_byXinXcreatedX_name_fold_orderXlocalXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsoftwareX_asXnameX_asXlanguageX_asXcreatorsX_selectXname_language_creatorsX_byXnameX_byXlangX_byXinXcreatedX_name_fold_orderXlocalXX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXlast_aX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXlast_aX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXfirst_aX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXfirst_aX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_asXaX_outXknowsX_asXaX_selectXall_constantXaXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_outXknowsX_asXaX_selectXall_constantXaXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_groupXmX_by_byXbothE_countX_barrier_selectXmX_selectXselectXaXX_byXmathX_plus_XX() {
            return (Traversal) Clojure.var(NS, "get_g_V_asXaX_groupXmX_by_byXbothE_countX_barrier_selectXmX_selectXselectXaXX_byXmathX_plus_XX").invoke(g);
        }

        @Override
        public Traversal get_g_V_asXaX_groupXmX_by_byXbothE_countX_barrier_selectXmX_selectXselectXaXX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_asXaX_groupXmX_by_byXbothE_countX_barrier_selectXmX_selectXselectXaXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXaX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXa_bX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXfirst_aX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXfirst_aX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXfirst_a_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXfirst_a_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXfirst_aX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXfirst_aX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXfirst_a_bX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXfirst_a_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXlast_aX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXlast_aX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXlast_a_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXlast_a_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXlast_aX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXlast_aX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXlast_a_bX() {
            return  (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXlast_a_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXaX_count() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXaX_count").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXall_aX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXall_aX").invoke(g);
        }

        @Override
        public Traversal get_g_V_selectXall_a_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_selectXall_a_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXall_aX() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXall_aX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_selectXall_a_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMap_selectXall_a_bX").invoke(g);
        }
    }

    public static class VertexTestTraversals extends VertexTest {
        private static final String NS = "clojurewerkz.ogre.suite.vertex-test";
        static {
            require.invoke(Clojure.read(NS));
        }

        @Override
        public Traversal get_g_V() {
            return (Traversal) Clojure.var(NS, "get_g_V").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_out(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_E() {
            return (Traversal) Clojure.var(NS, "get_g_E").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_outE(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outE").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX2X_inE(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX2X_inE").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_bothE(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_bothE").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_bothEXcreatedX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_bothEXcreatedX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outE_inV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outE_inV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX2X_inE_outV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX2X_inE_outV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_outE_hasXweight_1X_outV() {
            return (Traversal) Clojure.var(NS, "get_g_V_outE_hasXweight_1X_outV").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_outE_inV_inE_inV_both_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_outE_inV_inE_inV_both_name").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_outEXknowsX_bothV_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXknowsX_bothV_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outXknowsX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXknowsX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outXknows_createdX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXknows_createdX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outEXknowsX_inV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXknowsX_inV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outEXknows_createdX_inV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outEXknows_createdX_inV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_outE_otherV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outE_otherV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_bothE_otherV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_bothE_otherV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_bothE_hasXweight_lt_1X_otherV(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_bothE_hasXweight_lt_1X_otherV").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX2X_in(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX2X_in").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX4X_both(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX4X_both").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_out_out() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_out_out_out(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_out_out").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_to_XOUT_knowsX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_to_XOUT_knowsX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_EX11X(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_EX11X").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1_2_3_4X_name(java.lang.Object arg0, java.lang.Object arg1, java.lang.Object arg2, java.lang.Object arg3) {
            return (Traversal) Clojure.var(NS, "get_g_VX1_2_3_4X_name").invoke(g, arg0, arg1, arg2, arg3);
        }

        @Override
        public Traversal get_g_VXlistX1_2_3XX_name(java.lang.Object arg0, java.lang.Object arg1, java.lang.Object arg2) {
            return (Traversal) Clojure.var(NS, "get_g_VXlistX1_2_3XX_name").invoke(g, arg0, arg1, arg2);
        }

        @Override
        public Traversal get_g_VXlistXv1_v2_v3XX_name(org.apache.tinkerpop.gremlin.structure.Vertex arg0, org.apache.tinkerpop.gremlin.structure.Vertex arg1, org.apache.tinkerpop.gremlin.structure.Vertex arg2) {
            return (Traversal) Clojure.var(NS, "get_g_VXlistXv1_v2_v3XX_name").invoke(g, arg0, arg1, arg2);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_V_hasLabelXsoftwareX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_V_hasLabelXsoftwareX_name").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXloopsX_bothEXselfX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXloopsX_bothEXselfX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXloopsX_bothXselfX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXloopsX_bothXselfX").invoke(g);
        }

    }

    public static class UnfoldTestTraversals extends UnfoldTest {
        private static final String NS = "clojurewerkz.ogre.suite.unfold-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_localXoutE_foldX_unfold() {
            return (Traversal) Clojure.var(NS, "get_g_V_localXoutE_foldX_unfold").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMap_unfold_mapXkeyX() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMap_unfold_mapXkeyX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_repeatXboth_simplePathX_untilXhasIdX6XX_path_byXnameX_unfold(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_repeatXboth_simplePathX_untilXhasIdX6XX_path_byXnameX_unfold").invoke(g, arg0, arg1);
        }

    }

    public static class ValueMapTestTraversals extends ValueMapTest {
        private static final String NS = "clojurewerkz.ogre.suite.value-map-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_valueMap() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMap").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMapXname_ageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMapXname_ageX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_outXcreatedX_valueMap(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_outXcreatedX_valueMap").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_filterXoutEXcreatedXX_valueMapXtrueX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_filterXoutEXcreatedXX_valueMapXtrueX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMapXtrueX() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMapXtrueX").invoke(g);
        }

        @Override
        public Traversal get_g_V_valueMapXtrue_name_ageX() {
            return (Traversal) Clojure.var(NS, "get_g_V_valueMapXtrue_name_ageX").invoke(g);
        }

    }

    public static class AggregateTestTraversals extends AggregateTest {
        private static final String NS = "clojurewerkz.ogre.suite.aggregate-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_name_aggregateXxX_capXxX() {
            return (Traversal) Clojure.var(NS, "get_g_V_name_aggregateXxX_capXxX").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_aggregateXaX_path() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_aggregateXaX_path").invoke(g);
        }

        @Override
        public Traversal get_g_V_aggregateXxX_byXnameX_capXxX() {
            return (Traversal) Clojure.var(NS, "get_g_V_aggregateXxX_byXnameX_capXxX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_aggregateXxX_byXageX_capXxX_asXyX_selectXyX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_aggregateXxX_byXageX_capXxX_asXyX_selectXyX").invoke(g);
        }

    }

    public static class ExplainTestTraversals extends ExplainTest {
        private static final String NS = "clojurewerkz.ogre.suite.explain-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public TraversalExplanation get_g_V_outE_identity_inV_explain() {
            return (TraversalExplanation) Clojure.var(NS, "get_g_V_outE_identity_inV_explain").invoke(g);
        }

    }

    public static class GroupTestTraversals extends GroupTest {
        private static final String NS = "clojurewerkz.ogre.suite.group-test";
        static {
            require.invoke(Clojure.read(NS));
        }

        @Override
        public Traversal get_g_V_repeatXbothXfollowedByXX_timesX2X_group_byXsongTypeX_byXcountX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothXfollowedByXX_timesX2X_group_byXsongTypeX_byXcountX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXbothXfollowedByXX_timesX2X_groupXaX_byXsongTypeX_byXcountX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXbothXfollowedByXX_timesX2X_groupXaX_byXsongTypeX_byXcountX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXname_substring_1X_byXconstantX1XX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXname_substring_1X_byXconstantX1XX").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupXaX_byXname_substring_1X_byXconstantX1XX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupXaX_byXname_substring_1X_byXconstantX1XX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_group_byXlabelX_selectXpersonX_unfold_outXcreatedX_name_limitX2X() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_group_byXlabelX_selectXpersonX_unfold_outXcreatedX_name_limitX2X").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsongX_group_byXnameX_byXproperties_groupCount_byXlabelXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsongX_group_byXnameX_byXproperties_groupCount_byXlabelXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXsongX_groupXaX_byXnameX_byXproperties_groupCount_byXlabelXX_out_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXsongX_groupXaX_byXnameX_byXproperties_groupCount_byXlabelXX_out_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXunionXoutXknowsX_groupXaX_byXageX__outXcreatedX_groupXbX_byXnameX_byXcountXX_groupXaX_byXnameXX_timesX2X_capXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXunionXoutXknowsX_groupXaX_byXageX__outXcreatedX_groupXbX_byXnameX_byXcountXX_groupXaX_byXnameXX_timesX2X_capXa_bX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXnameX_by() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXnameX_by").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupXaX_byXnameX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupXaX_byXnameX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXlangX_groupXaX_byXlangX_byXnameX_out_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXlangX_groupXaX_byXlangX_byXnameX_out_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXlangX_group_byXlangX_byXcountX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXlangX_group_byXlangX_byXcountX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXout_groupXaX_byXnameX_byXcountX_timesX2X_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXout_groupXaX_byXnameX_byXcountX_timesX2X_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXoutE_countX_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXoutE_countX_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupXaX_byXlabelX_byXoutE_weight_sumX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupXaX_byXlabelX_byXoutE_weight_sumX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXfollowedByX_group_byXsongTypeX_byXbothE_group_byXlabelX_byXweight_sumXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXfollowedByX_group_byXsongTypeX_byXbothE_group_byXlabelX_byXweight_sumXX").invoke(g);
        }

        @Override
        public Traversal get_g_V_group_byXbothE_countX_byXgroup_byXlabelXX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXbothE_countX_byXgroup_byXlabelXX").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXa__marko_666_noone_blahX_V_groupXaX_byXnameX_byXoutE_label_foldX_capXaX(final Map<String, List<Object>> m) {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa__marko_666_noone_blahX_V_groupXaX_byXnameX_byXoutE_label_foldX_capXaX").invoke(g, m);
        }

        @Override
        public Traversal get_g_V_group_byXlabelX_byXbothE_groupXaX_byXlabelX_byXweight_sumX_weight_sumX() {
            return (Traversal) Clojure.var(NS, "get_g_V_group_byXlabelX_byXbothE_groupXaX_byXlabelX_byXweight_sumX_weight_sumX").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupXmX_byXnameX_byXinXknowsX_nameX_capXmX() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupXmX_byXnameX_byXinXknowsX_nameX_capXmX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasLabelXpersonX_asXpX_outXcreatedX_group_byXnameX_byXselectXpX_valuesXageX_sumX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasLabelXpersonX_asXpX_outXcreatedX_group_byXnameX_byXselectXpX_valuesXageX_sumX").invoke(g);
        }

    }

    public static class GroupCountTestTraversals extends GroupCountTest {
        private static final String NS = "clojurewerkz.ogre.suite.group-count-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasXnoX_groupCount() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXnoX_groupCount").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_groupCount_byXnameX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_groupCount_byXnameX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_groupCountXaX_byXnameX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_groupCountXaX_byXnameX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_name_groupCount() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_name_groupCount").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_groupCountXxX_capXxX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_groupCountXxX_capXxX").invoke(g);
        }

        @Override
        public Traversal get_g_V_outXcreatedX_name_groupCountXaX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_outXcreatedX_name_groupCountXaX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_hasXnoX_groupCountXaX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXnoX_groupCountXaX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_repeatXout_groupCountXaX_byXnameXX_timesX2X_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_repeatXout_groupCountXaX_byXnameXX_timesX2X_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_unionXrepeatXoutX_timesX2X_groupCountXmX_byXlangXX__repeatXinX_timesX2X_groupCountXmX_byXnameXX_capXmX() {
            return (Traversal) Clojure.var(NS, "get_g_V_unionXrepeatXoutX_timesX2X_groupCountXmX_byXlangXX__repeatXinX_timesX2X_groupCountXmX_byXnameXX_capXmX").invoke(g);
        }

        @Override
        public Traversal get_g_V_groupCount_byXbothE_countX() {
            return (Traversal) Clojure.var(NS, "get_g_V_groupCount_byXbothE_countX").invoke(g);
        }

        @Override
        public Traversal get_g_V_unionXoutXknowsX__outXcreatedX_inXcreatedXX_groupCount_selectXvaluesX_unfold_sum() {
            return (Traversal) Clojure.var(NS, "get_g_V_unionXoutXknowsX__outXcreatedX_inXcreatedXX_groupCount_selectXvaluesX_unfold_sum").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_groupCountXaX_out_capXaX_selectXkeysX_unfold_both_groupCountXaX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_groupCountXaX_out_capXaX_selectXkeysX_unfold_both_groupCountXaX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_both_groupCountXaX_byXlabelX_asXbX_barrier_whereXselectXaX_selectXsoftwareX_isXgtX2XXX_selectXbX_name() {
            return (Traversal) Clojure.var(NS, "get_g_V_both_groupCountXaX_byXlabelX_asXbX_barrier_whereXselectXaX_selectXsoftwareX_isXgtX2XXX_selectXbX_name").invoke(g);
        }

    }

    public static class InjectTestTraversals extends InjectTest {
        private static final String NS = "clojurewerkz.ogre.suite.inject-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_out_name_injectXdanielX_asXaX_mapXlengthX_path(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_name_injectXdanielX_asXaX_mapXlengthX_path").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_injectXv2X_name(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_injectXv2X_name").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_VX1X_injectXg_VX4XX_out_name(java.lang.Object arg0, java.lang.Object arg1) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_injectXg_VX4XX_out_name").invoke(g, arg0, arg1);
        }

    }

    public static class SackTestTraversals extends SackTest {
        private static final String NS = "clojurewerkz.ogre.suite.sack-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_withSackX1_sumX_VX1X_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_withSackX1_sumX_VX1X_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_withBulkXfalseX_withSackX1_sumX_V_out_barrier_sack() {
            return (Traversal) Clojure.var(NS, "get_g_withBulkXfalseX_withSackX1_sumX_V_out_barrier_sack").invoke(g);
        }

        @Override
        public Traversal get_g_withSackX0X_V_outE_sackXsumX_byXweightX_inV_sack_sum() {
            return (Traversal) Clojure.var(NS, "get_g_withSackX0X_V_outE_sackXsumX_byXweightX_inV_sack_sum").invoke(g);
        }

        @Override
        public Traversal get_g_withSackX0X_V_repeatXoutE_sackXsumX_byXweightX_inVX_timesX2X_sack() {
            return (Traversal) Clojure.var(NS, "get_g_withSackX0X_V_repeatXoutE_sackXsumX_byXweightX_inVX_timesX2X_sack").invoke(g);
        }

        @Override
        public Traversal get_g_withSackXmap__map_cloneX_V_out_out_sackXmap_a_nameX_sack() {
            return (Traversal) Clojure.var(NS, "get_g_withSackXmap__map_cloneX_V_out_out_sackXmap_a_nameX_sack").invoke(g);
        }

        @Override
        public Traversal get_g_withSackXBigInteger_TEN_powX1000X_assignX_V_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack() {
            return (Traversal) Clojure.var(NS, "get_g_withSackXBigInteger_TEN_powX1000X_assignX_V_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack").invoke(g);
        }

        @Override
        public Traversal get_g_withBulkXfalseX_withSackX1_sumX_VX1X_localXoutEXknowsX_barrierXnormSackX_inVX_inXknowsX_barrier_sack(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_withBulkXfalseX_withSackX1_sumX_VX1X_localXoutEXknowsX_barrierXnormSackX_inVX_inXknowsX_barrier_sack").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_withSackXhelloX_V_outE_sackXassignX_byXlabelX_inV_sack() {
            return (Traversal) Clojure.var(NS, "get_g_withSackXhelloX_V_outE_sackXassignX_byXlabelX_inV_sack").invoke(g);
        }

        @Override
        public Traversal get_g_withSackX2X_V_sackXdivX_byXconstantX3_0XX_sack() {
            return (Traversal) Clojure.var(NS, "get_g_withSackX2X_V_sackXdivX_byXconstantX3_0XX_sack").invoke(g);
        }

    }

    public static class SideEffectCapTestTraversals extends SideEffectCapTest {
        private static final String NS = "clojurewerkz.ogre.suite.side-effect-cap-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_hasXageX_groupCountXaX_byXnameX_out_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_hasXageX_groupCountXaX_byXnameX_out_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_chooseXlabel_person__age_groupCountXaX__name_groupCountXbXX_capXa_bX() {
            return (Traversal) Clojure.var(NS, "get_g_V_chooseXlabel_person__age_groupCountXaX__name_groupCountXbXX_capXa_bX").invoke(g);
        }

    }

    public static class SideEffectTestTraversals extends SideEffectTest {
        private static final String NS = "clojurewerkz.ogre.suite.side-effect-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_VX1X_sideEffectXstore_aX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_sideEffectXstore_aX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_sideEffectXincr_cX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_sideEffectXincr_cX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_VX1X_out_sideEffectXX_name(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_sideEffectXX_name").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_withSideEffectXa__linkedhashmapX_V_out_groupCountXaX_byXlabelX_out_out_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa__linkedhashmapX_V_out_groupCountXaX_byXlabelX_out_out_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectsXa__linkedhashmapX_withSideEffectXb__arraylist__addAllX_withSideEffectXc__arrayList__addAllX_V_groupXaX_byXlabelX_byXcountX_sideEffectXb__1_2_3X_out_out_out_sideEffectXc__bob_danielX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectsXa__linkedhashmapX_withSideEffectXb__arraylist__addAllX_withSideEffectXc__arrayList__addAllX_V_groupXaX_byXlabelX_byXcountX_sideEffectXb__1_2_3X_out_out_out_sideEffectXc__bob_danielX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXa_0_sumX_V_out_sideEffectXsideEffectsXa_bulkXX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_0_sumX_V_out_sideEffectXsideEffectsXa_bulkXX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_withSideEffectXa_0X_V_out_sideEffectXsideEffectsXa_1XX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_0X_V_out_sideEffectXsideEffectsXa_1XX_capXaX").invoke(g);
        }

    }

    public static class StoreTestTraversals extends StoreTest {
        private static final String NS = "clojurewerkz.ogre.suite.store-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_withSideEffectXa_setX_V_both_name_storeXaX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXa_setX_V_both_name_storeXaX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_storeXaX_byXoutEXcreatedX_countX_out_out_storeXaX_byXinEXcreatedX_weight_sumX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_storeXaX_byXoutEXcreatedX_countX_out_out_storeXaX_byXinEXcreatedX_weight_sumX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_storeXaX_byXnameX_out_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_storeXaX_byXnameX_out_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_storeXaX_byXnameX_out_storeXaX_byXnameX_name_capXaX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_storeXaX_byXnameX_out_storeXaX_byXnameX_name_capXaX").invoke(g, arg0);
        }

    }

    public static class SubgraphTestTraversals extends SubgraphTest {
        private static final String NS = "clojurewerkz.ogre.suite.subgraph-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_withSideEffectXsgX_repeatXbothEXcreatedX_subgraphXsgX_outVX_timesX5X_name_dedup(org.apache.tinkerpop.gremlin.structure.Graph arg0) {
            return (Traversal) Clojure.var(NS, "get_g_V_withSideEffectXsgX_repeatXbothEXcreatedX_subgraphXsgX_outVX_timesX5X_name_dedup").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_withSideEffectXsgX_outEXknowsX_subgraphXsgX_name_capXsgX(java.lang.Object arg0, org.apache.tinkerpop.gremlin.structure.Graph arg1) {
            return (Traversal) Clojure.var(NS, "get_g_V_withSideEffectXsgX_outEXknowsX_subgraphXsgX_name_capXsgX").invoke(g, arg0, arg1);
        }

        @Override
        public Traversal get_g_withSideEffectXsgX_V_hasXname_danielX_outE_subgraphXsgX_inV(org.apache.tinkerpop.gremlin.structure.Graph arg0) {
            return (Traversal) Clojure.var(NS, "get_g_withSideEffectXsgX_V_hasXname_danielX_outE_subgraphXsgX_inV").invoke(g, arg0);
        }

    }

    public static class TreeTestTraversals extends TreeTest {
        private static final String NS = "clojurewerkz.ogre.suite.tree-test";
        static {
            require.invoke(Clojure.read(NS));
        }
        @Override
        public Traversal get_g_V_out_out_treeXaX_byXidX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_treeXaX_byXidX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_out_out_tree_byXnameX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_out_tree_byXnameX").invoke(g, arg0);
        }

        @Override
        public Traversal get_g_V_out_out_tree_byXidX() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_tree_byXidX").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_out_tree() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_tree").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_out_treeXaX_capXaX() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_treeXaX_capXaX").invoke(g);
        }

        @Override
        public Traversal get_g_V_out_out_out_tree() {
            return (Traversal) Clojure.var(NS, "get_g_V_out_out_out_tree").invoke(g);
        }

        @Override
        public Traversal get_g_VX1X_out_out_treeXaX_byXnameX_both_both_capXaX(java.lang.Object arg0) {
            return (Traversal) Clojure.var(NS, "get_g_VX1X_out_out_treeXaX_byXnameX_both_both_capXaX").invoke(g, arg0);
        }

    }

}
