package org.imgcat

import java.nio.file.{Files, Paths}
import java.util.Base64

object Main {

  val ecsi = "\u001b]"
  val st = '\u0007'

  def main(args: Array[String]): Unit = {
    printf("%s1337;File=inline=1:%s%s\n"
      , ecsi,
      new String(Base64.getEncoder.encode(Files.readAllBytes(Paths.get(args(0))))),
      st)
  }
}
