package com.joescii.presentera

import org.scalatest.FlatSpec
import org.scalatest.selenium.Chrome
import org.openqa.selenium.{ Keys, By }
import org.openqa.selenium.interactions.Actions

class ScreenShots extends FlatSpec with Chrome {
  "The presentation" should "advance until the end" in {
    go to ("http://localhost:8080/")
    webDriver.manage().window().maximize()
    setCaptureDir("target/screenshots")

    val lastStepId = "overview" // TODO: Update to be the ID of your last step
    def waitForAnimation(millis:Int) = Thread sleep millis
    def captureStep(i:Int) = capture to (i.toString)
    def advance() = new Actions(webDriver).sendKeys(Keys.PAGE_DOWN).perform()

    Stream.continually(currentUrl).takeWhile(!_.endsWith("/#/"+lastStepId)).zipWithIndex.foreach { case (url, i) =>
      waitForAnimation(1500)
      if(currentUrl endsWith "/#/ing") waitForAnimation(1500) // TODO: If you have a slow slide, wait longer
      captureStep(i)
      advance()
    }


  }

  "The suite" should "quit" in { quit() }
}
