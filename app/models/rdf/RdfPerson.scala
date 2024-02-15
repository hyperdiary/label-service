package models.rdf

import client.SRDFSource
import com.inrupt.client.solid.SolidRDFSource
import com.inrupt.client.spi.RDFFactory
import com.inrupt.client.{Headers, Resource}
import com.inrupt.rdf.wrapping.commons.{ValueMappings, WrapperIRI}
import org.apache.commons.rdf.api.*
import play.api.libs.json.{Json, OFormat, OWrites}

import java.io.InputStream
import java.net.URI

case class RdfPerson(
    identifier: URI,
    dataset: Dataset,
    syntax: RDFSyntax,
    givenName: Option[String] = None,
    surname: Option[String] = None,
    headers: Option[Headers] = None
) extends SRDFSource(dataset, identifier, syntax, headers) {

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

  override def getIdentifier: URI = identifier

  override def getContentType: String = syntax.mediaType()

  override def getHeaders: Headers = ???

  override def getEntity: InputStream = ???

  override def close(): Unit = ???
}
object RdfPerson {
  def apply(identifier: URI, dataset: Dataset): RdfPerson = RdfPerson(
    identifier,
    dataset,
    RDFSyntax.TURTLE)
}
