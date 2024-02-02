package solid

import org.scalatestplus.play.PlaySpec

import scala.util.{Failure, Success}

class PodHandlerSpec extends PlaySpec {

  "pod handler" must {
    "" in {
      val handler = new PodHandler()
      handler.readToDataset("person.ttl") match {
        case Success(dataset) => assert(dataset.size == 1)
        case Failure(e) => fail(e)
      }
    }
  }

}
