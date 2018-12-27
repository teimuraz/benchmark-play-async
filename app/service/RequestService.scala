package service

import javax.inject.{Inject, Singleton}
import play.api.libs.ws.WSClient

import scala.concurrent.Future

@Singleton
class RequestService @Inject()(wsClient: WSClient) {

  def makeRequest(url: String): Future[String] = {
      import scala.concurrent.ExecutionContext.Implicits._
      wsClient.url(url).get().map { r =>
        r.body
      }
  }

}
