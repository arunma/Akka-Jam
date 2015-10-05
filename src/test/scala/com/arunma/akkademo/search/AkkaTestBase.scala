package com.arunma.akkademo.search

import akka.actor.ActorSystem
import akka.testkit.{TestKit, ImplicitSender, TestKitBase}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpecLike, ShouldMatchers, BeforeAndAfterAll, Suite}

/**
 * Created by Arun on 10/5/15.
 */
trait AkkaTestBase extends TestKitBase with ImplicitSender with GeneratorDrivenPropertyChecks with ShouldMatchers with BeforeAndAfterAll{

  self:Suite =>

  lazy val system= ActorSystem ("TestActorSystem")

  override def afterAll(){
    TestKit.shutdownActorSystem(system)
  }
}
