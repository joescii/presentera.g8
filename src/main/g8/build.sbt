name := "$name$"

organization := "$organization;format="lower"$"

version := "0.0.1-SNAPSHOT"

scalaVersion := "$scalaVersion$"

resolvers ++= Seq(
  "snapshots"         at "https://oss.sonatype.org/content/repositories/snapshots",
  "releases"          at "https://oss.sonatype.org/content/repositories/releases"
)

seq(webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "$liftVersion$"
  val liftEdition = liftVersion.substring(0,3)
  Seq(
    "net.liftweb"             %% "lift-webkit"                        % liftVersion           % "compile",
    "net.liftmodules"         %% ("lift-jquery-module_"+liftEdition)  % "2.8",
    "net.liftmodules"         %% ("ng-js_"+liftEdition)               % "$liftngjsVersion$"    % "compile",
    "net.liftmodules"         %% ("ng_"+liftEdition)                  % "$liftngVersion$"               % "compile",
    "org.eclipse.jetty"       % "jetty-webapp"                        % "8.1.7.v20120910"     % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet"                       % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"          % "logback-classic"                     % "1.0.6",
    "org.specs2"              %% "specs2"                             % "1.14"                % "test"
  )
}

// Jasmine stuff
seq(jasmineSettings : _*)

appJsDir <+= sourceDirectory { src => src / "main" / "webapp" / "js" }

appJsLibDir <+= sourceDirectory { src => src / "test" / "js" / "3rdlib" }

jasmineTestDir <+= sourceDirectory { src => src /  "test" / "js" }

jasmineConfFile <+= sourceDirectory { src => src / "test" / "js" / "3rdlib" / "test.dependencies.js" }

jasmineRequireJsFile <+= sourceDirectory { src => src / "test" / "js" / "3rdlib" / "require" / "require-2.0.6.js" }

jasmineRequireConfFile <+= sourceDirectory { src => src / "test" / "js" / "3rdlib" / "require.conf.js" }

(Keys.test in Test) <<= (Keys.test in Test) dependsOn (jasmine)
