package models

import com.inrupt.client.{Headers, Resource}
import com.inrupt.client.solid.SolidRDFSource
import com.inrupt.client.spi.RDFFactory
import com.inrupt.rdf.wrapping.commons.{ValueMappings, WrapperIRI}
import org.apache.commons.rdf.api.{Graph, IRI, RDF, RDFTerm}
import play.api.libs.json.{Json, OFormat, OWrites}

import java.io.InputStream
import java.net.URI

case class Person(identifier: URI,  givenName: String, surname: String) extends Resource {

//  private val rdf: RDF = RDFFactory.getInstance
//
//  val RDF_TYPE = rdf.createIRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
//
//
//  val FOAF_GIVEN_NAME = rdf.createIRI("http://xmlns.com/foaf/0.1/givenName")
//  val FOAF_SURNAME = rdf.createIRI("http://xmlns.com/foaf/0.1/surname")
//
//  def getRDFType(): URI = {
//
//  }
//
//  class PersonNode(original: RDFTerm, graph: Graph) extends WrapperIRI(original,graph) {
//
//    def getRDFType() = {
//      anyOrNull(RDF_TYPE, ValueMappings.iriAsUri )
//    }
//
//  }


  override def getIdentifier: URI = ???

  override def getContentType: String = ???

  override def getHeaders: Headers = ???

  override def getEntity: InputStream = ???

  override def close(): Unit = ???
}
object Person {
  implicit val personFormat: OFormat[Person] = Json.format[Person]
}
