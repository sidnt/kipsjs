import mill._ , scalalib._ , scalajslib._

object index extends ScalaJSModule {
  def scalaVersion = "2.12.6"
  def scalaJSVersion = "0.6.24"
  def ivyDeps = Agg(
    ivy"org.querki::jquery-facade::1.2",
    ivy"io.udash::udash-jquery::2.0.0"
    )
}