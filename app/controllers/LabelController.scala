package controllers

import javax.inject.*
import play.api.*
import play.api.mvc.*
import services.LabelService

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class LabelController @Inject()(val controllerComponents: ControllerComponents, labelService: LabelService) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def getLink(label: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val htmlLink = labelService.getHtmlHyperlink(label)
    Future.successful(Ok(htmlLink))

  }
}
