package controllers

import java.util.Random

import javax.inject._
import play.api._
import play.api.mvc._
import service.RequestService

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(requestService: RequestService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

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

  val rand = new Random

  def parallelRequests() = Action.async {
    val futures = Seq(
      requestService.makeRequest(s"https://emoney.ge?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://github.com?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://google.com?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://twitter.com?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://linkedin.com?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://dropbox.com?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://microsoft.com?p=${rand.nextInt(99999) + 1}"),
      requestService.makeRequest(s"https://ebay.com?p=${rand.nextInt(99999) + 1}"),
    )

    Future.sequence(futures).map{_ =>
      println("All done")
      Ok("Done")
    }
  }
}
