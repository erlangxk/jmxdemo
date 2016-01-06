package coder.simon

import java.lang.management.ManagementFactory
import javax.management.ObjectName

trait WebServerMBean {
  def getPort(): Int

  def getLogLevel(): String

  def setLogLevel(level: String): Unit

  def isStarted(): Boolean

  def stop(): Unit
  def start(): Unit
}

class WebServer extends WebServerMBean {
  var level = "LogLevelXXX"
  var started = false

  def getLogLevel() = level

  def setLogLevel(level: String): Unit = {
    this.level = level
  }

  def isStarted() = started
  def start(): Unit = {
    started = true
  }

  def stop(): Unit = {
    started = false
  }

  def getPort = 12345

}

object Main {
  def main(args: Array[String]): Unit = {
    val ws=new WebServer();
    val ms=ManagementFactory.getPlatformMBeanServer
    ms.registerMBean(ws, new ObjectName("myapp:type=webserver,name= Web Server Listening to Port 12345"))
    Thread.sleep(1000000)
  }

}