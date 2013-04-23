import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.{Http, Response}
import com.twitter.finagle.Service
import com.twitter.util.Future
import java.net.InetSocketAddress
import util.Properties

import org.specs._
import org.specs.Specification
import org.specs.mock.Mockito
import org.mockito.Mockito._
import scala.reflect.ClassManifest

object Web {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8080").toInt
    println("Starting on port:"+port)
    ServerBuilder()
      .codec(Http())
      .name("city-server")
      .bindTo(new InetSocketAddress(port))
      .build(new Cities)
    println("Started.")
  }
}

class Cities extends Service[HttpRequest, HttpResponse] {
  def apply(req: HttpRequest): Future[HttpResponse] = {
    val response = Response()
    response.setStatusCode(200)
    response.setHeader("Content-Type", "application/json")
    response.setContentString("{\"cities\" :[\"San Francisco\", \"Amsterdam\",\"Berlin\",\"New York\"]}")
    Future(response)
  }
}

class WebTest extends Specification with Mockito {
  "Cities" should {
    "have content" in {
      var request = mock[HttpRequest]

      var cities = new Cities
      var f = cities.apply(request)

      f onSuccess {
        case response => response.getContent().readable() mustEqual true
      }
    }
  }
}
