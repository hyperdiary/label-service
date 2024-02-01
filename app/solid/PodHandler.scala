package solid

import com.inrupt.client.{Request, Response}
import com.inrupt.client.openid.OpenIdSession
import com.inrupt.client.rdf4j.RDF4JBodyHandlers
import com.inrupt.client.solid.SolidSyncClient
import com.inrupt.client.spi.ServiceProvider
import org.apache.commons.rdf.api.{Dataset, RDFSyntax}
import org.eclipse.rdf4j.model.Model

import java.net.URI
import scala.util.Try

class PodHandler {


  /**
   * Note 1: Authenticated Session
   * Using the client credentials, create an authenticated session.
   */
  private val session = OpenIdSession.ofClientCredentials(
    URI.create(System.getenv("MY_SOLID_IDP")).normalize,
    System.getenv("MY_SOLID_CLIENT_ID"),
    System.getenv("MY_SOLID_CLIENT_SECRET"),
    System.getenv("MY_AUTH_FLOW"))

  /**
   * Note 2: SolidSyncClient
   * Instantiates a synchronous client for the authenticated session.
   * The client has methods to perform CRUD operations.
   */
  private val client = SolidSyncClient.getClient.session(session)


  // See https://docs.inrupt.com/developer-tools/api/java/inrupt-client/1.0/com/inrupt/client/rdf4j/package-summary.html

  def readToDataset: Try[Dataset] = {
    val service = ServiceProvider.getRdfService
    val input = this.getClass.getResourceAsStream("/tripleExamples.trig")
    Try(service.toDataset(RDFSyntax.TRIG, input,""))
  }

  def readToModel: Response[Model] = {
    val request = Request.newBuilder.uri(URI.create("https://example.example/tripleExamples.trig")).GET.build
    client.send(request, RDF4JBodyHandlers.ofModel) //.toCompletableFuture().join()
  }





}
