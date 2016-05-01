//:install org.apache.tinkerpop gremlin-test 3.2.0-incubating
import org.apache.tinkerpop.gremlin.process.traversal.step.map.*
toIgnore = [CoreTraversalTest, MatchTest.CountMatchTraversals, MatchTest.GreedyMatchTraversals]
tests = org.apache.tinkerpop.gremlin.process.ProcessStandardSuite.allTests.
  findAll{!toIgnore.contains(it)}.
  findAll{ !it.simpleName.toLowerCase().contains("strategy") }.
  findAll{ it.getAnnotation(Deprecated) == null}

sb = new StringBuilder()
sb.append("package org.clojurewerkz.ogre.gremlin.process;\n\n")
sb.append("import clojure.java.api.Clojure;\n")
sb.append("import clojure.lang.IFn;\n")
sb.append("import org.apache.tinkerpop.gremlin.process.traversal.Traversal;\n")
sb.append("import org.apache.tinkerpop.gremlin.process.traversal.step.branch.*;\n")
sb.append("import org.apache.tinkerpop.gremlin.process.traversal.step.filter.*;\n")
sb.append("import org.apache.tinkerpop.gremlin.process.traversal.step.map.*;\n")
sb.append("import org.apache.tinkerpop.gremlin.process.traversal.step.sideEffect.*;\n")
sb.append("import org.apache.tinkerpop.gremlin.process.traversal.step.util.*;\n")
sb.append("import org.apache.tinkerpop.gremlin.structure.Edge;\n")
sb.append("import org.apache.tinkerpop.gremlin.structure.Vertex;\n")
sb.append("\n")
sb.append("public class OgreTinkerPopCheck {\n")
sb.append("""public static IFn require = Clojure.var("clojure.core", "require");\n""");

dashSeparated = { s ->
  s.replaceAll(/\B[A-Z]/) { '-' + it }.toLowerCase()
}

tests.each { test ->
  def parentTest = test.enclosingClass
  def clojureTestName = dashSeparated(parentTest.simpleName)

  sb.append("    public static class ${parentTest.simpleName}Traversals extends ${parentTest.simpleName} {\n")
  sb.append("        private static final String NS = \"clojurewerkz.ogre.suite.${clojureTestName}\"; \n")
  sb.append("        static {\n")
  sb.append("            require.invoke(Clojure.read(NS));\n")
  sb.append("        }\n");

  def methods = test.methods
  methods.each{ m ->
    if (m.name.startsWith("get_")) {
      def returnType = m.returnType

      sb.append("        @Override\n")
      sb.append("        @SuppressWarnings(\"unchecked\")\n")
      sb.append("        public ${returnType.name} ${m.name}(")
      def params = m.parameterTypes
      for (int ix = 0; ix < params.length; ix++) {
        def p = params[ix].name
        sb.append("${p} arg${ix}")
        if (ix + 1 < params.length) sb.append(", ")
      }
      sb.append(") {\n")
      sb.append("            return (${returnType.name}) Clojure.var(NS, \"${m.name}\").invoke(g")
      for (int ix = 0; ix < params.length; ix++) {
        if (ix == 0) sb.append(", ")
        def p = params[ix].name
        sb.append("arg${ix}")
        if (ix + 1 < params.length) sb.append(", ")
      }
      sb.append(");\n")
      sb.append("        }\n")
      sb.append("\n")
    }
  }

  sb.append("    }\n\n")
}

sb.append("}")
sb.toString()
new File('OgreTinkerPopCheck.java').withWriter('UTF-8') { writer ->
  writer.write(sb.toString())
}

