package com.arunma.akkademo.search

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, ShouldMatchers}

/**
 * Created by Arun on 10/5/15.
 */
class DummyScalaCheck extends FunSpec with GeneratorDrivenPropertyChecks with ShouldMatchers {

  describe("Addition") {
    it("should be associative") {
      forAll { (a: Int, b: Int) => {
        (a + b) should be(b + a)
      }
      }
    }
  }

}
