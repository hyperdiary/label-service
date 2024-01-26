package controllers

import org.scalatestplus.play.*
import org.scalatestplus.play.guice.*
import play.api.test.*
import play.api.test.Helpers.*
import services.LabelService

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class LabelControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new LabelController(stubControllerComponents(), new LabelService())
      val labelResult = controller.getLink("El Paso").apply(FakeRequest(GET, "/label/El%20Paso"))

      status(labelResult) mustBe OK
      contentType(labelResult) mustBe Some(XML)
      contentAsString(labelResult) mustBe ("<a href=\"https://en.wikipedia.org/wiki/El_Paso,_Texas\"/>")
    }

  }
}
