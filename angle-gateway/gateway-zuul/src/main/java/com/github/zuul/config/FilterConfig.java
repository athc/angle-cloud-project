package com.github.zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 忽略的路径
 *
 * @author dellll
 */
@Component
@ConfigurationProperties(prefix = "filter")
public class FilterConfig {

  private String ignores;

  public String getIgnores() {
    return ignores;
  }

  public void setIgnores(String ignores) {
    this.ignores = ignores;

  }
}
