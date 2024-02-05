package solid

import org.scalatestplus.play.PlaySpec

import scala.util.{Failure, Success}

class PodHandlerSpec extends PlaySpec {

  "pod handler" must {
    "" in {
      val handler = new PodHandler()
      val filePath = getClass.getResource("/person.ttl").getPath
      handler.readToDataset(filePath) match {
        case Success(dataset) => assert(dataset.size == 5)
        case Failure(e) => fail(e)
      }
    }
  }

}
