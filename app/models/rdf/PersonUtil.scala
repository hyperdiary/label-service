package models.rdf

import org.apache.commons.rdf.api.Graph
import org.apache.commons.rdf.rdf4j.RDF4J

object PersonUtil {

  val rdf = new RDF4J()
  
  def getPersonGraph(identifier: String, givenName: String, surname: String): Graph = {
    
    val personUri = rdf.createIRI(s"http://krw.hyperdiary.io/person.$identifier")
    val rdfType = rdf.createIRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type>")
    val foafPerson = rdf.createIRI("http://xmlns.com/foaf/0.1/Person")
    val foafGivenName = rdf.createIRI("http://xmlns.com/foaf/0.1/givenName")
    val foafSurname = rdf.createIRI("http://xmlns.com/foaf/0.1/surname")
    val literalGivenName = rdf.createLiteral(givenName)
    val literalSurname = rdf.createLiteral(surname)
    
    val personGraph = rdf.createGraph()
    personGraph.add(rdf.createTriple(personUri,rdfType,foafPerson))
    personGraph.add(rdf.createTriple(personUri, foafGivenName, literalGivenName))
    personGraph.add(rdf.createTriple(personUri,foafSurname, literalSurname))
    personGraph
  }

}
