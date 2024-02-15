package services

import com.inrupt.client.openid.OpenIdSession
import com.inrupt.client.solid.SolidClient
import models.rdf.RdfPerson

import java.net.URI
import javax.inject.{Inject, Singleton}

@Singleton
class PodService @Inject() {

  private val session = OpenIdSession.ofClientCredentials(
    URI.create(System.getenv("MY_SOLID_IDP")).normalize,
    System.getenv("MY_SOLID_CLIENT_ID"),
    System.getenv("MY_SOLID_CLIENT_SECRET"),
    System.getenv("MY_AUTH_FLOW"))

  private val client = SolidClient.getClient.session(session)

  def createPerson(rdfPerson: RdfPerson) = {
    client.create(rdfPerson)
  }

}
