package com.github.consul.util;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author dellll
 * 获取程序端口
 */
@Configuration
public class ServiceInfoUtil implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
  private static EmbeddedServletContainerInitializedEvent event;

  @Override
  public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
    ServiceInfoUtil.event = event;
  }

  public static int getPort() {
    Assert.notNull(event);
    int port = event.getEmbeddedServletContainer().getPort();
    Assert.state(port != -1, "端口号获取失败");
    return port;
  }
}