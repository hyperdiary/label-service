package models.json

import models.rdf.RdfPerson
import play.api.libs.json.{Json, OFormat}

import java.net.URI

case class JsonPerson (identifier: URI, givenName: String, surname: String) {
  def asRdfPerson: RdfPerson = ???
}
object JsonPerson {
  implicit val jsonPersonFormat: OFormat[JsonPerson] = Json.format[JsonPerson]
}
