package acs


import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.MustMatchers
import org.scalatest.WordSpec
import org.scalatest.mockito.MockitoSugar


class MockitoTest extends WordSpec with MockitoSugar with MustMatchers {

  trait Baz {
    def traitMethod(defaultArg: Int = 30, anotherDefault: String = "hola"): Int = ???
  }

  "mock[T]" should {
    "not fail with default arguments in traits" in {
      val aMock = mock[Baz]

      when(aMock.traitMethod(any[Int], any[String])) thenReturn 69

      aMock.traitMethod() mustBe 69

      // verify(aMock).traitMethod(0, "")
    }
  }
}