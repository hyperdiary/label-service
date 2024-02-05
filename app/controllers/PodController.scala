package controllers

import com.inrupt.client.auth.Session
import com.inrupt.client.openid.OpenIdSession
import com.inrupt.client.solid.{SolidClient, SolidSyncClient}
import com.inrupt.client.webid.WebIdProfile
import models.Person
import play.api.libs.json.{JsSuccess, JsValue, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}
import services.PodService

import java.net.URI
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.*
import scala.util.Try
import scala.jdk.FutureConverters.*

@Singleton
class PodController @Inject()(val controllerComponents: ControllerComponents, podService: PodService)(implicit ec: ExecutionContext) extends BaseController {

  


  //implicit val storageWrites = Json.

//  def getPods(webId:String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    val profile = client.read(URI.create(webId).normalize(), classOf[WebIdProfile])
//    Future.successful(Ok(Json.toJson(profile.getStorages.asScala)))
//  }

//  def getPerson(personUrl: String):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    val resource = client.read[Person](URI.create(personUrl).normalize(), classOf[Person])
//    Future.successful(Ok(resource))
//  }

  def createPerson(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Person] match {
      case JsSuccess(person,_) => 
        podService.createPerson(person).asScala.map(_ => Created)
      case _ => Future.successful(BadRequest("bad json body"))
    }
  }

  def updatePerson() = {}

  def deletePerson() = {}

}
