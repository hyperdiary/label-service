package repository

import org.scalatestplus.play.PlaySpec

import scala.util.Success

class LabelRepositorySpec extends PlaySpec {

  "HomeController GET" must {

    "render the index page from a new instance of controller" in {
      val repository = new LabelRepository()
      val res = repository.getLabelUri("El%20Paso")
      res mustBe Success("http://dbpedia.org/resource/El_Paso,_Texas")
      //status(labelResult) mustBe OK
      //contentType(labelResult) mustBe Some(XML)
      //contentAsString(labelResult) mustBe ("<a href=\"https://en.wikipedia.org/wiki/El_Paso,_Texas\"/>")
    }

  }

}
