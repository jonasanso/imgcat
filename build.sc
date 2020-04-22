import mill._
import mill.util.Ctx
import scalalib._
import ammonite.ops._
import ammonite.ops.Shellout

object imgcat extends ScalaModule with GraalVM {
  def scalaVersion = "2.13.1"

  // entry point
  def mainClass = Some("org.imgcat.Main")
  def native =  T {
    buildNative(assembly(), "imgcat")
  }
}

trait GraalVM {
  val options =  Vector(
    "--verbose",
    "--no-server",
    "--no-fallback",
    //"--static", //requires static libc and zlib
    "--report-unsupported-elements-at-runtime",
    "-H:+ReportExceptionStackTraces",
    "-H:+ReportUnsupportedElementsAtRuntime",
    "-H:+TraceClassInitialization",
    "-H:+PrintClassInitialization",
    "--initialize-at-build-time=scala.runtime.Statics$VM"
  )

  def buildNative(jar: PathRef, name: String)(implicit ctx: Ctx.Dest): Unit = {
    val jarName = s"$name.jar"
    cp(jar.path, ctx.dest / jarName)
    Shellout.executeStream(ctx.dest, Command(Vector("native-image", "-jar", jarName) ++ options, Map.empty, Shellout.executeStream)) match{
      case CommandResult(0, s) => s.foreach(_.left.foreach(print))
      case c@CommandResult(e, s) =>
        throw ShelloutException(c)
    }
  }
}