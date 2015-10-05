package com.arunma.akkademo.search

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.LoggingReceive
import com.arunma.akkademo.search.SearchActor.{Echo, SearchCriteria}

/**
 * Created by Arun on 10/5/15.
 */
class SearchActor extends Actor with ActorLogging {
  override def receive: Receive = LoggingReceive {

    case SearchCriteria(id, term) => {
      println(s"term: $term")
    }

    case echo@Echo(term) => sender ! echo

  }
}

object SearchActor {

  def props = Props(new SearchActor)

  case class Echo(term: String)

  case class SearchCriteria(id:Int, term: String)
  case class SearchResult(results:List[String])


}
