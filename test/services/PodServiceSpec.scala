package services

import models.rdf.{PersonUtil, RdfPerson}
import org.apache.commons.rdf.rdf4j.RDF4J
import org.scalatestplus.play.PlaySpec

import java.net.URI

class PodServiceSpec extends PlaySpec {
  
  "" must {
    "" in {
      val service = new PodService()
      val personGraph = PersonUtil.getPersonGraph("I100194342000","Kenneth Robert","Walpole")
      
      
      
      service.createPerson(RdfPerson(new URI(""),"",""))
    }
  }

}
