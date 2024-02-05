package solid

import com.inrupt.client.{Request, Response}
import com.inrupt.client.openid.OpenIdSession
import com.inrupt.client.rdf4j.{RDF4JBodyHandlers, RDF4JBodyPublishers}
import com.inrupt.client.solid.SolidSyncClient
import com.inrupt.client.spi.ServiceProvider
import org.apache.commons.rdf.api.{Dataset, RDFSyntax}
import org.eclipse.rdf4j.model.Model
import org.eclipse.rdf4j.model.util.ModelBuilder

import java.io.{BufferedInputStream, FileInputStream}
import java.net.{URI, URL}
import scala.util.{Try, Using}

class PodHandler {

  /** Note 1: Authenticated Session
    * Using the client credentials, create an authenticated session.
    */
  private val session = OpenIdSession.ofClientCredentials(
    URI.create(System.getenv("MY_SOLID_IDP")).normalize,
    System.getenv("MY_SOLID_CLIENT_ID"),
    System.getenv("MY_SOLID_CLIENT_SECRET"),
    System.getenv("MY_AUTH_FLOW")
  )

  /** Note 2: SolidSyncClient
    * Instantiates a synchronous client for the authenticated session.
    * The client has methods to perform CRUD operations.
    */
  private val client = SolidSyncClient.getClient.session(session)

  // See https://docs.inrupt.com/developer-tools/api/java/inrupt-client/1.0/com/inrupt/client/rdf4j/package-summary.html

  def readToDataset(filePath: String): Try[Dataset] = {
    val service = ServiceProvider.getRdfService // gets an RDF4JService instance
    Using(new BufferedInputStream(new FileInputStream(filePath))) { input =>
      service.toDataset(RDFSyntax.TURTLE, input, "")
    }
  }

  def readToModel: Response[Model] = {
    val request = Request.newBuilder
      .uri(URI.create("https://example.example/tripleExamples.trig"))
      .GET
      .build
    val response: Response[Model] = client.send(
      request,
      RDF4JBodyHandlers.ofModel
    ) //.toCompletableFuture().join()
    response
  }

  def writeModel(): Response[Void] = {
    val builder = new ModelBuilder()
    builder
      .namedGraph("https://example.example/graph")
      .subject("https://example.example/subject")
      .add("https://example.example/predicate", "object")
    val model = builder.build();

    val request = Request
      .newBuilder()
      .uri(new URI("https://example.example/postEndpoint"))
      .header("Content-Type", "text/turtle")
      .POST(RDF4JBodyPublishers.ofModel(model))
      .build()
    client.send(request, Response.BodyHandlers.discarding())

  }

}
