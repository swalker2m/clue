package clue.model.json

import cats.tests.CatsSuite
import clue.model.StreamingMessage.{FromClient, FromServer}
import clue.model.arb._
import io.circe._
import org.scalacheck.Arbitrary
import org.scalatest.Assertion

final class StreamingMessageJsonSpec extends CatsSuite {

  import ArbFromClient._
  import ArbFromServer._

  private def roundTrips[A: Encoder: Decoder: Arbitrary]: Assertion =
    forAll { a: A =>
      Right(a) shouldEqual Decoder[A].decodeJson(Encoder[A].apply(a))
    }

  test("FromClient round trip") {
    roundTrips[FromClient]
  }

  test("FromServer round trip") {
    roundTrips[FromServer]
  }

}