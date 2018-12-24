package service

import java.util.Random

import javax.inject.{Inject, Singleton}
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RequestService @Inject()(wsClient: WSClient)(implicit ec: ExecutionContext){

  def makeRequest(url: String): Future[String] = {
      wsClient.url("http://localhost:9000").get().map { r =>

        r.body
      }
  }

}
