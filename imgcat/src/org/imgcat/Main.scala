package org.imgcat

import java.nio.file.{Files, Paths}
import org.terminal.ITerm2

object Main {

  def main(args: Array[String]): Unit = {
    val fileName = args(0)
    val bytes = Files.readAllBytes(Paths.get(fileName))
    ITerm2.renderImage(bytes)
  }
}