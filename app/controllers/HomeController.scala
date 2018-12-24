package controllers

import java.util.Random

import javax.inject._
import play.api._
import play.api.mvc._
import service.RequestService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(requestService: RequestService, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  val rand = new Random

  def parallelRequests() = Action.async {
    val futures = (1 to 10).map( _ =>
      requestService.makeRequest(s"http://3.17.161.135:9000?p=${rand.nextInt(99999) + 1}"),
    )

    Future.sequence(futures).map{ _ =>
      Ok("Done")
    }
  }
}
