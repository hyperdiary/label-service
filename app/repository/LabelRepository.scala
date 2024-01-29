package repository

import org.eclipse.rdf4j.query.TupleQuery
import org.eclipse.rdf4j.repository.Repository
import org.eclipse.rdf4j.repository.http.HTTPRepository

import scala.util.{Failure, Success, Try, Using}
import scala.jdk.CollectionConverters.*
import javax.inject.{Inject, Singleton}

@Singleton
class LabelRepository @Inject() {

  val rdf4jServer = "http://localhost:8080/rdf4j-server/"
  val repositoryID = "KRW"
  val repo: Repository = new HTTPRepository(rdf4jServer, repositoryID)

  // TODO (RW): it would be better to use https://github.com/phenoscape/sparql-utils here
  def getLabel(label: String): Try[String] = {
    Using(repo.getConnection) { conn =>
      val tupleQuery: TupleQuery = conn.prepareTupleQuery(
      s"""PREFIX hd: <http://hyperdiary.io/terms/>
         PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
         SELECT ?label ?labelText ?uri WHERE
         {
            ?label rdfs:label "$label" ;
                   hd:isLabelFor ?uri .
         }"""
      )
      Try(tupleQuery.evaluate()) match {
        case Success(results) =>
          val bindingSet = results.asScala.head
          val res = bindingSet.getValue("uri")
          if(res != null) {
            res.stringValue()
          } else 
            ""
        case Failure(e) => e.getMessage
      }
    }
  }

}
