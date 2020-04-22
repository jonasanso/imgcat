import mill._
import mill.scalalib._

object imgcat extends ScalaModule {
  def scalaVersion = "2.13.1"

  // entry point
  def mainClass = Some("org.imgcat.Main")

}
