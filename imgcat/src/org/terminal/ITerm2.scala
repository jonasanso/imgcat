package org.terminal

import java.util.Base64

object ITerm2 {

  private val ecsi = "\u001b]"
  private val st = '\u0007'

  def renderImage(bytes: Array[Byte]): Unit = {
    val encoded = new String(Base64.getEncoder.encode(bytes))
    println(s"${ecsi}1337;File=inline=1:$encoded$st\n")
  }
}
