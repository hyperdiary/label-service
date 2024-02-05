package client

import com.inrupt.client.spi.ServiceProvider
import com.inrupt.client.{Headers, Resource}
import com.inrupt.rdf.wrapping.commons.WrapperDataset
import org.apache.commons.rdf.api.{Dataset, RDFSyntax}

import java.io.{
  ByteArrayInputStream,
  ByteArrayOutputStream,
  InputStream,
  OutputStream
}
import java.net.URI

class SRDFSource(
    dataset: Dataset,
    identifier: URI,
    syntax: RDFSyntax,
    headers: Option[Headers]
) extends WrapperDataset(dataset)
    with Resource {

  override def getIdentifier: URI = identifier

  override def getContentType: String = syntax.mediaType()

  override def getHeaders: Headers = headers.getOrElse(Headers.empty())

  override def getEntity: InputStream = {
    val out = new ByteArrayOutputStream()
    serialize(syntax, out)
    new ByteArrayInputStream(out.toByteArray)
  }

  /** Serialize this object with a defined RDF syntax.
    *
    * @param syntax the RDF syntax
    * @param out    the output stream
    * @throws IOException in the case of an I/O error
    */
  private def serialize(syntax: RDFSyntax, out: OutputStream): Unit = {
    ServiceProvider.getRdfService.fromDataset(this, syntax, out)
  }
}
