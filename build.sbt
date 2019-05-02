
val versionString = "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "jasperreports-font-nanum",
    organization := "com.thing2x",
    version := versionString,
    scalaVersion := "2.12.8",
    scalacOptions in ThisBuild ++= Seq("-feature", "-deprecation"),
  ).settings(
  // Publishing
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    credentials += Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org",
      sys.env.getOrElse("SONATYPE_USER", ""), sys.env.getOrElse("SONATYPE_PASS", "")),
    homepage := Some(url("https://github.com/smqd/")),
    scmInfo := Some(ScmInfo(url("https://github.com/smqd/jasperreports-font-nanum"), "scm:git@github.com:smqd/jasperreports-font-nanum.git")),
    developers := List(
      Developer("OutOfBedlam", "Kwon, Yeong Eon", "eirny@uangel.com", url("http://www.uangel.com"))
    ),
    publishArtifact in Test := false, // Not publishing the test artifacts (default)
    publishMavenStyle := true
  ).settings(
    // PGP signing
    pgpPublicRing := file("./travis/local.pubring.asc"),
    pgpSecretRing := file("./travis/local.secring.asc"),
    pgpPassphrase := sys.env.get("PGP_PASS").map(_.toArray),
    useGpg := false
  ).settings(
    // License
    organizationName := "UANGEL",
    startYear := Some(2019),
    licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
  )