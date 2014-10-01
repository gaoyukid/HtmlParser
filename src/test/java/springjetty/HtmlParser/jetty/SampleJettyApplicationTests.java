/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package springjetty.HtmlParser.jetty;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Basic integration tests for demo application.
 * 
 * @author yug
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MyJettyApplication.class)
//@WebAppConfiguration
//@IntegrationTest("server.port:8089")
//@DirtiesContext
public class SampleJettyApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testHome() throws Exception {
//		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
//				"http://localhost:" + this.port + "/htdocs/css/biaoxin.css", String.class);
//		assertEquals(HttpStatus.OK, entity.getStatusCode());
//		assertEquals(true, entity.getBody().contains("style"));
	}

}
