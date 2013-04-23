import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import org.specs._
import org.specs.Specification
import org.specs.mock.Mockito
import org.mockito.Mockito._
import scala.reflect.ClassManifest

class WebSpec extends Specification with Mockito {
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
