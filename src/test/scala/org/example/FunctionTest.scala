package org.example

import java.util.Optional
import java.util.logging.Logger

import com.microsoft.azure.functions.{ExecutionContext, HttpRequestMessage, HttpStatus}
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.mockito.MockitoSugar.mock

import org.junit.Assert._

import scala.collection.JavaConverters._

/**
 * Unit test for Function class.
 */
@Test
class FunctionTest {
  /**
   * Unit test for HttpTriggerJava method.
   */
  @Test
  @throws[Exception]
  def testHttpTriggerJava(): Unit = {
    val queryParams = Map("name" -> "Azure")
    val requestBody = Optional.empty[String]()

    // Setup
    val req = mock[HttpRequestMessage[Optional[String]]]

    when(req.getQueryParameters).thenReturn(mapAsJavaMap(queryParams))


    when(req.getBody) thenReturn requestBody

    when(req.createResponseBuilder(any(classOf[HttpStatus]))) thenAnswer (
      invocation => {
        val status = invocation.getArguments()(0).asInstanceOf[HttpStatus]
        new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status)
      })

    val context = mock[ExecutionContext]
    when(context.getLogger) thenReturn Logger.getGlobal

    // Invoke
    val ret = new Function().run(req, context)

    // Verify
    assertEquals(ret.getStatus, HttpStatus.OK)
  }
}
