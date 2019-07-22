/**
 * Copyright
 */
package com.ivy.security.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author ivy on 2019-07-20.
 * 备注：
 * 下载：wiremock-standalone-2.24.0.jar
 * 下载地址：http://wiremock.org/docs/running-standalone/
 * 运行wiremock服务端：java -jar wiremock-standalone-2.24.0.jar --port 8062
 * jar文件已经下载好放在doc目录里
 * <p>
 * 然后启动本类后就可以调用到 http://localhost:8062/orders/1 接口
 * 如果访问一个不存在的接口，则在服务端会提示 Request was not matched as there were no stubs registered: ......
 */
public class WireMockLanch {

    public static void main(String[] args) throws IOException {
        configureFor(8062);
        removeAllMappings();

        mock("/orders/1", "01");
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), Charset.defaultCharset()).toArray(), "\n");
        stubFor(
                get(urlEqualTo(url))
                        .willReturn(aResponse()
                                .withBody(content)
                                .withStatus(HttpStatus.SC_OK))
        );
    }
}
