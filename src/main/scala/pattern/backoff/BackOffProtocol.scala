package pattern.backoff

import akka.actor.ActorRef

/**
 * Message Protocol for the BackOffSender.
 */
object BackOffProtocol {
  /**
   * the message type that is used in the domain. The BackOffSender sends this type of message
   * to its child, which performs some dangerous operation in it.
   */
  case class Msg(id: Long, data: String)

  /**
   * Used between the supervisor and the child producer to track the original sender of the Msg message.
   * When a message fails for a while and succeeds after a number of retries, the original sender of the
   * Msg can be responded to.
   */
  case class TrackedMsg(msg: Msg, sender: ActorRef)

  /**
   * indicates that the producer successfully sent a message to the endpoint.
   * When the BackOffSender receives this message it resets the back off algorithm
   */
  case class Sent(id: Long)
}
